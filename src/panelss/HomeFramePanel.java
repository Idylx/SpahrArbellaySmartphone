/*
 * Author : Bryan Spahr
 */

/*
 * Personalized panel used as main panel for the Home Frame.
 * Personalize the wallpaper, the background color and the layout.
 */

package panelss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import photoo.Photo;

public class HomeFramePanel extends JPanel {

	// Layout
	private BorderLayout bl = new BorderLayout();

	// Wallpaper
	private Photo wallpaper = new Photo("./src/Pictures/wallpaper.jpg");

	// Constructor
	public HomeFramePanel() {

		setLayout(bl);
		setBackground(Color.black);
		setBorder(new EmptyBorder(20, 10, 0, 20));

	}

	// Paint the background with the wallpaper picture
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
