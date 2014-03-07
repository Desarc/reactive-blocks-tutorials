package level;

import items.Key.KeyColor;

public class Level2 extends GameLevel {

	@Override
	public int getLevelNumber() {
		return 2;
	}

	@Override
	protected KeyColor determineKeyColor(GridPosition position) {
		return null;
	}

}
