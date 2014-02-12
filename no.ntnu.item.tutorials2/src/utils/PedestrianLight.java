package utils;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PedestrianLight {

	private JFrame frame;
	private JLabel redLightLabel, greenLightLabel;
	private ImageIcon redLight, greenLight;
	
	public PedestrianLight() {
		frame = new JFrame("Pedestrian lights");
		frame.setBounds(250, 100, 100, 300);
		redLight = new ImageIcon("resources/pedestrian_red.jpg");
		greenLight= new ImageIcon("resources/pedestrian_green.jpg");
		redLightLabel = new JLabel();
		greenLightLabel = new JLabel();
		frame.getContentPane().setLayout(new GridLayout(2,1));
		frame.getContentPane().add(redLightLabel);
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
		greenLightLabel.setIcon(greenLight);
	}
	
	public void setRed() {
		greenLightLabel.setIcon(null);
		redLightLabel.setIcon(redLight);
	}
}
