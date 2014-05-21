package no.ntnu.oyvinric.tutorialgame.release.level7.component;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;
import no.ntnu.oyvinric.tutorialgame.item.DoorToken;
import no.ntnu.oyvinric.tutorialgame.level.GridPosition;
import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile;

public class Component extends Block {
	
	TutorialGame game;
	CharacterTile malcolm;
	CharacterTile lisa;
	
	public Component() {
		game = new TutorialGame();
		game.start(7);
		malcolm = game.getMalcolm();
		lisa = game.getLisa();
	}
	
	public void malcolmMoveForward() {
		game.moveCharacterForward(malcolm);
	}

	public void malcolmStop() {
		game.stopCharacterMovement(malcolm);
	}
	
	public void malcolmTurnLeft() {
		game.turnCharacterLeft(malcolm);
	}
	
	public void malcolmTurnRight() {
		game.turnCharacterRight(malcolm);
	}
	
	public void malcolmTurnAround() {
		game.turnCharacterAround(malcolm);
	}
	
	public void lisaMoveForward() {
		game.moveCharacterForward(lisa);
	}

	public void lisaStop() {
		game.stopCharacterMovement(lisa);
	}
	
	public void lisaTurnLeft() {
		game.turnCharacterLeft(lisa);
	}
	
	public void lisaTurnRight() {
		game.turnCharacterRight(lisa);
	}
	
	public void lisaTurnAround() {
		game.turnCharacterAround(lisa);
	}
	
	public String lisaInteract() {
		DoorToken token = (DoorToken)game.characterInteract(lisa);
		if (token != null) {
			game.leverPulled(new GridPosition(0, 0, 0), token.isActive());
			if (token.isActive()) {
				sendToBlock("DOOR_OPEN");
			}
		}	
		return "";
	}
	
	public void malcolmPickUp() {
		game.characterPickUp(malcolm);
	}
	
}