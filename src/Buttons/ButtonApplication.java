/*
 * Author : Bryan Spahr
 */

/*
 * Boutons des applications personnalisés avec photo comme icône
 */

package Buttons;

import javax.swing.JButton;

import Photo.Photo;

public class ButtonApplication extends JButton {

	public ButtonApplication(Photo photo) {
		setIcon(photo);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}

}