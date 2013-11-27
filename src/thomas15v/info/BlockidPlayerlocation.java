package thomas15v.info;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class BlockidPlayerlocation {
	Location location;
	Block block;
	int data;
	
	public BlockidPlayerlocation(Block block, Location location){
		this.location = location;
		this.block = block;
	}
	
	public Location getlocation(){
		return this.location;
	}
	
	public Block getblock(){
		return block;
	}
}
