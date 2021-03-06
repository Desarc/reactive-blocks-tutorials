package no.ntnu.oyvinric.tutorialgame.tile;

import no.ntnu.oyvinric.tutorialgame.core.Constants;
import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.level.GridPosition;

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
	protected Constants.Direction direction = Constants.Direction.EAST;
	protected boolean obstacle = false;
	protected String type;
	
	public Tile(GridPosition gridPosition, String type) {
		this.gridPosition = gridPosition;
		this.type = type;
		updateCoordinates();
	}
	
	public Tile(GridPosition gridPosition, String type, float horizontalAdjustment, float verticalAdjustment) {
		this(gridPosition, type);
		this.horizontalAdjustment = horizontalAdjustment;
		this.verticalAdjustment = verticalAdjustment;
		updateCoordinates();
	}
	
	public Tile(GridPosition gridPosition, String type, TextureRegion image) {
		this(gridPosition, type);
		this.image = image;
		this.width = image.getRegionWidth();
		this.height = image.getRegionHeight();
		updateCoordinates();
	}
	
	public Tile(GridPosition gridPosition, String type, TextureRegion image, float scaleFactor) {
		this(gridPosition, type, image);
		this.scaleFactor = scaleFactor;
		updateCoordinates();
	}
	
	public Tile(GridPosition gridPosition, String type, TextureRegion image, float horizontalAdjustment, float verticalAdjustment) {
		this(gridPosition, type, image);
		this.horizontalAdjustment = horizontalAdjustment;
		this.verticalAdjustment = verticalAdjustment;
		updateCoordinates();
	}
	
	public Tile(GridPosition gridPosition, String type, TextureRegion image, float horizontalAdjustment, float verticalAdjustment, float scaleFactor) {
		this(gridPosition, type, image, horizontalAdjustment, verticalAdjustment);
		this.scaleFactor = scaleFactor;
		updateCoordinates();
	}
	
	public abstract GameObject interact();
	
	public void setGridPosition(GridPosition gridPosition) {
		this.gridPosition = gridPosition;
		updateCoordinates();
	}
	
	protected void updateCoordinates() {
		coordsX = Constants.gameBoardHorizontalLeftLimit+gridPosition.getX()*Constants.tileWidth;
		coordsY = Constants.gameBoardVerticalUpperLimit-gridPosition.getY()*Constants.tileHeight-gridPosition.getZ();
	}
	
	public void rotate(float angle) {
		rotation = (rotation+angle)%360;
		if (rotation == 0) {
			originX = 0;
			originY = 0;
			direction = Constants.Direction.EAST;
		}
		else if (rotation == 90 || rotation == -270) {
			originX = Constants.tileWidth/2;
			originY = 0;
			direction = Constants.Direction.NORTH;
		}
		else if (rotation == 180 || rotation == -180) {
			originX = Constants.tileWidth/2;
			originY = 0;
			direction = Constants.Direction.WEST;
		}
		else if (rotation == 270 || rotation == -90) {
			originX = Constants.tileWidth/2;
			originY = 0;
			direction = Constants.Direction.SOUTH;
		}
	}
	
	public TextureRegion getImage() {
		return image;
	}
	
	public float getCoordsX() {
		return coordsX + horizontalAdjustment;
	}
	
	public float getCoordsY() {
		return coordsY + verticalAdjustment;
	}
	
	public int getGridX() {
		return gridPosition.getX();
	}
	
	public int getGridY() {
		return gridPosition.getY();
	}
	
	public int getGridZ() {
		return gridPosition.getZ();
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
	
	public Constants.Direction getDirection() {
		return direction;
	}
	
	public float getScaleFactor() {
		return scaleFactor;
	}
	
	public boolean isObstacle() {
		return obstacle;
	}
	
	public String getType() {
		return type;
	}
}
