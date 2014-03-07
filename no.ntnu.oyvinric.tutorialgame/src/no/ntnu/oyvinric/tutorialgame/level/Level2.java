package no.ntnu.oyvinric.tutorialgame.level;

import no.ntnu.oyvinric.tutorialgame.gui.UserInterfaceConfiguration;
import no.ntnu.oyvinric.tutorialgame.item.Key.KeyColor;

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
		// TODO Auto-generated method stub
		return null;
	}

}
