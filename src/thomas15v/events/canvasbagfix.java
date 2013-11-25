package thomas15v.events;

import java.util.Set;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class canvasbagfix implements Listener {
	
	Set<String>Playerusingcanvasbag = new ArrayList<String>;
	
	
	@EventHandler(priority = EventPriority.LOWEST)
	void PlayerInteractEvent(PlayerInteractEvent event){
		int canvasbag = 9268;
		
		if (canvasbag == event.getItem().getTypeId() && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)){
				
		}
	}
}
