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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;

public class IntroConfigurationDefault {
	
	public static final String DEFAULT = "default";
	public static final String LIST = "list";
	public static final String LEVEL_HEADER = "levelHeader";
	public static final String TITLE = "title";
	
	private Skin skin;
	private TextureAtlas introTextures;
	
	public IntroConfigurationDefault() {

		skin = new Skin();
		
		introTextures = new TextureAtlas(Gdx.files.internal(Constants.GFX_PATH+"intro.atlas"));
		skin.addRegions(introTextures);

		skin.add("default", new BitmapFont());
		
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		LabelStyle defaultStyle = new LabelStyle(new BitmapFont(), Color.BLACK);
		skin.add(DEFAULT, defaultStyle);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.GFX_PATH+"fonts/levelHeader.ttf"));
		BitmapFont levelHeaderFont = generator.generateFont(20);
		skin.add(LEVEL_HEADER, levelHeaderFont);
		LabelStyle levelHeaderStyle = new LabelStyle(levelHeaderFont, Color.BLACK);
		skin.add(LEVEL_HEADER, levelHeaderStyle);
		
		generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.GFX_PATH+"fonts/title.ttf"));
		BitmapFont titleFont = generator.generateFont(15);
		skin.add(TITLE, titleFont);
		LabelStyle titleStyle = new LabelStyle(titleFont, Color.BLACK);
		skin.add(TITLE, titleStyle);
		
		generator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.GFX_PATH+"fonts/moreInfoList.ttf"));
		BitmapFont listFont = generator.generateFont(16);
		skin.add(LIST, listFont);
		ListStyle listStyle = new ListStyle(listFont, Color.GRAY, Color.BLACK, skin.newDrawable("white", Color.LIGHT_GRAY));
		skin.add(LIST, listStyle);
		
		ScrollPaneStyle scrollPaneStyle = new ScrollPaneStyle();
		scrollPaneStyle.background = skin.newDrawable("white", Color.LIGHT_GRAY);
		skin.add(DEFAULT, scrollPaneStyle);
		
		SelectBoxStyle moreInfoDropdownStyle = new SelectBoxStyle(listFont, Color.BLACK, skin.newDrawable("white", Color.WHITE), scrollPaneStyle, listStyle);
		skin.add(DEFAULT, moreInfoDropdownStyle);
		
		generator.dispose();
		
	}
	
	public Skin getSkin() {
		return skin;
	}
	
	public void cleanUp() {
		introTextures.dispose();
	}

}
