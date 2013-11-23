package thomas15v;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class functions {

	public static boolean InBlockInfoArray(BlockInfo[] blocks, Block block){
		for (BlockInfo b : blocks){
			if (b.Equals(block)) return true;
		}
		return false;
	}
	
	public static boolean InBlockInfoArray(BlockInfo[] blocks, ItemStack block){
		for (BlockInfo b : blocks){
			if (b.Equals(block)) return true;
		}
		return false;
	}
	
	public static boolean InBlockInfoArray(BlockInfo[] blocks, BlockInfo block){
		for (BlockInfo b : blocks){
			if (b.equals(block)) return true;
		}
		return false;
	}
	
	public static BlockInfo[] StringToBlockInfo(String value){
		if (value != null){
			if (value.contains(",")){
				String[] array = value.split(",");
				BlockInfo[] blockInfo = new BlockInfo[array.length];
				int count = 0;
				for (String i : array) {
					blockInfo[count] = new BlockInfo(i.trim());
					count++;
				}
				return blockInfo;	
			}else{
				BlockInfo[] blockInfo = new BlockInfo[1];
				blockInfo[0] = new BlockInfo(value);
				return blockInfo;
			}	
		}
		else {
			BlockInfo[] blockInfo = new BlockInfo[0];
			return blockInfo;
		}	
	}
}
