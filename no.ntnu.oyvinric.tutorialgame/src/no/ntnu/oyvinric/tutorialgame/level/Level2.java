package no.ntnu.oyvinric.tutorialgame.level;

import com.badlogic.gdx.utils.Array;

import no.ntnu.oyvinric.tutorialgame.hud.UserInterfaceConfiguration;
import no.ntnu.oyvinric.tutorialgame.core.Constants.KeyColor;
import no.ntnu.oyvinric.tutorialgame.core.Constants.WinCondition;

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
	
	@Override
	public Array<WinCondition> getWinConditions() {
		Array<WinCondition> conditions = new Array<WinCondition>();
		conditions.add(WinCondition.STARS);
		return conditions;
	}

	@Override
	public void leverPulled(GridPosition position, boolean isActive) {
		// TODO Auto-generated method stub
		
	}

}
