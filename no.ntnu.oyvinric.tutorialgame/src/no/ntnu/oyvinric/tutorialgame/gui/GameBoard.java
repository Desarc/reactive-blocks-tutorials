package no.ntnu.oyvinric.tutorialgame.gui;

import no.ntnu.oyvinric.tutorialgame.core.TutorialGame;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile.CharacterName;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile;
import no.ntnu.oyvinric.tutorialgame.gui.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

public class GameBoard {

	public static final int windowWidth = 800;
	public static final int windowHeight = 480;
	public static final float tileHeight = 13f;
	public static final float tileWidth = 32f;
	public static final float stackingHeight = 26f;
	public static final int horizontalLeftLimit = 0;
	public static final int verticalUpperLimit = windowHeight+30;
	TutorialGame parent;
	
	TextureAtlas environmentTextures, objectTextures;
	OrthographicCamera camera;
	TiledMap levelMap;
	IsometricTiledMapRenderer renderer;
	SpriteBatch batch;
	CharacterTile malcolm, kaylee, wash;
	Array<CharacterTile> characterTiles;
	Array<ObjectTile> objectTiles;
	Array<Array<Array<Tile>>> levelGrid;
	Tile goal;
	
	boolean active = true;
	
	public GameBoard(TutorialGame parent, int levelNo) {
		this.parent = parent;
		
		environmentTextures = new TextureAtlas(Gdx.files.internal("resources/gfx/environment.atlas"));
		objectTextures = new TextureAtlas(Gdx.files.internal("resources/gfx/objects.atlas"));
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, windowWidth, windowHeight);
		camera.update();
		
		batch = new SpriteBatch();
		
		loadLevel(levelNo);
		
	}
	
	public void loadLevel(int levelNo) {
		levelMap = new TmxMapLoader().load("resources/levels/level"+levelNo+".tmx");
		characterTiles = new Array<CharacterTile>();
		objectTiles = new Array<ObjectTile>();
		levelGrid = new Array<Array<Array<Tile>>>();
		
		MapLayers layers = levelMap.getLayers();
		TiledMapTileLayer tiledLayer;
		Cell cell;
		for (int i = 0; i < layers.getCount(); i++) {
			Array<Array<Tile>> gridLayer = new Array<Array<Tile>>();
			tiledLayer = (TiledMapTileLayer)layers.get(i);
			for (int j = 0; j < tiledLayer.getWidth(); j++) {
				Array<Tile> gridRow = new Array<Tile>();
				for (int k = tiledLayer.getHeight(); k > 0; k--) {
					cell = tiledLayer.getCell(j, k);
					if (cell != null) {
						Tile tile;
						String type = (String)cell.getTile().getProperties().get("type");
						if (type != null) {
							if (type.equals("character")) {
								String name = (String)cell.getTile().getProperties().get("name");
								if (name.equals("malcolm")) {
									malcolm = new CharacterTile(CharacterName.MALCOLM);
									tile = malcolm;
									characterTiles.add(malcolm);
								}
								else if (name.equals("kaylee")) {
									kaylee = new CharacterTile(CharacterName.KAYLEE);
									tile = kaylee;
									characterTiles.add(kaylee);
								}
								else {
									wash = new CharacterTile(CharacterName.WASH);
									tile = wash;
									characterTiles.add(wash);
								}
							}
							else {
								String name = (String)cell.getTile().getProperties().get("name");
								tile = new EnvironmentTile(environmentTextures.findRegion(name));
							}
						}
						else {
							tile = new EnvironmentTile(environmentTextures.findRegion("grass"));
						}
						if (tile != null) {
							tile.setPosition(horizontalLeftLimit+j*tileWidth, verticalUpperLimit-(tiledLayer.getHeight()-k)*tileHeight-(layers.getCount()-i)*stackingHeight, i);
							gridRow.add(tile);
						}
					}
				}
				gridLayer.add(gridRow);
			}
			levelGrid.add(gridLayer);
		}
	}
	
	private void drawLevel() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
//		MapLayers layers = levelMap.getLayers();
//		TiledMapTileLayer tiledLayer;
//		Cell cell;
//		for (int i = 0; i < layers.getCount(); i++) {
//			tiledLayer = (TiledMapTileLayer)layers.get(i);
//			for (int j = 0; j < tiledLayer.getWidth(); j++) {
//				for (int k = 0; k < tiledLayer.getHeight(); k++) {
//					cell = tiledLayer.getCell(j, k);
//					if (cell != null) {
//						batch.draw(cell.getTile().getTextureRegion(), horizontalLeftLimit+j*tileWidth, verticalUpperLimit-k*tileHeight-(layers.getCount()-i)*stackingHeight);
//					}
//				}
//			}
//		}
//		
//		for (CharacterTile character : characterTiles) {
//			batch.draw(character.getImage(), character.getX(), character.getY(), character.getOriginX(), character.getOriginY(), character.getWidth(), character.getHeight(), character.getScaleFactor(), character.getScaleFactor(), character.getRotation());
//		}
		
		for (Array<Array<Tile>> gridLayer : levelGrid) {
			for (Array<Tile> gridRow : gridLayer) {
				for (Tile tile : gridRow) {
					batch.draw(tile.getImage(), tile.getX(), tile.getY(), tile.getOriginX(), tile.getOriginY(), tile.getWidth(), tile.getHeight(), tile.getScaleFactor(), tile.getScaleFactor(), tile.getRotation());
				}
			}
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
