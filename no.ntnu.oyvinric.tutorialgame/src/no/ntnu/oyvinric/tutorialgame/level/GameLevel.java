package no.ntnu.oyvinric.tutorialgame.level;

import no.ntnu.oyvinric.tutorialgame.core.Constants;
import no.ntnu.oyvinric.tutorialgame.core.Constants.CharacterName;
import no.ntnu.oyvinric.tutorialgame.core.Constants.Direction;
import no.ntnu.oyvinric.tutorialgame.core.Constants.WinCondition;
import no.ntnu.oyvinric.tutorialgame.hud.UserInterfaceConfiguration;
import no.ntnu.oyvinric.tutorialgame.item.DoorToken;
import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.item.Key;
import no.ntnu.oyvinric.tutorialgame.core.Constants.KeyColor;
import no.ntnu.oyvinric.tutorialgame.tile.CharacterTile;
import no.ntnu.oyvinric.tutorialgame.tile.ChestTile;
import no.ntnu.oyvinric.tutorialgame.tile.DoorTile;
import no.ntnu.oyvinric.tutorialgame.tile.LeverTile;
import no.ntnu.oyvinric.tutorialgame.tile.LockTile;
import no.ntnu.oyvinric.tutorialgame.tile.StarTile;
import no.ntnu.oyvinric.tutorialgame.tile.TerrainTile;
import no.ntnu.oyvinric.tutorialgame.tile.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;

public abstract class GameLevel {
	
	private TextureAtlas gameTextures;
	protected TiledMap levelMap;
	protected int levelWidth, levelHeight, levelLayers;
	protected CharacterTile malcolm, lisa, andrew;
	protected Array<Array<Array<Tile>>> levelGrid;
	protected Array<CharacterTile> characterTiles;
	protected Array<StarTile> starTiles;
	protected Array<ChestTile> chestTiles;
	protected Array<LockTile> lockTiles;
	protected Array<LeverTile> leverTiles;
	protected Array<DoorTile> doorTiles;
	
	public GameLevel() {
		gameTextures = new TextureAtlas(Gdx.files.internal(Constants.GFX_PATH+"game-tiles.atlas"));
		
		loadLevel(getLevelNumber());
	}
	
	public abstract int getLevelNumber();
	
	public abstract UserInterfaceConfiguration getUserInterfaceConfiguration();
	
	public abstract Array<WinCondition> getWinConditions();
	
