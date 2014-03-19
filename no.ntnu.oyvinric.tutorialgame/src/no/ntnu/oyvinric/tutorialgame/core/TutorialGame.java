package no.ntnu.oyvinric.tutorialgame.core;

import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class TutorialGame {
	
	private TutorialGameApplication game;
	private CharacterTile malcolm;

	public static void main(String[] args) {
		TutorialGameApplication game = new TutorialGameApplication(1);
		new LwjglApplication(game, "Level 1", Constants.mainWindowWidth, Constants.mainWindowHeight, false);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		game.characterSpeak(game.getMalcolm(), "Hello World!");
	}
	
	public TutorialGameApplication start(int levelNumber) {
		game = new TutorialGameApplication(levelNumber);
		new LwjglApplication(game, "Level 1", Constants.mainWindowWidth, Constants.mainWindowHeight, false);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		malcolm = game.getMalcolm();
		return game;
	}
	
	public CharacterTile getMalcolm() {
		return malcolm;
	}
	
	public void characterSpeak(CharacterTile character, String message) {
		game.characterSpeak(character, message);
	}
	
}
