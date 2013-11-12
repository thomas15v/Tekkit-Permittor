package thomas15v;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Worldguardevents implements Listener {
	
	int[] wrenches = {21257,30140,30183,4062,4370};
	int[] tools = {};
	
	WorldGuardPlugin worldguard;
	
	public Worldguardevents(WorldGuardPlugin worldgoard){
		this.worldguard = worldgoard;
	}
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	void PlayerInteractEvent(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.hasItem() && !worldguard.canBuild(player,block)){
			int iteminhand = event.getItem().getTypeId();
			
			if (functions.InArray(wrenches, iteminhand)){
				player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use a wrench here");
				Bukkit.getLogger().info(iteminhand + "");
				event.setCancelled(true);
			}		
					
		
		}
	}
	
}
