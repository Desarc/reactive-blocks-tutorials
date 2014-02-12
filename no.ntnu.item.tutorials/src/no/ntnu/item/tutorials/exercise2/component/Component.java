package no.ntnu.item.tutorials.exercise2.component;

import utils.PedestrianLight;
import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	private PedestrianLight light;
	
	public Component() {
		light = new PedestrianLight();
	}

	public void displayLight() {
		light.show();
	}

	public void setLightGreen() {
		light.setGreen();
	}
	
	public void setLightRed() {
		light.setRed();
	}
}