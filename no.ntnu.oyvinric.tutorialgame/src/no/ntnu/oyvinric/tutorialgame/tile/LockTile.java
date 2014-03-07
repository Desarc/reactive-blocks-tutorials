package no.ntnu.oyvinric.tutorialgame.tile;

import no.ntnu.oyvinric.tutorialgame.item.GameObject;
import no.ntnu.oyvinric.tutorialgame.item.Key.KeyColor;
import no.ntnu.oyvinric.tutorialgame.level.GameLevel.GridPosition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LockTile extends Tile {
	
	private KeyColor color;
	private boolean locked;
	private TextureRegion unlockedImage;
	private boolean keyAvailable = false;

	public LockTile(GridPosition gridPosition, String type, TextureRegion lockedImage, TextureRegion unlockedImage, KeyColor color) {
		super(gridPosition, type, lockedImage);
		this.unlockedImage = unlockedImage;
		this.color = color;
		obstacle = true;
	}


	@Override
	public GameObject interact() {
		if (locked) {
			if (keyAvailable) {
				unlock();
			}
			else {
				System.out.println("Sorry, you need to find the key.");
			}
		}
		else {
			System.out.println("Already unlocked!");
		}
		return null;
	}
	
	private void unlock() {
		obstacle = false;
		locked = false;
		image = unlockedImage;
	}
	
	public void setKeyAvailable(boolean available) {
		this.keyAvailable = available;
	}
	
	public KeyColor getColor() {
		return color;
	}

}
