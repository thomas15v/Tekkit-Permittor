package thomas15v;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import thomas15v.configuration.manager;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class main extends JavaPlugin {

	boolean eventsloaded = false;
	
	@Override
	public void onEnable() {
		loadConfiguration();
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
		manager.setdatafolder(getDataFolder());
		if (manager.ConfigFileExist()){
			manager.reload();
			if (manager.getforgottenrecipeenabled()) forgotenrecipes();	
			Bukkit.getLogger().info("[Tekkit Permitor] configuration loaded!");
			launchevents();
			Bukkit.getLogger().info("[Tekkit Permitor] events loaded!");
			loadWorldGuardsupport();
			Bukkit.getLogger().info("[Tekkit Permitor] loaded!");
			eventsloaded = true;
		}
		else{
			getDataFolder().mkdirs();
			getLogger().info("[Tekkit permittor] ERROR no config file do /tep choicedefault <TM|TL|B>");			
		}		
	}
	
	public void launchevents(){
		events Events = new events();		
		getServer().getPluginManager().registerEvents(Events, this);
	}
	
	private void loadWorldGuardsupport() {
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
    	 if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
 	        Bukkit.getLogger().info("[Tekkit Permitor] No worldguard plugin founded!!!");
 	    }else{
	    	Worldguardevents worldguardevents = new Worldguardevents((WorldGuardPlugin) plugin);	    	
	    	getServer().getPluginManager().registerEvents(worldguardevents, this);
 	    }

	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("tep") && args.length > 0){ 
			if (args[0].equalsIgnoreCase("reload")) {
				manager.reload();
				sender.sendMessage(ChatColor.GREEN + "Reload Complete!");
				return true;
			}
			else if (args[0].equalsIgnoreCase("choicedefault") && args.length > 1){
				
				if (!manager.ConfigFileExist()){
					try{
						copy(getResource(args[1].toUpperCase() +"config.yml"), manager.GetConfigFile());
						manager.reload();
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
				int replacedquarrys = 0;
				for (World w : getServer().getWorlds()){
					sender.sendMessage(ChatColor.GREEN + "Checking " + w.getName());
					for (Chunk c : w.getLoadedChunks()){
						for (int x = 0; x < 16; x++){
                            for(int y = 0; y < c.getWorld().getMaxHeight(); y++)
                            {
                                    for(int z = 0; z < 16; z++)
                                    {
                                    	 Block block = c.getBlock(x, y, z);
               
                                    	 if (block.getTypeId() == 153){
                                    		 replacedquarrys++;
                                    		 sender.sendMessage(ChatColor.GREEN + "Quarry found on " + block.getX() + " " + block.getY() + " " + block.getZ());
                                    		 block.breakNaturally(new ItemStack(0,0));
                                    		 block.setType(Material.CHEST);
                                    		 Chest chest = (Chest) block.getState();
                                    		 chest.getInventory().addItem(new ItemStack(153,1));                                   		 
                                    	 }
                                    }
                            }
						}
					}
				}
				sender.sendMessage(replacedquarrys + " Quarry's replaced");
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