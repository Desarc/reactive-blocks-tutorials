package no.ntnu.oyvinric.tutorialgame.release.level1.component;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;
import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile;

public class Component extends Block {
	
	TutorialGame game;
	CharacterTile malcolm;
	
	public Component() {
		game = new TutorialGame();
		game.start(1);
		malcolm = game.getMalcolm();
	}

	public void sayHello() {
		game.characterSpeak(malcolm, "Hello World!");
	}
}