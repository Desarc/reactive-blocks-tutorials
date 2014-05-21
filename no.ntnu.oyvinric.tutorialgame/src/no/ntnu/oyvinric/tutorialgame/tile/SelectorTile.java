package no.ntnu.oyvinric.tutorialgame.tile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.level.GridPosition;

public class SelectorTile extends Tile {

	public SelectorTile(GridPosition gridPosition, String type, TextureRegion image) {
		super(gridPosition, type, image);
		obstacle = false;
	}

	@Override
	public GameObject interact() {
		// TODO Auto-generated method stub
		return null;
	}

}
