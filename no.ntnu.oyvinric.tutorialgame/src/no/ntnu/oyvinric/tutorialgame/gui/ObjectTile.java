package no.ntnu.oyvinric.tutorialgame.gui;

import no.ntnu.oyvinric.tutorialgame.core.Level.GridPosition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ObjectTile extends Tile {
	
	public ObjectTile(GridPosition gridPosition) {
		super(gridPosition);
		// TODO Auto-generated constructor stub
	}

	private boolean movable;

//	public ObjectTile(float x, float y, TextureRegion image) {
//		super(x, y, image);
//	}
//	
//	public ObjectTile(float x, float y, TextureRegion image, float scaleFactor) {
//		super(x, y, image, scaleFactor);
//	}
//	
//	public ObjectTile(float x, float y, TextureRegion image, int horizontalAdjustment, int verticalAdjustment) {
//		super(x, y, image, horizontalAdjustment, verticalAdjustment);
//	}
//	
//	public ObjectTile(float x, float y, TextureRegion image, int horizontalAdjustment, int verticalAdjustment, float scaleFactor) {
//		super(x, y, image, horizontalAdjustment, verticalAdjustment, scaleFactor);
//	}

	public enum ObjectType {
		CHEST_CLOSED("chest-closed"),
		CHEST_OPEN("chest-open"),
		DOOR_CLOSED("door-closed"),
		DOOR_OPEN("door-open"),
		KEY("key"),
		STAR("star"),
		SELECTOR("selector");
		
		
		private final String type;
		
		ObjectType(String type) {
			this.type = type;
		}
		
		public String getValue() {
			return type;
		}
	}
	
	public boolean isMovable() {
		return movable;
	}

}
