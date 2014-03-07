package tile;

import items.GameObject;
import items.Star;
import level.GameLevel.GridPosition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarTile extends Tile {
	
	public StarTile(GridPosition gridPosition, String type, TextureRegion image) {
		super(gridPosition, type, image);
	}

	@Override
	public GameObject interact() {
		this.type = Tile.EMPTY;
		return new Star(image);
	}

}
