package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GraphicsObject {
	
	protected float coordsX;
	protected float coordsY;
	protected TextureRegion image;
	protected boolean active = true;
	
	public GraphicsObject(float coordsX, float coordsY, TextureRegion image) {
		this.coordsX = coordsX;
		this.coordsY = coordsY;
		this.image = image;
	}
	
	public float getX() {
		return coordsX;
	}
	
	public float getY() {
		return coordsY;
	}
	
	public TextureRegion getImage() {
		return image;
	}
	
	public boolean isActive() {
		return active;
	}
	

}
