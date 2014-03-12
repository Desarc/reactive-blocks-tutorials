package no.ntnu.oyvinric.tutorialgame.intro;

import no.ntnu.oyvinric.tutorialgame.core.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class IntroAnimation extends Actor {

	private TextureAtlas animationTextures;
	private Array<TextureRegion> images;
	private int frameNumber = 0;
	private int maxFrameNumber = 0;
	private float animationDelay;
	private float animationTime = 0f;
	private boolean playing;
	
	
	public IntroAnimation(String animationName, float animationDelay, Skin skin) {
		this.animationTextures = new TextureAtlas(Gdx.files.internal(Constants.GFX_PATH+animationName+".atlas"));
		this.images = new Array<TextureRegion>();
		for (TextureRegion image : animationTextures.getRegions()) {
			images.add(image);
		}
		this.animationDelay = animationDelay;
		this.maxFrameNumber = images.size-1;
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(getImage(), getX(), getY());
	}
	
	public TextureRegion getImage() {
		if (playing) {
			animationTime += Gdx.graphics.getDeltaTime();
			if (animationTime > animationDelay) {
				animationTime = 0f;
				frameNumber++;
				if (frameNumber > maxFrameNumber) {
					frameNumber = maxFrameNumber;
					playing = false;
				}
			}
		}
		return images.get(frameNumber);
	}
	
	@Override
	public float getHeight() {
		float max = 0f;
		for (TextureRegion image : images) {
			max = (image.getRegionHeight() > max) ? image.getRegionHeight() : max;
		}
		return max;
	}
	
	@Override
	public float getWidth() {
		float max = 0f;
		for (TextureRegion image : images) {
			max = (image.getRegionWidth() > max) ? image.getRegionWidth() : max;
		}
		return max;
	}
	
	
	public void play() {
		playing = true;
	}
	
	public void pause() {
		playing = false;
	}
	
	public void beginning() {
		playing = false;
		frameNumber = 0;
	}
	
	public void end() {
		playing = false;
		frameNumber = maxFrameNumber;
	}
	
	public void previousFrame() {
		frameNumber--;
	}
	
	public void nextFrame() {
		frameNumber++;
	}
	
	
	public void cleanUp() {
		animationTextures.dispose();
	}
	
	
	
}
