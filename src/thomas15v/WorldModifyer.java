package thomas15v;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import thomas15v.info.BlockInfo;

public class WorldModifyer {
	
	World world = null;
	CommandSender sender = null;
	
	public WorldModifyer(World world, CommandSender sender){
		this.world = world;
		this.sender = sender;
	}
	
	public void Replaceallblocks(BlockInfo[] ids){
		int replacedblocks = 0;
		for (Chunk c : world.getLoadedChunks()){
			for (BlockState blockState : c.getTileEntities()){
				Block block = blockState.getBlock();
				for (BlockInfo id : ids){
					if (id.Equals(block)){
           			 int oldblockid = block.getTypeId();
               		 replacedblocks++;
               		 sender.sendMessage(ChatColor.GREEN + "Chunkloader replaced on " + block.getX() + " " + block.getY() + " " + block.getZ());
               		 block.breakNaturally(new ItemStack(0,0));
               		 block.setType(Material.CHEST);
               		 Chest chest = (Chest) block.getState();
               		 chest.getInventory().addItem(new ItemStack(oldblockid,1));                                   		 
               	 }
				}
			}
		}
		sender.sendMessage(replacedblocks + " Chunkloaders replaced in world " + world.getName());
	}
	
	
	public void logblocks(BlockInfo[] ids){
		int logedblocks = 0;
		for (Chunk c : world.getLoadedChunks()){
			for (BlockState block : c.getTileEntities()){
				for (BlockInfo id : ids){
	               	if (id.Equals(block.getBlock())){
	               		logedblocks++;
	               		sender.sendMessage(ChatColor.RED + "Chunkloader found on " + block.getX() + " " + block.getY() + " " + block.getZ());                      		 
	               	}
           	 	}
			}
		}
		sender.sendMessage(ChatColor.DARK_GREEN + "" + logedblocks + " Chunkloaders found in world " + world.getName());
		
	}



	
	
	public Location Getclosedblock(BlockInfo[] ids, Location location){
		Location closedblocklocation = null;
		for (Chunk c : world.getLoadedChunks()){
			if (c.getTileEntities().length > 0){
				for (BlockState tile : c.getTileEntities()){
					for (BlockInfo id : ids){
						Block block = tile.getBlock();
		               	if (id.Equals(block)){
		               		if (closedblocklocation != null){
		               			if (closedblocklocation.distance(location) > block.getLocation().distance(location) ){
		               				closedblocklocation = block.getLocation();
		               			}
		               		}else{
		               			closedblocklocation = block.getLocation();
		               		}
		               	}
	           	 	}
				}
			}
		}
		return closedblocklocation;
	}
	
}

