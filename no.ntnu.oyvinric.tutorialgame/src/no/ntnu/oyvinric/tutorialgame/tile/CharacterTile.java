package no.ntnu.oyvinric.tutorialgame.tile;

import no.ntnu.oyvinric.tutorialgame.core.Constants;
import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.level.GridPosition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CharacterTile extends Tile {
	
	final float horizontalAnimationDelay = 0.25f;
	final float verticalAnimationDelay = 0.375f;
	
	private Constants.CharacterName name;
	private boolean moving = false;
	private float stateTime = 0;
	private TextureAtlas animationTextures;
	private TextureRegion[] walkNorthFrames;
	private TextureRegion[] walkSouthFrames;
	private TextureRegion[] walkEastFrames;
	private TextureRegion[] walkWestFrames;
	private Animation walkNorthAnimation;
	private Animation walkSouthAnimation;
	private Animation walkEastAnimation;
	private Animation walkWestAnimation;
	
	public CharacterTile(GridPosition gridPosition, String type, Constants.CharacterName name) {
		super(gridPosition, type);
		this.name = name;
		loadAnimation(name);
	}
	
	public CharacterTile(GridPosition gridPosition, String type, Constants.CharacterName name, float horizontalAdjustment, float verticalAdjustment) {
		super(gridPosition, type, horizontalAdjustment, verticalAdjustment);
		this.name = name;
		loadAnimation(name);
	}
	
	private void loadAnimation(Constants.CharacterName name) {
		animationTextures = new TextureAtlas(Gdx.files.internal(Constants.GFX_PATH+name.getValue()+".atlas"));
		
		walkNorthFrames = new TextureRegion[2];
		walkNorthFrames[0] = animationTextures.findRegion("north1");
		walkNorthFrames[1] = animationTextures.findRegion("north2");
		walkNorthAnimation = new Animation(verticalAnimationDelay, walkNorthFrames);
		
		walkSouthFrames = new TextureRegion[2];
		walkSouthFrames[0] = animationTextures.findRegion("south1");
		walkSouthFrames[1] = animationTextures.findRegion("south2");
		walkSouthAnimation = new Animation(verticalAnimationDelay, walkSouthFrames);
		
		walkWestFrames = new TextureRegion[2];
		walkWestFrames[0] = animationTextures.findRegion("west1");
		walkWestFrames[1] = animationTextures.findRegion("west2");
		walkWestAnimation = new Animation(horizontalAnimationDelay, walkWestFrames);
		
		walkEastFrames = new TextureRegion[2];
		walkEastFrames[0] = animationTextures.findRegion("east1");
		walkEastFrames[1] = animationTextures.findRegion("east2");
		walkEastAnimation = new Animation(horizontalAnimationDelay, walkEastFrames);
		
		image = walkEastFrames[1];
	}
	
	public void rotate(float angle) {
		rotation = (rotation+angle)%360;
		if (rotation == 0) {
			setDirection(Constants.Direction.EAST);
		}
		else if (rotation == 90 || rotation == -270) {
			setDirection(Constants.Direction.NORTH);
		}
		else if (rotation == 180 || rotation == -180) {
			setDirection(Constants.Direction.WEST);
		}
		else if (rotation == 270 || rotation == -90) {
			setDirection(Constants.Direction.SOUTH);
		}
	}
	
	public void setDirection(Constants.Direction direction) {
		this.direction = direction;
		if (direction == Constants.Direction.EAST) {
			image = walkEastFrames[1];
			rotation = 0;
		}
		else if (direction == Constants.Direction.NORTH) {
			image = walkNorthFrames[1];
			rotation = 90;
		}
		else if (direction == Constants.Direction.WEST) {
			image = walkWestFrames[1];
			rotation = 180;
		}
		else if (direction == Constants.Direction.SOUTH) {
			image = walkSouthFrames[1];
			rotation = 270;
		}
	}
	
	public void move(float dx, float dy) {
		coordsX += dx;
		coordsY += dy;
		updateGridPosition();
	}
	
	public void alignWithGrid() {
		coordsX = Constants.gameBoardHorizontalLeftLimit+(Math.round((coordsX-Constants.gameBoardHorizontalLeftLimit) / Constants.tileWidth)*Constants.tileWidth);
		coordsY = Constants.gameBoardVerticalUpperLimit-(Math.round((Constants.gameBoardVerticalUpperLimit-coordsY) / Constants.tileHeight)*Constants.tileHeight);
	}
	
	public void updateGridPosition() {
		gridPosition.setX(Math.round((coordsX-Constants.gameBoardHorizontalLeftLimit) / Constants.tileWidth));
		gridPosition.setY(Math.round((Constants.gameBoardVerticalUpperLimit-coordsY) / Constants.tileHeight));
	}
	
	public float getRotation() {
		return 0;
	}
	
	public Constants.CharacterName getName() {
		return name;
	}
	
	public TextureRegion getImage() {
		stateTime += Gdx.graphics.getDeltaTime();
		if (direction == Constants.Direction.EAST && moving) {
			return walkEastAnimation.getKeyFrame(stateTime, true);
		}
		else if (direction == Constants.Direction.NORTH && moving) {
			return walkNorthAnimation.getKeyFrame(stateTime, true);
		}
		else if (direction == Constants.Direction.SOUTH && moving) {
			return walkSouthAnimation.getKeyFrame(stateTime, true);
		}
		else if (direction == Constants.Direction.WEST && moving) {
			return walkWestAnimation.getKeyFrame(stateTime, true);
		}
		else {
			return image;
		}
	}
	
	public boolean getMoving() {
		return moving;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	@Override
	public GameObject interact() {
		System.out.println("Can't interact with another character!");
		return null;
	}
	
}




