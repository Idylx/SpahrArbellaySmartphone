/*
 * Author : Bryan Spahr

/*
 * Bouton home qui apparaît au bas de l'appli
 */

package Buttons;

import javax.swing.JButton;

import Photo.Photo;

public class ButtonHome extends JButton {

	private Photo home = new Photo("./src/Pictures/home.png");

	public ButtonHome() {
		setIcon(home);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}

}
