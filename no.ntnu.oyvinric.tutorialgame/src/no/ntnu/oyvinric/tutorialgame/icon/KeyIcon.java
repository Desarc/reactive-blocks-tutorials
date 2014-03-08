package no.ntnu.oyvinric.tutorialgame.icon;

import no.ntnu.oyvinric.tutorialgame.gui.UserInterface.UserInterfacePosition;
import no.ntnu.oyvinric.tutorialgame.item.Key.KeyColor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class KeyIcon extends Icon {

	private KeyColor color;
	private TextureRegion image;
	private TextureRegion foundImage;
	
	public KeyIcon(UserInterfacePosition position, TextureRegion notFoundImage, TextureRegion foundImage, KeyColor color) {
		super(position);
		this.image = notFoundImage;
		this.foundImage = foundImage;
		this.color = color;
	}
	
	public void keyFound() {
		image = foundImage;
	}
	
	public boolean matchColor(KeyColor color) {
		if (this.color == color) return true;
		return false;
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(image, position.getX(), position.getY());
	}

}
