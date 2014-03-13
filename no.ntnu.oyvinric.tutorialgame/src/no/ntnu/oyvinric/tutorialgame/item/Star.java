package no.ntnu.oyvinric.tutorialgame.item;

import no.ntnu.oyvinric.tutorialgame.core.Constants.ItemType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Star extends GameObject {

	public Star(TextureRegion image) {
		super(ItemType.STAR, image);
	}

}
