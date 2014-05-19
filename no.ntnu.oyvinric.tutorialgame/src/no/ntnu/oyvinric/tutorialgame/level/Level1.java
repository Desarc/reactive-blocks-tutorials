package no.ntnu.oyvinric.tutorialgame.level;

import com.badlogic.gdx.utils.Array;

import no.ntnu.oyvinric.tutorialgame.hud.UserInterfaceConfiguration;
import no.ntnu.oyvinric.tutorialgame.core.Constants.KeyColor;
import no.ntnu.oyvinric.tutorialgame.core.Constants.WinCondition;


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

	@Override
	public Array<WinCondition> getWinConditions() {
		Array<WinCondition> conditions = new Array<WinCondition>();
		conditions.add(WinCondition.SPEAK);
		return conditions;
	}

	@Override
	public void leverPulled(GridPosition position, boolean isActive) {
		// TODO Auto-generated method stub
		
	}

}
