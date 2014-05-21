package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class SpriteSheetPacker {

	public static void main(String[] args) {
		String[] texturePacks = {"fullscreens", "game-tiles", "tileset2", "malcolm", "lisa", "andrew", "wolf", "user-interface", "intro", "misc", "intro-level-01", "intro-level-02", "intro-level-03", "intro-level-04", "intro-level-05", "intro-level-06", "intro-level-07"};
		for (String texturePack : texturePacks) {
			TexturePacker2.process("images/"+texturePack, "resources/gfx", texturePack);
		}
	}
}
