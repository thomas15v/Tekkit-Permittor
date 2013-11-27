package thomas15v;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import thomas15v.configuration.Manager;
import thomas15v.events.Enchantmentbanner;
import thomas15v.events.Events;
import thomas15v.events.WarnAdminevents;
import thomas15v.events.Worldguardevents;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class TekkitPermittor extends JavaPlugin {

	boolean eventsloaded = false;
	
	static Logger logger;
	
	static public Logger GetLogger() {
		return logger;		
	}
	
	@Override
	public void onEnable() {
		logger = getLogger();
		loadConfiguration();
		
		Enchantmentbanner enchantmentbanner = new Enchantmentbanner();
		getServer().getPluginManager().registerEvents(enchantmentbanner, this);
	}
	
	void forgotenrecipes(){
		ShapedRecipe factorizationconsumer = new ShapedRecipe(new ItemStack(2855 ,1));
		factorizationconsumer.shape(new String[] {"G G"," F ","G G"});
		factorizationconsumer.setIngredient('G', Material.GOLD_INGOT);
		factorizationconsumer.setIngredient('F', new MaterialData(2050, (byte) 22));
		getServer().addRecipe(factorizationconsumer);
		getServer().addRecipe(factorizationconsumer);		
	}
	
	public void loadConfiguration(){
		Manager.setdatafolder(getDataFolder());
		if (Manager.ConfigFileExist()){
			Manager.reload();
			if (Manager.getforgottenrecipeenabled()) forgotenrecipes();	
			getLogger().info("configuration loaded!");
			launchevents();
			getLogger().info("events loaded!");
			loadWorldGuardsupport();
			getLogger().info("loaded!");
			eventsloaded = true;
		}
		else{
			getDataFolder().mkdirs();
			getLogger().info("ERROR no config file do /tep choicedefault <TM|TL|B>");	
			WarnAdminevents warnAdminevents = new WarnAdminevents();
			getServer().getPluginManager().registerEvents(warnAdminevents,this);
		}		
	}
	
	public void launchevents(){
		Events Events = new Events();		
		getServer().getPluginManager().registerEvents(Events, this);
	}
	
	private void loadWorldGuardsupport() {
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
    	 if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
 	        getLogger().info("No worldguard plugin founded!!!");
 	    }else{
	    	Worldguardevents worldguardevents = new Worldguardevents((WorldGuardPlugin) plugin);	    	
	    	getServer().getPluginManager().registerEvents(worldguardevents, this);
 	    }

	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("tep") && args.length > 0){ 
			if (args[0].equalsIgnoreCase("reload")) {
				Manager.reload();
				sender.sendMessage(ChatColor.GREEN + "Reload Complete!");
				return true;
			}
			else if (args[0].equalsIgnoreCase("choicedefault") && args.length > 1){
				if (!Manager.ConfigFileExist()){
					try{
						copy(getResource(args[1].toUpperCase() +"config.yml"), Manager.GetConfigFile());
						Manager.reload();
						sender.sendMessage(ChatColor.DARK_GREEN + "You Written the default configuration file");
					}
					catch (Exception e){
						sender.sendMessage(ChatColor.DARK_RED + "This configuration file isn't found!");
					}	
				}
				else{
					sender.sendMessage(ChatColor.RED + "Their is already an configuration file!");
					sender.sendMessage(ChatColor.RED + "Delete The old one, before executing this command");
				}
				
				if (!eventsloaded){
					loadConfiguration();
				}
				
				return true;						
			}
			else if (args[0].equalsIgnoreCase("unloadforcedchunks") && args.length > 0){
				for (World w : getServer().getWorlds()){
					sender.sendMessage(ChatColor.GREEN + "Checking " + w.getName());
					WorldModifyer worldModifyer = new WorldModifyer(w, sender);
					worldModifyer.Replaceallblocks(Manager.Getchunkloadersids());
				}
				return true;
			}		
			
			else if (args[0].equalsIgnoreCase("checkforcedchunks") && args.length > 0){
				for (World w : getServer().getWorlds()){
					sender.sendMessage(ChatColor.GREEN + "Checking " + w.getName());
					WorldModifyer worldModifyer = new WorldModifyer(w, sender);
					worldModifyer.logblocks(Manager.Getchunkloadersids());
				}
				return true;
			}		
			else if (args[0].equalsIgnoreCase("tpclosestchunkloader") && args.length > 0){
				
				if (sender instanceof Player){
					Player player = (Player) sender; //Lol my first cast in this plugin :)
					World w = player.getWorld();
					Location location = player.getLocation();
					
					WorldModifyer worldModifyer = new WorldModifyer(w, sender);
					
					Location telportlocation = worldModifyer.Getclosedblock(Manager.Getchunkloadersids(), location);
					if (telportlocation != null) player.teleport(telportlocation);
					else sender.sendMessage(ChatColor.RED + "No chunkloaders found," + ChatColor.GREEN +" the world is clean");
					
					
					
				}
				else{
					sender.sendMessage("This Command has to be run as player");
				}
				return true;
			}		
		}
		
		return false;
	}
	 /*
     * this copy(); method copies the specified file from your jar
     *     to your /plugins/<pluginName>/ folder
     */
    private void copy(InputStream in, File file) throws Exception {
    	OutputStream out = new FileOutputStream(file);
        try {
            
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
        	in.close();
        	out.close();
            throw new Exception();
            
        }
    }
	
}