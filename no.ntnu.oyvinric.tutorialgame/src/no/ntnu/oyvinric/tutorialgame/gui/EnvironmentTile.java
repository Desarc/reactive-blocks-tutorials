package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnvironmentTile extends Tile {
	
	public EnvironmentTile(int x, int y, int z) {
		super(x, y, z);
		// TODO Auto-generated constructor stub
	}

	private boolean obstacle;
	
//	public EnvironmentTile(float x, float y, TextureRegion image) {
//		super(x, y, image);
//	}
//	
//	public EnvironmentTile(float x, float y, TextureRegion image, float scaleFactor) {
//		super(x, y, image, scaleFactor);
//	}
//	
//	public EnvironmentTile(float x, float y, TextureRegion image, int horizontalAdjustment, int verticalAdjustment) {
//		super(x, y, image, horizontalAdjustment, verticalAdjustment);
//	}
	
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
	
	public boolean isObstacle() {
		return obstacle;
	}
	
}