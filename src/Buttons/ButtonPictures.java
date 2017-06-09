/*
 * Author : Bryan Spahr
 */

/*
 * Boutons de la galerie photo
 */

package Buttons;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JButton;

import Photo.Photo;

public class ButtonPictures extends JButton {

	private Dimension dimension = new Dimension(150, 150);

	public ButtonPictures(Photo photo) {
		setIcon(photo);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
		setPreferredSize(dimension);

	}

}
