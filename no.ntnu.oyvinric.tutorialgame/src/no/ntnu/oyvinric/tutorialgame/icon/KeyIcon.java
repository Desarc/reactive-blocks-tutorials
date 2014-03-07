package no.ntnu.oyvinric.tutorialgame.icon;

import no.ntnu.oyvinric.tutorialgame.gui.UserInterface.UserInterfacePosition;
import no.ntnu.oyvinric.tutorialgame.item.Key.KeyColor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class KeyIcon extends Icon {

	private KeyColor color;
	private boolean found = false;
	private Image notFoundImage;
	private Image foundImage;
	
	public KeyIcon(UserInterfacePosition position, Image notFoundImage, Image foundImage, KeyColor color) {
		super(position, notFoundImage);
		this.notFoundImage = notFoundImage;
		this.foundImage = foundImage;
		this.color = color;
	}
	
	public void keyFound() {
		found = true;
		setDrawable(foundImage);
	}
	
	public boolean matchColor(KeyColor color) {
		if (this.color == color) return true;
		return false;
	}

}
