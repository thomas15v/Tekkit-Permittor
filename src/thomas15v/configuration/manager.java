package thomas15v.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import thomas15v.functions;

public class manager {


	
	public static manager instance;
	
	static WorldGuardConfig worldGuardConfig;
	static EventConfig eventConfig;
	static File datafolder;
	
	static File ConfigFile;
	static FileConfiguration Config;
	
	static boolean forgottenrecipeenabled;
	
	
	public static void setdatafolder(File folder){
		datafolder = folder;
		ConfigFile = new File(datafolder, "Config.yml");
	}
	
	public static void rmconfigfile(){
		ConfigFile.delete();
	}
	
	static public File GetConfigFile(){
		return ConfigFile;
	}
	
	static public boolean ConfigFileExist(){
		ConfigFile = new File(datafolder, "Config.yml");
		Config = new YamlConfiguration();
		if (ConfigFile.exists()){
			try{
				Config.load(ConfigFile);
				return Config.getString("configname") != null;
			}
			catch (Exception e){
				return false;
			}
			
		}
		return false;		
	}
	
	public static boolean getforgottenrecipeenabled(){
		return forgottenrecipeenabled;
	}
	
	
	static public void reload(){
		
		if (ConfigFile.exists()){
			ConfigFile = new File(datafolder, "Config.yml");
			Config = new YamlConfiguration();
			
			try {
				Config.load(ConfigFile);
				
				worldGuardConfig = new WorldGuardConfig();
				
		    	worldGuardConfig.wrenches = functions.StringToIntArray(Config.getString("Protection.wrenches"));
		    	worldGuardConfig.tools = functions.StringToIntArray(Config.getString("Protection.tools"));
		    	worldGuardConfig.alwaysblockedtools = functions.StringToIntArray(Config.getString("Protection.alwaysblockedtools"));
		    	worldGuardConfig.Containerblocks = functions.StringToIntArray(Config.getString("Protection.Containerblocks"));
		    	worldGuardConfig.UseBlocks = functions.StringToIntArray(Config.getString("Protection.UseBlocks"));
		    	
		    	eventConfig = new EventConfig();
		    	
		    	eventConfig.noplaceblock = functions.StringToIntArray(Config.getString("block-Mod-block-place.blocks"));
		    	eventConfig.Modblockplaceenabled = Config.getBoolean("block-Mod-block-place.enabled");
				
		    	eventConfig.onePlayerBlocks = Config.getString("Block-moreplayer-using-block.blocks").split(",");
		    	eventConfig.Blockmoreplayerusingblockenabled = Config.getBoolean("Block-moreplayer-using-block.enabled");
				
		    	eventConfig.blockillegalexprewardenabled = Config.getBoolean("block-illegal-exp-reward.enabled");
		    	eventConfig.illegalexprewardenabledblocks = Config.getString("block-illegal-exp-reward.blocks").split(",");
		    	eventConfig.maxexp = Config.getInt("block-illegal-exp-reward.maxexp");
		    	
		    	forgottenrecipeenabled = Config.getBoolean("Add_forgoten_recipe");
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
	}
	
	public static WorldGuardConfig getworldguardConfig(){
		return worldGuardConfig;
	}
	public static EventConfig geteventconfig(){
		return eventConfig;
	}
}
