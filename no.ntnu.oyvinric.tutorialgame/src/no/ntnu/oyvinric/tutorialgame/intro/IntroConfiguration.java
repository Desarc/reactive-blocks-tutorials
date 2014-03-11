package no.ntnu.oyvinric.tutorialgame.intro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import no.ntnu.oyvinric.tutorialgame.core.Constants;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

public class IntroConfiguration {
	
	private Properties configFile;
	
	final float xPadding = 2f;
	final float yPadding = 4f;
	
	private float positionY = yPadding;
	private float positionX = xPadding;
	
	private Skin skin;
	private Label levelHeader;
	private Label title;
	private Label tellMeMore;
	private IntroAnimation animation;
	private SelectBox moreInfoDropdown;
	private ArrayMap<String, String> newConcepts;
	private ArrayMap<String, TextureRegion> moreInfoImages;
	private Slideshow moreInfoSlideshow;
	
	private Array<Actor> actors;
	
	public IntroConfiguration(int levelNumber, IntroConfigurationDefault defaultValues) {
		configFile = new Properties();
		try {
			configFile.load(new FileInputStream(Constants.INTRO_CONFIG_PATH+"level"+levelNumber+".properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		skin = defaultValues.getSkin();
		
		actors = new Array<Actor>();
				
		levelHeader = new Label("Level "+levelNumber, skin.get("levelHeader", LabelStyle.class));
		horizontalCenterAlign(levelHeader);
		actors.add(levelHeader);
		
		title = new Label(configFile.getProperty("title", ""), skin.get("title", LabelStyle.class));
		horizontalCenterAlign(title);
		actors.add(title);
		
		animation = new IntroAnimation("intro-level"+levelNumber, 4f);
		horizontalCenterAlign(animation);
		actors.add(animation);
		
		tellMeMore = new Label("Tell me more about: ", skin);
		horizontalLeftAlign(tellMeMore);
		actors.add(tellMeMore);
		
		newConcepts = new ArrayMap<String, String>();
		moreInfoImages = new ArrayMap<String, TextureRegion>();
		String [] concepts = configFile.getProperty("concepts", "").split(",");
		for (String concept : concepts) {
			newConcepts.put(concept, configFile.getProperty(concept+"_name", ""));
		}
		Array<String> textValues = new Array<String>();
		for (Object conceptText : newConcepts.values) {
			if (conceptText != null) {
				textValues.add((String)conceptText);
			}
		}
		moreInfoDropdown = new SelectBox(textValues.toArray(), skin);
		alignRightSideOf(tellMeMore, moreInfoDropdown);
		moreInfoDropdown.addCaptureListener(new EventListener() {
			
			@Override
			public boolean handle(Event event) {
				moreInfoSlideshow.setCurrentImage(moreInfoDropdown.getSelection());
				return false;
			}
		});
		actors.add(moreInfoDropdown);
		
		for (Object concept : newConcepts.keys) {
			if (concept != null) {
				moreInfoImages.put(newConcepts.get((String)concept), skin.getRegion(configFile.getProperty((String)concept+"_image", "")));
			}
		}
		moreInfoSlideshow = new Slideshow(moreInfoImages);
		horizontalCenterAlign(moreInfoSlideshow);
		actors.add(moreInfoSlideshow);

	}
	
	public void horizontalCenterAlign(Actor actor) {
		positionX = Constants.introductionWindowWidth/2 - actor.getWidth()/2;
		positionY += actor.getHeight() + yPadding;
		actor.setPosition(positionX, positionY);
	}
	
	public void horizontalLeftAlign(Actor actor) {
		positionX = xPadding;
		positionY += actor.getHeight() + yPadding;
		actor.setPosition(positionX, positionY);
	}
	
	public void alignRightSideOf(Actor old, Actor toBeAligned) {
		positionX = old.getX() + old.getWidth() + xPadding;
		positionY = old.getY();
		toBeAligned.setPosition(positionX, positionY);
	}
	
	public Array<Actor> getActors() {
		return actors;
	}
	
	public IntroAnimation getAnimation() {
		return animation;
	}
	
	public float getTopOffset() {
		return levelHeader.getHeight();
	}
	
	public void cleanUp() {
		animation.cleanUp();
	}
	
	
}
