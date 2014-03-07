package level;

import items.Key.KeyColor;

public class Level3 extends GameLevel {

	@Override
	public int getLevelNumber() {
		return 3;
	}

	@Override
	protected KeyColor determineKeyColor(GridPosition position) {
		return null;
	}

}
