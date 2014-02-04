package no.ntnu.item.tutorials.exercise8a.component;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public static final String BUTTON1_CLICKED = "BUTTON1_CLICKED";
	public static final String BUTTON2_CLICKED = "BUTTON2_CLICKED";
	private JFrame button1Frame, button2Frame;
	private JButton button1, button2;
	
	public void showButton1() {
		button1Frame = new JFrame("Button1");
		button1Frame.setBounds(100, 100, 200, 200);
		button1Frame.getContentPane().setLayout(new GridLayout(1,1));
		button1 = new JButton("Click me!");
		button1.addActionListener(new Button1Listener());
		button1Frame.getContentPane().add(button1);
		button1Frame.setVisible(true);
	}

	public void showButton2() {
		button2Frame = new JFrame("Button2");
		button2Frame.setBounds(300, 100, 200, 200);
		button2Frame.getContentPane().setLayout(new GridLayout(1,1));
		button2 = new JButton("Click me!");
		button2.addActionListener(new Button2Listener());
		button2Frame.getContentPane().add(button2);
		button2Frame.setVisible(true);
	}

	public void printMessage() {
		System.out.println("Both buttons were clicked!");
	}

	class Button1Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			sendToBlock(BUTTON1_CLICKED);;
        }
	}
 
	class Button2Listener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			sendToBlock(BUTTON2_CLICKED);
	    }
	}
	
}
