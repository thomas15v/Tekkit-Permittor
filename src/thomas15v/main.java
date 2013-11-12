package thomas15v;

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
		launchevents();
		
		ShapedRecipe factorizationconsumer = new ShapedRecipe(new ItemStack(2855 ,1));
		factorizationconsumer.shape(new String[] {"G G"," F ","G G"});
		factorizationconsumer.setIngredient('G', Material.GOLD_INGOT);
		factorizationconsumer.setIngredient('F', new MaterialData(2050, (byte) 22));
		getServer().addRecipe(factorizationconsumer);			
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
	
	private WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }
	 
	    return (WorldGuardPlugin) plugin;
	}
}
