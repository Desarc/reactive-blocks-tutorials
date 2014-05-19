package no.ntnu.oyvinric.tutorialgame.item;

import no.ntnu.oyvinric.tutorialgame.core.Constants.ItemType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DoorToken extends GameObject {
	
	boolean isActive;
	
	public DoorToken(TextureRegion image, boolean isActive) {
		super(ItemType.DOOR_TOKEN, image);
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
