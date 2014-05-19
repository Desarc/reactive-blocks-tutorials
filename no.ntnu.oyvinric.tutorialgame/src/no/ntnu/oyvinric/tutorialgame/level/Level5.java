package no.ntnu.oyvinric.tutorialgame.level;

import no.ntnu.oyvinric.tutorialgame.core.Constants.KeyColor;
import no.ntnu.oyvinric.tutorialgame.core.Constants.WinCondition;
import no.ntnu.oyvinric.tutorialgame.hud.UserInterfaceConfiguration;

import com.badlogic.gdx.utils.Array;

public class Level5 extends GameLevel {

	@Override
	public int getLevelNumber() {
		return 5;
	}

	@Override
	public UserInterfaceConfiguration getUserInterfaceConfiguration() {
		UserInterfaceConfiguration configuration = new UserInterfaceConfiguration();
		configuration.blueKeyActive = true;
		configuration.yellowKeyActive = true;
		configuration.redKeyActive = true;
		configuration.greenKeyActive = true;
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
		int rand = (int)(Math.random()*4);
		if (rand == 0) return KeyColor.BLUE;
		else if (rand == 1) return KeyColor.GREEN;
		else if (rand == 2) return KeyColor.RED;
		else return KeyColor.YELLOW;
	}

	@Override
	public void leverPulled(GridPosition position, boolean isActive) {
		// TODO Auto-generated method stub
		
	}

}
