package thomas15v.info;

public class ModEnchantmentInfo {
	int id;
	int level;
	
	boolean levellimit;
	
	public ModEnchantmentInfo(String id){
		this.levellimit = id.contains(":");
		if (this.levellimit){
			this.id = Integer.parseInt(id.split(":")[0]);
			this.level = Integer.parseInt(id.split(":")[1]);
		}else{
			this.id = Integer.parseInt(id);
		}
	}

	public boolean Limitonlevel(){
		return levellimit;
	}
	
	public int GetMaxLevel(){
		return level;
	}
	
	public int GetTypeId(){
		return id;
	}
	
}