	public void loadLevel(int levelNumber) {
		levelMap = new TmxMapLoader().load(Constants.LEVEL_PATH+"level"+levelNumber+".tmx");
		levelGrid = new Array<Array<Array<Tile>>>();
		characterTiles = new Array<CharacterTile>();
		starTiles = new Array<StarTile>();
		chestTiles = new Array<ChestTile>();
		lockTiles = new Array<LockTile>();
		leverTiles = new Array<LeverTile>();
		doorTiles = new Array<DoorTile>();
		
		MapLayers mapLayers = levelMap.getLayers();
		levelLayers = mapLayers.getCount();
		TiledMapTileLayer tiledLayer;
		Cell cell;
		for (int i = 0; i < levelLayers; i++) {
			Array<Array<Tile>> gridLayer = new Array<Array<Tile>>();
			tiledLayer = (TiledMapTileLayer)mapLayers.get(i);
			for (int j = 0; j < tiledLayer.getWidth(); j++) {
				levelWidth = tiledLayer.getWidth();
				Array<Tile> gridRow = new Array<Tile>();
				for (int k = tiledLayer.getHeight(); k > 0; k--) {
					levelHeight = tiledLayer.getHeight();
					GridPosition gridPosition = new GridPosition(j, tiledLayer.getHeight()-k, i);
					cell = tiledLayer.getCell(j, k);
					if (cell != null) {
						Tile tile;
						String type = (String)cell.getTile().getProperties().get("type");
						if (type != null) {
							if (type.equals("character")) {
								String name = (String)cell.getTile().getProperties().get("name");
								if (name.equals(Constants.CharacterName.MALCOLM.getValue())) {
									malcolm = new CharacterTile(gridPosition, type, CharacterName.MALCOLM);
									tile = malcolm;
									characterTiles.add(malcolm);
								}
								else if (name.equals(CharacterName.LISA.getValue())) {
									lisa = new CharacterTile(gridPosition, type, CharacterName.LISA);
									tile = lisa;
									characterTiles.add(lisa);
								}
								else {
									andrew = new CharacterTile(gridPosition, type, CharacterName.ANDREW);
									tile = andrew;
									characterTiles.add(andrew);
								}
							}
							else if (type.equals("terrain")) {
								tile = new TerrainTile(gridPosition, type, cell.getTile().getTextureRegion());
							}
							else if (type.equals("star")) {
								tile = new StarTile(gridPosition, type, gameTextures.findRegion(type));
								starTiles.add((StarTile)tile);
							}
							else if (type.equals("chest")) {
								KeyColor keyColor = determineKeyColor(gridPosition);
								tile = new ChestTile(gridPosition, "chest", gameTextures.findRegion("chest-open"), gameTextures.findRegion("chest-closed"), new Key(keyColor, gameTextures.findRegion("key-"+keyColor.value())));
								chestTiles.add((ChestTile)tile);
							}
							else if (type.equals("lock")) {
								String color = (String)cell.getTile().getProperties().get("color");
								tile = new LockTile(gridPosition, type, gameTextures.findRegion(type+"-"+color), gameTextures.findRegion(type+"-"+color+"-unlocked"), KeyColor.valueOf(color.toUpperCase()));
								lockTiles.add((LockTile)tile);
							}
							else if (type.equals("lever")) {
								tile = new LeverTile(gridPosition, type, gameTextures.findRegion(type+"-left"), gameTextures.findRegion(type+"-right"), new DoorToken(gameTextures.findRegion(type+"-right"), true));
								leverTiles.add((LeverTile)tile);
							}
							else if (type.equals("door")) {
								String state = (String)cell.getTile().getProperties().get("state");
								tile = new DoorTile(gridPosition, type, gameTextures.findRegion(type+"-open"), gameTextures.findRegion(type+"-closed"), state);
								doorTiles.add((DoorTile)tile);
							}
							else {
								tile = new TerrainTile(gridPosition, type, cell.getTile().getTextureRegion());
							}
						}
						else {
							tile = new TerrainTile(gridPosition, null, cell.getTile().getTextureRegion());
						}
						gridRow.add(tile);
					}
					else {
						Tile tile = new TerrainTile(gridPosition, Constants.EMPTY_TILE);
						gridRow.add(tile);
					}
				}
				gridLayer.add(gridRow);
			}
			levelGrid.add(gridLayer);
		}
	}
	
	protected abstract KeyColor determineKeyColor(GridPosition position);
	
	public GridPosition findGridPosition(float coordsX, float coordsY, int z) {
		int x = (int)((coordsX-Constants.gameBoardHorizontalLeftLimit) / Constants.tileWidth);
		int y = (int)((Constants.gameBoardVerticalUpperLimit-coordsY) / Constants.tileHeight);
		return new GridPosition(x, y, z);
	}
	
	public Tile getTile(GridPosition gridPosition) {
		return levelGrid.get(gridPosition.getZ()).get(gridPosition.getX()).get(gridPosition.getY());
	}
	
	public Tile getTile(float coordsX, float coordsY, int z) {
		return getTile(findGridPosition(coordsX, coordsY, z));
	}
	
	public void addTile(Tile tile) {
		levelGrid.get(tile.getGridPosition().getZ()).get(tile.getGridPosition().getY()).add(tile);
	}
	
	public boolean removeTile(Tile tile) {
		return levelGrid.get(tile.getGridPosition().getZ()).get(tile.getGridPosition().getY()).removeValue(tile, false);
	}
	
