package me.arrows.HotKey;


import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_16_R3.DataWatcherObject;
import net.minecraft.server.v1_16_R3.DataWatcherRegistry;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.MinecraftServer;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_16_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_16_R3.PlayerConnection; import net.minecraft.server.v1_16_R3.PlayerInteractManager;
import net.minecraft.server.v1_16_R3.WorldServer;

public class NPC {

	private static List<EntityPlayer> NPC = new ArrayList<EntityPlayer>();
	
	public static void createNPC(Player player, String skin) {
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
		GameProfile gameProfile = new GameProfile(UUID.randomUUID(),skin);
		EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
		
		npc.getDataWatcher().set(new DataWatcherObject<>(16, DataWatcherRegistry.a), (byte)127);
		
		npc.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 
				player.getLocation().getYaw(), player.getLocation().getPitch());		
		
		String[] name = getSkin(player, skin);
		gameProfile.getProperties().put("textures", new Property("textures", name[0],name[1] ));
		

		addNPCPacket(npc);
		NPC.add(npc);
		
		int var = 1;
		if(Main.getData().contains("data"))
			var = Main.getData().getConfigurationSection("data").getKeys(false).size() + 1;
		
		Main.getData().set("data." + var +  ".x",(float) player.getLocation().getX());
		Main.getData().set("data." + var + ".y", (float) player.getLocation().getY());
		Main.getData().set("data." + var + ".z", (float) player.getLocation().getZ());
		Main.getData().set("data." + var + ".yaw", player.getLocation().getYaw());
		Main.getData().set("data." + var + ".p", player.getLocation().getPitch());
		Main.getData().set("data." + var + ".world", player.getLocation().getWorld().getName());
		Main.getData().set("data." + var + ".name", skin);
		Main.getData().set("data." + var + ".text", name[0]);
		Main.getData().set("data." + var + ".signature", name[1]);
		Main.saveData();
		
		
		
	
	
	}
	
	public static void loadNPC(Location location, GameProfile profile) {
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
		GameProfile gameProfile = profile;
		EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
		
		npc.getDataWatcher().set(new DataWatcherObject<>(16, DataWatcherRegistry.a), (byte)127);
		
		npc.setLocation(location.getX(), location.getY(), location.getZ(), 
				location.getYaw(), location.getPitch());		

		addNPCPacket(npc);
		NPC.add(npc);
	}
	
	private static String[] getSkin(Player player, String name) {
		try {
			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
			InputStreamReader reader = new InputStreamReader(url.openStream());
			String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();
			
			URL url2 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid
				     + "?unsigned=false");
			InputStreamReader reader2 = new InputStreamReader(url2.openStream());
			JsonObject property = new JsonParser().parse(reader2).getAsJsonObject().get("properties")
					.getAsJsonArray().get(0).getAsJsonObject(); 
			String texture = property.get("value").getAsString();
			String signature = property.get("signature").getAsString();
			return new String[] {texture, signature};
			
		} catch (Exception e) {
			EntityPlayer p = ((CraftPlayer)player).getHandle();
			GameProfile profile = p.getProfile();
			Property property = profile.getProperties().get("texturs").iterator().next();
			String texture = property.getValue();
			String signature = property.getSignature();
			return new String[] {texture, signature};
			
		}
	}
	
	public static void addNPCPacket(EntityPlayer npc) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
			connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), npc.getDataWatcher(), true));
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));

					
		}
	}
	
	public static void removeNPC(Player player, EntityPlayer npc) {
		PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));
	}
	public static void addJoinacket(Player player) {
		for (EntityPlayer npc : NPC) {
			PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
			connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), npc.getDataWatcher(), true));
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));

			
			
			
			
		}
	}
	
	public static List<EntityPlayer> getNPCs() {
		return NPC;
	}
}
