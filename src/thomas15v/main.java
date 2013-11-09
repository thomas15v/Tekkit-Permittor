package thomas15v;

import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	
	
	@Override
	public void onEnable() {
		loadConfiguration();
		launchevents();
			
	}
	
	public void loadConfiguration(){
		
		getConfig().addDefault("block-Mod-block-place.enabled",true);
		getConfig().addDefault("block-Mod-block-place.blocks", "48,56,16,15,21,73,49,14");	
		
		getConfig().addDefault("block-illegal-exp-reward.enabled", true);		
		getConfig().addDefault("block-illegal-exp-reward.blocks", "188,250,2050:8,61");
		getConfig().addDefault("block-illegal-exp-reward.maxexp", 0);
		
		getConfig().addDefault("Block-moreplayer-using-block.enabled", true);
		getConfig().addDefault("Block-moreplayer-using-block.blocks", "751:3");
		
		
	    getConfig().options().copyDefaults(true);
	    saveConfig();
	}
	
	public void launchevents(){
		events Events = new events();
		
		Events.noplaceblock = functions.StringToIntArray(getConfig().getString("block-Mod-block-place.blocks"));
		Events.Modblockplaceenabled = getConfig().getBoolean("block-Mod-block-place.enabled");
		
		Events.onePlayerBlocks = getConfig().getString("Block-moreplayer-using-block.blocks").split(",");
		Events.Blockmoreplayerusingblockenabled = getConfig().getBoolean("Block-moreplayer-using-block.enabled");
		
		Events.blockillegalexprewardenabled = getConfig().getBoolean("block-illegal-exp-reward.enabled");
		Events.illegalexprewardenabledblocks = getConfig().getString("block-illegal-exp-reward.blocks").split(",");
		Events.maxexp = getConfig().getInt("block-illegal-exp-reward.maxexp");
		
		getServer().getPluginManager().registerEvents(Events, this);	
		
		
	}
}
