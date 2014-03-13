package no.ntnu.oyvinric.tutorialgame.item;

import no.ntnu.oyvinric.tutorialgame.core.Constants.ItemType;
import no.ntnu.oyvinric.tutorialgame.core.Constants.KeyColor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Key extends GameObject {
	
	private KeyColor color;
	
	public Key(KeyColor color, TextureRegion image) {
		super(ItemType.KEY, image);
		this.color = color;
	}
	
	public KeyColor getColor() {
		return color;
	}
}
