package no.ntnu.oyvinric.tutorialgame.intro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import no.ntnu.oyvinric.tutorialgame.core.Constants;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

public class IntroConfiguration {
	
	private Properties configFile;
	
	static final float xPadding = 2f;
	static final float yPadding = 4f;
	
	private Skin skin;
	private Stage stage;
	private Label levelHeader;
	private Label title;
	private Label tellMeMore;
	private IntroAnimation animation;
	private HorizontalGroup controlButtons;
	private Label goalLabel;
	private String[] goals;
	private Label mapLabel;
	private Image map;
	private ArrayMap<String, String> newConcepts;
	private Window moreInfoWindow;
	
	private Array<Actor> actors;
	
	public IntroConfiguration(int levelNumber, IntroConfigurationDefault defaultValues, Stage stage) {
		this.stage = stage;
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
		verticalTopAlign(levelHeader);
		actors.add(levelHeader);
		
		title = new Label(configFile.getProperty("title", ""), skin.get("title", LabelStyle.class));
		horizontalCenterAlign(title);
		verticalAlignUnder(title, levelHeader);
		actors.add(title);
		
		animation = new IntroAnimation("intro-level"+levelNumber, 4f, skin);
		horizontalCenterAlign(animation);
		verticalAlignUnder(animation, title);
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
		horizontalCenterAlign(controlButtons);
		verticalAlignUnder(controlButtons, animation);
		actors.add(controlButtons);
		
		goalLabel = new Label("Goals and tips:", skin);
		horizontalLeftAlign(goalLabel);
		verticalAlignUnder(goalLabel, controlButtons);
		actors.add(goalLabel);
		
		Array<Image> goalImages = new Array<Image>();
		goals = configFile.getProperty("goals", "").split(",");
		for (String goal : goals) {
			Image goalImage = new Image(skin.getRegion("icon-"+goal));
			horizontalLeftAlignWithOffset(goalImage, 30);
			verticalAlignUnder(goalImage, (goalImages.size > 0) ? goalImages.peek() : goalLabel);
			goalImages.add(goalImage);
			actors.add(goalImage);
			Label goalLabel = new Label(configFile.getProperty(goal, ""), skin);
			horizontalAlignRightSideOf(goalLabel, goalImage);
			verticalCenterAlignWith(goalLabel, goalImage);
			actors.add(goalLabel);
		}
		
		float splitAmount = 0.70f;
		map = new Image(skin.getDrawable("level"+levelNumber+"map"));
		float mapScale = (Constants.introductionWindowWidth*splitAmount)/map.getWidth();
		map.setSize(map.getWidth()*mapScale, map.getHeight()*mapScale);
		horizontalLeftAlign(map);
		verticalBottomAlign(map);
		actors.add(map);
		
		mapLabel = new Label("Level map", skin);
		horizontalLeftAlign(mapLabel);
		verticalAlignOver(mapLabel, map);
		actors.add(mapLabel);
		
		tellMeMore = new Label("Tell me more about... ", skin);
		horizontalCenterAlignRightSideOf(tellMeMore, map);
		verticalAlignWith(tellMeMore, mapLabel);
		actors.add(tellMeMore);
		
		newConcepts = new ArrayMap<String, String>();
		String [] concepts = configFile.getProperty("concepts", "").split(",");
		for (String concept : concepts) {
			newConcepts.put(configFile.getProperty(concept+"_name", ""), concept);
		}
		Array<String> textValues = new Array<String>();
		Array<ImageTextButton> moreInfoButtons = new Array<ImageTextButton>();
		for (Object conceptText : newConcepts.keys) {
			if (conceptText != null) {
				textValues.add((String)conceptText);
				ImageTextButton moreInfoButton = new ImageTextButton((String)conceptText, skin);
				moreInfoButton.addCaptureListener(new ChangeListener() {
					
					@Override
					public void changed(ChangeEvent event, Actor actor) {
						String concept = ((ImageTextButton)actor).getText().toString();
						moreInfoWindow = new Window(concept, skin);
						moreInfoWindow.padTop(skin.getFont("default").getLineHeight());
						//moreInfoDialog.text(configFile.getProperty(newConcepts.get(concept)+"_text"));
						moreInfoWindow.setSize(Constants.introductionWindowWidth, Constants.introductionWindowHeight/2);
						Image image = new Image(skin.getRegion(configFile.getProperty(newConcepts.get(concept)+"_image")));
						//float scaleFactor = moreInfoWindow.getWidth()/image.getWidth();
						//image.setScale(scaleFactor);
						moreInfoWindow.add(image);
						moreInfoWindow.row();
						TextButton closeButton = new TextButton("Close", skin);
						closeButton.addCaptureListener(new ChangeListener() {
							
							@Override
							public void changed(ChangeEvent event, Actor actor) {
								actor.getStage().getActors().removeValue(moreInfoWindow, true);
								
							}
						});
						moreInfoWindow.add(closeButton);
						actor.getStage().addActor(moreInfoWindow);
						event.cancel();
					}
				});
				horizontalCenterAlignRightSideOf(moreInfoButton, map);
				verticalAlignUnder(moreInfoButton, (moreInfoButtons.size > 0) ? moreInfoButtons.peek() : tellMeMore);
				moreInfoButtons.add(moreInfoButton);
				actors.add(moreInfoButton);
			}
		}

	}
	
