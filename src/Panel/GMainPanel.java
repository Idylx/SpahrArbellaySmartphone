/*
 * Author : Bryan Spahr
 */

/*
 * Panel qui sert de panel de base au panel PicturePanel
 */

package Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JPanel;

import Photo.Photo;

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

		double newW = (imageWidth / imageHeight) * frameHeight;
		double newH = (imageHeight / imageWidth) * frameWidth;

		g.drawImage(img, (int) (frameWidth - imageWidth) / 2, (int) (frameHeight - imageHeight) / 2, (int) imageWidth,
				(int) imageHeight, this);

	}
}