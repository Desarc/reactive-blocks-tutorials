package no.ntnu.oyvinric.tutorialgame.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameBoard {

	TutorialGame parent;
	
	Texture wallImage, groundImage, playerImage, goalImage;
	Sound winSound;
	Music gameTheme;
	OrthographicCamera camera;
	SpriteBatch batch;
	Array<Tile> groundTiles;
	Array<Tile> objectTiles;
	Tile player;
	Tile goal;
	
	boolean active = true;
	
	public GameBoard(TutorialGame parent, int levelNo) {
		this.parent = parent;
		
		wallImage = new Texture(Gdx.files.internal("resources/gfx/wall-block.png"));
		groundImage = new Texture(Gdx.files.internal("resources/gfx/grass.png"));
		playerImage = new Texture(Gdx.files.internal("resources/gfx/boy.png"));
		goalImage = new Texture(Gdx.files.internal("resources/gfx/blue-gem.png"));
		winSound = Gdx.audio.newSound(Gdx.files.internal("resources/sound/drop.wav"));
		gameTheme = Gdx.audio.newMusic(Gdx.files.internal("resources/sound/odysseus.mp3"));
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		camera.update();
		
		batch = new SpriteBatch();
		loadLevel(levelNo);
		drawLevel();
		
	}
	
	private void drawLevel() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Tile tile : groundTiles) {
			batch.draw(tile.texture, tile.rectangle.x, tile.rectangle.y);
		}
		for (Tile tile : objectTiles) {
			batch.draw(tile.texture, tile.rectangle.x, tile.rectangle.y);
		}
		batch.draw(player.texture, player.rectangle.x, player.rectangle.y);
		batch.end();
	}
	
	public void redraw() {
		drawLevel();
	}
	
	class Tile {
		
		Rectangle rectangle;
		Texture texture;
		
		public Tile(int width, int height, int x, int y, Texture texture ) {
			rectangle = new Rectangle();
			rectangle.width = width;
			rectangle.height = height;
			rectangle.x = x;
			rectangle.y = y;
			this.texture = texture;
		}
	}
	
	private void createTile(char type, int x, int y) {
		if (type == '#') {
			groundTiles.add(new Tile(wallImage.getWidth(), wallImage.getHeight(), x, y, wallImage));
		}
		else if (type == ' ') {
			groundTiles.add(new Tile(groundImage.getWidth(), groundImage.getHeight(), x, y, groundImage));
		}
		else if (type == 'p') {
			player = new Tile(playerImage.getWidth(), playerImage.getHeight(), x, y, playerImage);
			groundTiles.add(new Tile(groundImage.getWidth(), groundImage.getHeight(), x, y, groundImage));
		}
		else if (type == 'g') {
			Tile tile = new Tile(goalImage.getWidth(), goalImage.getHeight(), x, y, goalImage);
			objectTiles.add(tile);
			goal = tile;
			groundTiles.add(new Tile(groundImage.getWidth(), groundImage.getHeight(), x, y, groundImage));
		}
	}
	

	public void loadLevel(int levelNo) {
		groundTiles = new Array<Tile>();
		objectTiles = new Array<Tile>();
		try {
	    	BufferedReader br = new BufferedReader(new FileReader("resources/levels/level"+levelNo+".txt"));
	    	int xLim = 0;
	    	int yLim = 480;
			int xCount = 0;
			int yCount = 1;
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				for (Character c : line.toCharArray()) {
					int xPos = xLim+xCount*64;
					int yPos = yLim-yCount*64;
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
	
	
	public void show() {
		gameTheme.setLooping(true);
		//gameTheme.play();
	}
	
	public void cleanUp() {
		wallImage.dispose();
		groundImage.dispose();
		goalImage.dispose();
		playerImage.dispose();
		gameTheme.dispose();
		winSound.dispose();
	}
	
	public Rectangle getPlayer() {
		return player.rectangle;
	}
	
	
}
