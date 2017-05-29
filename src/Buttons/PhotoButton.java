package Buttons;

import java.awt.Dimension;

import javax.swing.JButton;

import Gallerie.Photo;


public class PhotoButton extends JButton {

	private Dimension dimension = new Dimension(125, 100);

	public PhotoButton(Photo photo) {
		setIcon(photo);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}

	public void setButtonSize(Dimension dimension) {
		setPreferredSize(dimension);
	}
}
