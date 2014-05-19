package no.ntnu.oyvinric.tutorialgame.tile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import no.ntnu.oyvinric.tutorialgame.item.DoorToken;
import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.level.GridPosition;

public class LeverTile extends Tile {

	private boolean active;
	private DoorToken doorToken;
	private TextureRegion inactiveImage;
	private TextureRegion activeImage;
	
	public LeverTile(GridPosition gridPosition, String type, TextureRegion inactiveImage, TextureRegion activeImage, DoorToken doorToken) {
		super(gridPosition, type, inactiveImage);
		this.inactiveImage = inactiveImage;
		this.activeImage = activeImage;
		obstacle = true;
		active = false;
		this.doorToken = doorToken;
	}

	@Override
	public GameObject interact() {
		if (active) {
			return deactivate();
		}
		else {
			return activate();
		}
	}
	
	private GameObject activate() {
		image = activeImage;
		active = true;
		doorToken.setActive(true);
		return doorToken;
	}
	
	private GameObject deactivate() {
		image = inactiveImage;
		active = false;
		doorToken.setActive(false);
		return doorToken;
	}
	
	public boolean leverIsActive() {
		return active;
	}

}
