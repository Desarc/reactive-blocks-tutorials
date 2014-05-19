package no.ntnu.oyvinric.tutorialgame.level;

import no.ntnu.oyvinric.tutorialgame.core.Constants.KeyColor;
import no.ntnu.oyvinric.tutorialgame.core.Constants.WinCondition;
import no.ntnu.oyvinric.tutorialgame.hud.UserInterfaceConfiguration;
import no.ntnu.oyvinric.tutorialgame.tile.DoorTile;

import com.badlogic.gdx.utils.Array;

public class Level7 extends GameLevel {

	@Override
	public int getLevelNumber() {
		return 7;
	}

	@Override
	public UserInterfaceConfiguration getUserInterfaceConfiguration() {
		UserInterfaceConfiguration configuration = new UserInterfaceConfiguration();
		configuration.starActive = true;
		configuration.starCount = 1;
		return configuration;
	}

	@Override
	public Array<WinCondition> getWinConditions() {
		Array<WinCondition> conditions = new Array<WinCondition>();
		conditions.add(WinCondition.STARS);
		return conditions;
	}

	@Override
	protected KeyColor determineKeyColor(GridPosition position) {
		return null;
	}

	@Override
	public void leverPulled(GridPosition position, boolean isActive) {
		DoorTile tile = doorTiles.get(0);
		if (isActive) {
			tile.open();
		}
		else {
			tile.close();
		}
		
	}

}
