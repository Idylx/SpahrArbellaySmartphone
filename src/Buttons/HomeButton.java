/*
 * Author : Bryan Spahr
 */

package Buttons;

import javax.swing.JButton;

import Photo.Photo;

public class HomeButton extends JButton {

	private Photo homePhoto = new Photo("./src/Pictures/home.png");

	public HomeButton() {
		setIcon(homePhoto);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}

}
