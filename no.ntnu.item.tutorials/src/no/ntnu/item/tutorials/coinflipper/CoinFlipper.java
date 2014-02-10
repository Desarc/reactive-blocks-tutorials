package no.ntnu.item.tutorials.coinflipper;

import no.ntnu.item.arctis.runtime.Block;

public class CoinFlipper extends Block {
	
	private int heads = 0;
	private int tails = 0;

	public String flip() {
		double rand = Math.random();
		if (rand < 0.5) return "heads";
		else return "tails";
	}
	
	public void addHeads() {
		heads++;
	}
	
	public void addTails() {
		tails++;
	}
	
	public String getResult() {
		return "Heads: "+heads+", tails: "+tails;
	}

}
