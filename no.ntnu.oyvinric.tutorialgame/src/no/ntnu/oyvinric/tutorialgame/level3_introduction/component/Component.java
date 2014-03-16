package no.ntnu.oyvinric.tutorialgame.level3_introduction.component;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.oyvinric.tutorialgame.core.Constants;
import no.ntnu.oyvinric.tutorialgame.intro.Introduction;

public class Component extends Block {

	Introduction intro;
	
	public Component() {
		intro = new Introduction(3);
		new LwjglApplication(intro, "Level 3 Intro", Constants.introductionWindowWidth, Constants.introductionWindowHeight, false);
	}

	public void playIntroduction() {
	}
}