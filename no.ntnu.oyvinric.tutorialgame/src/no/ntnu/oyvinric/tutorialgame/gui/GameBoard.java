package no.ntnu.oyvinric.tutorialgame.gui;

import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile.CharacterName;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile;
import no.ntnu.oyvinric.tutorialgame.gui.Tile;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

public class GameBoard {

	public static final float tileHeight = 13f;
	public static final float tileWidth = 32f;
	public static final float stackingHeight = 26f;
	public static final int horizontalLeftLimit = 0;
	public static final int verticalUpperLimit = 460;
	TutorialGame parent;
	
//	TextureAtlas environmentTextures;
//	TextureAtlas objectTextures;
	OrthographicCamera camera;
	TiledMap levelMap;
	IsometricTiledMapRenderer renderer;
	SpriteBatch batch;
	CharacterTile malcolm, kaylee, wash;
	Array<CharacterTile> characterTiles;
	Tile goal;
	
	boolean active = true;
	
	public GameBoard(TutorialGame parent, int levelNo) {
		this.parent = parent;
		
//		environmentTextures = new TextureAtlas(Gdx.files.internal("resources/gfx/environment.atlas"));
//		objectTextures = new TextureAtlas(Gdx.files.internal("resources/gfx/objects.atlas"));
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		camera.update();
		
		batch = new SpriteBatch();
		
		loadLevel(levelNo);
		
	}
	
	public void loadLevel(int levelNo) {
		levelMap = new TmxMapLoader().load("resources/levels/level"+levelNo+".tmx");
		characterTiles = new Array<CharacterTile>();
		
		MapLayers layers = levelMap.getLayers();
		TiledMapTileLayer tiledLayer;
		Cell cell;
		for (int i = 0; i < layers.getCount(); i++) {
			tiledLayer = (TiledMapTileLayer)layers.get(i);
			for (int j = 0; j < tiledLayer.getWidth(); j++) {
				for (int k = 0; k < tiledLayer.getHeight(); k++) {
					cell = tiledLayer.getCell(j, k);
					if (cell != null) {
						String type = (String)cell.getTile().getProperties().get("type");
						if (type != null && type.equals("character")) {
							String name = (String)cell.getTile().getProperties().get("name");
							if (name.equals("malcolm")) {
								malcolm = new CharacterTile(CharacterName.MALCOLM, horizontalLeftLimit+j*tileWidth, verticalUpperLimit-(k-1)*tileHeight-(layers.getCount()-i)*stackingHeight, i);
								characterTiles.add(malcolm);
							}
							else if (name.equals("kaylee")) {
								kaylee = new CharacterTile(CharacterName.KAYLEE, horizontalLeftLimit+j*tileWidth, verticalUpperLimit-(k-1)*tileHeight-(layers.getCount()-i)*stackingHeight, i);
								characterTiles.add(kaylee);
							}
						}
					}
				}
			}
		}
	}
	
	private void drawLevel() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		MapLayers layers = levelMap.getLayers();
		TiledMapTileLayer tiledLayer;
		Cell cell;
		for (int i = 0; i < layers.getCount(); i++) {
			tiledLayer = (TiledMapTileLayer)layers.get(i);
			for (int j = 0; j < tiledLayer.getWidth(); j++) {
				for (int k = 0; k < tiledLayer.getHeight(); k++) {
					cell = tiledLayer.getCell(j, k);
					if (cell != null) {
						batch.draw(cell.getTile().getTextureRegion(), horizontalLeftLimit+j*tileWidth, verticalUpperLimit-k*tileHeight-(layers.getCount()-i)*stackingHeight);
					}
				}
			}
		}
		
		for (CharacterTile character : characterTiles) {
			batch.draw(character.getImage(), character.getX(), character.getY(), character.getOriginX(), character.getOriginY(), character.getWidth(), character.getHeight(), character.getScaleFactor(), character.getScaleFactor(), character.getRotation());
		}
		
		batch.end();
	}
	
	public void redraw() {
		drawLevel();
	}
	
	public void cleanUp() {
//		environmentTextures.dispose();
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
		//character.alignWithGrid();
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
