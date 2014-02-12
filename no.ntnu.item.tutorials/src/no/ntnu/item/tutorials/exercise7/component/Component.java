package no.ntnu.item.tutorials.exercise7.component;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public int number;
	
	public Component() {
		number = 10;
	}

	public void displayResult(String result) {
		System.out.println(result);
	}
}