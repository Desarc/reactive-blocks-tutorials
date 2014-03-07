package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class SpriteSheetPacker {

	public static void main(String[] args) {
		String[] texturePacks = {"environment", "objects", "tileset", "malcolm", "kaylee", "wash", "wolf", "user-interface"};
		for (String texturePack : texturePacks) {
			TexturePacker2.process("resources/gfx/"+texturePack, "resources/gfx", texturePack);
		}
	}
}
