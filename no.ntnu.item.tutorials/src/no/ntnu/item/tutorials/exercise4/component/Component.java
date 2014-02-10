package no.ntnu.item.tutorials.exercise4.component;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public int generateNumber() {
		int number = (int)(Math.random()*200);
		System.out.println("The number is "+number);
		return number;
	}

	public boolean isNumberBig(int number) {
		if (number < 100) return false;
		else {
			System.out.println("Number is big!");
			return true;
		}
	}
}