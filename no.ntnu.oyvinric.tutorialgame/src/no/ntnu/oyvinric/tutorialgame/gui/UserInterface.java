package no.ntnu.oyvinric.tutorialgame.gui;

import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;
import no.ntnu.oyvinric.tutorialgame.icon.CounterIcon;
import no.ntnu.oyvinric.tutorialgame.icon.KeyIcon;
import no.ntnu.oyvinric.tutorialgame.icon.StarIcon;
import no.ntnu.oyvinric.tutorialgame.item.Key;
import no.ntnu.oyvinric.tutorialgame.item.Key.KeyColor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class UserInterface {

	public static final int windowHeight = TutorialGame.windowHeight;
	public static final int windowWidth = 200;
	public static final int iconWidth = 32;
	public static final int iconHeight = 32;
	public static final int horizontalLeftLimit = TutorialGame.windowWidth-windowWidth+20;
	public static final int verticalUpperLimit = TutorialGame.windowHeight-iconHeight-20;	
	
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
		stage = new Stage(TutorialGame.windowWidth, windowHeight, true, batch);
		
		userInterfaceTextures = new TextureAtlas(Gdx.files.internal("resources/gfx/user-interface.atlas"));
		
		handleConfiguration();
	}
	
	private void handleConfiguration() {
		int elementCount = 0;
		if (configuration.blueKeyActive) {
			blueKey = new KeyIcon(new UserInterfacePosition(iconWidth*elementCount, 0), new Image(userInterfaceTextures.findRegion("key-blue-disabled")), new Image(userInterfaceTextures.findRegion("key-blue")), KeyColor.BLUE);
			blueKey.setActive(true);
			stage.addActor(blueKey.getDrawable());
		}
		if (configuration.yellowKeyActive) {
			yellowKey = new KeyIcon(new UserInterfacePosition(iconWidth*elementCount, 0), new Image(userInterfaceTextures.findRegion("key-yellow-disabled")), new Image(userInterfaceTextures.findRegion("key-yellow")), KeyColor.YELLOW);
			yellowKey.setActive(true);
			stage.addActor(yellowKey.getDrawable());
		}
		if (configuration.redKeyActive) {
			redKey = new KeyIcon(new UserInterfacePosition(iconWidth*elementCount, 0), new Image(userInterfaceTextures.findRegion("key-red-disabled")), new Image(userInterfaceTextures.findRegion("key-red")), KeyColor.RED);
			redKey.setActive(true);
			stage.addActor(redKey.getDrawable());
		}
		if (configuration.greenKeyActive) {
			greenKey = new KeyIcon(new UserInterfacePosition(iconWidth*elementCount, 0), new Image(userInterfaceTextures.findRegion("key-green-disabled")), new Image(userInterfaceTextures.findRegion("key-green")), KeyColor.GREEN);
			greenKey.setActive(true);
			stage.addActor(greenKey.getDrawable());
		}
		if (configuration.starActive) {
			star = new StarIcon(new UserInterfacePosition(iconWidth*elementCount, 0), new Image(userInterfaceTextures.findRegion("star")));
			star.setActive(true);
			stage.addActor(star.getDrawable());
			starCounter = new CounterIcon(new UserInterfacePosition(iconWidth*elementCount, 0), 0, configuration.starCount);
			stage.addActor(starCounter.getDrawable());
		}
	}
	
	public void draw() {
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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
			this.x = horizontalLeftLimit + x;
			this.y = verticalUpperLimit - y;
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


