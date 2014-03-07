package no.ntnu.oyvinric.tutorialgame.tile;

import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.item.Star;
import no.ntnu.oyvinric.tutorialgame.level.GameLevel.GridPosition;

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
