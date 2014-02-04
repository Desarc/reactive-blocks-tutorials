package no.ntnu.item.tutorials.exercisexx_completed.component;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	private JFrame frameRed, frameGreen;
	private JLabel light1, light2;
	private ImageIcon redLight;
	private ImageIcon greenLight;
	
	public void displayRed() {
		frameRed = new JFrame("Red light");
		frameRed.setBounds(100, 100, 200, 200);
		redLight = new ImageIcon("../resources/red_light.jpg");
		light1 = new JLabel(redLight);
		frameRed.getContentPane().setLayout(new GridLayout(1,1));
		frameRed.getContentPane().add(light1);
		frameRed.setVisible(true);
	}

	public void displayGreen() {
		frameGreen = new JFrame("Green light");
		frameGreen.setBounds(300, 100, 200, 200);
		greenLight = new ImageIcon("../resources/green_light.jpg");
		light2 = new JLabel(greenLight);
		frameGreen.getContentPane().setLayout(new GridLayout(1,1));
		frameGreen.getContentPane().add(light2);
		frameGreen.setVisible(true);
	}
}