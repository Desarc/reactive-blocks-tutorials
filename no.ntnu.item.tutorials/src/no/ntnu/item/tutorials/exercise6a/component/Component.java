package no.ntnu.item.tutorials.exercise6a.component;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public static final String CHANGE_COLOR = "CHANGE_COLOR";
	public static final String EXIT = "EXIT";
	
	private JFrame lightFrame, buttonFrame;
	private JLabel light;
	private JButton change, exit;
	private ImageIcon redLight;
	private ImageIcon greenLight;
	private boolean alt;

	public void changeColor() {
		if (alt) {
			light.setIcon(greenLight);
			alt = false;
		}
		else {
			light.setIcon(redLight);
			alt = true;
		}
	}

	public void showLight() {
		lightFrame = new JFrame("Light");
		lightFrame.setBounds(100, 100, 200, 200);
		redLight = new ImageIcon("../resources/red_light.jpg");
		greenLight = new ImageIcon("../resources/green_light.jpg");
		light = new JLabel(redLight);
		alt = true;
		lightFrame.getContentPane().setLayout(new GridLayout(1,1));
		lightFrame.getContentPane().add(light);
		lightFrame.setVisible(true);
	}

	public void showButtons() {
		buttonFrame = new JFrame("Buttons");
		buttonFrame.setBounds(300, 100, 200, 200);
		buttonFrame.getContentPane().setLayout(new GridLayout(2,1));
		change = new JButton("Change color");
		change.addActionListener(new ChangeListener());
		buttonFrame.getContentPane().add(change);
		exit = new JButton("Exit");
		exit.addActionListener(new ExitListener());
		buttonFrame.getContentPane().add(exit);
		buttonFrame.setVisible(true);	
	}
	
	class ChangeListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			sendToBlock(CHANGE_COLOR);;
        }
	}
 
	class ExitListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			sendToBlock(EXIT);
	    }
	}

}