	public boolean tileCanMove(Tile tile, float dx, float dy) {
		if (tile.getDirection() == Direction.WEST) {
			if (tile.getCoordsX()-dx <= Constants.gameBoardHorizontalLeftLimit) {
				return false;
			}
			Tile nextTile = getTile(tile.getCoordsX()-dx, tile.getCoordsY(), tile.getGridPosition().getZ());
			if (nextTile.getType() == null || !nextTile.isObstacle()) {
				return true;
			}
		}
		else if (tile.getDirection() == Direction.EAST) {
			if (tile.getCoordsX()+dx+Constants.tileWidth >= Constants.gameBoardHorizontalLeftLimit+Constants.tileWidth*levelWidth) {
				return false;
			}
			Tile nextTile = getTile(tile.getCoordsX()+dx+Constants.tileWidth, tile.getCoordsY(), tile.getGridPosition().getZ());
			if (nextTile.getType() == null || !nextTile.isObstacle()) {
				return true;
			}
		}
		else if (tile.getDirection() == Direction.SOUTH) {
			if (tile.getCoordsY()-dy <= Constants.gameBoardVerticalUpperLimit-Constants.tileHeight*levelHeight) {
				return false;
			}
			Tile enteringTile = getTile(tile.getCoordsX(), tile.getCoordsY()-dy-Constants.tileHeight*2, tile.getGridPosition().getZ());
			Gdx.app.debug("Entering tile", enteringTile.getType());
			if (enteringTile.getType() == null || !enteringTile.isObstacle()) {
				return true;
			}
		}
		else if (tile.getDirection() == Direction.NORTH) {
			if (tile.getCoordsY()+dy+Constants.tileHeight >= Constants.gameBoardVerticalUpperLimit) {
				return false;
			}
			Tile enteringTile = getTile(tile.getCoordsX()+dy, tile.getCoordsY()+dy+Constants.tileHeight, tile.getGridPosition().getZ());
			if (enteringTile.getType() == null || !enteringTile.isObstacle()) {
				return true;
			}
		}
		return false;
	}
	
	public Array<Array<Array<Tile>>> getLevelGrid() {
		return levelGrid;
	}
	
	public CharacterTile getMalcolm() {
		return malcolm;
	}
	
	public CharacterTile getLisa() {
		return lisa;
	}
	
	public CharacterTile getAndrew() {
		return andrew;
	}
	
	public Array<CharacterTile> getCharacterTiles() {
		return characterTiles;
	}
	
	public void updateCharacterPosition(CharacterTile character, float dx, float dy) {
		character.move(dx, dy);
		character.updateGridPosition();
	}
	
	public GameObject characterInteraction(CharacterTile character) {
		int x = character.getGridPosition().getX();
		int y = character.getGridPosition().getY();
		int z = character.getGridPosition().getZ();
		if (character.getDirection() == Constants.Direction.EAST) x += 2;
		else if (character.getDirection() == Constants.Direction.WEST) x -= 2;
		else if (character.getDirection() == Constants.Direction.NORTH) y -= 2;
		else if (character.getDirection() == Constants.Direction.SOUTH) y += 2;
		Tile interactingTile = getTile(new GridPosition(x, y, z));
		return interactingTile.interact();
	}
	
	public GameObject characterPickUp(CharacterTile character) {
		Tile interactingTile = getTile(character.getGridPosition());
		return interactingTile.interact();
	}
	
	public int getNumberOfStars() {
		return starTiles.size;
	}
	
	public void keyFound(Key key) {
		for (LockTile lock : lockTiles) {
			if (key.getColor() == lock.getColor()) {
				lock.setKeyAvailable(true);
			}
		}
	}
	
	public abstract void leverPulled(GridPosition position, boolean isActive);
	
	public void cleanUp() {
		gameTextures.dispose();
		levelMap.dispose();
	}
	

}
