/*
 * Author : Bryan Spahr
 */

/*
 * Personalized panel that is used as main panel for the GalleryPanel
 */

package panelss;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JPanel;

import photoo.Photo;

public class GMainPanel extends JPanel {

	// Layout
	private GridLayout gl = new GridLayout(0, 3);

	// Wallpaper
	private Photo wallpaper = new Photo("./src/Pictures/wallpaper.jpg");

	// Constructor
	public GMainPanel() {

		setLayout(gl);
		setBackground(Color.black);

	}

	// Paint the background with the picture wallpaper
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = wallpaper.getImage();
		int frameWidth = this.getWidth();
		int frameHeight = this.getHeight();

		double imageWidth = img.getWidth(this);
		double imageHeight = img.getHeight(this);

		g.drawImage(img, (int) (frameWidth - imageWidth) / 2, (int) (frameHeight - imageHeight) / 2, (int) imageWidth,
				(int) imageHeight, this);

	}
}