package no.ntnu.item.tutorials.exercise2a.component;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	private JFrame frame;
	private JLabel light;
	private ImageIcon redLight;
	private ImageIcon greenLight;

	public Component() {
		frame = new JFrame("Light");
		frame.setBounds(100, 100, 200, 200);
		redLight = new ImageIcon("../resources/red_light.jpg");
		greenLight = new ImageIcon("../resources/green_light.jpg");
		light = new JLabel();
		frame.getContentPane().setLayout(new GridLayout(1,1));
		frame.getContentPane().add(light);
		frame.setVisible(true);
	}

	public void setGreen() {
		light.setIcon(greenLight);
	}
	
	public void setRed() {
		light.setIcon(redLight);
	}

}
