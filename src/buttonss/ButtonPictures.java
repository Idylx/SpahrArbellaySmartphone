/*
 * Author : Bryan Spahr
 */

/*
 * Buttons of the GalleryPanel with personalized dimensions and icons
 */

package buttonss;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JButton;

import photoo.Photo;

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
