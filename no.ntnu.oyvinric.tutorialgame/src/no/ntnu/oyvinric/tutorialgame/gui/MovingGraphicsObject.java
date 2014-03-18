package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MovingGraphicsObject extends GraphicsObject {
	
	private float endX, endY;
	private float speedX, speedY;
	private boolean remove = false;

	public MovingGraphicsObject(float startX, float startY, float endX, float endY, float speedX, float speedY, TextureRegion image) {
		super(startX, startY, image);
		this.endX = endX;
		this.endY = endY;
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	public MovingGraphicsObject(float startX, float startY, float endX, float endY, float speedX, float speedY, TextureRegion image, boolean remove) {
		this(startX, startY, endX, endY, speedX, speedY, image);
		this.remove = remove;
	}
	
	@Override
	public TextureRegion getImage() {
		if (coordsX >= endX && coordsY >= endY) {
			if (remove) {
				active = false;				
			}
		}
		else {
			coordsX += Gdx.graphics.getDeltaTime()*speedX;
			coordsY += Gdx.graphics.getDeltaTime()*speedY;
		}
		return image;
	}

}
