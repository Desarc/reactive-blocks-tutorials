package no.ntnu.oyvinric.tutorialgame.item;

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

	
	
	public enum KeyColor {
		BLUE("blue"),
		YELLOW("yellow"),
		RED("red"),
		GREEN("green");
		
		private String color;
		
		private KeyColor(String color) {
			this.color = color;
		}
		
		public String value() {
			return color;
		}
	}
}
