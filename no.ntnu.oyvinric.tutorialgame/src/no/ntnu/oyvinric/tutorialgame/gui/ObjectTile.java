package no.ntnu.oyvinric.tutorialgame.gui;

import level.GameLevel.GridPosition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ObjectTile extends Tile {
	
	private boolean movable;

	public ObjectTile(GridPosition gridPosition, String type, TextureRegion image) {
		super(gridPosition, type, image);
	}


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
