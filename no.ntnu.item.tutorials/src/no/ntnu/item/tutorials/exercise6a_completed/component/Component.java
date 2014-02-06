package no.ntnu.item.tutorials.exercise6a_completed.component;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import utils.TrafficLight;
import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public static final String BUTTON_PRESSED = "BUTTON_PRESSED";
	public static final String EXIT = "EXIT";
	
	private TrafficLight light;
	private JFrame buttonFrame;
	private JButton change, exit;
	private boolean alt;

	public void changeColor() {
		if (alt) {
			light.setGreen();
			alt = false;
		}
		else {
			light.setRed();
			alt = true;
		}
	}

	public void showLight() {
		light = new TrafficLight();
		light.setRed();
		alt = true;
		light.show();
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
			sendToBlock(BUTTON_PRESSED);;
        }
	}
 
	class ExitListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			sendToBlock(EXIT);
	    }
	}

}