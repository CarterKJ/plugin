package me.arrows.HotKey;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_16_R3.PlayerConnection;
import java.io.File;

public class Main extends JavaPlugin implements Listener {
	
	public static DataManager data;
	
	@Override
	public void onEnable() {
		data = new DataManager(this);
		System.out.println("Easy Cmd loaded!, /cmds for command list");
		this.getServer().getPluginManager().registerEvents(new Join(), this);
		if(data.getConfig().contains("data"))
		loadNPC();
	}
	
	
	@Override
	public void onDisable() {
		for(Player player :Bukkit.getOnlinePlayers()) {
			for(EntityPlayer npc : NPC.getNPCs())
				NPC.removeNPC(player, npc);
		}
	
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("rs")) {
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if(player.isOp()){
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Server Reastarting....");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String commandr = "restart";
					Bukkit.dispatchCommand(console, commandr);		
					return true;
				}
				if(!player.isOp()) {
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
					return true;
				}
			}
			 
		else {
			sender.sendMessage("Hi console");
			return true;
			}
		}
			
		
		
		if(label.equalsIgnoreCase("sp")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				if(player.isOp()){
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Server Stoping....");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String commands = "stop";
					Bukkit.dispatchCommand(console, commands);
					return true;
				}	
				if(!player.isOp()) {
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
					return true;
							}
			}
			else {
				sender.sendMessage("Hi console");
				return true;
			}
		
		
		}	
		
		if(label.equalsIgnoreCase("rl")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				if(player.isOp()) {
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Realoading plugins....");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String commandrl = "reload";
					Bukkit.dispatchCommand(console, commandrl);	
					String commandpl = "plugins";
					Bukkit.dispatchCommand(console, commandpl);	
					return true;
				}
			if(!player.isOp()) {
				player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
				return true;
			}
			
			}
			else {
				sender.sendMessage("Hi console");
				return true;
			}
		
		
		}
		
		if(label.equalsIgnoreCase("w-off")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				if(player.isOp()){
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Whitelist = False");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String commandwof = "whitelist off";
					Bukkit.dispatchCommand(console, commandwof);
					return true;
				}	
				if(!player.isOp()) {
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
					return true;
							}
			}
			
			
			else {
				sender.sendMessage("Hi console");
				return true;
			}
		
		
		}	
		if(label.equalsIgnoreCase("w-on")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				if(player.isOp()){
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Whitelist = True");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String commandwon = "whitelist on";
					Bukkit.dispatchCommand(console, commandwon);
					return true;
				}	
				if(!player.isOp()) {
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
					return true;
							}
			}
			
			
			else {
				sender.sendMessage("Hi console");
				return true;
			}
		
		
		}
		
		
		
		if(label.equalsIgnoreCase("gc")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				if(player.isOp()){
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Set Gamemode Creative");
					
					player.setGameMode(GameMode.CREATIVE);
					return true;
				}	
				if(!player.isOp()) {
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
					return true;
							}
			}
			
			
			else {
				sender.sendMessage("Hi console");
				return true;
			}
		
		
		}
		
		if(label.equalsIgnoreCase("gs")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				if(player.isOp()){
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Set Gamemode Survial");
					
					player.setGameMode(GameMode.SURVIVAL);
					return true;
				}	
				if(!player.isOp()) {
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
					return true;
							}
			}
			
			
			else {
				sender.sendMessage("Hi console");
				return true;
			}
		
		
		}
		
		if(label.equalsIgnoreCase("gsp")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				if(player.isOp()){
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Set Gamemode Spectator");
					
					player.setGameMode(GameMode.SPECTATOR);
					return true;
				}	
				if(!player.isOp()) {
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
					return true;
							}
			}
			
			
			else {
				sender.sendMessage("Hi console");
				return true;
			}
		
		
		}
		

		if(label.equalsIgnoreCase("cmds")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				player.sendMessage(ChatColor.BLUE +""+ ChatColor.BOLD + ""
						+ "r: Restarts the server\r\n"
						+ " sp: Stops the server\r\n"
						+ " rl: Realoads plugins\r\n"
						+ " w-off: Turns off whitelist\r\n"
						+ " w-on: Turns on whitelist\r\n"
						+ " gc: Sets gamemode creative\r\n"
						+ " gs: Sets gamemode survial\r\n"
						+ " gsp: Sets gamemode specator"
						+ "t-d: Sets time day\r\n"
						+ " t-n: sets time night\r\n"
						+ " f: makes you fly\r\n"
						+ " w-c: wether clear\r\n"
						+ " w-t: weather thunder\r\n"
						+ " w-r: weath rain\r\n"
						+ " s: sets speed max .99\r\n"
						+ " s-n: sets normal speed\r\n"
						+ " k: kicks\r\n"
						+ " b: bans\r\n"
						+ " ipb: ip bans\r\n"
						+ " s-h: sets health max 20\r\n"
						+ " s-l: sets levles\r\n"
						+ " s-fl: sets food level max 20 ");
	
				
				return true;
			}
			else {
				sender.sendMessage("Hi console");
				return true;
			}
		}
		
		if(label.equalsIgnoreCase("t-d")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				if(player.isOp()){
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Time set to day");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String commandt = "time set day";
					Bukkit.dispatchCommand(console, commandt);
					return true;
				}	
				if(!player.isOp()) {
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
					return true;
							}
			}
			else {
				sender.sendMessage("Hi console");
				return true;
			}		
		
		}	
		
		if(label.equalsIgnoreCase("t-n")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				if(player.isOp()){
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Time set to night");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String commandt = "time set night";
					Bukkit.dispatchCommand(console, commandt);
					return true;
				}	
				if(!player.isOp()) {
					player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
					return true;
							}
			}
			else {
				sender.sendMessage("Hi console");
				return true;
			}		
		
		}
		
		   if(cmd.getName().equalsIgnoreCase("f")){
			   Player player = (Player) sender;
		       if(!sender.hasPermission("pluginname.fly")){
		           sender.sendMessage(ChatColor.DARK_RED + "You Dont Have Permissions For Using This Command.");
		       return true;
		       }
		       
		      if(player.getAllowFlight() == false){
		         player.setAllowFlight(true);
		         return true;
		      }
		       player.setAllowFlight(false);
		     return true;
		   }
		
		   if(label.equalsIgnoreCase("w-c")) {
				if(sender instanceof Player) {
					
					Player player = (Player) sender;
					if(player.isOp()){
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Weather set to clear");
						
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						String commandt = "Weather clear";
						Bukkit.dispatchCommand(console, commandt);
						return true;
					}	
					if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								}
				}
				else {
					sender.sendMessage("Hi console");
					return true;
				}		
			
			}	   
		   
		   if(label.equalsIgnoreCase("w-t")) {
				if(sender instanceof Player) {
					
					Player player = (Player) sender;
					if(player.isOp()){
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Weather set to thunder");
						
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						String commandt = "Weather thunder";
						Bukkit.dispatchCommand(console, commandt);
						return true;
					}	
					if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								}
				}
				else {
					sender.sendMessage("Hi console");
					return true;
				}		
			
			}	
		   
		   if(label.equalsIgnoreCase("w-r")) {
				if(sender instanceof Player) {
					
					Player player = (Player) sender;
					if(player.isOp()){
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "Weather set to rain");
						
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						String commandt = "Weather rain";
						Bukkit.dispatchCommand(console, commandt);
						return true;
					}	
					if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								}
				}
				else {
					sender.sendMessage("Hi console");
					return true;
				}		
			
			}
		   
		   if(label.equalsIgnoreCase("s")) {
			   if(sender instanceof Player) {
				   Player player = (Player) sender;
				   if(player.isOp()){
					   if(args.length == 0) {
						   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Updated Speed");
						   player.getPlayer().setWalkSpeed(0.5f);
						   player.getPlayer().setFlySpeed(0.5f);
						   
						   return true;
					   }
					   
					   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Updated Speed");
					   player.getPlayer().setWalkSpeed(Float.parseFloat(args[0]));
					   player.getPlayer().setFlySpeed(Float.parseFloat(args[0]));
					   
					   return true;
				   }
				   
				   
				   if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								}
			   }
			   else {
				   sender.sendMessage("Hi console");
					return true;
			   }
		   }
		   
		   if(label.equalsIgnoreCase("s-n")) {
				if(sender instanceof Player) {
					
					Player player = (Player) sender;
					if(player.isOp()){
						player.sendMessage(ChatColor.LIGHT_PURPLE +""+ ChatColor.BOLD + "Updated Speed");
						
						player.getPlayer().setWalkSpeed(0.2f);
						player.getPlayer().setFlySpeed(0.1f);
					}	
					if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								}
				}
				else {
					sender.sendMessage("Hi console");
					return true;
				}		
			
			}
		   
		   if(label.equalsIgnoreCase("k")) {
			   if(sender instanceof Player) {
				   Player player = (Player) sender;
				   if(player.isOp()){
					   if(args.length == 0) {
						   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Please specify name ");
						   
						   return true;
					   }
					   
					   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Player Kicked");
					   
					   ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						String commandt = String.format("kick %s",args[0]);
						Bukkit.dispatchCommand(console,commandt);
						return true;
			
				   }
				   
				   
				   if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								}
			   }
			   else {
				   sender.sendMessage("Hi console");
					return true;
			   }
		   }
		   
		   if(label.equalsIgnoreCase("b")) {
			   if(sender instanceof Player) {
				   Player player = (Player) sender;
				   if(player.isOp()){
					   if(args.length == 0) {
						   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Please specify name ");
						   
						   return true;
					   }
					   
					   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Player Banned");
					   
					   ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						String commandt = String.format("ban %s",args[0]);
						Bukkit.dispatchCommand(console,commandt);
						return true;
			
				   }
				   
				   
				   if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								}
			   }
			   else {
				   sender.sendMessage("Hi console");
					return true;
			   }
		   }
		   if(label.equalsIgnoreCase("ipb")) {
			   if(sender instanceof Player) {
				   Player player = (Player) sender;
				   if(player.isOp()){
					   if(args.length == 0) {
						   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Please specify name ");
						   
						   return true;
					   }
					   
					   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Player ip banned");
					   
					   ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						String commandt = String.format("ip ban %s",args[0]);
						Bukkit.dispatchCommand(console,commandt);
						return true;
			
				   }
				   
				   
				   if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								}
			   }
			   else {
				   sender.sendMessage("Hi console");
					return true;
			   }
		   }
		   
		   if(label.equalsIgnoreCase("s-h")) {
			   if(sender instanceof Player) {
				   Player player = (Player) sender;
				   if(player.isOp()){
					   if(args.length == 0) {
						   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Please specify number");
						   return true;
					   }
					   
					   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Max health set");
					   player.setHealth(Integer.parseInt(args[0]));
						return true;
			
				   }
				   
				   
				   if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								}
			   }
			   else {
				   sender.sendMessage("Hi console");
					return true;
			   }
		   }
		   if(label.equalsIgnoreCase("s-l")) {
			   if(sender instanceof Player) {
				   Player player = (Player) sender;
				   if(player.isOp()){
					   if(args.length == 0) {
						   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Please specify number");
						   return true;
					   }
					   
					   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Levels set");
					   player.setLevel(Integer.parseInt(args[0]));
						return true;
			
				   }
				   
				   
				   if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								}
			   }
			   else {
				   sender.sendMessage("Hi console");
					return true;
			   }
		   }
		   if(label.equalsIgnoreCase("s-fl")) {
			   if(sender instanceof Player) {
				   Player player = (Player) sender;
				   if(player.isOp()){
					   if(args.length == 0) {
						   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Please specify number");
						   return true;
					   }
					   
					   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Food level set");
					   player.setFoodLevel(Integer.parseInt(args[0]));
						return true;
			
				   }
				   
				   
				   if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								}
			   }
			   else {
				   sender.sendMessage("Hi console");
					return true;
			   }
		   }
		   
		   
		   if(label.equalsIgnoreCase("npc-add")) {
			   if(sender instanceof Player) {
				   Player player = (Player) sender;
				   if(player.isOp()) {
					   if(args.length == 0) {
						   NPC.createNPC(player, player.getName());
						   player.sendMessage("Npc added");
						   return true;
					   }
					   NPC.createNPC(player, args[0]);
					   player.sendMessage("Npc added");
					   return true;
				   }
				   if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								} 
			   }
			   else {
				   sender.sendMessage("Hi console");
					return true;
			   }
		   }
		   if(label.equalsIgnoreCase("npc-r" )) {
			   if(sender instanceof Player) {
				   Player player = (Player) sender;
				   if(player.isOp()){				   
					   player.sendMessage(ChatColor.LIGHT_PURPLE+""+ ChatColor.BOLD + "Npc removed");
					   for(EntityPlayer npc : NPC.getNPCs())
							NPC.removeNPC(player, npc);



					   
						return true;
			
				   }
				   
				   
				   if(!player.isOp()) {
						player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "You cant do that");
						return true;
								}
			   }
			   else {
				   sender.sendMessage("Hi console");
					return true;
			   }
		   }
		   
		   
		   
		   return false;
	}
	
	public static FileConfiguration getData() {
		return data.getConfig();
	}
	
	public static void saveData() {
		data.saveConfig();
	}
	
	public void loadNPC() {
		FileConfiguration file = data.getConfig();
		file.getConfigurationSection("data").getKeys(false).forEach(npc ->{
			Location location = new Location(Bukkit.getWorld(file.getString("data." + npc + ".world")),
					file.getInt("data." + npc + ".x"), file.getInt("data." + npc + ".y"), file.getInt("data." + npc + ".z"));
			location.setPitch((float) file.getDouble("data." + npc + ".p"));
			location.setYaw((float) file.getDouble("data." + npc + ".yaw"));
			String name = file.getString("data." + npc + ".name");
			GameProfile gameProfile = new GameProfile(UUID.randomUUID(),name);
			gameProfile.getProperties().put("textures", new Property("textures", file.getString("data." + npc + ".text"),
					file.getString("data." + npc + ".signature")));
			
			NPC.loadNPC(location, gameProfile);
		});
	}
	
}
