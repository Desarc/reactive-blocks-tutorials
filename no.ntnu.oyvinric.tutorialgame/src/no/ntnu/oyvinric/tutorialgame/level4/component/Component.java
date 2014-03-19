package no.ntnu.oyvinric.tutorialgame.level4.component;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.oyvinric.tutorialgame.core.Constants;
import no.ntnu.oyvinric.tutorialgame.core.TutorialGameApplication;
import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.item.Key;
import no.ntnu.oyvinric.tutorialgame.core.Constants.ItemType;
import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile;

public class Component extends Block {

	TutorialGameApplication game;
	CharacterTile malcolm;
	
	public Component() {
		game = new TutorialGameApplication(4);
		new LwjglApplication(game, "Level 4", Constants.mainWindowWidth, Constants.mainWindowHeight, false);
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
	
	public void turnLeft() {
		game.turnCharacterLeft(malcolm);
	}
	
	public void turnRight() {
		game.turnCharacterRight(malcolm);
	}
	
	public void turnAround() {
		game.turnCharacterAround(malcolm);
	}
	
	public String interact() {
		GameObject item = game.characterInteract(malcolm);
		if (item != null) {
			if (item.getType() == ItemType.KEY) {
				return ((Key)item).getColor().value();
			}
		}
		return "";
	}
	
	public void pickUp() {
		game.pickUp(malcolm);
	}
}