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
	int[] tools = {30119,30124,5582,5587,20257,20259,27003,27002,19297};
	int[] alwaysblockedtools = {19263,4363,4364,19261,30208,30215};
	WorldGuardPlugin worldguard;
	
	public Worldguardevents(WorldGuardPlugin worldgoard){
		this.worldguard = worldgoard;
	}
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	void PlayerInteractEvent(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.hasItem() ){
			int iteminhand = event.getItem().getTypeId();
			
			if (functions.InArray(wrenches, iteminhand) && !worldguard.canBuild(player,block)){
				player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use a wrench here");
				event.setCancelled(true);
			}					
			
			if (functions.InArray(wrenches, iteminhand) && !worldguard.canBuild(player,block)){
				player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use a tool here");
				event.setCancelled(true);
			}
			
			Bukkit.getLogger().info(worldguard.getRegionManager(block.getWorld()).getApplicableRegions(block.getLocation()).iterator().hasNext() + "");
			
			
		}
		
		
	}
	
}
