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
	
	Texture wallTexture, groundTexture, playerTexture, goalTexture;
	TextureRegion wallImage, groundImage, playerImage, goalImage;
	OrthographicCamera camera;
	SpriteBatch batch;
	Array<Tile> groundTiles;
	Array<Tile> objectTiles;
	Tile player;
	Tile goal;
	
	boolean active = true;
	
	public GameBoard(TutorialGame parent, int levelNo) {
		this.parent = parent;
		wallTexture = new Texture(Gdx.files.internal("resources/gfx/wall-block.png"));
		wallImage = new TextureRegion(wallTexture);
		groundTexture = new Texture(Gdx.files.internal("resources/gfx/grass.png"));
		groundImage = new TextureRegion(groundTexture);
		playerTexture = new Texture(Gdx.files.internal("resources/gfx/boy.png"));
		playerImage = new TextureRegion(playerTexture);
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
		for (Tile tile : groundTiles) {
			batch.draw(tile.image, tile.getX(), tile.getY());
			batch.draw(tile.image, tile.getX(), tile.getY(), 0f, 0f, tile.getWidth(), tile.getHeight(), 1f, 1f, tile.getRotation());
		}
		for (Tile tile : objectTiles) {
			batch.draw(tile.image, tile.getX(), tile.getY());
		}
		//System.out.println("Player position: "+player.getX()+","+player.getY());
		batch.draw(player.image, player.getX(), player.getY(), player.getOriginX(), player.getOriginY(), player.getWidth(), player.getHeight(), 1f, 1f, player.getRotation());
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
		else if (type == 'p') {
			player = new Tile(x, y, playerImage, 0, 16);
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
		playerTexture.dispose();
	}
	
	public Tile getPlayer() {
		return player;
	}
	
	public void updatePlayerPosition(float dx, float dy) {
		player.move(dx, dy);
	}
	
	public void adjustCharacterPosition() {
		player.alignWithGrid();
	}

	public void turnPlayerLeft() {
		player.rotate(90);
	}

	public void turnPlayerRight() {
		player.rotate(-90);
	}

	public void turnPlayerAround() {
		player.rotate(180);
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
	
	public static enum Direction {
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
	
	
}
