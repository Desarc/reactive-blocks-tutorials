package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Tile {
	
	protected TextureRegion image;
	private float width = 0f;
	private float height = 0f;
	private float horizontalAdjustment = 0f;
	private float verticalAdjustment = 0f;
	protected float rotation = 0f;
	private float originX = 0f;
	private float originY = 0f;
	protected float positionX;
	protected float positionY;
	protected int layer;
	private float scaleFactor = 1f;
	protected Direction direction = Direction.EAST;
	
	public Tile() {
		positionX = 0f;
		positionY = 0f;
		layer = 0;
	}
	
	public Tile(float x, float y, int layer) {
		positionX = x;
		positionY = y;
		this.layer = layer;
	}
	
	public Tile(float x, float y, int layer, int horizontalAdjustment, int verticalAdjustment) {
		positionX = x;
		positionY = y;
		this.layer = layer;
		this.horizontalAdjustment = horizontalAdjustment;
		this.verticalAdjustment = verticalAdjustment;
	}
	
	public Tile(TextureRegion image) {
		positionX = 0f;
		positionY = 0f;
		this.layer = 0;
		this.image = image;
		this.width = image.getRegionWidth();
		this.height = image.getRegionHeight();
	}
	
	public Tile(int x, int y, int layer, TextureRegion image) {
		positionX = x;
		positionY = y;
		this.layer = layer;
		this.image = image;
		this.width = image.getRegionWidth();
		this.height = image.getRegionHeight();
	}
	
	public Tile(int x, int y, int layer, TextureRegion image, float scaleFactor) {
		positionX = x;
		positionY = y;
		this.layer = layer;
		this.image = image;
		this.width = image.getRegionWidth();
		this.height = image.getRegionHeight();
		this.scaleFactor = scaleFactor;
	}
	
	public Tile(int x, int y, int layer, TextureRegion image, int horizontalAdjustment, int verticalAdjustment) {
		positionX = x;
		positionY = y;
		this.layer = layer;
		this.image = image;
		this.width = image.getRegionWidth();
		this.height = image.getRegionHeight();
		this.horizontalAdjustment = horizontalAdjustment;
		this.verticalAdjustment = verticalAdjustment;
	}
	
	public Tile(int x, int y, int layer, TextureRegion image, int horizontalAdjustment, int verticalAdjustment, float scaleFactor) {
		positionX = x;
		positionY = y;
		this.layer = layer;
		this.image = image;
		this.width = image.getRegionWidth();
		this.height = image.getRegionHeight();
		this.horizontalAdjustment = horizontalAdjustment;
		this.verticalAdjustment = verticalAdjustment;
		this.scaleFactor = scaleFactor;
	}
	
	public void setPosition(float x, float y, int layer) {
		positionX = x;
		positionY = y;
		this.layer = layer;
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
	
	public int getLayer() {
		return layer;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
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
