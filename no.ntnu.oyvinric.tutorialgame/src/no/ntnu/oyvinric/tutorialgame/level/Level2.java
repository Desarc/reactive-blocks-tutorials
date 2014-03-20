package no.ntnu.oyvinric.tutorialgame.level;

import no.ntnu.oyvinric.tutorialgame.hud.UserInterfaceConfiguration;
import no.ntnu.oyvinric.tutorialgame.core.Constants.KeyColor;

public class Level2 extends GameLevel {

	@Override
	public int getLevelNumber() {
		return 2;
	}

	@Override
	protected KeyColor determineKeyColor(GridPosition position) {
		return null;
	}

	@Override
	public UserInterfaceConfiguration getUserInterfaceConfiguration() {
		UserInterfaceConfiguration configuration = new UserInterfaceConfiguration();
		configuration.starActive = true;
		configuration.starCount = 1;
		return configuration;
	}

}
