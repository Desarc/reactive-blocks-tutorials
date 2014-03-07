package no.ntnu.oyvinric.tutorialgame.level1.component;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;
import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile;

public class Component extends Block {
	
	TutorialGame game;
	CharacterTile malcolm;
	
	public Component() {
		game = new TutorialGame(1);
		new LwjglApplication(game, "Level 1", TutorialGame.windowWidth, TutorialGame.windowHeight, false);
		try {
			Thread.sleep(2000);
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
	
	public void doNothing() {
		
	}

}
