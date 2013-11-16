package thomas15v;

import org.bukkit.plugin.Plugin;

public class generateconfig {
	

	public static void ForTM(Plugin plugin){
		plugin.getConfig().addDefault("Add_forgoten_recipe", false);
		plugin.getConfig().addDefault("block-Mod-block-place.enabled",true);
		plugin.getConfig().addDefault("block-Mod-block-place.blocks", "48,56,16,15,21,73,49,14");	
		
		plugin.getConfig().addDefault("block-illegal-exp-reward.enabled", false);		
		plugin.getConfig().addDefault("block-illegal-exp-reward.blocks", "188,250,2050:8,61");
		plugin.getConfig().addDefault("block-illegal-exp-reward.maxexp", 0);
		
		plugin.getConfig().addDefault("Block-moreplayer-using-block.enabled", false);
		plugin.getConfig().addDefault("Block-moreplayer-using-block.blocks", "751:3");
		
		plugin.getConfig().addDefault("Protection.enabled", true);
		plugin.getConfig().addDefault("Protection.wrenches", "5397,5766,8858");
		plugin.getConfig().addDefault("Protection.tools", "5909,5206,5205,5662,5693,5695,5696,5967,5968,6037");
		plugin.getConfig().addDefault("Protection.alwaysblockedtools", "5759,5967");
		plugin.getConfig().addDefault("Protection.Containerblocks", "917,918,924,930,551,987,871,872,873,762,887,888,889,890,892,894,911,501,502,503,504,510,520,686,687,742,743,1404,1405,1406,1407,1411,1415,1416,1419,761,762,763,764,765,1026,1027,1029,1030,1031,1032,581,582,583,584,594,597,598,599,602,607,608,1061,200,846,849,848,847");
		plugin.getConfig().addDefault("Protection.UseBlocks", "537");
		
		 plugin.getConfig().options().copyDefaults(true);
		 plugin.saveConfig();
		
	}
	
	public static void ForTL(Plugin plugin){
		plugin.getConfig().addDefault("Add_forgoten_recipe", false);
		plugin.getConfig().addDefault("block-Mod-block-place.enabled",true);
		plugin.getConfig().addDefault("block-Mod-block-place.blocks", "48,56,16,15,21,73,49,14");	
		
		plugin.getConfig().addDefault("block-illegal-exp-reward.enabled", true);		
		plugin.getConfig().addDefault("block-illegal-exp-reward.blocks", "188,250,2050:8,61");
		plugin.getConfig().addDefault("block-illegal-exp-reward.maxexp", 0);
		
		plugin.getConfig().addDefault("Block-moreplayer-using-block.enabled", true);
		plugin.getConfig().addDefault("Block-moreplayer-using-block.blocks", "751:3");
		
		plugin.getConfig().addDefault("Protection.enabled", true);
		plugin.getConfig().addDefault("Protection.wrenches", "21257,30140,30183,4062,4370");
		plugin.getConfig().addDefault("Protection.tools", "30119,30124,5582,5587,20257,20259,27003,27002,19297" );
		plugin.getConfig().addDefault("Protection.alwaysblockedtools", "19263,4363,4364,19261,30208,30215,30131");
		plugin.getConfig().addDefault("Protection.Containerblocks", "192,901,250,246,188,277,2491,207,900,181,251,3120,3131,227,751,233,2050,183,2002,30208,3893,223,763,2004,2007,2005");
		plugin.getConfig().addDefault("Protection.UseBlocks", "255");
		
		 plugin.getConfig().options().copyDefaults(true);
		 plugin.saveConfig();
	
	}
	
	public static void ForB(){
		
	}
}
