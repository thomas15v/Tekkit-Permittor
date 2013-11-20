package thomas15v.configuration;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;

import thomas15v.BlockidPlayerlocation;

public class EventConfig {
	public int[] noplaceblock = {48,56,16,15,21,73,49,14};
	public boolean Modblockplaceenabled = true;
	
	public boolean Blockmoreplayerusingblockenabled = true;
	public String[] onePlayerBlocks = {"751:3"};
	
	public int maxexp = 10;
	public boolean blockillegalexprewardenabled = true;
	public String[] illegalexprewardenabledblocks = {"188","250"};
	
	public Map<String,Location> OnePlayerBlocksUsed = new HashMap<String,Location>();
	public Map<String,BlockidPlayerlocation> playerusingblock = new HashMap<String,BlockidPlayerlocation>();
	
}
