package no.ntnu.item.tutorials2.exercise5b_completed.component;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	private String[] insults = {"Every enemy I have met, I've annihilated!", "You're as repulsive as a monkey in a negligee!", "Killing you would be justifiable homicide!", "You're the ugliest monster ever created!", "When your father first saw you, he must have been mortified!", "En Garde! Touché!"};
	private String[] comebacks = {"With your breath, I'm sure they all suffocated.", "I look that much like your fiancée?", "Then killing you must be justifiable fungicide.", "If you don't count all the ones you've dated.", "At least mine can be identified.", "Oh, that is so cliché."};
	private int currentInsult;
	
	public void insult() {
		currentInsult = (int)(Math.random()*insults.length);
		System.out.println("Insult: "+insults[currentInsult]);
	}
	
	public boolean comeback() {
		int comeback = (int)(Math.random()*comebacks.length);
		System.out.println("Comeback: "+comebacks[comeback]);
		if (comeback == currentInsult) {
			System.out.println("(success)\n");
			return true;
		}
		else {
			System.out.println("(failed)\n");
			return false;
		}
	}

	public void victory() {
		System.out.println("Victory!");
	}

}
