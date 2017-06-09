/*
 * Author : Bryan Spahr
 */

/*
 * Boutons de la calculette (avec dimension imposée et égale pour tous)
 */

package Buttons;

import java.awt.Dimension;

import javax.swing.JButton;

import Photo.Photo;

public class ButtonCalculator extends JButton {

	private Dimension dimension = new Dimension(100, 100);

	public ButtonCalculator(Photo photo) {
		setIcon(photo);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
		setPreferredSize(dimension);

	}
}