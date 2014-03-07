package level;

import items.Key.KeyColor;


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

}
