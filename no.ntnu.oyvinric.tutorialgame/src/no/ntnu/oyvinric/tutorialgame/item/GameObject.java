package no.ntnu.oyvinric.tutorialgame.item;

import no.ntnu.oyvinric.tutorialgame.core.Constants.ItemType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class GameObject {
	
	private ItemType type;
	private TextureRegion image;
	
	public GameObject (ItemType type, TextureRegion image) {
		this.type = type;
		this.image = image;
	}
	
	public ItemType getType() {
		return type;
	}
	
	public TextureRegion getImage() {
		return image;
	}

}
