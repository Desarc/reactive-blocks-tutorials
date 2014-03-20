package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GraphicsObject {
	
	protected float coordsX;
	protected float coordsY;
	protected float width, height;
	protected TextureRegion image;
	protected boolean active = true;
	
	public GraphicsObject(float coordsX, float coordsY, TextureRegion image) {
		this.coordsX = coordsX;
		this.coordsY = coordsY;
		this.width = image.getRegionWidth();
		this.height = image.getRegionHeight();
		this.image = image;
	}
	
	public void setPosition(float x, float y) {
		this.coordsX = x;
		this.coordsY = y;
	}
	
	public float getX() {
		return coordsX;
	}
	
	public float getY() {
		return coordsY;
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setSize(float width, float height) {
		this.width = width;
		this.height = height;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public TextureRegion getImage() {
		return image;
	}
	
	public boolean isActive() {
		return active;
	}
	

}
