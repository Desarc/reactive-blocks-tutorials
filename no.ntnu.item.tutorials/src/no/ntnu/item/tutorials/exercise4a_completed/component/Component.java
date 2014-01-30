package no.ntnu.item.tutorials.exercise4a_completed.component;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public double generateNumber() {
		double number = Math.random();
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
		if (number < 0.5) return false;
		else return true;
	}

}