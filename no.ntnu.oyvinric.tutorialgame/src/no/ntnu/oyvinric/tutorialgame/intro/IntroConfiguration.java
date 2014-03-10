package no.ntnu.oyvinric.tutorialgame.intro;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import no.ntnu.oyvinric.tutorialgame.core.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;

public class IntroConfiguration {
	
	private Properties configFile; 
	private TextureAtlas introTextures;
	
	private float yOffset = 0f;
	private float xOffset = 0f;
	
	private String[] newConcepts;
	
	private Skin skin;
	private Label levelHeader;
	private Label title;
	private IntroAnimation animation;
	private SelectBox moreInfoDropdown;
	private Array<TextButton> moreInfoButtons;
	
	private Array<Actor> actors;
	
	public IntroConfiguration(int levelNumber) {
		configFile = new Properties();
		try {
			configFile.load(new FileInputStream(Constants.INTRO_CONFIG_PATH+"level"+levelNumber+".properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		introTextures = new TextureAtlas(Gdx.files.internal(Constants.GFX_PATH+"intro.atlas"));
		
		actors = new Array<Actor>();
		
		skin = new Skin();

		skin.add("default", new BitmapFont());
		
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		LabelStyle levelHeaderStyle = new LabelStyle(new BitmapFont(), Color.BLACK);
		levelHeaderStyle.font.scale(0.7f);
		skin.add("default", levelHeaderStyle);
		
		LabelStyle titleStyle = new LabelStyle(new BitmapFont(), Color.BLACK);
		titleStyle.font.scale(0.5f);
		skin.add("default", titleStyle);
		
		TextButtonStyle moreInfoButtonStyle = new TextButtonStyle();
		moreInfoButtonStyle.font = new BitmapFont();
		moreInfoButtonStyle.fontColor = Color.BLACK;
		skin.add("default", moreInfoButtonStyle);
		
		SelectBoxStyle moreInfoDropdownStyle = new SelectBoxStyle();
		moreInfoDropdownStyle.font = new BitmapFont();
		moreInfoDropdownStyle.background = skin.newDrawable("white", Color.LIGHT_GRAY);
		skin.add("default", moreInfoDropdownStyle);
		
		levelHeader = new Label("Level "+levelNumber, skin);
		yOffset += levelHeader.getHeight();
		levelHeader.setPosition(xOffset, yOffset);
		actors.add(levelHeader);
		
		title = new Label(configFile.getProperty("title", ""), skin);
		yOffset += title.getHeight();
		title.setPosition(xOffset, yOffset);
		actors.add(title);
		
		animation = new IntroAnimation("intro-level"+levelNumber, 4f);
		yOffset += animation.getHeight();
		animation.setPosition(xOffset, yOffset);
		actors.add(animation);
		
		moreInfoButtons = new Array<TextButton>();
		newConcepts = configFile.getProperty("concepts", "").split(",");
		moreInfoDropdown = new SelectBox(newConcepts, skin);
		yOffset += moreInfoDropdown.getHeight();
		moreInfoDropdown.setPosition(xOffset, yOffset);
//		for (String concept : newConcepts) {
//			System.out.println(concept);
//			TextButton button = new TextButton("Tell me more about "+concept, skin);
//			yOffset += button.getHeight();
//			button.setPosition(xOffset, yOffset);
//			button.addAction(new MoreInfoAction(concept));
//			moreInfoButtons.add(button);
//			actors.add(button);
//		}
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
	
	
}
