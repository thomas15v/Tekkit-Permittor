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

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

public class Worldguardevents implements Listener {
	
	int[] wrenches = {21257,30140,30183,4062,4370};
	int[] tools = {30119,30124,5582,5587,20257,20259,27003,27002,19297};
	int[] alwaysblockedtools = {19263,4363,4364,19261,30208,30215};
	int[] alwaysblockedblocks= {46,237};
	int [] Containerblocks = {23,25,54,61,62,64,69,70,71,72,77,96,84,93,94,107,192,901,250,246,188,277,2491,207,900,181,251,3120,3131,227,751,233,2050,183,2002,30208,3893,223};
	int [] UseBlocks = {255};
	WorldGuardPlugin worldguard;
	
	public Worldguardevents(WorldGuardPlugin worldgoard){
		this.worldguard = worldgoard;
	}
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	void PlayerInteractEvent(PlayerInteractEvent event){
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK ){
			Player player = event.getPlayer();
			Block block = event.getClickedBlock();
			ApplicableRegionSet region =  worldguard.getRegionManager(block.getWorld()).getApplicableRegions(block.getLocation());
			if (event.hasItem()){
				int iteminhand = event.getItem().getTypeId();
				Boolean inarea =  region.iterator().hasNext();
				
				if (functions.InArray(wrenches, iteminhand) && !worldguard.canBuild(player,block)){
					player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use a wrench in this area");
					event.setCancelled(true);
					return;
				}					
				
				if (functions.InArray(wrenches, iteminhand) && !worldguard.canBuild(player,block)){
					player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use a tool in this area");
					event.setCancelled(true);
					return;
				}
				
				if (functions.InArray(alwaysblockedtools, iteminhand) && inarea){
					player.sendMessage(ChatColor.DARK_RED + "The usage of this tool is disabled globally in every region");
					event.setCancelled(true);
				}
			}
			if (functions.InArray(Containerblocks, block.getTypeId()) && !region.allows(DefaultFlag.CHEST_ACCESS,worldguard.wrapPlayer(player)) && !worldguard.canBuild(player, block)){
				player.sendMessage(ChatColor.DARK_RED + "You don't have permission to open that in this area");
				event.setCancelled(true);
				return;
			}
			if (functions.InArray(UseBlocks, block.getTypeId()) && !region.allows(DefaultFlag.USE ,worldguard.wrapPlayer(player)) && !worldguard.canBuild(player, block)){
				player.sendMessage(ChatColor.DARK_RED + "You don't have permission to open that in this area");
				event.setCancelled(true);
				return;
			}			
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockPlaceEvent(BlockPlaceEvent event){
		Block block = event.getBlock();
		ApplicableRegionSet region =  worldguard.getRegionManager(block.getWorld()).getApplicableRegions(block.getLocation());
		Boolean inarea =  region.iterator().hasNext();
		if (inarea && (event.getPlayer().getName().equalsIgnoreCase("[RedPower]") || event.getPlayer().getName().equalsIgnoreCase("[computercraft]") )){
			event.setCancelled(true);			
		}		
		
		if (inarea && functions.InArray(alwaysblockedblocks, block.getTypeId())){
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
