package no.ntnu.oyvinric.tutorialgame.level1.component;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;

public class Component extends Block {
	
	TutorialGame game;
	
	public Component() {
		game = new TutorialGame(2);
		new LwjglApplication(game, "Level 1", 800, 480, false);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void moveForward() {
		game.movePlayerForward();
	}
	
	public void stepForward() {
		game.stepForward();
	}

	public void stop() {
		game.stopPlayerMovement();
	}
	
	public void turnLeft() {
		game.turnLeft();
	}
	
	public void turnRight() {
		game.turnRight();
	}
	
	public void turnAround() {
		game.turnAround();
	}
	
	public void pushButton() {
		game.pushButton();
	}

}
