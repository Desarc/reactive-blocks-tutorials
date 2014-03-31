package no.ntnu.oyvinric.tutorialgame.tile;

import no.ntnu.oyvinric.tutorialgame.core.Constants;
import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.item.Star;
import no.ntnu.oyvinric.tutorialgame.level.GridPosition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarTile extends Tile {
	
	boolean taken = false;
	
	public StarTile(GridPosition gridPosition, String type, TextureRegion image) {
		super(gridPosition, type, image);
	}

	@Override
	public GameObject interact() {
		if (!taken) {
			this.type = Constants.EMPTY_TILE;
			taken = true;
			return new Star(image);
		}
		return null;
	}

}
