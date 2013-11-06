package thomas15v;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class events implements Listener {
	
	
	FileConfiguration config;
	public int loseexp = 5;
	public int[] noplaceblock = {48,56,16,15,21,73,49,14};
	
	public boolean Modblockplaceenabled = true;
	
	Map<Player,Location> playeruseprojecttable = new HashMap<Player,Location >();
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void BlockPlaceEvent(BlockPlaceEvent event){

		//Bukkit.getLogger().info("Blockplace event from " + event.getPlayer().getName());
		
		int block = event.getBlock().getTypeId();
		/*
		if (block == 237){
			int exp = event.getPlayer().getLevel();
			if (exp >= loseexp){
				event.getPlayer().setLevel(exp - loseexp);
				event.setCancelled(false);
				event.getPlayer().sendMessage("Your nuke is ready and filled with the necessary Magic");
				
			}
			else{
				event.getPlayer().sendMessage("Your magic level is to low ... . We need to see at least " + loseexp + "levels!!!");
				event.setCancelled(true);
			}
		}
		*/
		
		
		if (event.getPlayer().getName().equalsIgnoreCase("[RedPower]") || event.getPlayer().getName().equalsIgnoreCase("[computercraft]") ){
			Bukkit.getLogger().info("Blockplace event from " + event.getPlayer().getName());
			
			if (functions.InArray(noplaceblock, block)){
				Bukkit.getLogger().info("Job abusing exploit blocked");
				event.setCancelled(true);
			}
		}
		
		
	}	
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractEvent(PlayerInteractEvent event) {
		int data = event.getClickedBlock().getData();
		int id = event.getClickedBlock().getTypeId();
		
		if (id == 751 && data == 3){
			Location location = event.getClickedBlock().getLocation();
			Player player = event.getPlayer();
			if (playeruseprojecttable.containsValue(location) && !playeruseprojecttable.containsKey(player)){
				player.sendMessage(ChatColor.RED + "This Block is already in use");
				event.setCancelled(true);
			}else{
				playeruseprojecttable.put(player, location);
			}
			
			
			
		}
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerMoveEvent(PlayerMoveEvent event) {
		if (playeruseprojecttable.containsKey(event.getPlayer())) playeruseprojecttable.remove(event.getPlayer());
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerQuitEvent(PlayerQuitEvent event){
		if (playeruseprojecttable.containsKey(event.getPlayer())) playeruseprojecttable.remove(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerQuitEvent(PlayerKickEvent event){
		if (playeruseprojecttable.containsKey(event.getPlayer())) playeruseprojecttable.remove(event.getPlayer());
	}

	
	
	
}
