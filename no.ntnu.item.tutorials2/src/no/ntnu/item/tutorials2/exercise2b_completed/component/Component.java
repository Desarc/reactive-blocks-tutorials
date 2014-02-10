package no.ntnu.item.tutorials2.exercise2b_completed.component;

import utils.PedestrianLight;
import utils.TrafficLight;
import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	private TrafficLight carLight;
	private PedestrianLight pedLight;

	public void displayCarLight() {
		carLight = new TrafficLight();
		carLight.show();
	}
	
	public void displayPedestrianLight() {
		pedLight = new PedestrianLight();
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