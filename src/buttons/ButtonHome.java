/*
 * Author : Bryan Spahr

/*
 * Home Button that's displayed in the south of the HomeFrame
 */

package buttons;

import javax.swing.JButton;

import photo.Photo;

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
