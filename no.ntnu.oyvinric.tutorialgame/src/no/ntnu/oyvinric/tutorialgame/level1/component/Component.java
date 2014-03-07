package no.ntnu.oyvinric.tutorialgame.level1.component;

import tile.CharacterTile;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;
import no.ntnu.oyvinric.tutorialgame.gui.GameBoard;
import no.ntnu.oyvinric.tutorialgame.gui.UserInterface;

public class Component extends Block {
	
	TutorialGame game;
	CharacterTile malcolm;
	
	public Component() {
		game = new TutorialGame(1);
		new LwjglApplication(game, "Level 1", GameBoard.windowWidth+UserInterface.windowWidth, GameBoard.windowHeight, false);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		malcolm = game.getMalcolm();
	}

	public void moveForward() {
		game.moveCharacterForward(malcolm);
	}

	public void stop() {
		game.stopCharacterMovement(malcolm);
	}
	
	public void pickUp() {
		game.pickUp(malcolm);
	}

}
