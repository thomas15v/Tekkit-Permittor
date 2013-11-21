package thomas15v.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import thomas15v.functions;
import thomas15v.main;

public class manager {
	
	public static manager instance;
	
	main plugin = null;
	WorldGuardConfig worldGuardConfig = null;
	EventConfig eventConfig = null;
	
	
	File ConfigFile = null;
	FileConfiguration Config = null;
	
	public manager(main plugin){
		this.plugin = plugin;
	}
	
	public boolean ConfigFileExist(){
		ConfigFile = new File(plugin.getDataFolder(), "config.yml");
		return ConfigFile.exists();
	}
	
	
	public void reload(){
		ConfigFile = new File(plugin.getDataFolder(), "config.yml");
		Config = new YamlConfiguration();
		
		try {
			Config.load(ConfigFile);
			
			worldGuardConfig = new WorldGuardConfig();
			
	    	worldGuardConfig.wrenches = functions.StringToIntArray(plugin.getConfig().getString("Protection.wrenches"));
	    	worldGuardConfig.tools = functions.StringToIntArray(plugin.getConfig().getString("Protection.tools"));
	    	worldGuardConfig.alwaysblockedtools = functions.StringToIntArray(plugin.getConfig().getString("Protection.alwaysblockedtools"));
	    	worldGuardConfig.Containerblocks = functions.StringToIntArray(plugin.getConfig().getString("Protection.Containerblocks"));
	    	worldGuardConfig.UseBlocks = functions.StringToIntArray(plugin.getConfig().getString("Protection.UseBlocks"));
	    	
	    	eventConfig = new EventConfig();
	    	

	    	eventConfig.noplaceblock = functions.StringToIntArray(plugin.getConfig().getString("block-Mod-block-place.blocks"));
	    	eventConfig.Modblockplaceenabled = plugin.getConfig().getBoolean("block-Mod-block-place.enabled");
			
	    	eventConfig.onePlayerBlocks = plugin.getConfig().getString("Block-moreplayer-using-block.blocks").split(",");
	    	eventConfig.Blockmoreplayerusingblockenabled = plugin.getConfig().getBoolean("Block-moreplayer-using-block.enabled");
			
	    	eventConfig.blockillegalexprewardenabled = plugin.getConfig().getBoolean("block-illegal-exp-reward.enabled");
	    	eventConfig.illegalexprewardenabledblocks = plugin.getConfig().getString("block-illegal-exp-reward.blocks").split(",");
	    	eventConfig.maxexp = plugin.getConfig().getInt("block-illegal-exp-reward.maxexp");
	    	
	    	
	    	plugin.getLogger().info(eventConfig.maxexp + "");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public WorldGuardConfig getworldguardconfig(){
		return worldGuardConfig;
	}
}
