package no.ntnu.oyvinric.tutorialgame.level;

import no.ntnu.oyvinric.tutorialgame.gui.UserInterfaceConfiguration;
import no.ntnu.oyvinric.tutorialgame.item.Key.KeyColor;


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
		configuration.starActive = true;
		return configuration;
	}

}
