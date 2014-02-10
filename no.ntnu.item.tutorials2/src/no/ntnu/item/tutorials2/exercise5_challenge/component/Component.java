package no.ntnu.item.tutorials2.exercise5_challenge.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public static final String OPEN_DOOR1 = "OPEN_DOOR1";
	public static final String OPEN_DOOR2 = "OPEN_DOOR2";
	public static final String OPEN_DOOR3 = "OPEN_DOOR3";
	public static final String OPEN_DOOR4 = "OPEN_DOOR4";
	public static final String OPEN_DOOR5 = "OPEN_DOOR5";
	public static final String EXIT = "EXIT";
	public static final String RED = "red";
	public static final String GREEN = "green";
	public static final String BLUE = "blue";
	
	private JFrame gameFrame;
	private JButton redDoor, greenDoor, blueDoor, exit;
	private JLabel messageLabel, victoryLabel, cakeLabel;
	private String message;
	private int currentStep;
	
	public Component() {
		message = "Welcome to the door game!";
		gameFrame = new JFrame("Doors");
		gameFrame.setBounds(100, 100, 800, 600);
		messageLabel = new JLabel(message);
		redDoor = new JButton(new ImageIcon("../resources/red_door.gif"));
		redDoor.addActionListener(new RedDoorListener());
		greenDoor = new JButton(new ImageIcon("../resources/green_door.gif"));
		greenDoor.addActionListener(new GreenDoorListener());
		blueDoor = new JButton(new ImageIcon("../resources/blue_door.gif"));
		blueDoor.addActionListener(new BlueDoorListener());
		gameFrame.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		gameFrame.getContentPane().add(messageLabel, c);
		
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		gameFrame.getContentPane().add(redDoor, c);
		
		c.gridx = 1;
		gameFrame.getContentPane().add(greenDoor, c);
		
		c.gridx = 2;
		gameFrame.getContentPane().add(blueDoor, c);
	}
	
	public void showDoors1() {
		currentStep = 1;
		messageLabel.setText(message);
		gameFrame.setVisible(true);
	}
	
	public void showDoors2() {
		currentStep = 2;
		messageLabel.setText(message);
		gameFrame.setVisible(true);
	}
	
	public void showDoors3() {
		currentStep = 3;
		messageLabel.setText(message);
		gameFrame.setVisible(true);
	}
	
	public void showDoors4() {
		currentStep = 4;
		messageLabel.setText(message);
		gameFrame.setVisible(true);
	}
	
	public void showDoors5() {
		currentStep = 5;
		messageLabel.setText(message);
		gameFrame.setVisible(true);
	}
	
	public void victory() {
		gameFrame = new JFrame("Victory!");
		gameFrame.setBounds(100, 100, 800, 600);
		victoryLabel = new JLabel("You win!");
		cakeLabel = new JLabel(new ImageIcon("../resources/cake.jpg"));
		exit = new JButton("Exit");
		exit.addActionListener(new ExitListener());
		gameFrame.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		gameFrame.getContentPane().add(victoryLabel, c);
		
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		gameFrame.getContentPane().add(cakeLabel, c);
		
		c.gridy = 2;
		gameFrame.getContentPane().add(exit, c);
		gameFrame.setVisible(true);
	}
	
	public void goBack() {
		message = "The door you chose took you one step back!";
	}
	
	public void dontMove() {
		message = "The door you chose didn't take you anywhere.";
	}
	
	public void goForward() {
		message = "The door you chose took you one step forward!";
	}
	
	public void goSideways() {
		message = "The door you chose took you one step to the side.";
	}
	
	class RedDoorListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (currentStep == 1) {
				sendToBlock(OPEN_DOOR1, RED);
			}
			else if (currentStep == 2) {
				sendToBlock(OPEN_DOOR2, RED);
			}
			else if (currentStep == 3) {
				sendToBlock(OPEN_DOOR3, RED);
			}
			else if (currentStep == 4) {
				sendToBlock(OPEN_DOOR4, RED);
			}
			else if (currentStep == 5) {
				sendToBlock(OPEN_DOOR5, RED);
			}
			gameFrame.setVisible(false);
        }
	}
 
	class GreenDoorListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			if (currentStep == 1) {
				sendToBlock(OPEN_DOOR1, GREEN);
			}
			else if (currentStep == 2) {
				sendToBlock(OPEN_DOOR2, GREEN);
			}
			else if (currentStep == 3) {
				sendToBlock(OPEN_DOOR3, GREEN);
			}
			else if (currentStep == 4) {
				sendToBlock(OPEN_DOOR4, GREEN);
			}
			else if (currentStep == 5) {
				sendToBlock(OPEN_DOOR5, GREEN);
			}
			gameFrame.setVisible(false);
	    }
	}
	
	class BlueDoorListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (currentStep == 1) {
				sendToBlock(OPEN_DOOR1, BLUE);
			}
			else if (currentStep == 2) {
				sendToBlock(OPEN_DOOR2, BLUE);
			}
			else if (currentStep == 3) {
				sendToBlock(OPEN_DOOR3, BLUE);
			}
			else if (currentStep == 4) {
				sendToBlock(OPEN_DOOR4, BLUE);
			}
			else if (currentStep == 5) {
				sendToBlock(OPEN_DOOR5, BLUE);
			}
			gameFrame.setVisible(false);
	    }
	}
	
	class ExitListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			gameFrame.setVisible(false);
			sendToBlock(EXIT);
	    }
	}
}