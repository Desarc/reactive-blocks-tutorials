package no.ntnu.oyvinric.tutorialgame.release.level5_introduction.component;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.oyvinric.tutorialgame.intro.LevelIntroduction;

public class Component extends Block {
	
	LevelIntroduction intro;
	
	public Component() {
		intro = new LevelIntroduction();
	}

	public void playIntroduction() {
		intro.show(5);
	}

}