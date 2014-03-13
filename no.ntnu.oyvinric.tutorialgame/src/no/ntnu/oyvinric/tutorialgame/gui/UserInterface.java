package no.ntnu.oyvinric.tutorialgame.gui;

import no.ntnu.oyvinric.tutorialgame.core.Constants;
import no.ntnu.oyvinric.tutorialgame.icon.CounterIcon;
import no.ntnu.oyvinric.tutorialgame.icon.KeyIcon;
import no.ntnu.oyvinric.tutorialgame.icon.StarIcon;
import no.ntnu.oyvinric.tutorialgame.item.Key;
import no.ntnu.oyvinric.tutorialgame.core.Constants.KeyColor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class UserInterface {

	private TextureAtlas userInterfaceTextures;
	private UserInterfaceConfiguration configuration;
	private SpriteBatch batch;
	
	private KeyIcon blueKey, redKey, yellowKey, greenKey;
	private StarIcon star;
	private CounterIcon starCounter;
	
	private Stage stage;
	
	public UserInterface(UserInterfaceConfiguration configuration) {
		this.configuration = configuration;
		batch = new SpriteBatch();
		stage = new Stage(Constants.mainWindowWidth, Constants.userInterfaceWindowHeight, true, batch);
		
		userInterfaceTextures = new TextureAtlas(Gdx.files.internal(Constants.GFX_PATH+"user-interface.atlas"));
		
		handleConfiguration();
	}
	
	private void handleConfiguration() {
		int elementCount = 0;
		if (configuration.blueKeyActive) {
			blueKey = new KeyIcon(new UserInterfacePosition(Constants.iconWidth*elementCount, 0), userInterfaceTextures.findRegion("key-blue-disabled"), userInterfaceTextures.findRegion("key-blue"), KeyColor.BLUE);
			blueKey.setActive(true);
			stage.addActor(blueKey);
			elementCount++;
		}
		if (configuration.yellowKeyActive) {
			yellowKey = new KeyIcon(new UserInterfacePosition(Constants.iconWidth*elementCount, 0), userInterfaceTextures.findRegion("key-yellow-disabled"), userInterfaceTextures.findRegion("key-yellow"), KeyColor.YELLOW);
			yellowKey.setActive(true);
			stage.addActor(yellowKey);
			elementCount++;
		}
		if (configuration.redKeyActive) {
			redKey = new KeyIcon(new UserInterfacePosition(Constants.iconWidth*elementCount, 0), userInterfaceTextures.findRegion("key-red-disabled"), userInterfaceTextures.findRegion("key-red"), KeyColor.RED);
			redKey.setActive(true);
			stage.addActor(redKey);
			elementCount++;
		}
		if (configuration.greenKeyActive) {
			greenKey = new KeyIcon(new UserInterfacePosition(Constants.iconWidth*elementCount, 0), userInterfaceTextures.findRegion("key-green-disabled"), userInterfaceTextures.findRegion("key-green"), KeyColor.GREEN);
			greenKey.setActive(true);
			stage.addActor(greenKey);
			elementCount++;
		}
		if (configuration.starActive) {
			star = new StarIcon(new UserInterfacePosition(Constants.iconWidth*elementCount, 0), new Image(userInterfaceTextures.findRegion("star")));
			star.setActive(true);
			stage.addActor(star.getDrawable());
			elementCount++;
			starCounter = new CounterIcon(new UserInterfacePosition(Constants.iconWidth*elementCount, 0), 0, configuration.starCount);
			stage.addActor(starCounter.getDrawable());
			elementCount++;
		}
	}
	
	public void draw() {
		stage.draw();
	}
	
	public void updateStarCounter(int newCount) {
		if (starCounter != null) {
			starCounter.setCount(newCount);
		}
	}
	
	public void keyFound(Key key) {
		if (key.getColor() == KeyColor.BLUE) {
			blueKey.keyFound();
		}
		else if (key.getColor() == KeyColor.YELLOW) {
			yellowKey.keyFound();
		}
		else if (key.getColor() == KeyColor.RED) {
			redKey.keyFound();
		}
		else if (key.getColor() == KeyColor.GREEN) {
			greenKey.keyFound();
		}
	}
	
	public void cleanUp() {
		stage.dispose();
	}
	
	
	public class UserInterfacePosition {
		
		private float x;
		private float y;
		
		public UserInterfacePosition(float x, float y) {
			this.x = Constants.userInterfaceHorizontalLeftLimit+ x;
			this.y = Constants.userInterfaceVerticalUpperLimit - y;
		}
		
		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}
		
		public void setX(float x) {
			this.x = x;
		}

		public void setY(float y) {
			this.y = y;
		}
		
	}
	
}


