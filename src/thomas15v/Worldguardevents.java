package thomas15v;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import thomas15v.configuration.WorldGuardConfig;
import thomas15v.configuration.manager;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

public class Worldguardevents implements Listener {
	
	//main plugin = null;
	
	WorldGuardPlugin worldguard;
	
	public Worldguardevents(WorldGuardPlugin worldgoard){
		this.worldguard = worldgoard;
		//this.plugin = plugin
	}
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	void PlayerInteractEvent(PlayerInteractEvent event){
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK ){
			
			
			
			Player player = event.getPlayer();
			Block block = event.getClickedBlock();
			ApplicableRegionSet region =  worldguard.getRegionManager(block.getWorld()).getApplicableRegions(block.getLocation());
			
			WorldGuardConfig mgr = manager.getworldguardConfig();
			
			if (event.hasItem()){
				int iteminhand = event.getItem().getTypeId();
				Boolean inarea =  region.iterator().hasNext();
				
				
				
				if (functions.InArray(mgr.wrenches, iteminhand) && !worldguard.canBuild(player,block)){
					player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use a wrench in this area");
					event.setCancelled(true);
					return;
				}					
				
				if (functions.InArray(mgr.wrenches, iteminhand) && !worldguard.canBuild(player,block)){
					player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use a tool in this area");
					event.setCancelled(true);
					return;
				}
				
				if (functions.InArray(mgr.alwaysblockedtools, iteminhand) && inarea){
					player.sendMessage(ChatColor.DARK_RED + "The usage of this tool is disabled globally in every region");
					event.setCancelled(true);
				}
			}
			if (functions.InArray(mgr.Containerblocks, block.getTypeId()) && !region.allows(DefaultFlag.CHEST_ACCESS,worldguard.wrapPlayer(player)) && !worldguard.canBuild(player, block)){
				player.sendMessage(ChatColor.DARK_RED + "You don't have permission to open that in this area");
				event.setCancelled(true);
				return;
			}
			if (functions.InArray(mgr.UseBlocks, block.getTypeId()) && !region.allows(DefaultFlag.USE ,worldguard.wrapPlayer(player)) && !worldguard.canBuild(player, block)){
				player.sendMessage(ChatColor.DARK_RED + "You don't have permission to open that in this area");
				event.setCancelled(true);
				return;
			}			
			
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockPlaceEvent(BlockPlaceEvent event){
		WorldGuardConfig mgr = manager.getworldguardConfig();
		Block block = event.getBlock();
		ApplicableRegionSet region =  worldguard.getRegionManager(block.getWorld()).getApplicableRegions(block.getLocation());
		Boolean inarea =  region.iterator().hasNext();
		if (inarea && (event.getPlayer().getName().equalsIgnoreCase("[RedPower]") || event.getPlayer().getName().equalsIgnoreCase("[computercraft]") )){
			event.setCancelled(true);			
		}		
		
		if (inarea && functions.InArray(mgr.alwaysblockedblocks, block.getTypeId())){
			event.getPlayer().sendMessage(ChatColor.DARK_RED + "The placement of this block is blocked globally");
			event.setCancelled(true);
		}
		
	}	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockBreakEvent(BlockBreakEvent event){
		Block block = event.getBlock();
		ApplicableRegionSet region =  worldguard.getRegionManager(block.getWorld()).getApplicableRegions(block.getLocation());
		Boolean inarea =  region.iterator().hasNext();
		if (inarea && (event.getPlayer().getName().equalsIgnoreCase("[RedPower]") || event.getPlayer().getName().equalsIgnoreCase("[computercraft]") )){
			event.setCancelled(true);			
		}		
		
	}	
}
