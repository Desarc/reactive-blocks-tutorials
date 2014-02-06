package no.ntnu.item.tutorials2.exercise3.component;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public double generateNumber() {
		double number = Math.random()*200;
		System.out.println("The number is "+number);
		return number;
	}

	public void small() {
		System.out.println("Small!");
	}

	public void big() {
		System.out.println("Big!");
	}
	
	public boolean isNumberBig(double number) {
		if (number < 100) return false;
		else return true;
	}
}