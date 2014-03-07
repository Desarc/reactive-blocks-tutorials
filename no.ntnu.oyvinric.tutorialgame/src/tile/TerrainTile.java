package tile;

import items.GameObject;
import level.GameLevel.GridPosition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TerrainTile extends Tile {
		
	public TerrainTile(GridPosition gridPosition, String type, TextureRegion image) {
		super(gridPosition, type, image);
		obstacle = true;
	}
	
	public TerrainTile(GridPosition gridPosition, String type) {
		super(gridPosition, type);
		if (!type.equals(EMPTY)) {
			obstacle = true;			
		}
	}

	@Override
	public GameObject interact() {
		System.out.println("Can't interact with the terrain.");
		return null;
	}
	
}
