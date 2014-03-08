package no.ntnu.oyvinric.tutorialgame.level;

import no.ntnu.oyvinric.tutorialgame.gui.UserInterfaceConfiguration;
import no.ntnu.oyvinric.tutorialgame.item.Key.KeyColor;

public class Level3 extends GameLevel {

	@Override
	public int getLevelNumber() {
		return 3;
	}

	@Override
	protected KeyColor determineKeyColor(GridPosition position) {
		int rand = (int)(Math.random()*2);
		if (rand == 0) return KeyColor.BLUE;
		else return KeyColor.YELLOW;
	}

	@Override
	public UserInterfaceConfiguration getUserInterfaceConfiguration() {
		UserInterfaceConfiguration configuration = new UserInterfaceConfiguration();
		configuration.blueKeyActive = true;
		configuration.yellowKeyActive = true;
		configuration.starActive = true;
		configuration.starCount = 1;
		return configuration;
	}

}
