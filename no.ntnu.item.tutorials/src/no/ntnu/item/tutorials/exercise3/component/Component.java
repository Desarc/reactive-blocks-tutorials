package no.ntnu.item.tutorials.exercise3.component;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public int generateNumber() {
		int number = (int)(Math.random()*200);
		System.out.println("The number is "+number);
		return number;
	}

	public void small() {
		System.out.println("Small!");
	}

	public void big() {
		System.out.println("Big!");
	}
	
	public boolean isNumberBig(int number) {
		if (number > 100) return true;
		else return false;
	}
}