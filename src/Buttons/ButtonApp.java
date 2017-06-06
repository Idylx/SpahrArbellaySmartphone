/*
 * Author : Bryan Spahr
 */

package Buttons;

import javax.swing.JButton;

import Photo.Photo;

public class ButtonApp extends JButton {

	public ButtonApp(Photo photo) {
		setIcon(photo);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}

}