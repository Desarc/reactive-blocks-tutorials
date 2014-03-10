package no.ntnu.oyvinric.tutorialgame.tile;

import no.ntnu.oyvinric.tutorialgame.core.Constants;
import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.level.GridPosition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TerrainTile extends Tile {
		
	public TerrainTile(GridPosition gridPosition, String type, TextureRegion image) {
		super(gridPosition, type, image);
		obstacle = true;
	}
	
	public TerrainTile(GridPosition gridPosition, String type) {
		super(gridPosition, type);
		if (!type.equals(Constants.EMPTY_TILE)) {
			obstacle = true;			
		}
	}

	@Override
	public GameObject interact() {
		System.out.println("Can't interact with the terrain.");
		return null;
	}
	
}
