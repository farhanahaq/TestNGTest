package util;

import java.util.Random;

public class BasePage {
	
	public static int randomNumber() {
		Random rand = new Random(); 
		int rand_int = rand.nextInt(999);
		return rand_int;
		
	}
	
}
