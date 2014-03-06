package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Tile {
	
	protected TextureRegion image;
	private float horizontalAdjustment = 0f;
	private float verticalAdjustment = 0f;
	protected float rotation = 0f;
	private float originX = 0f;
	private float originY = 0f;
	protected float positionX;
	protected float positionY;
	protected float positionZ;
	private float scaleFactor = 1f;
	protected Direction direction = Direction.EAST;
	
	public Tile(float x, float y, float z) {
		positionX = x;
		positionY = y;
		positionZ = z;
	}
	
	public Tile(float x, float y, float z, int horizontalAdjustment, int verticalAdjustment) {
		positionX = x;
		positionY = y;
		positionZ = z;
		this.horizontalAdjustment = horizontalAdjustment;
		this.verticalAdjustment = verticalAdjustment;
	}
	
	public Tile(int x, int y, int z, TextureRegion image) {
		positionX = x;
		positionY = y;
		positionZ = z;
		this.image = image;
	}
	
	public Tile(int x, int y, int z, TextureRegion image, float scaleFactor) {
		positionX = x;
		positionY = y;
		positionZ = z;
		this.image = image;
		this.scaleFactor = scaleFactor;
	}
	
	public Tile(int x, int y, int z, TextureRegion image, int horizontalAdjustment, int verticalAdjustment) {
		positionX = x;
		positionY = y;
		positionZ = z;
		this.image = image;
		this.horizontalAdjustment = horizontalAdjustment;
		this.verticalAdjustment = verticalAdjustment;
	}
	
	public Tile(int x, int y, int z, TextureRegion image, int horizontalAdjustment, int verticalAdjustment, float scaleFactor) {
		positionX = x;
		positionY = y;
		positionZ = z;
		this.image = image;
		this.horizontalAdjustment = horizontalAdjustment;
		this.verticalAdjustment = verticalAdjustment;
		this.scaleFactor = scaleFactor;
	}
	
	public void setPosition(int x, int y, int z) {
		positionX = x;
		positionY = y;
		positionZ = z;
	}
	
	public void rotate(float angle) {
		rotation = (rotation+angle)%360;
		if (rotation == 0) {
			originX = 0;
			originY = 0;
			direction = Direction.EAST;
		}
		else if (rotation == 90 || rotation == -270) {
			originX = GameBoard.tileWidth/2;
			originY = 0;
			direction = Direction.NORTH;
		}
		else if (rotation == 180 || rotation == -180) {
			originX = GameBoard.tileWidth/2;
			originY = 0;
			direction = Direction.WEST;
		}
		else if (rotation == 270 || rotation == -90) {
			originX = GameBoard.tileWidth/2;
			originY = 0;
			direction = Direction.SOUTH;
		}
	}
	
	public TextureRegion getImage() {
		return image;
	}
	
	public float getX() {
		return positionX + horizontalAdjustment;
	}
	
	public float getY() {
		return positionY + verticalAdjustment;
	}
	
	public float getZ() {
		return positionZ;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public float getWidth() {
		return image.getRegionWidth();
	}
	
	public float getHeight() {
		return image.getRegionHeight();
	}
	
	public float getOriginX() {
		return originX + horizontalAdjustment;
	}
	
	public float getOriginY() {
		return originY + verticalAdjustment;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public float getScaleFactor() {
		return scaleFactor;
	}
	
	public static enum Direction {
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
}
