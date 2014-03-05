package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class AnimationPacker {

	public static void main(String[] args) {
		String texturePack = "malcolm";
		TexturePacker2.process("resources/gfx/"+texturePack, "resources/gfx", texturePack);
	}
}
