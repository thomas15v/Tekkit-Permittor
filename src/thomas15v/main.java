package thomas15v;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class main extends JavaPlugin {
	
	
	@Override
	public void onEnable() {
		loadConfiguration();
		Bukkit.getLogger().info("[Tekkit Little Permitor] configuration loaded!");
		launchevents();
		Bukkit.getLogger().info("[Tekkit Little Permitor] events loaded!");
		loadWorldGuardsupport();
		Bukkit.getLogger().info("[Tekkit Little Permitor] loaded!");
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
		
		getConfig().addDefault("Add_forgoten_recipe", false);
		getConfig().addDefault("block-Mod-block-place.enabled",true);
		getConfig().addDefault("block-Mod-block-place.blocks", "48,56,16,15,21,73,49,14");	
		
		getConfig().addDefault("block-illegal-exp-reward.enabled", true);		
		getConfig().addDefault("block-illegal-exp-reward.blocks", "188,250,2050:8,61");
		getConfig().addDefault("block-illegal-exp-reward.maxexp", 0);
		
		getConfig().addDefault("Block-moreplayer-using-block.enabled", true);
		getConfig().addDefault("Block-moreplayer-using-block.blocks", "751:3");
		
		getConfig().addDefault("Protection.enabled", true);
		getConfig().addDefault("Protection.wrenches", "21257,30140,30183,4062,4370");
		getConfig().addDefault("Protection.tools", "30119,30124,5582,5587,20257,20259,27003,27002,19297" );
		getConfig().addDefault("Protection.alwaysblockedtools", "19263,4363,4364,19261,30208,30215,30131");
		getConfig().addDefault("Protection.Containerblocks", "192,901,250,246,188,277,2491,207,900,181,251,3120,3131,227,751,233,2050,183,2002,30208,3893,223");
		getConfig().addDefault("Protection.UseBlocks", "255");
		
		if (getConfig().getBoolean("Add_forgoten_recipe")) forgotenrecipes();	
		
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
	
	private void loadWorldGuardsupport() {
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        Bukkit.getLogger().info("[Tekkit Little Permitor] No worldguard plugin founded!!!");
	    }else{
	   	
	    	
	    	Worldguardevents worldguardevents = new Worldguardevents((WorldGuardPlugin) plugin);
	    	worldguardevents.wrenches = functions.StringToIntArray(getConfig().getString("Protection.wrenches"));
	    	worldguardevents.tools = functions.StringToIntArray(getConfig().getString("Protection.tools"));
	    	worldguardevents.alwaysblockedtools = functions.StringToIntArray(getConfig().getString("Protection.alwaysblockedtools"));
	    	worldguardevents.Containerblocks = functions.StringToIntArray(getConfig().getString("Protection.Containerblocks"));
	    	worldguardevents.UseBlocks = functions.StringToIntArray(getConfig().getString("Protection.UseBlocks"));
	    	
	    	getServer().getPluginManager().registerEvents(worldguardevents, this);
	    }
	}
}
