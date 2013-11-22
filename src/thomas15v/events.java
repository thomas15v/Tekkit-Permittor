package thomas15v;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import thomas15v.configuration.EventConfig;
import thomas15v.configuration.manager;


public class events implements Listener {
	
	public Map<String,Location> OnePlayerBlocksUsed = new HashMap<String,Location>();
	public Map<String,BlockidPlayerlocation> playerusingblock = new HashMap<String,BlockidPlayerlocation>();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void BlockPlaceEvent(BlockPlaceEvent event){
		if (event.getPlayer().getName().startsWith("[") || event.getPlayer().getName().endsWith("]")){
			EventConfig mgr = manager.geteventconfig();
			int block = event.getBlock().getTypeId();
			Bukkit.getLogger().info("Blockplace event from " + event.getPlayer().getName());
			
			if (functions.InArray(mgr.noplaceblock, block)){
				Bukkit.getLogger().info("Job abusing exploit blocked");
				event.setCancelled(true);
			}
		}		
		
	}	
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractEvent(PlayerInteractEvent event) {
		EventConfig mgr = manager.geteventconfig();
		
		removeplayeroutlist(event.getPlayer());
		
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
			
			Block block = event.getClickedBlock();
			int data = block.getData();
			int id = block.getTypeId();
					
			//multiple block use blocking
			if (functions.InBlockInfoArray(mgr.onePlayerBlocks ,block) && mgr.Blockmoreplayerusingblockenabled ){
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
			if (functions.InBlockInfoArray(mgr.illegalexprewardenabledblocks, block) && mgr.blockillegalexprewardenabled){
				Player player = event.getPlayer();
				BlockidPlayerlocation blockPlocation = new BlockidPlayerlocation(id, data, player.getLocation());
				playerusingblock.put(player.getName(), blockPlocation);				
			}
			
			/**Bukkit.getLogger().info(event.getClickedBlock().getTypeId() + "");
			/*
			if (event.getClickedBlock().getTypeId() == 188 && event.getClickedBlock().getData() == 2 && event.getPlayer().getItemInHand().getTypeId() == 21257){
				event.getClickedBlock().setTypeId(2051);
				ItemStack item = new ItemStack(188 , 1, (short) 0 ,(byte) 2);
				event.getPlayer().getInventory().addItem(item);
				event.setCancelled(true);
			}
			**/
		}
    }
	
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerExpChangeEvent (PlayerExpChangeEvent event){
		EventConfig mgr = manager.geteventconfig();
		//EXPBLOCKER
		Player player = event.getPlayer();
		if (mgr.maxexp < event.getAmount() && playerusingblock.containsKey(player.getName())){
				
			String block = playerusingblock.get(player.getName()).getblock();
			if (functions.InArray(mgr.illegalexprewardenabledblocks, block )){
				Bukkit.getLogger().info(player.getName() + " Took to mutch EXP from the banned exp giving blocks");
				if (player.getItemOnCursor().getType().equals(Material.DIAMOND) && !block.equalsIgnoreCase("188:1")) player.kickPlayer("No exphacking allowed here");//LOOL KICK THOSE CHEATERS :D
				event.setAmount(0);
			}
		
		}
		
		
		
	}
	
	/*
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	void BlockBreakEvent(BlockBreakEvent event){
		
		if (event.getBlock().getTypeId() == 188 && event.getBlock().getData() == 2){
			event.setCancelled(true);
			event.getBlock().setTypeIdAndData(0 ,(byte) 0, false);
			event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),new ItemStack(250,1,(short) 0,(byte) 12));			
		}
		
		
	}
	*/
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void InventoryClickEvent(InventoryClickEvent event) {
		//Bukkit.getLogger().info(event.getInventory().getName());
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerMoveEvent(PlayerMoveEvent event) {
		
		
		Player player = event.getPlayer();
		
		if (playerusingblock.containsKey(player.getName()) && 
				(playerusingblock.get(player.getName()).getlocation().distance(player.getLocation()) > 6 ||
						event.getFrom().distance(event.getTo()) == (double) 0)){
			playerusingblock.remove(player.getName());
		}
		
		if (OnePlayerBlocksUsed.containsKey(player.getName()) && 
				(OnePlayerBlocksUsed.get(player.getName()).distance(player.getLocation()) > 6 ||
						event.getFrom().distance(event.getTo()) == (double) 0)){
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

	
	

