package no.ntnu.oyvinric.tutorialgame.core;

public class Constants {

	public static final String GFX_PATH = "resources/gfx/";
	public static final String INTRO_CONFIG_PATH = "resources/intro/";
	public static final String LEVEL_PATH = "resources/levels/";
	public static final String SOUND_PATH = "resources/sound/";
	
	public static final int mainWindowWidth = 800;
	public static final int mainWindowHeight = 600;
	public static final float tileHeight = 13f;
	public static final float visualTileHeight = 32f;
	public static final float tileWidth = 32f;
	public static final float visualTileWidth = 32f;
	
	public static final int userInterfaceWindowWidth = 200;
	public static final int userInterfaceWindowHeight = mainWindowHeight;
	public static final float iconWidth = 32f;
	public static final float iconHeight = 32f;
	public static final float userInterfaceHorizontalLeftLimit = mainWindowWidth-userInterfaceWindowWidth;
	public static final float userInterfaceVerticalUpperLimit = mainWindowHeight-iconHeight;	
	
	public static final int gameBoardWindowWidth = mainWindowHeight-userInterfaceWindowWidth;
	public static final int gameBoardWindowHeight = mainWindowHeight;
	public static final float gameBoardHorizontalLeftLimit = visualTileWidth;
	public static final float gameBoardVerticalUpperLimit = gameBoardWindowHeight-visualTileHeight;
	
	public static final float horizontalMoveSpeed = tileWidth*2;
	public static final float verticalMoveSpeed = tileHeight*4;
	
	public static final int introductionWindowWidth = 400;
	public static final int introductionWindowHeight = 600;
	
	public static final String EMPTY_TILE = "empty";
	
}
