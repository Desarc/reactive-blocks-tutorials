package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class SpriteSheetPacker {

	public static void main(String[] args) {
		String[] texturePacks = {"game-tiles", "tileset", "malcolm", "lisa", "andrew", "wolf", "user-interface", "intro", "misc", "intro-level1", "intro-level2"};
		for (String texturePack : texturePacks) {
			TexturePacker2.process("resources/gfx/"+texturePack, "resources/gfx", texturePack);
		}
	}
}
