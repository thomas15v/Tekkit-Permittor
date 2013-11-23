package thomas15v;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

public class WorldModifyer {
	
	World world = null;
	CommandSender sender = null;
	
	public WorldModifyer(World world, CommandSender sender){
		this.world = world;
		this.sender = sender;
	}
	
	public void Replaceallblocks(int id){
		int replacedblocks = 0;
		for (Chunk c : world.getLoadedChunks()){
			for (int x = 0; x < 16; x++){
                for(int y = 0; y < c.getWorld().getMaxHeight(); y++)
                {
                        for(int z = 0; z < 16; z++)
                        {
                        	 Block block = c.getBlock(x, y, z);
                        	 if (block.getTypeId() == id){
                        		 replacedblocks++;
                        		 sender.sendMessage(ChatColor.GREEN + "Chunkloader replaced on " + block.getX() + " " + block.getY() + " " + block.getZ());
                        		 block.breakNaturally(new ItemStack(0,0));
                        		 block.setType(Material.CHEST);
                        		 Chest chest = (Chest) block.getState();
                        		 chest.getInventory().addItem(new ItemStack(153,1));                                   		 
                        	 }
                        }
                }
			}
		}
		sender.sendMessage(replacedblocks + " Chunkloaders replaced in world " + world.getName());
	}
	
	public void Replaceallblocks(int[] ids){
		int replacedblocks = 0;
		for (Chunk c : world.getLoadedChunks()){
			for (int x = 0; x < 16; x++){
                for(int y = 0; y < c.getWorld().getMaxHeight(); y++)
                {
                        for(int z = 0; z < 16; z++)
                        {
                        	 Block block = c.getBlock(x, y, z);
                        	 for (int id : ids){
                        		 if (block.getTypeId() == id){
                            		 replacedblocks++;
                            		 sender.sendMessage(ChatColor.GREEN + "Chunkloader replaced on " + block.getX() + " " + block.getY() + " " + block.getZ());
                            		 block.breakNaturally(new ItemStack(0,0));
                            		 block.setType(Material.CHEST);
                            		 Chest chest = (Chest) block.getState();
                            		 chest.getInventory().addItem(new ItemStack(153,1));                                   		 
                            	 }
                        	 }
                        }
                }
			}
		}
		sender.sendMessage(replacedblocks + " Chunkloaders replaced in world " + world.getName());
	}
	
	
	public void logblocks(int id){
		int replacedblocks = 0;
		for (Chunk c : world.getLoadedChunks()){
			for (int x = 0; x < 16; x++){
                for(int y = 0; y < c.getWorld().getMaxHeight(); y++)
                {
                        for(int z = 0; z < 16; z++)
                        {
                        	 Block block = c.getBlock(x, y, z);
   
                        	 if (block.getTypeId() == id){
                        		 replacedblocks++;
                        		 sender.sendMessage(ChatColor.RED + "Chunkloader found on " + block.getX() + " " + block.getY() + " " + block.getZ());                               		 
                        	 }
                        }
                }
			}
		}
		sender.sendMessage(ChatColor.DARK_GREEN + "" + replacedblocks + " Chunkloaders found in world " + world.getName());
	}
	
	public void logblocks(int[] ids){
		int replacedblocks = 0;
		for (Chunk c : world.getLoadedChunks()){
			for (int x = 0; x < 16; x++){
                for(int y = 0; y < c.getWorld().getMaxHeight(); y++)
                {
                        for(int z = 0; z < 16; z++)
                        {
                        	Block block = c.getBlock(x, y, z);
                        	 for (int id : ids){
	                        	 if (block.getTypeId() == id){
	                        		 replacedblocks++;
	                        		 sender.sendMessage(ChatColor.RED + "Chunkloader found on " + block.getX() + " " + block.getY() + " " + block.getZ());                               		 
	                        	 }
                        	 }
                        }
                }
			}
		}
		sender.sendMessage(ChatColor.DARK_GREEN + "" + replacedblocks + " Chunkloaders found in world " + world.getName());
	}
	
	public void logblocks(BlockInfo[] ids){
		int replacedblocks = 0;
		for (Chunk c : world.getLoadedChunks()){
			for (int x = 0; x < 16; x++){
                for(int y = 0; y < c.getWorld().getMaxHeight(); y++)
                {
                        for(int z = 0; z < 16; z++)
                        {
                        	Block block = c.getBlock(x, y, z);
                        	 for (BlockInfo id : ids){
	                        	 if (id.Equals(block)){
	                        		 replacedblocks++;
	                        		 sender.sendMessage(ChatColor.RED + "Chunkloader found on " + block.getX() + " " + block.getY() + " " + block.getZ());                               		 
	                        	 }
                        	 }
                        }
                }
			}
		}
		sender.sendMessage(ChatColor.DARK_GREEN + "" + replacedblocks + " Chunkloaders found in world " + world.getName());
	}
	
}
