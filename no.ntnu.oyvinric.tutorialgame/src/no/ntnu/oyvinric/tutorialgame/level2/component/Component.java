package no.ntnu.oyvinric.tutorialgame.level2.component;

import tile.CharacterTile;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;
import no.ntnu.oyvinric.tutorialgame.gui.GameBoard;
import no.ntnu.oyvinric.tutorialgame.gui.UserInterface;

public class Component extends Block {

	TutorialGame game;
	CharacterTile malcolm;
	CharacterTile kaylee;
	
	public Component() {
		game = new TutorialGame(2);
		new LwjglApplication(game, "Level 1", GameBoard.windowWidth+UserInterface.windowWidth, GameBoard.windowHeight, false);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		malcolm = game.getMalcolm();
		kaylee = game.getKaylee();
	}

	public void moveMalcolmForward() {
		game.moveCharacterForward(malcolm);
	}

	public void stopMalcolm() {
		game.stopCharacterMovement(malcolm);
	}
	
	public void turnMalcolmLeft() {
		game.turnCharacterLeft(malcolm);
	}
	
	public void turnMalcolmRight() {
		game.turnCharacterRight(malcolm);
	}
	
	public void turnMalcolmAround() {
		game.turnCharacterAround(malcolm);
	}
	
	public void moveKayleeForward() {
		game.moveCharacterForward(kaylee);
	}

	public void stopKaylee() {
		game.stopCharacterMovement(kaylee);
	}
	
	public void turnKayleeLeft() {
		game.turnCharacterLeft(kaylee);
	}
	
	public void turnKayleeRight() {
		game.turnCharacterRight(kaylee);
	}
	
	public void turnKayleeAround() {
		game.turnCharacterAround(kaylee);
	}
	
	public void malcolmPushButton() {
		game.pushButton();
	}

	public void doNothing() {
	}
	
}
