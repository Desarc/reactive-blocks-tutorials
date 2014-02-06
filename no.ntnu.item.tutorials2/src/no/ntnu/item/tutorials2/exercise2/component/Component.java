package no.ntnu.item.tutorials2.exercise2.component;

import utils.TrafficLight;
import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	private TrafficLight light;

	public void displayLight() {
		light = new TrafficLight();
		light.show();
	}

	public void setLightGreen() {
		light.setGreen();
	}
	
	public void setLightRed() {
		light.setRed();
	}
}