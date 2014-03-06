package no.ntnu.oyvinric.tutorialgame.gui;

import level.GameLevel.GridPosition;

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
	protected float coordsX;
	protected float coordsY;
	protected GridPosition gridPosition;
	private float scaleFactor = 1f;
	protected Direction direction = Direction.EAST;
	
	public Tile(GridPosition gridPosition) {
		this.gridPosition = gridPosition;
		updateCoordinates();
	}
	
	public Tile(GridPosition gridPosition, int horizontalAdjustment, int verticalAdjustment) {
		this.gridPosition = gridPosition;
		this.horizontalAdjustment = horizontalAdjustment;
		this.verticalAdjustment = verticalAdjustment;
		updateCoordinates();
	}
	
	public Tile(GridPosition gridPosition, TextureRegion image) {
		this.gridPosition = gridPosition;
		this.image = image;
		this.width = image.getRegionWidth();
		this.height = image.getRegionHeight();
		updateCoordinates();
	}
	
	public Tile(GridPosition gridPosition, TextureRegion image, float scaleFactor) {
		this.gridPosition = gridPosition;
		this.image = image;
		this.width = image.getRegionWidth();
		this.height = image.getRegionHeight();
		this.scaleFactor = scaleFactor;
		updateCoordinates();
	}
	
	public Tile(GridPosition gridPosition, TextureRegion image, int horizontalAdjustment, int verticalAdjustment) {
		this.gridPosition = gridPosition;
		this.image = image;
		this.width = image.getRegionWidth();
		this.height = image.getRegionHeight();
		this.horizontalAdjustment = horizontalAdjustment;
		this.verticalAdjustment = verticalAdjustment;
		updateCoordinates();
	}
	
	public Tile(GridPosition gridPosition, TextureRegion image, int horizontalAdjustment, int verticalAdjustment, float scaleFactor) {
		this.gridPosition = gridPosition;
		this.image = image;
		this.width = image.getRegionWidth();
		this.height = image.getRegionHeight();
		this.horizontalAdjustment = horizontalAdjustment;
		this.verticalAdjustment = verticalAdjustment;
		this.scaleFactor = scaleFactor;
		updateCoordinates();
	}
	
	public void setGridPosition(GridPosition gridPosition) {
		this.gridPosition = gridPosition;
		updateCoordinates();
	}
	
	protected void updateCoordinates() {
		coordsX = GameBoard.horizontalLeftLimit+gridPosition.getX()*GameBoard.tileWidth;
		coordsY = GameBoard.verticalUpperLimit-gridPosition.getY()*GameBoard.tileHeight-gridPosition.getZ();
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
		return coordsX + horizontalAdjustment;
	}
	
	public float getY() {
		return coordsY + verticalAdjustment;
	}
	
	public GridPosition getGridPosition() {
		return gridPosition;
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
