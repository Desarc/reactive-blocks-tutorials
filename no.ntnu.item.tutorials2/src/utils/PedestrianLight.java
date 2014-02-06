package utils;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PedestrianLight {

	private JFrame frame;
	private JLabel light;
	private ImageIcon redLight, greenLight;
	
	public PedestrianLight() {
		frame = new JFrame("Pedestrian lights");
		frame.setBounds(250, 100, 100, 150);
		redLight = new ImageIcon("../resources/pedestrian_red.jpg");
		greenLight= new ImageIcon("../resources/pedestrian_green.jpg");
		light = new JLabel();
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
}
