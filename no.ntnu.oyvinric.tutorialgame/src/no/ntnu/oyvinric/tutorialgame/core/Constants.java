package no.ntnu.oyvinric.tutorialgame.core;

public class Constants {

	public static final String JAR_PATH = "lib/tutorialgame.jar/";
	public static final String GFX_PATH = "resources/gfx/";
	public static final String FONT_PATH = "resources/fonts/";
	public static final String INTRO_CONFIG_PATH = "resources/intro/";
	public static final String LEVEL_PATH = "resources/levels/";
	public static final String SOUND_PATH = "resources/sound/";
	
	public static final int mainWindowWidth = 1000;
	public static final int mainWindowHeight = 800;
	public static final float tileHeight = 13f;
	public static final float visualTileHeight = 32f;
	public static final float tileWidth = 32f;
	public static final float visualTileWidth = 32f;
	
	public static final int userInterfaceWindowWidth = 250;
	public static final int userInterfaceWindowHeight = mainWindowHeight;
	public static final float iconWidth = 32f;
	public static final float iconHeight = 32f;
	public static final float frameBorderWidth = 24f;
	public static final float userInterfaceHorizontalLeftLimit = mainWindowWidth-userInterfaceWindowWidth;
	public static final float userInterfaceVerticalUpperLimit = mainWindowHeight;	
	
	public static final int gameBoardWindowWidth = mainWindowWidth-userInterfaceWindowWidth;
	public static final int gameBoardWindowHeight = mainWindowHeight;
	public static final float gameBoardHorizontalLeftLimit = visualTileWidth;
	public static final float gameBoardVerticalUpperLimit = gameBoardWindowHeight-visualTileHeight;
	
	public static final float horizontalMoveSpeed = tileWidth*2;
	public static final float verticalMoveSpeed = tileHeight*4;
	
	public static final int introductionWindowWidth = 600;
	public static final int introductionWindowHeight = 800;
	public static final float introAnimationHeight = 300;
	public static final float mapMaxHeight = 220;
	
	public static final String EMPTY_TILE = "empty";
	public static final String SELECTOR = "selector";
	public static final String DEFAULT = "default";
	public static final String WHITE = "white";
	public static final String LIST = "list";
	public static final String LEVEL_HEADER = "levelHeader";
	public static final String TITLE = "title";
	public static final String MAP_HEADER = "mapHeader";
	public static final String GOALS = "goals";
	public static final String CONCEPTS = "concepts";
	public static final String BUTTON = "button";
	public static final String LABEL = "label";
	
	public static enum ItemType {
		STAR,
		KEY,
		DOOR_TOKEN;
	}

	public static enum KeyColor {
		BLUE("blue"),
		YELLOW("yellow"),
		RED("red"),
		GREEN("green"),
		BLACK("black"),
		WHITE("white");
		
		private String color;
		
		private KeyColor(String color) {
			this.color = color;
		}
		
		public String value() {
			return color;
		}
	}

	public static enum CharacterName {
		MALCOLM("malcolm"),
		LISA("lisa"),
		ANDREW("andrew");
		
		private final String name;
		
		CharacterName(String name) {
			this.name = name;
		}
		
		public String getValue() {
			return name;
		}
	}

	public static enum Direction {
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
	
	public static enum WinCondition {
		STARS,
		SPEAK;
	}
	
}


