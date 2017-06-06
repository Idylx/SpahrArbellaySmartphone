package Buttons;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JButton;

import Photo.Photo;

public class PhotoButton extends JButton {

	private Dimension dimension = new Dimension(100, 100);

	public PhotoButton(Photo photo) {
		setIcon(photo);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
		
	}

}
