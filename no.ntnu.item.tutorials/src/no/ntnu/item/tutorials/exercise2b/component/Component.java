package no.ntnu.item.tutorials.exercise2b.component;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	private JFrame frame;
	private JLabel carLight, pedestrianLight;
	private ImageIcon redLightCar, greenLightCar, yellowLightCar;
	private ImageIcon greenLightPedestrian, redLightPedestrian;

	public Component() {
		frame = new JFrame("Lights");
		frame.setBounds(100, 100, 200, 200);
		redLightCar = new ImageIcon("../resources/red_light.jpg");
		yellowLightCar = new ImageIcon("../resources/car_yellow.jpg");
		greenLightCar = new ImageIcon("../resources/green_light.jpg");
		carLight = new JLabel();
		frame.getContentPane().setLayout(new GridLayout(2,1));
		frame.getContentPane().add(carLight);
		
		redLightPedestrian = new ImageIcon("../resources/pedestrian_red.jpg");
		greenLightPedestrian = new ImageIcon("../resources/pedestrian_green.jpg");
		pedestrianLight = new JLabel();
		frame.getContentPane().add(pedestrianLight);
		frame.setVisible(true);
	}
	
	public void setCarGreen() {
		carLight.setIcon(greenLightCar);
	}

	public void setCarYellow() {
		carLight.setIcon(yellowLightCar);
	}
	
	public void setCarRed() {
		carLight.setIcon(redLightCar);
	}
	
	public void setPedestrianGreen() {
		pedestrianLight.setIcon(greenLightPedestrian);
	}
	
	public void setPedestrianRed() {
		pedestrianLight.setIcon(redLightPedestrian);
	}
}
