package no.ntnu.oyvinric.tutorialgame.intro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import no.ntnu.oyvinric.tutorialgame.core.Constants;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

public class IntroConfiguration {
	
	private Properties configFile;
	
	final float animationDelay = 8f;
	
	static final float xPadding = 3f;
	static final float yPadding = 4f;
	static final float defaultPadding = 4f;
	final float mapSplitAmount = 0.70f;
	
	private Skin skin;
	private Label levelHeader;
	private Label title;
	private Label tellMeMore;
	private IntroAnimation animation;
	private HorizontalGroup controlButtons;
	private Label goalLabel;
	private String[] goalStrings;
	private ImageTextButton tipsButton;
	private Window tipsWindow;
	private Label mapLabel;
	private ImageButton map;
	private boolean mapBig;
	private ArrayMap<String, String> newConcepts;
	private Window moreInfoWindow;
	
	private Array<Actor> actors;
	
	public IntroConfiguration(int levelNumber, IntroConfigurationStyles defaultValues) {
		configFile = new Properties();
		try {
			configFile.load(new FileInputStream(Constants.INTRO_CONFIG_PATH+"level"+levelNumber+".properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		skin = defaultValues.getSkin();
		
		actors = new Array<Actor>();
				
		levelHeader = new Label("Level "+levelNumber, skin.get(Constants.LEVEL_HEADER, LabelStyle.class));
		horizontalCenterAlign(levelHeader);
		verticalTopAlign(levelHeader);
		actors.add(levelHeader);
		
		title = new Label(configFile.getProperty(Constants.TITLE, ""), skin.get(Constants.TITLE, LabelStyle.class));
		horizontalCenterAlign(title);
		verticalAlignUnder(title, levelHeader);
		actors.add(title);
		
		if (levelNumber < 10) {
			animation = new IntroAnimation("intro-level-0"+levelNumber, animationDelay, skin);
		}
		else {
			animation = new IntroAnimation("intro-level-"+levelNumber, animationDelay, skin);			
		}
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
		
		goalLabel = new Label("Goals:", skin);
		horizontalLeftAlign(goalLabel);
		verticalAlignUnder(goalLabel, controlButtons);
		actors.add(goalLabel);
		
		VerticalGroup goals = new VerticalGroup();
		goalStrings = configFile.getProperty(Constants.GOALS, "").split(",");
		for (String goalString : goalStrings) {
			HorizontalGroup goal = new HorizontalGroup();
			goal.setSpacing(defaultPadding);
			Image goalImage = new Image(skin.getRegion("icon-"+goalString));
			goal.addActor(goalImage);
			Label goalLabel = new Label(configFile.getProperty(goalString, ""), skin);
			goal.addActor(goalLabel);
			goal.setHeight(Math.max(goalImage.getHeight(), goalLabel.getHeight()));
			goal.setWidth(goalImage.getWidth() + goalLabel.getWidth() + defaultPadding);
			goals.addActor(goal);
		}
		goals.setAlignment(Align.left);
		goals.setHeight(goals.getChildren().first().getHeight()*goalStrings.length);
		horizontalLeftAlign(goals);
		verticalAlignUnder(goals, goalLabel);
		actors.add(goals);
		
		tipsButton = new ImageTextButton("Tips", skin);
		tipsButton.addCaptureListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				tipsWindow = new Window("Tips", skin);
				tipsWindow.padTop(skin.getFont(Constants.DEFAULT).getLineHeight());
				
				VerticalGroup tips = new VerticalGroup();
				String[] tipsStrings = configFile.getProperty("tips", "").split(";");
				for (String tip : tipsStrings) {
					HorizontalGroup line = new HorizontalGroup();
					Image bulletImageLeft = new Image(skin.getRegion("spanner"));
					line.addActor(bulletImageLeft);
					Label tipLabel = new Label(tip, skin);
					line.addActor(tipLabel);
					Image bulletImageRight = new Image(skin.getRegion("spanner"));
					line.addActor(bulletImageRight);
					line.setSpacing(defaultPadding);
					tips.addActor(line);
					line.setWidth(bulletImageLeft.getWidth() + tipLabel.getWidth() + bulletImageRight.getWidth() + defaultPadding*2);
					line.setHeight(Math.max(bulletImageLeft.getHeight(), tipLabel.getHeight()));
					tips.setWidth(Math.max(tips.getWidth(), line.getWidth()));
					tips.setHeight(tips.getHeight() + line.getHeight());
				}
				ImageTextButton closeButton = new ImageTextButton("Close", skin);
				closeButton.addCaptureListener(new ChangeListener() {
					
					@Override
					public void changed(ChangeEvent event, Actor actor) {
						actor.getStage().getActors().removeValue(tipsWindow, true);
					}
				});
				tips.addActor(closeButton);
				tips.setHeight(tips.getHeight() + closeButton.getHeight());
				tips.setSpacing(defaultPadding);
				tips.setAlignment(Align.left);
				tipsWindow.add(tips);
				tipsWindow.setSize(Constants.introductionWindowWidth, tips.getHeight() + tipsWindow.getPadTop() + defaultPadding*(tipsStrings.length+1));
				tipsWindow.setY(Constants.introductionWindowHeight/2 - tipsWindow.getHeight());
				actor.getStage().addActor(tipsWindow);
				event.cancel();
				
			}
		});
		horizontalRightAlign(tipsButton);
		verticalAlignWith(tipsButton, goalLabel);
		actors.add(tipsButton);
		
		
		map = new ImageButton(skin.getDrawable("level"+levelNumber+"map"));
		float mapScale = (Constants.introductionWindowWidth*mapSplitAmount)/map.getWidth();
		map.setSize(map.getWidth()*mapScale, map.getHeight()*mapScale);
		if (map.getHeight() > Constants.mapMaxHeight) {
			mapScale = Constants.mapMaxHeight/map.getHeight();
			map.setSize(map.getWidth()*mapScale, map.getHeight()*mapScale);
		}
		mapBig = false;
		map.addCaptureListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (mapBig) {
					float scaleFactor = (Constants.introductionWindowWidth*mapSplitAmount)/map.getWidth();
					map.setSize(map.getWidth()*scaleFactor, map.getHeight()*scaleFactor);
					if (map.getHeight() > Constants.mapMaxHeight) {
						scaleFactor = Constants.mapMaxHeight/map.getHeight();
						map.setSize(map.getWidth()*scaleFactor, map.getHeight()*scaleFactor);
					}
					mapBig = false;
				}
				else {
					float scaleFactor = Constants.introductionWindowWidth/map.getWidth();
					map.setSize(map.getWidth()*scaleFactor, map.getHeight()*scaleFactor);
					if (map.getHeight() > Constants.introductionWindowHeight) {
						scaleFactor = Constants.mapMaxHeight/map.getHeight();
						map.setSize(map.getWidth()*scaleFactor, map.getHeight()*scaleFactor);
					}
					mapBig = true;
					actor.getStage().addActor(map);
				}
			}
		});
		horizontalLeftAlign(map);
		verticalBottomAlign(map);
		actors.add(map);
		
		mapLabel = new Label("Level map (click to zoom)", skin.get(Constants.MAP_HEADER, LabelStyle.class));
		horizontalLeftAlign(mapLabel);
		verticalAlignOver(mapLabel, map);
		actors.add(mapLabel);
		
		tellMeMore = new Label("Tell me more about... ", skin);
		horizontalRightAlign(tellMeMore);
		verticalAlignWith(tellMeMore, map);
		actors.add(tellMeMore);
		
		newConcepts = new ArrayMap<String, String>();
		String [] concepts = configFile.getProperty(Constants.CONCEPTS, "").split(",");
		for (String concept : concepts) {
			newConcepts.put(configFile.getProperty(concept+"_name", ""), concept);
		}
		VerticalGroup moreInfoButtons = new VerticalGroup();
		VerticalGroup moreInfoIcons = new VerticalGroup();
		for (Object concept : newConcepts.keys) {
			if (concept != null) {
				String conceptText = (String)concept;
				Image moreInfoIcon = new Image(skin.getRegion(newConcepts.get(conceptText)));
				moreInfoIcons.addActor(moreInfoIcon);
				moreInfoIcons.setWidth((moreInfoIcon.getWidth() > moreInfoIcons.getWidth() ? moreInfoIcon.getWidth() : moreInfoIcons.getWidth()));
				moreInfoIcons.setHeight(moreInfoIcons.getHeight()+moreInfoIcon.getHeight());
				
				ImageTextButton moreInfoButton = new ImageTextButton(conceptText, skin);
				moreInfoButton.addCaptureListener(new ChangeListener() {
					
					@Override
					public void changed(ChangeEvent event, Actor actor) {
						VerticalGroup vGroup = new VerticalGroup();
						String concept = ((ImageTextButton)actor).getText().toString();
						moreInfoWindow = new Window(concept, skin);
						moreInfoWindow.padTop(skin.getFont(Constants.DEFAULT).getLineHeight());
						moreInfoWindow.setSize(Constants.introductionWindowWidth, Constants.introductionWindowHeight/2);
						Image infoImage = new Image(skin.getRegion(configFile.getProperty(newConcepts.get(concept)+"_image")));
						float scaleFactor = moreInfoWindow.getWidth()/infoImage.getWidth();
						infoImage.setScale(scaleFactor);
						vGroup.addActor(infoImage);
						//moreInfoWindow.addActor(infoImage);
						//moreInfoWindow.row();
						ImageTextButton closeButton = new ImageTextButton("Close", skin);
						closeButton.addCaptureListener(new ChangeListener() {
							
							@Override
							public void changed(ChangeEvent event, Actor actor) {
								actor.getStage().getActors().removeValue(moreInfoWindow, true);
								
							}
						});
						//moreInfoWindow.addActor(closeButton);
						vGroup.addActor(closeButton);
						moreInfoWindow.add(vGroup);
						actor.getStage().addActor(moreInfoWindow);
						event.cancel();
					}
				});
				moreInfoButtons.addActor(moreInfoButton);
				moreInfoButtons.setWidth((moreInfoButton.getWidth() > moreInfoButtons.getWidth() ? moreInfoButton.getWidth() : moreInfoButtons.getWidth()));
				moreInfoButtons.setHeight(moreInfoButtons.getHeight()+moreInfoButton.getHeight());
			}
		}
		moreInfoIcons.setSpacing(defaultPadding);
		moreInfoButtons.setSpacing(defaultPadding);
		HorizontalGroup moreInfo = new HorizontalGroup();
		moreInfo.addActor(moreInfoIcons);
		moreInfo.addActor(moreInfoButtons);
		moreInfo.setSpacing(defaultPadding);
		moreInfo.setWidth(moreInfoIcons.getWidth()+moreInfoButtons.getWidth());
		moreInfo.setHeight(Math.max(moreInfoIcons.getHeight(), moreInfoButtons.getHeight()));
		horizontalRightAlign(moreInfo);
		verticalAlignUnder(moreInfo, tellMeMore);
		actors.add(moreInfo);
		

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
		float y = old.getY() - old.getHeight() + toBeAligned.getHeight();
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
