package no.ntnu.oyvinric.tutorialgame.core;

import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class TutorialGame {
	
	private TutorialGameApplication game;
	private CharacterTile malcolm;
	private CharacterTile lisa;

	public static void main(String[] args) {
		TutorialGameApplication game = new TutorialGameApplication(7);
		new LwjglApplication(game, "Level 1", Constants.mainWindowWidth, Constants.mainWindowHeight, false);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		game.moveCharacterForward(game.getMalcolm());
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//game.stopCharacterMovement(game.getMalcolm());
		//game.pickUp(game.getMalcolm());
		//game.pickUp(game.getMalcolm());
		//game.pickUp(game.getMalcolm());
		//game.characterSpeak(game.getMalcolm(), "Hello World!");
	}
	
	public TutorialGameApplication start(int levelNumber) {
		game = new TutorialGameApplication(levelNumber);
		new LwjglApplication(game, "Level "+levelNumber, Constants.mainWindowWidth, Constants.mainWindowHeight, false);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		malcolm = game.getMalcolm();
		lisa = game.getLisa();
		return game;
	}
	
	public CharacterTile getMalcolm() {
		return malcolm;
	}
	
	public CharacterTile getLisa() {
		return lisa;
	}
	
	public void characterSpeak(CharacterTile character, String message) {
		game.characterSpeak(character, message);
	}
	
	public void moveCharacterForward(CharacterTile character) {
		game.moveCharacterForward(character);
	}

	public void stopCharacterMovement(CharacterTile character) {
		game.stopCharacterMovement(character);
	}
	
	public void turnCharacterLeft(CharacterTile character) {
		game.turnCharacterLeft(character);
	}
	
	public void turnCharacterRight(CharacterTile character) {
		game.turnCharacterRight(character);
	}
	
	public void turnCharacterAround(CharacterTile character) {
		game.turnCharacterAround(character);
	}
	
	public void characterPickUp(CharacterTile character) {
		game.pickUp(character);
	}
	
	public GameObject characterInteract(CharacterTile character) {
		return game.characterInteract(character);
	}
	
	public TutorialGameApplication getGameApp() {
		return game;
	}
	
}
