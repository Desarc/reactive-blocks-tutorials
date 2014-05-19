package no.ntnu.oyvinric.tutorialgame.tile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.level.GridPosition;

public class DoorTile extends Tile {

	private boolean open;
	private TextureRegion openImage;
	private TextureRegion closedImage;
	
	public DoorTile(GridPosition gridPosition, String type, TextureRegion openImage, TextureRegion closedImage, String state) {
		super(gridPosition, type, closedImage);
		this.openImage = openImage;
		this.closedImage = closedImage;
		if (state == "open") {
			image = openImage;
			open = true;
			obstacle = false;
		}
		else {
			open = false;
			obstacle = true;
		}
	}

	@Override
	public GameObject interact() {
		return null;
	}
	
	public void open() {
		if (!open) {
			image = openImage;
			obstacle = false;
			open = true;
		}
	}
	
	public void close() {
		if (open) {
			image = closedImage;
			obstacle = true;
			open = false;
		}
	}

}
