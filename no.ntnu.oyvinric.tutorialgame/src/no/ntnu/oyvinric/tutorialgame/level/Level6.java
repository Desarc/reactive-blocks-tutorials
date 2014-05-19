package no.ntnu.oyvinric.tutorialgame.level;

import no.ntnu.oyvinric.tutorialgame.core.Constants.KeyColor;
import no.ntnu.oyvinric.tutorialgame.core.Constants.WinCondition;
import no.ntnu.oyvinric.tutorialgame.hud.UserInterfaceConfiguration;

import com.badlogic.gdx.utils.Array;

public class Level6 extends GameLevel {

	private int keyCounter = 0;
	
	@Override
	public int getLevelNumber() {
		return 6;
	}

	@Override
	public UserInterfaceConfiguration getUserInterfaceConfiguration() {
		UserInterfaceConfiguration configuration = new UserInterfaceConfiguration();
		configuration.blueKeyActive = true;
		configuration.yellowKeyActive = true;
		configuration.redKeyActive = true;
		configuration.greenKeyActive = true;
		configuration.blackKeyActive = true;
		configuration.whiteKeyActive = true;
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
		int rand = (int)(Math.random()*2);
		keyCounter++;
		if (keyCounter == 1) {
			if (rand == 0) return KeyColor.BLUE;
			else return KeyColor.GREEN;
		}
		else if (keyCounter == 2) {
			if (rand == 0) return KeyColor.RED;
			else return KeyColor.YELLOW;
		}
		else {
			if (rand == 0) return KeyColor.BLACK;
			else return KeyColor.WHITE;
		}
	}

	@Override
	public void leverPulled(GridPosition position, boolean isActive) {
		// TODO Auto-generated method stub
		
	}

}
