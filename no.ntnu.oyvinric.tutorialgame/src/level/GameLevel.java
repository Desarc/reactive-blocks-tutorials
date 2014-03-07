package level;

import items.GameObject;
import items.Key;
import items.Key.KeyColor;
import tile.CharacterTile;
import tile.ChestTile;
import tile.LockTile;
import tile.TerrainTile;
import tile.StarTile;
import tile.Tile;
import tile.CharacterTile.CharacterName;
import tile.Tile.Direction;
import no.ntnu.oyvinric.tutorialgame.gui.GameBoard;

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
	protected CharacterTile malcolm, kaylee, wash;
	protected Array<Array<Array<Tile>>> levelGrid;
	protected Array<CharacterTile> characterTiles;
	protected Array<StarTile> starTiles;
	protected Array<ChestTile> chestTiles;
	protected Array<LockTile> lockTiles;
	
	public GameLevel() {
		gameTextures = new TextureAtlas(Gdx.files.internal("resources/gfx/game-tiles.atlas"));
		
		loadLevel(getLevelNumber());
	}
	
	public abstract int getLevelNumber();
	
	public void loadLevel(int levelNumber) {
		levelMap = new TmxMapLoader().load("resources/levels/level"+levelNumber+".tmx");
		levelGrid = new Array<Array<Array<Tile>>>();
		characterTiles = new Array<CharacterTile>();
		starTiles = new Array<StarTile>();
		chestTiles = new Array<ChestTile>();
		lockTiles = new Array<LockTile>();
		
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
								if (name.equals(CharacterName.MALCOLM.getValue())) {
									malcolm = new CharacterTile(gridPosition, type, CharacterName.MALCOLM);
									tile = malcolm;
									characterTiles.add(malcolm);
								}
								else if (name.equals(CharacterName.KAYLEE.getValue())) {
									kaylee = new CharacterTile(gridPosition, type, CharacterName.KAYLEE);
									tile = kaylee;
									characterTiles.add(kaylee);
								}
								else {
									wash = new CharacterTile(gridPosition, type, CharacterName.WASH);
									tile = wash;
									characterTiles.add(wash);
								}
							}
							else if (type.equals("terrain")) {
								//String name = (String)cell.getTile().getProperties().get("name");
								//tile = new TerrainTile(gridPosition, name, objectTextures.findRegion(name));
								tile = new TerrainTile(gridPosition, type, cell.getTile().getTextureRegion());
							}
							else if (type.equals("star")) {
								tile = new StarTile(gridPosition, type, gameTextures.findRegion(type));
								starTiles.add((StarTile)tile);
							}
							else if (type.equals("chest-closed")) {
								KeyColor keyColor = determineKeyColor(gridPosition);
								tile = new ChestTile(gridPosition, "chest", gameTextures.findRegion("chest-open"), gameTextures.findRegion("chest-closed"), new Key(keyColor, gameTextures.findRegion("key-"+keyColor.value())));
								chestTiles.add((ChestTile)tile);
							}
							else if (type.equals("lock")) {
								String color = (String)cell.getTile().getProperties().get("color");
								tile = new LockTile(gridPosition, type, gameTextures.findRegion(type+"-"+color), gameTextures.findRegion(type+"-"+color+"-unlocked"), KeyColor.valueOf(color));
								lockTiles.add((LockTile)tile);
							}
							else {
								tile = new TerrainTile(gridPosition, type, gameTextures.findRegion("grass"));
							}
							
						}
						else {
							tile = new TerrainTile(gridPosition, null, cell.getTile().getTextureRegion());
						}
						gridRow.add(tile);
					}
					else {
						Tile tile = new TerrainTile(gridPosition, Tile.EMPTY);
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
		int x = (int)((coordsX-GameBoard.horizontalLeftLimit) / GameBoard.tileWidth);
		int y = (int)((GameBoard.verticalUpperLimit-coordsY) / GameBoard.tileHeight);
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
	
	public boolean tileCanMove(Tile tile, Direction direction, float dx, float dy) {
		if (tile.getDirection() == Direction.WEST) {
			if (tile.getCoordsX()-dx <= GameBoard.horizontalLeftLimit) {
				return false;
			}
			Tile nextTile = getTile(tile.getCoordsX()-dx, tile.getCoordsY(), tile.getGridPosition().getZ());
			if (nextTile.getType() == null || !nextTile.isObstacle()) {
				return true;
			}
		}
		else if (tile.getDirection() == Direction.EAST) {
			if (tile.getCoordsX()+dx+GameBoard.tileWidth >= GameBoard.horizontalLeftLimit+GameBoard.tileWidth*levelWidth) {
				return false;
			}
			Tile nextTile = getTile(tile.getCoordsX()+dx+GameBoard.tileWidth, tile.getCoordsY(), tile.getGridPosition().getZ());
			if (nextTile.getType() == null || !nextTile.isObstacle()) {
				return true;
			}
		}
		else if (tile.getDirection() == Direction.SOUTH) {
			if (tile.getCoordsY()-dy <= GameBoard.horizontalLeftLimit-GameBoard.tileHeight*levelHeight) {
				return false;
			}
			Tile enteringTile = getTile(tile.getCoordsX(), tile.getCoordsY()-dy, tile.getGridPosition().getZ());
			if (enteringTile.getType() == null || !enteringTile.isObstacle()) {
				
			}
		}
		else if (tile.getDirection() == Direction.NORTH) {
			if (tile.getCoordsY()+dy+GameBoard.tileHeight >= GameBoard.verticalUpperLimit) {
				return false;
			}
			Tile enteringTile = getTile(tile.getCoordsX()+dx, tile.getCoordsY()+dy+GameBoard.tileHeight, tile.getGridPosition().getZ());
			if (enteringTile.getType() == null || !enteringTile.isObstacle()) {
				
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
		character.updateGridPosition();
	}
	
	public GameObject characterInteraction(CharacterTile character) {
		int x = character.getGridPosition().getX();
		int y = character.getGridPosition().getY();
		int z = character.getGridPosition().getZ();
		if (character.getDirection() == Direction.EAST) x++;
		else if (character.getDirection() == Direction.WEST) x--;
		else if (character.getDirection() == Direction.NORTH) y--;
		else if (character.getDirection() == Direction.SOUTH) y++;
		Tile interactingTile = getTile(new GridPosition(x, y, z));
		return interactingTile.interact();
	}
	
	public GameObject pickUp(CharacterTile character) {
		//System.out.println(character.getGridPosition().getX()+","+character.getGridPosition().getY()+","+character.getGridPosition().getZ());
		Tile interactingTile = getTile(character.getGridPosition());
		return interactingTile.interact();
	}
	
	public int getNumberOfStars() {
		return starTiles.size;
	}
	
//	public void adjustCharacterPosition(CharacterTile character) {
//		character.alignWithGrid();
//	}
	
	public class GridPosition {
		
		private int x;
		private int y;
		private int z;
		
		public GridPosition(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getZ() {
			return z;
		}

		public void setZ(int z) {
			this.z = z;
		}
		
	}

}
