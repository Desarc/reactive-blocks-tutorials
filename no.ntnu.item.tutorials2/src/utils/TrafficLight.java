package utils;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TrafficLight {

	private JFrame frame;
	private JLabel light;
	private ImageIcon redLight, greenLight, yellowLight;

	public TrafficLight() {
		frame = new JFrame("Traffic light");
		frame.setBounds(100, 150, 100, 100);
		redLight = new ImageIcon("../resources/red_light.jpg");
		greenLight = new ImageIcon("../resources/green_light.jpg");
		yellowLight = new ImageIcon("../resources/car_yellow.jpg");
		light = new JLabel();
		frame.getContentPane().setLayout(new GridLayout(1,1));
		frame.getContentPane().add(light);
	}
	
	public void show() {
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}
	
	public void setGreen() {
		light.setIcon(greenLight);
	}
	
	public void setRed() {
		light.setIcon(redLight);
	}
	
	public void setYellow() {
		light.setIcon(yellowLight);
	}
}
