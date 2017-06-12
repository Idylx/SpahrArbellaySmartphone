/*
 * Author : Bryan Spahr
 */

/*
 * Buttons of the apps with personalized icons
 */

package buttons;

import javax.swing.JButton;

import photo.Photo;

public class ButtonApplication extends JButton {

	public ButtonApplication(Photo photo) {
		setIcon(photo);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}

}