package no.ntnu.oyvinric.tutorialgame.intro;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.ArrayMap;

public class Slideshow extends Actor {
	
	private ArrayMap<String, TextureRegion> images;
	private TextureRegion currentImage;
	
	public Slideshow(ArrayMap<String, TextureRegion> images) {
		this.images = images;
		currentImage = (TextureRegion)images.firstValue();
	}
	
	public void setCurrentImage(String key) {
		currentImage = images.get(key);
	}
	
	@Override
	public float getHeight() {
		float max = 0;
		for (Object image : images.values) {
			if (image != null) {
				max = (((TextureRegion)image).getRegionHeight() > max) ? ((TextureRegion)image).getRegionHeight() : max;
			}
		}
		return max;
	}
	
	@Override
	public float getWidth() {
		float max = 0;
		for (Object image : images.values) {
			if (image != null) {
				max = (((TextureRegion)image).getRegionWidth() > max) ? ((TextureRegion)image).getRegionWidth() : max;
			}
		}
		return max;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(currentImage, getX(), getY());
	}
	
}
