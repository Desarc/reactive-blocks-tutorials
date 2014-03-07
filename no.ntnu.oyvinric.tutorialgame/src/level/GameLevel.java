package level;

import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile;
import no.ntnu.oyvinric.tutorialgame.gui.EnvironmentTile;
import no.ntnu.oyvinric.tutorialgame.gui.GameBoard;
import no.ntnu.oyvinric.tutorialgame.gui.ObjectTile;
import no.ntnu.oyvinric.tutorialgame.gui.Tile;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile.CharacterName;
import no.ntnu.oyvinric.tutorialgame.gui.Tile.Direction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;

public abstract class GameLevel {
	
	private TextureAtlas environmentTextures, objectTextures;
	protected TiledMap levelMap;
	protected int levelWidth, levelHeight, levelLayers;
	protected CharacterTile malcolm, kaylee, wash;
	protected Array<CharacterTile> characterTiles;
	protected Array<ObjectTile> objectTiles;
	protected Array<Array<Array<Tile>>> levelGrid;
	protected Array<ObjectTile> stars;
	
	public GameLevel() {
		environmentTextures = new TextureAtlas(Gdx.files.internal("resources/gfx/environment.atlas"));
		objectTextures = new TextureAtlas(Gdx.files.internal("resources/gfx/objects.atlas"));
		
		loadLevel(getLevelNumber());
	}
	
	public abstract int getLevelNumber();
	
	public void loadLevel(int levelNumber) {
		levelMap = new TmxMapLoader().load("resources/levels/level"+levelNumber+".tmx");
		characterTiles = new Array<CharacterTile>();
		objectTiles = new Array<ObjectTile>();
		levelGrid = new Array<Array<Array<Tile>>>();
		
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
							else if (type.equals("object")) {
								String name = (String)cell.getTile().getProperties().get("name");
								tile = new ObjectTile(gridPosition, name, objectTextures.findRegion(name));
							}
							else if (type.equals("environment")) {
								String name = (String)cell.getTile().getProperties().get("name");
								tile = new EnvironmentTile(gridPosition, name, environmentTextures.findRegion(name));
							}
							else {
								tile = new EnvironmentTile(gridPosition, type, environmentTextures.findRegion("grass"));
							}
							
						}
						else {
							tile = new EnvironmentTile(gridPosition, null, cell.getTile().getTextureRegion());
						}
						gridRow.add(tile);
					}
					else {
						Tile tile = new EnvironmentTile(gridPosition, Tile.EMPTY);
						gridRow.add(tile);
					}
				}
				gridLayer.add(gridRow);
			}
			levelGrid.add(gridLayer);
		}
	}
	
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
			if (tile.getX()-dx <= GameBoard.horizontalLeftLimit) {
				return false;
			}
			Tile nextTile = getTile(tile.getX()-dx, tile.getY(), tile.getGridPosition().getZ());
			if (nextTile.getType() == null || !nextTile.isObstacle()) {
				return true;
			}
		}
		else if (tile.getDirection() == Direction.EAST) {
			if (tile.getX()+dx+GameBoard.tileWidth >= GameBoard.horizontalLeftLimit+GameBoard.tileWidth*(levelWidth+1)) {
				return false;
			}
			Tile nextTile = getTile(tile.getX()+dx+GameBoard.tileWidth, tile.getY(), tile.getGridPosition().getZ());
			if (nextTile.getType() == null || !nextTile.isObstacle()) {
				return true;
			}
		}
		else if (tile.getDirection() == Direction.SOUTH) {
			if (tile.getY()-dy <= GameBoard.horizontalLeftLimit-GameBoard.tileHeight*(levelHeight)+1) {
				return false;
			}
			Tile enteringTile = getTile(tile.getX(), tile.getY()-dy, tile.getGridPosition().getZ());
			if (enteringTile.getType() == null || !enteringTile.isObstacle()) {
				
			}
		}
		else if (tile.getDirection() == Direction.NORTH) {
			if (tile.getY()+dy+GameBoard.tileHeight >= GameBoard.verticalUpperLimit) {
				return false;
			}
			Tile enteringTile = getTile(tile.getX()+dx, tile.getY()+dy+GameBoard.tileHeight, tile.getGridPosition().getZ());
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
	
	public class GridPosition {
		
		int x;
		int y;
		int z;
		
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
