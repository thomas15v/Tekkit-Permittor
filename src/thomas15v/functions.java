package thomas15v;

import org.bukkit.block.Block;


public class functions {

	public static boolean InArray(int[] array, int value){
		if (array.length > 0){
			for (int i : array) {
				if (i == value) return true;
			}
		} 
		return false;	
		
	}
	
	boolean InArray(String[] array, String value){
		if (array.length > 0){
			String bettervalue = value;
			for (String i : array) {
				
				if (!i.contains(":"))bettervalue = value.split(":")[0];
				else bettervalue = value;
				if (i.trim().equalsIgnoreCase(bettervalue)) return true;
			}
		}
		return false;
	}
	
	public static int[] StringToIntArray(String value){
		String[] array = value.split(",");
		int[] returnvalue = new int[array.length];
		
		int count = 0;
		for (String i : array) {
			returnvalue[count] = Integer.parseInt(i.trim());
			count++;
		}
		return returnvalue;
	}
	
	public static boolean InBlockInfoArray(BlockInfo[] blocks, Block block){
		for (BlockInfo b : blocks){
			if (b.equals(block)) return true;
		}
		return false;
	}
	
	public static BlockInfo[] StringToBlockInfo(String value){
		String[] array = value.split(",");
		BlockInfo[] blockInfo = new BlockInfo[array.length];
		int count = 0;
		for (String i : array) {
			blockInfo[count] = new BlockInfo(i.trim());
			count++;
		}
		return blockInfo;	
	}
}
