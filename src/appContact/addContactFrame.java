/*
Author : Olivier Arbellay
Date: 24 mai 2017
*/
package appContact;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class addContactFrame extends JFrame{

	private JFrame frame;


	public addContactFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 426);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
