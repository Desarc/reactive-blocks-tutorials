package no.ntnu.oyvinric.tutorialgame.level;

import no.ntnu.oyvinric.tutorialgame.gui.UserInterfaceConfiguration;
import no.ntnu.oyvinric.tutorialgame.core.Constants.KeyColor;

public class Level3 extends GameLevel {

	@Override
	public int getLevelNumber() {
		return 3;
	}

	@Override
	protected KeyColor determineKeyColor(GridPosition position) {
		return null;
	}

	@Override
	public UserInterfaceConfiguration getUserInterfaceConfiguration() {
		UserInterfaceConfiguration configuration = new UserInterfaceConfiguration();
		configuration.starActive = true;
		configuration.starCount = 4;
		return configuration;
	}

}
