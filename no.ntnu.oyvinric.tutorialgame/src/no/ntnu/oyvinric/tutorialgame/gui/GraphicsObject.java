package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GraphicsObject {
	
	private float coordsX;
	private float coordsY;
	private TextureRegion image;
	
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
	

}
