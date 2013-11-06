package thomas15v;

import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	
	
	@Override
	public void onEnable() {
		loadConfiguration();
		launchevents();
			
	}
	
	public void loadConfiguration(){
		
		getConfig().addDefault("Modblockplace.enabled", "true");
		getConfig().addDefault("Modblockplace.blocks", "48,56,16,15,21,73,49,14");		
	    getConfig().options().copyDefaults(true);
	    saveConfig();
	}
	
	public void launchevents(){
		events Events = new events();
		
		Events.noplaceblock = functions.StringToIntArray(getConfig().getString("Modblockplace.blocks"));
		Events.Modblockplaceenabled = getConfig().getBoolean("Modblockplace.enabled");
		
		getServer().getPluginManager().registerEvents(Events, this);	
		
		
	}
}