	public static void horizontalCenterAlign(Actor actor) {
		float x = Constants.introductionWindowWidth/2 - actor.getWidth()/2;
		actor.setX(x);
	}
	
	public static void horizontalLeftAlign(Actor actor) {
		float x = xPadding;
		actor.setX(x);
	}
	
	public static void horizontalLeftAlignWithOffset(Actor actor, float offset) {
		float x = xPadding + offset;
		actor.setX(x);
	}
	
	public static void horizontalRightAlign(Actor actor) {
		float x = Constants.introductionWindowWidth - actor.getWidth() - xPadding;
		actor.setX(x);
	}
	
	public static void horizontalAlignLeftSideOf(Actor toBeAligned, Actor old) {
		float x = old.getX() - toBeAligned.getWidth() - xPadding;
		toBeAligned.setX(x);
	}
	
	public static void horizontalAlignRightSideOf(Actor toBeAligned, Actor old) {
		float x = old.getX() + old.getWidth() + xPadding;
		toBeAligned.setX(x);
	}
	
	public static void horizontalCenterAlignRightSideOf(Actor toBeAligned, Actor old) {
		horizontalCenterAlignWithinBounds(toBeAligned, old.getX()+old.getWidth(), Constants.introductionWindowWidth);
	}
	
	public static void horizontalCenterAlignWithinBounds(Actor actor, float leftX, float rightX) {
		float x = leftX + (rightX-leftX)/2 - actor.getWidth()/2;
		actor.setX(x);
	}
	
	public static void horizontalAlignWith(Actor toBeAligned, Actor old) {
		float x = old.getX();
		toBeAligned.setX(x);
	}

	public static void verticalTopAlign(Actor actor) {
		float y = actor.getHeight() + yPadding;
		actor.setY(y);
	}
	
	public static void verticalBottomAlign(Actor actor) {
		float y = Constants.introductionWindowHeight - yPadding;
		actor.setY(y);
	}
	
	public static void verticalAlignUnder(Actor toBeAligned, Actor old) {
		float y = old.getY() + toBeAligned.getHeight() + yPadding;
		toBeAligned.setY(y);
	}
	
	public static void verticalAlignOver(Actor toBeAligned, Actor old) {
		float y = old.getY() - old.getHeight() - yPadding;
		toBeAligned.setY(y);
	}
	
	public static void verticalAlignWith(Actor toBeAligned, Actor old) {
		float y = old.getY();
		toBeAligned.setY(y);
	}
	
	public static void verticalCenterAlignWith(Actor toBeAligned, Actor old) {
		float y = old.getY() - old.getHeight()/2 + toBeAligned.getHeight()/2;
		toBeAligned.setY(y);
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
