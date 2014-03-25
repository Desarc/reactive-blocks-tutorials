package no.ntnu.oyvinric.tutorialgame.intro;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import no.ntnu.oyvinric.tutorialgame.core.Constants;

public class LevelIntroduction {
	
	private Introduction introduction;

	public static void main(String[] args) {
		Introduction introduction = new Introduction(4);
		new LwjglApplication(introduction, "Level 1 Intro", Constants.introductionWindowWidth, Constants.introductionWindowHeight, false);
	}
	
	public void show(int levelNumber) {
		introduction = new Introduction(levelNumber);
		new LwjglApplication(introduction, "Level "+levelNumber+" Intro", Constants.introductionWindowWidth, Constants.introductionWindowHeight, false);
	}

}
