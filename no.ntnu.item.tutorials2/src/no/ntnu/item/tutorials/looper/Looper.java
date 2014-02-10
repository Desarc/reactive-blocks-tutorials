package no.ntnu.item.tutorials.looper;

import no.ntnu.item.arctis.runtime.Block;

public class Looper extends Block {
	
	private int number = 5;
	
	public boolean count() {
		number--;
		if (number >= 0) {
			return false;
		}
		else {
			return true;
		}
		
	}

}
