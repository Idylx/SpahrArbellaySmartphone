/*
 * Author : Bryan Spahr
 */

package Buttons;

import java.awt.Dimension;

import javax.swing.JButton;

import Photo.Photo;

public class CalculatorButton extends JButton {

	private Dimension dimension = new Dimension(100, 100);

	public CalculatorButton(Photo photo) {
		setIcon(photo);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);

	}
}