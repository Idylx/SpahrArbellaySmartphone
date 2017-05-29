package Buttons;

import javax.swing.JButton;

import Gallerie.Photo;


public class ButtonApp extends JButton {

	public ButtonApp(Photo photo) {
		setIcon(photo);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}

}