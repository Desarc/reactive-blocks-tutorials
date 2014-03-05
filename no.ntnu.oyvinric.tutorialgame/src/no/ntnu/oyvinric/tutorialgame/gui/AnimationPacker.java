package no.ntnu.oyvinric.tutorialgame.gui;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class AnimationPacker {

	public static void main(String[] args) {
		TexturePacker2.process("resources/gfx/malcolm", "resources/gfx", "malcolm-walk");
	}
}
