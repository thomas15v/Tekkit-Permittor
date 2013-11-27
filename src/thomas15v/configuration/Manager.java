package thomas15v.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import thomas15v.info.BlockInfo;
import thomas15v.info.ModEnchantmentInfo;
import thomas15v.other.Functions;

public class Manager {


	
	public static Manager instance;
	
	static WorldGuardConfig worldGuardConfig;
	static EventConfig eventConfig;
	static File datafolder;
	
	static File ConfigFile;
	static FileConfiguration Config;
	
	static boolean forgottenrecipeenabled;
	static BlockInfo[] Chunkloadersids;
	
	static ModEnchantmentInfo[] modEnchantmentInfo;
	
	
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
				
		    	worldGuardConfig.wrenches = Functions.StringToBlockInfo(Config.getString("Protection.wrenches"));
		    	worldGuardConfig.tools = Functions.StringToBlockInfo(Config.getString("Protection.tools"));
		    	worldGuardConfig.alwaysblockedtools = Functions.StringToBlockInfo(Config.getString("Protection.alwaysblockedtools"));
		    	worldGuardConfig.Containerblocks = Functions.StringToBlockInfo(Config.getString("Protection.Containerblocks"));
		    	worldGuardConfig.alwaysblockedblocks = Functions.StringToBlockInfo(Config.getString("Protection.alwaysblockedblocks"));
		    	worldGuardConfig.UseBlocks = Functions.StringToBlockInfo(Config.getString("Protection.UseBlocks"));
		    	
		    	eventConfig = new EventConfig();
		    	
		    	eventConfig.noplaceblock = Functions.StringToBlockInfo(Config.getString("block-Mod-block-place.blocks"));
		    	eventConfig.Modblockplaceenabled = Config.getBoolean("block-Mod-block-place.enabled");
				
		    	eventConfig.onePlayerBlocks = Functions.StringToBlockInfo(Config.getString("Block-moreplayer-using-block.blocks"));
		    	eventConfig.Blockmoreplayerusingblockenabled = Config.getBoolean("Block-moreplayer-using-block.enabled");
				
		    	eventConfig.blockillegalexprewardenabled = Config.getBoolean("block-illegal-exp-reward.enabled");
		    	eventConfig.illegalexprewardenabledblocks = Functions.StringToBlockInfo(Config.getString("block-illegal-exp-reward.blocks"));
		    	eventConfig.maxexp = Config.getInt("block-illegal-exp-reward.maxexp");
		    	
		    	forgottenrecipeenabled = Config.getBoolean("Add_forgoten_recipe");
		    	Chunkloadersids = Functions.StringToBlockInfo(Config.getString("Chunksloaders"));
		    	
		    	modEnchantmentInfo = Functions.stringToEnchantmentInfos(Config.getString("Banned.Enchantments"));
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
	
	public static ModEnchantmentInfo[] GetBannedEnchantments(){
		return modEnchantmentInfo;
	}
	
	public static BlockInfo[] Getchunkloadersids(){
		return Chunkloadersids;
	}
	
	public static WorldGuardConfig getworldguardConfig(){
		return worldGuardConfig;
	}
	public static EventConfig geteventconfig(){
		return eventConfig;
	}
}
