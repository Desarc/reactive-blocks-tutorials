package no.ntnu.oyvinric.tutorialgame.tile;

import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.level.GridPosition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ChestTile extends Tile {

	private boolean open, empty;
	private GameObject content;
	private TextureRegion openImage;
	private TextureRegion closedImage;
	
	public ChestTile(GridPosition gridPosition, String type, TextureRegion openImage, TextureRegion closedImage, GameObject content) {
		super(gridPosition, type, closedImage);
		this.content = content;
		this.openImage = openImage;
		this.closedImage = closedImage;
		obstacle = true;
		open = false;
		empty = false;
	}

	@Override
	public GameObject interact() {
		if (open) {
			close();
			return null;
		}
		else {
			if (!empty) {
				return open();				
			}
			else {
				open();
				return null;
			}
		}
	}
	
	private GameObject open() {
		image = openImage;
		open = true;
		return content;
	}
	
	private void close() {
		image = closedImage;
		open = false;
	}

}
