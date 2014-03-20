package no.ntnu.oyvinric.tutorialgame.level;

import no.ntnu.oyvinric.tutorialgame.hud.UserInterfaceConfiguration;
import no.ntnu.oyvinric.tutorialgame.core.Constants.KeyColor;


/**
 * Level-specific logic
 * 
 * @author Desarc
 *
 */
public class Level1 extends GameLevel {

	@Override
	public int getLevelNumber() {
		return 1;
	}

	@Override
	protected KeyColor determineKeyColor(GridPosition position) {
		return null;
	}

	@Override
	public UserInterfaceConfiguration getUserInterfaceConfiguration() {
		UserInterfaceConfiguration configuration = new UserInterfaceConfiguration();
		configuration.starActive = false;
		return configuration;
	}

}
