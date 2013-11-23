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
import org.bukkit.inventory.ItemStack;

import thomas15v.configuration.WorldGuardConfig;
import thomas15v.configuration.Manager;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

public class Worldguardevents implements Listener {
	

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
			
			WorldGuardConfig mgr = Manager.getworldguardConfig();
			
			if (event.hasItem()){
				ItemStack iteminhand = event.getItem();
				Boolean inarea =  region.iterator().hasNext();
				
				
				
				if (Functions.InBlockInfoArray(mgr.wrenches, iteminhand) && !worldguard.canBuild(player,block)){
					player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use a wrench in this area");
					event.setCancelled(true);
					return;
				}					
				
				if (Functions.InBlockInfoArray(mgr.tools, iteminhand) && !worldguard.canBuild(player,block)){
					player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use a tool in this area");
					event.setCancelled(true);
					return;
				}
				
				if (Functions.InBlockInfoArray(mgr.alwaysblockedtools, iteminhand) && inarea){
					player.sendMessage(ChatColor.DARK_RED + "The usage of this tool is disabled globally in every region");
					event.setCancelled(true);
					return;
				}
			}
			if (Functions.InBlockInfoArray(mgr.Containerblocks, block ) && !region.allows(DefaultFlag.CHEST_ACCESS,worldguard.wrapPlayer(player)) && !worldguard.canBuild(player, block)){
				player.sendMessage(ChatColor.DARK_RED + "You don't have permission to open that in this area");
				event.setCancelled(true);
				return;
			}
			if (Functions.InBlockInfoArray(mgr.UseBlocks, block) && !region.allows(DefaultFlag.USE ,worldguard.wrapPlayer(player)) && !worldguard.canBuild(player, block)){
				player.sendMessage(ChatColor.DARK_RED + "You don't have permission to open that in this area");
				event.setCancelled(true);
				return;
			}			
			
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockPlaceEvent(BlockPlaceEvent event){
		WorldGuardConfig mgr = Manager.getworldguardConfig();
		Block block = event.getBlock();
		ApplicableRegionSet region =  worldguard.getRegionManager(block.getWorld()).getApplicableRegions(block.getLocation());
		Boolean inarea =  region.iterator().hasNext();
		if (inarea && (event.getPlayer().getName().startsWith("[") || event.getPlayer().getName().endsWith("]"))){
			event.setCancelled(true);			
		}		
		
		if (inarea && Functions.InBlockInfoArray(mgr.alwaysblockedblocks, block)){
			event.getPlayer().sendMessage(ChatColor.DARK_RED + "The placement of this block is blocked globally");
			event.setCancelled(true);
		}
		
	}	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockBreakEvent(BlockBreakEvent event){
		Block block = event.getBlock();
		ApplicableRegionSet region =  worldguard.getRegionManager(block.getWorld()).getApplicableRegions(block.getLocation());
		Boolean inarea =  region.iterator().hasNext();
		if (inarea && (event.getPlayer().getName().startsWith("[") || event.getPlayer().getName().endsWith("]") )){
			event.setCancelled(true);			
		}		
		
	}	
}
