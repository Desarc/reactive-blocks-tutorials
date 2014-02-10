package no.ntnu.item.tutorials2.counter;

import no.ntnu.item.arctis.runtime.Block;

public class Counter extends Block {

	public int counter;

	public void decreaseCounter() {
		counter = counter - 1;
	}

	public boolean counterLargeZero() {
		return counter >= 0;
	}

}
