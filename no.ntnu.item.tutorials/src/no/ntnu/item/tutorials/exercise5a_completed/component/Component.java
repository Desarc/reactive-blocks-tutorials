package no.ntnu.item.tutorials.exercise5a_completed.component;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public double generateNumber() {
		double number = Math.random();
		System.out.println("The number is "+number);
		return number;
	}

	public boolean isNumberBig(double number) {
		if (number < 0.5) return false;
		else {
			System.out.println("Number is big!");
			return true;
		}
	}
}