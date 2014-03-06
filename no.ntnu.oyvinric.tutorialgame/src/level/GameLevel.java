package level;

import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile;
import no.ntnu.oyvinric.tutorialgame.gui.EnvironmentTile;
import no.ntnu.oyvinric.tutorialgame.gui.ObjectTile;
import no.ntnu.oyvinric.tutorialgame.gui.Tile;
import no.ntnu.oyvinric.tutorialgame.gui.CharacterTile.CharacterName;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;

public abstract class GameLevel {
	
	//private TextureAtlas environmentTextures, objectTextures;
	protected TiledMap levelMap;
	protected CharacterTile malcolm, kaylee, wash;
	protected Array<CharacterTile> characterTiles;
	protected Array<ObjectTile> objectTiles;
	protected Array<Array<Array<Tile>>> levelGrid;
	protected Array<ObjectTile> stars;
	
	public GameLevel() {
		//environmentTextures = new TextureAtlas(Gdx.files.internal("resources/gfx/environment.atlas"));
		//objectTextures = new TextureAtlas(Gdx.files.internal("resources/gfx/objects.atlas"));
		
		loadLevel(getLevelNumber());
	}
	
	public abstract int getLevelNumber();
	
	public void loadLevel(int levelNumber) {
		levelMap = new TmxMapLoader().load("resources/levels/level"+levelNumber+".tmx");
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
						GridPosition gridPosition = new GridPosition(j, tiledLayer.getHeight()-k, i);
						String type = (String)cell.getTile().getProperties().get("type");
						if (type != null) {
							if (type.equals("character")) {
								String name = (String)cell.getTile().getProperties().get("name");
								if (name.equals("malcolm")) {
									malcolm = new CharacterTile(gridPosition, CharacterName.MALCOLM);
									tile = malcolm;
									characterTiles.add(malcolm);
									System.out.println("malcolm is in the game!");
								}
								else if (name.equals("kaylee")) {
									kaylee = new CharacterTile(gridPosition, CharacterName.KAYLEE);
									tile = kaylee;
									characterTiles.add(kaylee);
								}
								else {
									wash = new CharacterTile(gridPosition, CharacterName.WASH);
									tile = wash;
									characterTiles.add(wash);
								}
							}
							else {
								String name = (String)cell.getTile().getProperties().get("name");
								tile = new EnvironmentTile(gridPosition, cell.getTile().getTextureRegion());
							}
						}
						else {
							tile = new EnvironmentTile(gridPosition, cell.getTile().getTextureRegion());
						}
						if (tile != null) {
							gridRow.add(tile);
						}
					}
				}
				gridLayer.add(gridRow);
			}
			levelGrid.add(gridLayer);
		}
	}
	
	public Tile getTile(GridPosition gridPosition) {
		return levelGrid.get(gridPosition.getZ()).get(gridPosition.getY()).get(gridPosition.getX());
	}
	
	public void addTile(Tile tile) {
		levelGrid.get(tile.getGridPosition().getZ()).get(tile.getGridPosition().getY()).add(tile);
	}
	
	public boolean removeTile(Tile tile) {
		return levelGrid.get(tile.getGridPosition().getZ()).get(tile.getGridPosition().getY()).removeValue(tile, false);
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
