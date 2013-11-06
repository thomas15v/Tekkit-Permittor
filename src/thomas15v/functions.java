package thomas15v;


public class functions {

	static boolean InArray(int[] array, int value){
		for (int i : array) {
			if (i == value) return true;
		}
		return false;
	}
	
	static int[] StringToIntArray(String value){
		String[] array = value.split(",");
		int[] returnvalue = new int[array.length];
		
		int count = 0;
		for (String i : array) {
			returnvalue[count] = Integer.parseInt(i.trim());
			count++;
		}
		return returnvalue;
	}
}
