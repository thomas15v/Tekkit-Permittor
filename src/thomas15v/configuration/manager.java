package thomas15v.configuration;

import org.bukkit.plugin.Plugin;

import thomas15v.functions;

public class manager {
	Plugin plugin = null;
	WorldGuardConfig worldGuardConfig = null;
	EventConfig eventConfig = null;
	
	public manager(Plugin plugin){
		this.plugin = plugin;
	}
	
	
	public void reload(){
		plugin.reloadConfig();
		
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
    	
	}
	
	public WorldGuardConfig getworldguardconfig(){
		return worldGuardConfig;
	}
}
