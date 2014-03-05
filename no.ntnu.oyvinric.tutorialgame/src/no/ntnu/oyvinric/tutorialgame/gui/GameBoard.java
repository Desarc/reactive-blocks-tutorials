package no.ntnu.oyvinric.tutorialgame.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile.CharacterName;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile;
import no.ntnu.oyvinric.tutorialgame.gui.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class GameBoard {

	public static final float tileHeight = 56f;
	public static final float tileWidth = 64f;
	public static final int horizontalLeftLimit = 0;
	public static final int verticalUpperLimit = 480;

	TutorialGame parent;
	
	TextureAtlas environmentTextures;
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
		
		environmentTextures = new TextureAtlas(Gdx.files.internal("resources/gfx/environment.atlas"));
		
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
			batch.draw(groundTile.getImage(), groundTile.getX(), groundTile.getY(), groundTile.getOriginX(), groundTile.getOriginY(), groundTile.getWidth(), groundTile.getHeight(), 1f, 1f, groundTile.getRotation());
		}
		for (Tile objectTile : objectTiles) {
			batch.draw(objectTile.getImage(), objectTile.getX(), objectTile.getY(), objectTile.getOriginX(), objectTile.getOriginY(), objectTile.getWidth(), objectTile.getHeight(), 1f, 1f, objectTile.getRotation());
		}
		for (CharacterTile characterTile : characterTiles) {
			batch.draw(characterTile.getImage(), characterTile.getX(), characterTile.getY(), characterTile.getOriginX(), characterTile.getOriginY(), characterTile.getWidth(), characterTile.getHeight(), 1f, 1f, characterTile.getRotation());
		}
		//System.out.println("Player position: "+player.getX()+","+player.getY());
		batch.end();
	}
	
	public void redraw() {
		drawLevel();
	}
	
	private void createTile(char type, float x, float y) {
		if (type == '#') {
			groundTiles.add(new Tile(x, y, environmentTextures.findRegion("wall-block-tall"), 0, -25));
		}
		else if (type == ' ') {
			groundTiles.add(new Tile(x, y, environmentTextures.findRegion("grass"), 0, -25));
		}
		else if (type == 'M') {
			malcolm = new CharacterTile(CharacterName.MALCOLM, x, y);
			characterTiles.add(malcolm);
			groundTiles.add(new Tile(x, y, environmentTextures.findRegion("grass"), 0, -25));
		}
		else if (type == 'K') {
			kaylee = new CharacterTile(CharacterName.KAYLEE, x, y);
			characterTiles.add(kaylee);
			groundTiles.add(new Tile(x, y, environmentTextures.findRegion("grass"), 0, -25));
		}
		else if (type == 'W') {
			wash = new CharacterTile(CharacterName.WASH, x, y);
			characterTiles.add(wash);
			groundTiles.add(new Tile(x, y, environmentTextures.findRegion("grass"), 0, -25));
		}
		else if (type == 'g') {
			goal = new Tile(x, y, environmentTextures.findRegion("star"));
			objectTiles.add(goal);
			groundTiles.add(new Tile(x, y, environmentTextures.findRegion("grass"), 0, -25));
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
		environmentTextures.dispose();
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
	
	public void updateCharacterPosition(CharacterTile character, float dx, float dy) {
		character.move(dx, dy);
	}
	
	public void adjustCharacterPosition(CharacterTile character) {
		character.alignWithGrid();
	}

	public void turnCharacterLeft(CharacterTile character) {
		character.rotate(90);
	}

	public void turnCharacterRight(CharacterTile character) {
		character.rotate(-90);
	}

	public void turnCharacterAround(CharacterTile character) {
		character.rotate(180);
	}
}
