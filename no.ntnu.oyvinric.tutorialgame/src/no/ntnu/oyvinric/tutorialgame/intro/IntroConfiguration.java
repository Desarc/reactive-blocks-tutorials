package no.ntnu.oyvinric.tutorialgame.intro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import no.ntnu.oyvinric.tutorialgame.core.Constants;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
	private HorizontalGroup controlButtons;
	private Image map;
	private SelectBox moreInfoDropdown;
	private ArrayMap<String, String> newConcepts;
	private ArrayMap<String, TextureRegion> moreInfoImages;
	private Slideshow moreInfoSlideshow;
	private Array<ImageTextButton> moreInfoButtons;
	
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
		horizontalCenterAlignTop(levelHeader);
		actors.add(levelHeader);
		
		title = new Label(configFile.getProperty("title", ""), skin.get("title", LabelStyle.class));
		horizontalCenterAlignUnder(levelHeader, title);
		actors.add(title);
		
		animation = new IntroAnimation("intro-level"+levelNumber, 4f, skin);
		horizontalCenterAlignUnder(title, animation);
		actors.add(animation);
		
		controlButtons = new HorizontalGroup();
		
		Button play = new Button(skin.getDrawable("play-up"), skin.getDrawable("play-down"));
		play.addCaptureListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				animation.play();
				event.cancel();
			}
		});
		controlButtons.addActor(play);
		Button pause = new Button(skin.getDrawable("pause-up"), skin.getDrawable("pause-down"));
		pause.addCaptureListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				animation.pause();
				event.cancel();
			}
		});
		controlButtons.addActor(pause);
		Button beginning = new Button(skin.getDrawable("beginning-up"), skin.getDrawable("beginning-down"));
		beginning.addCaptureListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				animation.beginning();
				event.cancel();
			}
		});
		controlButtons.addActor(beginning);
		Button previous = new Button(skin.getDrawable("previous-up"), skin.getDrawable("previous-down"));
		previous.addCaptureListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				animation.previousFrame();
				event.cancel();
			}
		});
		controlButtons.addActor(previous);
		Button next = new Button(skin.getDrawable("next-up"), skin.getDrawable("next-down"));
		next.addCaptureListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				animation.nextFrame();
				event.cancel();
			}
		});
		controlButtons.addActor(next);
		Button end = new Button(skin.getDrawable("end-up"), skin.getDrawable("end-down"));
		end.addCaptureListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				animation.end();
				event.cancel();
			}
		});
		controlButtons.addActor(end);
		
		controlButtons.setSize(play.getWidth()*6, play.getHeight());
		horizontalCenterAlignUnder(animation, controlButtons);
		actors.add(controlButtons);
		
		float splitAmount = 0.75f;
		map = new Image(skin.getDrawable("level"+levelNumber+"map"));
		float mapScale = (Constants.introductionWindowWidth*splitAmount)/map.getWidth();
		map.setSize(map.getWidth()*mapScale, map.getHeight()*mapScale);
		horizontalLeftAlignUnder(controlButtons, map);
		actors.add(map);
		
		tellMeMore = new Label("Tell me more about... ", skin);
		horizontalRightAlignUnder(controlButtons, tellMeMore);
		actors.add(tellMeMore);
		
//		newConcepts = new ArrayMap<String, String>();
//		moreInfoImages = new ArrayMap<String, TextureRegion>();
//		String [] concepts = configFile.getProperty("concepts", "").split(",");
//		for (String concept : concepts) {
//			newConcepts.put(concept, configFile.getProperty(concept+"_name", ""));
//		}
//		Array<String> textValues = new Array<String>();
//		for (Object conceptText : newConcepts.values) {
//			if (conceptText != null) {
//				textValues.add((String)conceptText);
//			}
//		}
//		moreInfoDropdown = new SelectBox(textValues.toArray(), skin);
//		alignRightSideOf(tellMeMore, moreInfoDropdown);
//		moreInfoDropdown.addCaptureListener(new EventListener() {
//			
//			@Override
//			public boolean handle(Event event) {
//				moreInfoSlideshow.setCurrentImage(moreInfoDropdown.getSelection());
//				return false;
//			}
//		});
//		actors.add(moreInfoDropdown);
//		
//		for (Object concept : newConcepts.keys) {
//			if (concept != null) {
//				moreInfoImages.put(newConcepts.get((String)concept), skin.getRegion(configFile.getProperty((String)concept+"_image", "")));
//			}
//		}
//		moreInfoSlideshow = new Slideshow(moreInfoImages);
//		horizontalCenterAlign(moreInfoSlideshow);
//		actors.add(moreInfoSlideshow);

	}
	
	public void horizontalCenterAlignTop(Actor actor) {
		positionX = Constants.introductionWindowWidth/2 - actor.getWidth()/2;
		positionY = actor.getHeight() + yPadding;
		actor.setPosition(positionX, positionY);
	}
	
	public void horizontalCenterAlignUnder(Actor old, Actor toBeAligned) {
		positionX = Constants.introductionWindowWidth/2 - toBeAligned.getWidth()/2;
		positionY = old.getY() + toBeAligned.getHeight() + yPadding;
		toBeAligned.setPosition(positionX, positionY);
	}
	
	public void horizontalLeftAlignUnder(Actor old, Actor toBeAligned) {
		positionX = xPadding;
		positionY = old.getY() + toBeAligned.getHeight() + yPadding;
		toBeAligned.setPosition(positionX, positionY);
	}
	
	public void horizontalLeftAlignWithOffset(Actor actor, float offset) {
		positionX = xPadding+offset;
		positionY += actor.getHeight() + yPadding;
		actor.setPosition(positionX, positionY);
	}
	
	public void horizontalRightAlignUnder(Actor old, Actor toBeAligned) {
		positionX = Constants.introductionWindowWidth - toBeAligned.getWidth() - xPadding;
		positionY = old.getY() + toBeAligned.getHeight() + yPadding;
		toBeAligned.setPosition(positionX, positionY);
	}
	
	public void alignLeftSideOf(Actor old, Actor toBeAligned) {
		positionX = old.getX() - toBeAligned.getWidth() - xPadding;
		positionY = old.getY();
		toBeAligned.setPosition(positionX, positionY);
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
