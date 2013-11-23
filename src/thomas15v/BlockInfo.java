package thomas15v;

import org.bukkit.block.Block;

public class BlockInfo {
	
	int id;
	byte subid;
	
	public BlockInfo(Block block) {
		this.id = block.getTypeId();
		this.subid = block.getData();
	}
	
	public BlockInfo(String block) {
		if (block.contains(":")){
			this.id = Integer.parseInt(block.split(":")[0]);
			this.subid = Byte.parseByte(block.split(":")[1]);
		}
		else{
			this.id = Integer.parseInt(block);
			this.subid = 0;
		}
	}
	
	public boolean Equals(Block block){
		return block.getTypeId() == id & block.getData() == subid;
	}
	
	public boolean EqualsIgnoreSubid(Block block){
		return block.getTypeId() == id; 
	}
	
}
