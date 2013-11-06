package thomas15v;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class events implements Listener {
	
	
	FileConfiguration config;
	public int loseexp = 5;
	public int[] noplaceblock = {48,56,16,15,21,73,49,14};
	
	public boolean Modblockplaceenabled = true;
	
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void blockplace(BlockPlaceEvent event){
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
	
}
