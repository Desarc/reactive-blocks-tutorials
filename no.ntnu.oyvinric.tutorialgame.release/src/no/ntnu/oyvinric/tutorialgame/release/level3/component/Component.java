package no.ntnu.oyvinric.tutorialgame.release.level3.component;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;
import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile;

public class Component extends Block {

	TutorialGame game;
	CharacterTile malcolm;
	
	public Component() {
		game = new TutorialGame();
		game.start(3);
		malcolm = game.getMalcolm();
	}

	public void moveForward() {
		game.moveCharacterForward(malcolm);
	}

	public void stop() {
		game.stopCharacterMovement(malcolm);
	}
	
	public void turnLeft() {
		game.turnCharacterLeft(malcolm);
	}
	
	public void turnRight() {
		game.turnCharacterRight(malcolm);
	}
	
	public void turnAround() {
		game.turnCharacterAround(malcolm);
	}
	
	public void pickUp() {
		game.characterPickUp(malcolm);
	}
}