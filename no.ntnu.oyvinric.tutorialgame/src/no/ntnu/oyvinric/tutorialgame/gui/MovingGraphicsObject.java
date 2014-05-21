package no.ntnu.oyvinric.tutorialgame.gui;

import no.ntnu.oyvinric.tutorialgame.core.Constants.Direction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MovingGraphicsObject extends GraphicsObject {
	
	private float endX, endY;
	private float speedX, speedY;
	private Direction direction;
	private boolean remove = false;

	public MovingGraphicsObject(float startX, float startY, float endX, float endY, float speedX, float speedY, TextureRegion image) {
		super(startX, startY, image);
		this.endX = endX;
		this.endY = endY;
		if (startX < endX) {
			direction = Direction.WEST;
			this.speedX = speedX;
		}
		else if (startX > endX) {
			direction = Direction.EAST;
			this.speedX = -speedX;
		}
		else if (startY < endY) {
			direction = Direction.NORTH;
			this.speedY = speedY;
		}
		else {
			direction = Direction.SOUTH;
			this.speedY = -speedY;
		}
	}
	
	public MovingGraphicsObject(float startX, float startY, float endX, float endY, float speedX, float speedY, TextureRegion image, boolean remove) {
		this(startX, startY, endX, endY, speedX, speedY, image);
		this.remove = remove;
	}
	
	@Override
	public TextureRegion getImage() {
		
		if (direction == Direction.WEST && coordsX >= endX) {
			if (remove) active = false;
			else speedX = 0;
		}
		else if (direction == Direction.EAST && coordsX <= endX) {
			if (remove) active = false;
			else speedX = 0;
		}
		else if (direction == Direction.SOUTH && coordsY <= endY) {
			if (remove) active = false;
			else speedY = 0;
		}
		else if (direction == Direction.NORTH && coordsY >= endY) {
			if (remove) active = false;
			else speedY = 0;
		}
		else {
			coordsX += Gdx.graphics.getDeltaTime()*speedX;
			coordsY += Gdx.graphics.getDeltaTime()*speedY;
		}
		return image;
	}

}
