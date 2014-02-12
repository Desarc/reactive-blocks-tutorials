package no.ntnu.item.tutorials.exercise2_challenge.component;

import utils.PedestrianLight;
import utils.TrafficLight;
import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	private TrafficLight carLight;
	private PedestrianLight pedLight;

	public Component() {
		carLight = new TrafficLight();
		pedLight = new PedestrianLight();
	}
	
	public void displayCarLight() {
		
		carLight.show();
	}
	
	public void displayPedestrianLight() {
		pedLight.show();
	}
	
	public void setCarGreen() {
		carLight.setGreen();
	}

	public void setCarYellow() {
		carLight.setYellow();
	}
	
	public void setCarRed() {
		carLight.setRed();
	}
	
	public void setPedestrianGreen() {
		pedLight.setGreen();
	}
	
	public void setPedestrianRed() {
		pedLight.setRed();
	}
}