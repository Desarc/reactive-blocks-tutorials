package no.ntnu.oyvinric.tutorialgame.intro;

import no.ntnu.oyvinric.tutorialgame.core.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class IntroAnimation extends Actor {

	private TextureAtlas animationTextures;
	private Array<TextureRegion> images;
	private Animation animation;
	private TextureRegion currentImage;
	private float animationDelay;
	private float animationTime = 0f;
	private boolean playing;
	
	
	public IntroAnimation(String animationName, float animationDelay) {
		animationTextures = new TextureAtlas(Gdx.files.internal(Constants.GFX_PATH+animationName+".atlas"));
		images = new Array<TextureRegion>();
		for (TextureRegion image : animationTextures.getRegions()) {
			images.add(image);
		}
		this.animationDelay = animationDelay;
		animation = new Animation(animationDelay, images);
		currentImage = images.first();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(getImage(), getX(), getY());
	}
	
	public TextureRegion getImage() {
		if (playing) {
			animationTime += Gdx.graphics.getDeltaTime();
			return animation.getKeyFrame(animationTime);
		}
		return currentImage;
	}
	
	@Override
	public float getHeight() {
		float max = 0;
		for (TextureRegion image : images) {
			max = (image.getRegionHeight() > max) ? image.getRegionHeight() : max;
		}
		return max;
	}
	
	@Override
	public float getWidth() {
		float max = 0;
		for (TextureRegion image : images) {
			max = (image.getRegionWidth() > max) ? image.getRegionWidth() : max;
		}
		return max;
	}
	
	
	public void play() {
		playing = true;
	}
	
	public void pause() {
		
	}
	
	
	public void cleanUp() {
		animationTextures.dispose();
	}
	
	
	
}
