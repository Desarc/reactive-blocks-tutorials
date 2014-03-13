package no.ntnu.oyvinric.tutorialgame.intro;

import no.ntnu.oyvinric.tutorialgame.core.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane.SplitPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;

public class IntroConfigurationStyles {
	
	private Skin skin;
	private TextureAtlas introTextures;
	
	public IntroConfigurationStyles() {

		skin = new Skin();
		
		introTextures = new TextureAtlas(Gdx.files.internal(Constants.GFX_PATH+"intro.atlas"));
		skin.addRegions(introTextures);

		BitmapFont defaultFont = new BitmapFont();
		defaultFont.setColor(Color.BLACK);
		skin.add(Constants.DEFAULT, defaultFont);
		
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add(Constants.WHITE, new Texture(pixmap));
		
		LabelStyle defaultStyle = new LabelStyle(skin.getFont(Constants.DEFAULT), Color.BLACK);
		skin.add(Constants.DEFAULT, defaultStyle);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.GFX_PATH+"fonts/levelHeader.ttf"));
		BitmapFont levelHeaderFont = generator.generateFont(20);
		skin.add(Constants.LEVEL_HEADER, levelHeaderFont);
		LabelStyle levelHeaderStyle = new LabelStyle(levelHeaderFont, Color.BLACK);
		skin.add(Constants.LEVEL_HEADER, levelHeaderStyle);
		
		generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.GFX_PATH+"fonts/mapHeader.ttf"));
		BitmapFont mapHeaderFont = generator.generateFont(15);
		skin.add(Constants.MAP_HEADER, mapHeaderFont);
		LabelStyle mapHeaderStyle = new LabelStyle(mapHeaderFont, Color.BLACK);
		skin.add(Constants.MAP_HEADER, mapHeaderStyle);
		
		generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.GFX_PATH+"fonts/title.ttf"));
		BitmapFont titleFont = generator.generateFont(15);
		skin.add(Constants.TITLE, titleFont);
		LabelStyle titleStyle = new LabelStyle(titleFont, Color.BLACK);
		skin.add(Constants.TITLE, titleStyle);
		
		generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.GFX_PATH+"fonts/button.ttf"));
		BitmapFont buttonFont = generator.generateFont(11);
		skin.add(Constants.BUTTON, buttonFont);
		
		generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.GFX_PATH+"fonts/moreInfoList.ttf"));
		BitmapFont listFont = generator.generateFont(16);
		skin.add(Constants.LIST, listFont);
		ListStyle listStyle = new ListStyle(listFont, Color.GRAY, Color.BLACK, skin.newDrawable(Constants.WHITE, Color.LIGHT_GRAY));
		skin.add(Constants.LIST, listStyle);
		
		ScrollPaneStyle scrollPaneStyle = new ScrollPaneStyle();
		scrollPaneStyle.background = skin.newDrawable(Constants.WHITE, Color.LIGHT_GRAY);
		skin.add(Constants.DEFAULT, scrollPaneStyle);
		
		SelectBoxStyle moreInfoDropdownStyle = new SelectBoxStyle(listFont, Color.BLACK, skin.newDrawable(Constants.WHITE, Color.WHITE), scrollPaneStyle, listStyle);
		skin.add(Constants.DEFAULT, moreInfoDropdownStyle);
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = skin.getFont(Constants.DEFAULT);
		skin.add(Constants.DEFAULT, textButtonStyle);
		
		ImageTextButtonStyle imageTextButtonStyle = new ImageTextButtonStyle(skin.getDrawable(Constants.BUTTON), skin.getDrawable(Constants.BUTTON), skin.getDrawable(Constants.BUTTON), skin.getFont(Constants.DEFAULT));
		imageTextButtonStyle.font = skin.getFont(Constants.BUTTON);
		imageTextButtonStyle.fontColor = Color.DARK_GRAY;
		imageTextButtonStyle.downFontColor = Color.LIGHT_GRAY;
		skin.add(Constants.DEFAULT, imageTextButtonStyle);
		
		WindowStyle windowStyle = new WindowStyle(skin.getFont(Constants.DEFAULT), Color.BLACK, skin.newDrawable(Constants.WHITE, Color.LIGHT_GRAY));
		skin.add(Constants.DEFAULT, windowStyle);
		
		SplitPaneStyle horizontalSplitPaneStyle = new SplitPaneStyle(skin.newDrawable(Constants.WHITE, Color.WHITE));
		skin.add(Constants.DEFAULT+"-horizontal", horizontalSplitPaneStyle);
		
		SplitPaneStyle verticalSplitPaneStyle = new SplitPaneStyle(skin.newDrawable(Constants.WHITE, Color.WHITE));
		skin.add(Constants.DEFAULT+"-vertical", verticalSplitPaneStyle);
		
		generator.dispose();
		
	}
	
	public Skin getSkin() {
		return skin;
	}
	
	public void cleanUp() {
		introTextures.dispose();
	}

}
