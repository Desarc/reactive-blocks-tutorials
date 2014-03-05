package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Tile {
	
	protected Rectangle rectangle;
	protected TextureRegion image;
	private float horizontalAdjustment = 0;
	private float verticalAdjustment = 0;
	protected float rotation = 0;
	private float originX = 0;
	private float originY = 0;
	protected Direction direction = Direction.EAST;
	
	public Tile(float x, float y, TextureRegion image) {
		rectangle = new Rectangle();
		rectangle.width = GameBoard.tileWidth;
		rectangle.height = GameBoard.tileHeight;
		rectangle.x = x;
		rectangle.y = y;
		this.image = image;
	}
	
	public Tile(float x, float y, TextureRegion image, int horizontalAdjustment, int verticalAdjustment) {
		rectangle = new Rectangle();
		rectangle.width = GameBoard.tileWidth;
		rectangle.height = GameBoard.tileHeight;
		rectangle.x = x;
		rectangle.y = y;
		this.image = image;
		this.horizontalAdjustment = horizontalAdjustment;
		this.verticalAdjustment = verticalAdjustment;
	}
	
	public void setPosition(float x, float y) {
		rectangle.x = x;
		rectangle.y = y;
	}
	
	public void move(float dx, float dy) {
		rectangle.x += dx;
		rectangle.y += dy;
	}
	
	public void alignWithGrid() {
		rectangle.x = Math.round(rectangle.x / GameBoard.tileWidth)*GameBoard.tileWidth;
		rectangle.y = Math.round(rectangle.y / GameBoard.tileHeight)*GameBoard.tileHeight;
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
		return rectangle.x + horizontalAdjustment;
	}
	
	public float getY() {
		return rectangle.y + verticalAdjustment;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public float getWidth() {
		return rectangle.width;
	}
	
	public float getHeight() {
		return rectangle.height;
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
	
	public static enum Direction {
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
}
