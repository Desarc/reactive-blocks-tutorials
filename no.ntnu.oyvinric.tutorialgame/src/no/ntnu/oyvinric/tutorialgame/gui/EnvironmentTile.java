package no.ntnu.oyvinric.tutorialgame.gui;

import level.GameLevel.GridPosition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnvironmentTile extends Tile {
		
	public EnvironmentTile(GridPosition gridPosition, String type, TextureRegion image) {
		super(gridPosition, type, image);
		obstacle = true;
	}
	
	public EnvironmentTile(GridPosition gridPosition, String type) {
		super(gridPosition, type);
		if (!type.equals(EMPTY)) {
			obstacle = true;			
		}
	}
	
	public enum EnvironmentType {
		GRASS("grass"),
		WALL("wall-block"),
		WALL_TALL("wall-block-tall"),
		BROWN_BLOCK("brown-block"),
		DIRT_BLOCK("dirt-block"),
		PLAIN_BLOCK("plain-block"),
		RAMP_EAST("ramp-east");
		
		private final String type;
		
		EnvironmentType(String type) {
			this.type = type;
		}
		
		public String getValue() {
			return type;
		}
	}
	
}
