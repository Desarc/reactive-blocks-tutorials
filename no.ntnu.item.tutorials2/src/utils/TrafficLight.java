package utils;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TrafficLight {

	private JFrame frame;
	private JLabel redLightLabel, greenLightLabel, yellowLightLabel;
	private ImageIcon redLight, greenLight, yellowLight;

	public TrafficLight() {
		frame = new JFrame("Traffic light");
		frame.setBounds(100, 100, 100, 300);
		redLight = new ImageIcon("resources/red_light.jpg");
		greenLight = new ImageIcon("resources/green_light.jpg");
		yellowLight = new ImageIcon("resources/car_yellow.jpg");
		greenLightLabel = new JLabel();
		redLightLabel = new JLabel();
		yellowLightLabel = new JLabel();
		frame.getContentPane().setLayout(new GridLayout(3,1));
		frame.getContentPane().add(redLightLabel);
		frame.getContentPane().add(yellowLightLabel);
		frame.getContentPane().add(greenLightLabel);
	}
	
	public void show() {
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}
	
	public void setGreen() {
		redLightLabel.setIcon(null);
		yellowLightLabel.setIcon(null);
		greenLightLabel.setIcon(greenLight);
	}
	
	public void setRed() {
		greenLightLabel.setIcon(null);
		yellowLightLabel.setIcon(null);
		redLightLabel.setIcon(redLight);
	}
	
	public void setYellow() {
		greenLightLabel.setIcon(null);
		redLightLabel.setIcon(null);
		yellowLightLabel.setIcon(yellowLight);
	}
}
