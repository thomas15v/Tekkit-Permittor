package thomas15v;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class BlockInfo {
	
	int id;
	byte subid;
	boolean subidincluded;
	
	public BlockInfo(Block block) {
		this.id = block.getTypeId();
		this.subid = block.getData();
	}
	
	public BlockInfo(String block) {
		subidincluded = block.contains(":");
		if (subidincluded){
			this.id = Integer.parseInt(block.split(":")[0]);
			this.subid = Byte.parseByte(block.split(":")[1]);
		}
		else{
			this.id = Integer.parseInt(block);
		}
	}
	
	public boolean Equals(Block block){
		if (subidincluded){
			
			TekkitPermittor.logger.info(block.getTypeId() + " " + id  + " " + block.getData() + " " + subid);
			return block.getTypeId() == id && block.getData() == subid;
		}
		else{
			return block.getTypeId() == id;
		}
		
	}	
	
	public boolean Equals(String block){
		if (subidincluded){
			if (block.contains(":")){
				return Integer.parseInt(block.split(":")[0]) == id && Byte.parseByte(block.split(":")[1]) == subid;
			}
			else{
				return Integer.parseInt(block.split(":")[0]) == id;
			}
		}
		else{
			if (block.contains(":")){
				return Integer.parseInt(block.split(":")[0]) == id;
			}
			else{
				return Integer.parseInt(block) == id;
			}
		}
		
	}	
	
	public boolean Equals(ItemStack block){
		if (subidincluded){
			return block.getTypeId() == id && block.getData().getData() == subid;
		}
		else{
			return block.getTypeId() == id;
		}
		
	}
}
