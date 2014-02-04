package no.ntnu.item.tutorials.exercise6b_2_completed.component;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public static final String OPEN_DOOR = "OPEN_DOOR";
	public static final String EXIT = "EXIT";
	public static final String RED = "red";
	public static final String GREEN = "green";
	public static final String BLUE = "blue";
	
	private JFrame gameFrame;
	private JButton redDoor, greenDoor, blueDoor, exit;
	private JLabel messageLabel, victoryLabel, cakeLabel;
	private String message;
	
	public Component() {
		message = "Welcome to the door game!";
	}
	
	public void showDoors() {
		gameFrame = new JFrame("Doors");
		gameFrame.setBounds(100, 100, 300, 200);
		messageLabel = new JLabel(message);
		redDoor = new JButton(new ImageIcon("../resources/red_door.gif"));
		redDoor.addActionListener(new RedDoorListener());
		greenDoor = new JButton(new ImageIcon("../resources/green_door.gif"));
		greenDoor.addActionListener(new GreenDoorListener());
		blueDoor = new JButton(new ImageIcon("../resources/blue_door.gif"));
		blueDoor.addActionListener(new BlueDoorListener());
		gameFrame.getContentPane().setLayout(new GridLayout(3,2));
		gameFrame.getContentPane().add(messageLabel);
		gameFrame.getContentPane().add(redDoor);
		gameFrame.getContentPane().add(greenDoor);
		gameFrame.getContentPane().add(blueDoor);
		gameFrame.setVisible(true);
	}
	
	public void victory() {
		gameFrame = new JFrame("Victory!");
		gameFrame.setBounds(100, 100, 300, 200);
		victoryLabel = new JLabel("You win!");
		cakeLabel = new JLabel(new ImageIcon("../resources/cake.jpg"));
		exit = new JButton("Exit");
		exit.addActionListener(new ExitListener());
		gameFrame.getContentPane().setLayout(new GridLayout(1,3));
		gameFrame.getContentPane().add(victoryLabel);
		gameFrame.getContentPane().add(cakeLabel);
		gameFrame.getContentPane().add(exit);
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
			gameFrame.setVisible(false);
			sendToBlock(OPEN_DOOR, RED);
        }
	}
 
	class GreenDoorListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			gameFrame.setVisible(false);
			sendToBlock(OPEN_DOOR, GREEN);
	    }
	}
	
	class BlueDoorListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			gameFrame.setVisible(false);
			sendToBlock(OPEN_DOOR, BLUE);
	    }
	}
	
	class ExitListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			gameFrame.setVisible(false);
			sendToBlock(EXIT);
	    }
	}
}