package no.ntnu.oyvinric.tutorialgame.level1.component;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;

public class Component extends Block {
	
	TutorialGame game;
	
	public Component() {
		game = new TutorialGame();
		new LwjglApplication(game, "Level 1", 800, 480, false);
	}

	public void moveForward() {
		game.movePlayerForward();
	}

	public void stop() {
		game.stopPlayerMovement();
	}

}
