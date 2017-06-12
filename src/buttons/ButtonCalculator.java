/*
 * Author : Bryan Spahr
 */

/*
 * Buttons of the calculatorPanel personalized with icons and dimensions
 */

package buttons;

import java.awt.Dimension;

import javax.swing.JButton;

import photo.Photo;

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