package no.ntnu.oyvinric.tutorialgame.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameBoard {

	public static final float tileHeight = 56f;
	public static final float tileWidth = 64f;
	public static final int horizontalLeftLimit = 0;
	public static final int verticalUpperLimit = 480;

	TutorialGame parent;
	
	Texture wallTexture, groundTexture, malcolmTexture, kayleeTexture, washTexture, goalTexture;
	TextureRegion wallImage, groundImage, malcolmImage, kayleeImage, washImage, goalImage;
	OrthographicCamera camera;
	SpriteBatch batch;
	Array<Tile> groundTiles;
	Array<Tile> objectTiles;
	CharacterTile malcolm, kaylee, wash;
	Array<CharacterTile> characterTiles;
	Tile goal;
	
	boolean active = true;
	
	public GameBoard(TutorialGame parent, int levelNo) {
		this.parent = parent;
		wallTexture = new Texture(Gdx.files.internal("resources/gfx/wall-block.png"));
		wallImage = new TextureRegion(wallTexture);
		groundTexture = new Texture(Gdx.files.internal("resources/gfx/grass.png"));
		groundImage = new TextureRegion(groundTexture);
		malcolmTexture = new Texture(Gdx.files.internal("resources/gfx/malcolm.png"));
		malcolmImage = new TextureRegion(malcolmTexture);
		kayleeTexture = new Texture(Gdx.files.internal("resources/gfx/kaylee.png"));
		kayleeImage = new TextureRegion(kayleeTexture);
		washTexture = new Texture(Gdx.files.internal("resources/gfx/wash.png"));
		washImage = new TextureRegion(washTexture);
		goalTexture = new Texture(Gdx.files.internal("resources/gfx/blue-gem.png"));
		goalImage = new TextureRegion(goalTexture);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		camera.update();
		
		batch = new SpriteBatch();
		
		loadLevel(levelNo);
		
	}
	
	private void drawLevel() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Tile groundTile : groundTiles) {
			batch.draw(groundTile.image, groundTile.getX(), groundTile.getY(), groundTile.getOriginX(), groundTile.getOriginY(), groundTile.getWidth(), groundTile.getHeight(), 1f, 1f, groundTile.getRotation());
		}
		for (Tile objectTile : objectTiles) {
			batch.draw(objectTile.image, objectTile.getX(), objectTile.getY(), objectTile.getOriginX(), objectTile.getOriginY(), objectTile.getWidth(), objectTile.getHeight(), 1f, 1f, objectTile.getRotation());
		}
		for (Tile characterTile : characterTiles) {
			batch.draw(characterTile.image, characterTile.getX(), characterTile.getY(), characterTile.getOriginX(), characterTile.getOriginY(), characterTile.getWidth(), characterTile.getHeight(), 1f, 1f, characterTile.getRotation());
		}
		//System.out.println("Player position: "+player.getX()+","+player.getY());
		batch.end();
	}
	
	public void redraw() {
		drawLevel();
	}
	
	private void createTile(char type, float x, float y) {
		if (type == '#') {
			groundTiles.add(new Tile(x, y, wallImage, 0, 10));
		}
		else if (type == ' ') {
			groundTiles.add(new Tile(x, y, groundImage));
		}
		else if (type == 'M') {
			malcolm = new CharacterTile(CharacterName.MALCOLM, x, y, malcolmImage, 0, 16);
			characterTiles.add(malcolm);
			groundTiles.add(new Tile(x, y, groundImage));
		}
		else if (type == 'K') {
			kaylee = new CharacterTile(CharacterName.KAYLEE, x, y, kayleeImage, 0, 16);
			characterTiles.add(kaylee);
			groundTiles.add(new Tile(x, y, groundImage));
		}
		else if (type == 'W') {
			wash = new CharacterTile(CharacterName.WASH, x, y, washImage, 0, 16);
			characterTiles.add(wash);
			groundTiles.add(new Tile(x, y, groundImage));
		}
		else if (type == 'g') {
			Tile tile = new Tile(x, y, goalImage);
			objectTiles.add(tile);
			goal = tile;
			groundTiles.add(new Tile(x, y, groundImage));
		}
	}

	public void loadLevel(int levelNo) {
		groundTiles = new Array<Tile>();
		objectTiles = new Array<Tile>();
		characterTiles = new Array<CharacterTile>();
		try {
	    	BufferedReader br = new BufferedReader(new FileReader("resources/levels/level"+levelNo+".txt"));
			int xCount = 0;
			int yCount = 1;
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				for (Character c : line.toCharArray()) {
					float xPos = horizontalLeftLimit+xCount*tileWidth;
					float yPos = verticalUpperLimit-yCount*tileHeight;
					createTile(c, xPos, yPos);
					xCount++;
				}
				xCount = 0;
				yCount++;
			}
			br.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void cleanUp() {
		wallTexture.dispose();
		groundTexture.dispose();
		goalTexture.dispose();
		malcolmTexture.dispose();
		kayleeTexture.dispose();
		washTexture.dispose();
	}
	
	public CharacterTile getMalcolm() {
		return malcolm;
	}
	
	public CharacterTile getKaylee() {
		return kaylee;
	}
	
	public CharacterTile getWash() {
		return wash;
	}
	
	public Array<CharacterTile> getCharacterTiles() {
		return characterTiles;
	}
	
	public void updateCharacterPosition(Tile character, float dx, float dy) {
		character.move(dx, dy);
	}
	
	public void adjustCharacterPosition(Tile character) {
		character.alignWithGrid();
	}

	public void turnCharacterLeft(Tile character) {
		character.rotate(90);
	}

	public void turnCharacterRight(Tile character) {
		character.rotate(-90);
	}

	public void turnCharacterAround(Tile character) {
		character.rotate(180);
	}
	
	public class Tile {
		
		private Rectangle rectangle;
		private TextureRegion image;
		private float horizontalAdjustment = 0;
		private float verticalAdjustment = 0;
		private float rotation = 0;
		private float originX = 0;
		private float originY = 0;
		private Direction direction = Direction.EAST;
		
		public Tile(float x, float y, TextureRegion image) {
			rectangle = new Rectangle();
			rectangle.width = tileWidth;
			rectangle.height = tileHeight;
			rectangle.x = x;
			rectangle.y = y;
			this.image = image;
		}
		
		public Tile(float x, float y, TextureRegion image, int horizontalAdjustment, int verticalAdjustment) {
			rectangle = new Rectangle();
			rectangle.width = tileWidth;
			rectangle.height = tileHeight;
			rectangle.x = x;
			rectangle.y = y;
			this.image = image;
			this.horizontalAdjustment = horizontalAdjustment;
			this.verticalAdjustment = verticalAdjustment;
		}
		
		public void setPosition(float x, float y) {
			rectangle.x = x;
			rectangle.y = y;
		}
		
		public void move(float dx, float dy) {
			rectangle.x += dx;
			rectangle.y += dy;
		}
		
		public void alignWithGrid() {
			rectangle.x = Math.round(rectangle.x / tileWidth)*tileWidth;
			rectangle.y = Math.round(rectangle.y / tileHeight)*tileHeight;
		}
		
		public void rotate(float angle) {
			rotation = (rotation+angle)%360;
			if (rotation == 0) {
				originX = 0;
				originY = 0;
				direction = Direction.EAST;
			}
			else if (rotation == 90 || rotation == -270) {
				originX = tileWidth/2;
				originY = 0;
				direction = Direction.NORTH;
			}
			else if (rotation == 180 || rotation == -180) {
				originX = tileWidth/2;
				originY = 0;
				direction = Direction.WEST;
			}
			else if (rotation == 270 || rotation == -90) {
				originX = tileWidth/2;
				originY = 0;
				direction = Direction.SOUTH;
			}
		}
		
		public float getX() {
			return rectangle.x + horizontalAdjustment;
		}
		
		public float getY() {
			return rectangle.y + verticalAdjustment;
		}
		
		public float getRotation() {
			return rotation;
		}
		
		public float getWidth() {
			return rectangle.width;
		}
		
		public float getHeight() {
			return rectangle.height;
		}
		
		public float getOriginX() {
			return originX + horizontalAdjustment;
		}
		
		public float getOriginY() {
			return originY + verticalAdjustment;
		}
		
		public Direction getDirection() {
			return direction;
		}
	}
	
	public class CharacterTile extends Tile {
		
		private CharacterName name;
		
		public CharacterTile(CharacterName name, float x, float y, TextureRegion image) {
			super(x, y, image);
			this.name = name;
		}
		
		public CharacterTile(CharacterName name, float x, float y, TextureRegion image, int horizontalAdjustment, int verticalAdjustment) {
			super(x, y, image, horizontalAdjustment, verticalAdjustment);
			this.name = name;
		}
		
		public CharacterName getName() {
			return name;
		}
		
	}
	
	public static enum Direction {
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
	
	public static enum CharacterName {
		MALCOLM("Malcolm"),
		KAYLEE("Kaylee"),
		WASH("Wash");
		
		private final String name;
		
		CharacterName(String name) {
			this.name = name;
		}
		
		public String getValue() {
			return name;
		}
		
	}
}
