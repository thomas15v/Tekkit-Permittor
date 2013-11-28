package thomas15v.events;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerInventoryEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

import thomas15v.TekkitPermittor;

@SuppressWarnings("deprecation")
public class Placementlimitorevents implements Listener {
	
	Set<String> playerusingcanvasbag = new HashSet<String>();
	
	
	@EventHandler(priority = EventPriority.LOWEST)
	void PlayerInteractEvent(PlayerInteractEvent event){
		int canvasbag = 9268;
		
		if (canvasbag == event.getItem().getTypeId() && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)){
			TekkitPermittor.GetLogger().info("Canvas bag used by " + event.getPlayer().getName());	
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void PlayerInventoryEvent(PlayerItemHeldEvent event) {
		TekkitPermittor.GetLogger().info(event.getEventName());
		// TODO Auto-generated constructor stub
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void PlayerInventoryEvent (PlayerInventoryEvent event){
		TekkitPermittor.GetLogger().info(event.getEventName());
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void InventoryClickEvent (InventoryClickEvent event){
		TekkitPermittor.GetLogger().info(event.getEventName());
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void InventoryCloseEvent (InventoryCloseEvent event){
		TekkitPermittor.GetLogger().info(event.getEventName());
	}
}
