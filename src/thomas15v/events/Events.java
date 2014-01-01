package thomas15v.events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import thomas15v.TekkitPermittor;
import thomas15v.configuration.EventConfig;
import thomas15v.configuration.Manager;
import thomas15v.info.BlockidPlayerlocation;
import thomas15v.other.Functions;


public class Events implements Listener {
	
	public Map<String,Location> OnePlayerBlocksUsed = new HashMap<String,Location>();
	public Map<String,BlockidPlayerlocation> playerusingblock = new HashMap<String,BlockidPlayerlocation>();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockPlaceEvent(BlockPlaceEvent event){		
		if (event.getPlayer().getName().startsWith("[") || event.getPlayer().getName().endsWith("]")){
			EventConfig mgr = Manager.geteventconfig();
			Block block = event.getBlock(); 
					
			if (Functions.InBlockInfoArray(mgr.noplaceblock, block)){
				TekkitPermittor.GetLogger().info("Job abusing exploit blocked");
				event.setCancelled(true);
			}
		}			
	}	
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractEvent(PlayerInteractEvent event) {
		EventConfig mgr = Manager.geteventconfig();
		
		removeplayeroutlist(event.getPlayer());
		
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
			Block block = event.getClickedBlock();
			//multiple block use blocking
			if (Functions.InBlockInfoArray(mgr.onePlayerBlocks ,block) && mgr.Blockmoreplayerusingblockenabled ){
				Location location = event.getClickedBlock().getLocation();
				Player player = event.getPlayer();
				if (OnePlayerBlocksUsed.containsValue(location) && !OnePlayerBlocksUsed.containsKey(player.getName())){
					player.sendMessage(ChatColor.RED + "This Block is already in use");
					event.setCancelled(true);
				}else{
					OnePlayerBlocksUsed.put(player.getName(), location);
				}		
			}
			
			//EXPBLOCKER
			if (Functions.InBlockInfoArray(mgr.illegalexprewardenabledblocks, block) && mgr.blockillegalexprewardenabled){
				Player player = event.getPlayer();
				BlockidPlayerlocation blockPlocation = new BlockidPlayerlocation(block, player.getLocation());
				playerusingblock.put(player.getName(), blockPlocation);		
			}
		}
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerExpChangeEvent (PlayerExpChangeEvent event){
		EventConfig mgr = Manager.geteventconfig();
		//EXPBLOCKER
		Player player = event.getPlayer();
		if (mgr.maxexp < event.getAmount() && playerusingblock.containsKey(player.getName())){
				
			Block block = playerusingblock.get(player.getName()).getblock();
			if (Functions.InBlockInfoArray(mgr.illegalexprewardenabledblocks, block )){
				TekkitPermittor.GetLogger().info(player.getName() + " Took to mutch EXP from the banned exp giving blocks");
				event.setAmount(0);
			}
		
		}		
	}
		
	@EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerMoveEvent(PlayerMoveEvent event) {
		
		
		Player player = event.getPlayer();
		
		if (playerusingblock.containsKey(player.getName()) && 
				(playerusingblock.get(player.getName()).getlocation().distance(player.getLocation()) > 5)){
			playerusingblock.remove(player.getName());
		}
		
		if (OnePlayerBlocksUsed.containsKey(player.getName()) && 
				(OnePlayerBlocksUsed.get(player.getName()).distance(player.getLocation()) > 5)){
			OnePlayerBlocksUsed.remove(player.getName());
		}
		
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerQuitEvent(PlayerQuitEvent event){
		removeplayeroutlist(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerKickEvent(PlayerKickEvent event){
		removeplayeroutlist(event.getPlayer());
	}
	
	void removeplayeroutlist(Player player){
		if (OnePlayerBlocksUsed.containsKey(player.getName())) OnePlayerBlocksUsed.remove(player);
		if (playerusingblock.containsKey(player.getName())) playerusingblock.remove(player.getName());
	}
}	

	
	

