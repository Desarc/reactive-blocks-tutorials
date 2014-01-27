package no.ntnu.item.tutorials.exercise4_completed.component;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {
	
	private JFrame frame;
	private JLabel light;
	private ImageIcon red_light;
	private ImageIcon green_light;
	
	public Component() {
		frame = new JFrame("Light");
		frame.setBounds(100, 100, 200, 200);
		red_light = new ImageIcon("../resources/red_light.jpg");
		green_light = new ImageIcon("../resources/green_light.jpg");
		light = new JLabel(red_light);
		frame.getContentPane().setLayout(new GridLayout(1,1));
		frame.getContentPane().add(light);
		frame.setVisible(true);
	}

	public void change_color() {
		light.setIcon(green_light);
	}

}