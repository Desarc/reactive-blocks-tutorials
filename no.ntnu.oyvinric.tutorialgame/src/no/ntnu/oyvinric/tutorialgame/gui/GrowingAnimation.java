package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GrowingAnimation extends GraphicsObject {

	private float growingSpeed;
	private float maxWidth, maxHeight;
	
	public GrowingAnimation(float centerX, float centerY, TextureRegion image, float growingSpeed, float initialWidth, float initialHeight, float maxWidth, float maxHeight) {
		super(centerX, centerY, image);
		this.setSize(initialWidth, initialHeight);
		this.setPosition(centerX-getWidth()/2, centerY-getHeight()/2);
		this.growingSpeed = growingSpeed;
		this.maxHeight = maxHeight;
		this.maxWidth = maxWidth;
	}
	
	private void grow() {
		if (getHeight() < maxHeight && getWidth() < maxWidth) {
			float scaleFactor = 1+growingSpeed*Gdx.graphics.getDeltaTime();
			float deltaX = (getWidth()/2)-(getWidth()/2)*scaleFactor;
			float deltaY = (getHeight()/2)-(getHeight()/2)*scaleFactor;
			setSize(getWidth()*scaleFactor, getHeight()*scaleFactor);
			setPosition(getX()+deltaX, getY()+deltaY);
		}
	}
	
	@Override
	public TextureRegion getImage() {
		grow();
		return image;
	}

}
