package thomas15v;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Worldguardevents implements Listener {
	
	WorldGuardPlugin worldguard;
	
	public Worldguardevents(WorldGuardPlugin worldgoard){
		this.worldguard = worldgoard;
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	void PlayerInteractEvent(PlayerInteractEvent event){
		
	}
	
}
