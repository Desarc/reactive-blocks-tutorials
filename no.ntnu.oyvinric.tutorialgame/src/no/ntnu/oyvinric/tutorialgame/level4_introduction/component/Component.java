package no.ntnu.oyvinric.tutorialgame.level4_introduction.component;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.oyvinric.tutorialgame.core.Constants;
import no.ntnu.oyvinric.tutorialgame.intro.Introduction;

public class Component extends Block {

	Introduction intro;
	
	public Component() {
		intro = new Introduction(4);
		new LwjglApplication(intro, "Level 4 Intro", Constants.introductionWindowWidth, Constants.introductionWindowHeight, false);
	}

	public void playIntroduction() {
	}
}