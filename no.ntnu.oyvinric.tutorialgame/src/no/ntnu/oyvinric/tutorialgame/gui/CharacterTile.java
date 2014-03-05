package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CharacterTile extends Tile {
	
	final float horizontalAnimationDelay = 0.25f;
	final float verticalAnimationDelay = 0.375f;
	
	private CharacterName name;
	private boolean moving = false;
	private float stateTime = 0;
	TextureAtlas animationTextures;
	TextureRegion[] walkNorthFrames;
	TextureRegion[] walkSouthFrames;
	TextureRegion[] walkEastFrames;
	TextureRegion[] walkWestFrames;
	Animation walkNorthAnimation;
	Animation walkSouthAnimation;
	Animation walkEastAnimation;
	Animation walkWestAnimation;
	
	public CharacterTile(CharacterName name, float x, float y) {
		super(x, y);
		this.name = name;
		loadAnimation(name);
	}
	
	public CharacterTile(CharacterName name, float x, float y, int horizontalAdjustment, int verticalAdjustment) {
		super(x, y, horizontalAdjustment, verticalAdjustment);
		this.name = name;
		loadAnimation(name);
	}
	
	private void loadAnimation(CharacterName name) {
		animationTextures = new TextureAtlas(Gdx.files.internal("resources/gfx/"+name.getValue()+".atlas"));
		
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
		
		image = walkEastFrames[0];
	}
	
	public void rotate(float angle) {
		rotation = (rotation+angle)%360;
		if (rotation == 0) {
			direction = Direction.EAST;
			image = walkEastFrames[1];
		}
		else if (rotation == 90 || rotation == -270) {
			direction = Direction.NORTH;
			image = walkNorthFrames[1];
		}
		else if (rotation == 180 || rotation == -180) {
			direction = Direction.WEST;
			image = walkWestFrames[1];
		}
		else if (rotation == 270 || rotation == -90) {
			direction = Direction.SOUTH;
			image = walkSouthFrames[1];
		}
	}
	
	public float getRotation() {
		return 0;
	}
	
	public CharacterName getName() {
		return name;
	}
	
	public TextureRegion getImage() {
		stateTime += Gdx.graphics.getDeltaTime();
		if (direction == Direction.EAST && moving) {
			return walkEastAnimation.getKeyFrame(stateTime, true);
		}
		else if (direction == Direction.NORTH && moving) {
			return walkNorthAnimation.getKeyFrame(stateTime, true);
		}
		else if (direction == Direction.SOUTH && moving) {
			return walkSouthAnimation.getKeyFrame(stateTime, true);
		}
		else if (direction == Direction.WEST && moving) {
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
	
	public static enum CharacterName {
		MALCOLM("malcolm"),
		KAYLEE("kaylee"),
		WASH("wash");
		
		private final String name;
		
		CharacterName(String name) {
			this.name = name;
		}
		
		public String getValue() {
			return name;
		}
	}
	
}




