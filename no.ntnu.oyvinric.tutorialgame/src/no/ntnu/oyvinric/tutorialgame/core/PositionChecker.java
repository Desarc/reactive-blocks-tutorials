package no.ntnu.oyvinric.tutorialgame.core;

import no.ntnu.oyvinric.tutorialgame.level.GameLevel;
import no.ntnu.oyvinric.tutorialgame.level.GridPosition;
import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile;
import no.ntnu.oyvinric.tutorialgame.tile.Tile;

public class PositionChecker extends Thread {
	
	private TutorialGameApplication game;
	private GameLevel level;
	private CharacterTile malcolm;
	
	public PositionChecker(TutorialGameApplication game, GameLevel level) {
		this.game = game;
		this.level = level;
		malcolm = level.getMalcolm();
	}
	
	public void run() {
		while (true) {
			GridPosition position = malcolm.getGridPosition();
			Tile currentTile = level.getTile(position);
			if (currentTile.getType().equals(Constants.SELECTOR)) {
				game.removeAllKeys(malcolm);
			}
			try {
				Thread.sleep(250);
			} catch(InterruptedException ie) {
				
			}
		}
	}

}
