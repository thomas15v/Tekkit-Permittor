package thomas15v;

import org.bukkit.Location;

public class BlockidPlayerlocation {
	Location location;
	int id;
	int data;
	
	public BlockidPlayerlocation(int id, int data, Location location){
		this.location = location;
		this.id = id;
		this.data = data;
	}
	
	public Location getlocation(){
		return this.location;
	}
	
	public String getblock(){
		return id + ":" + data;
	}
}
