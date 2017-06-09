/*
 * Author : Bryan Spahr
 */

/*
 * Panel qui sert de panel de base à la frame principale HomeFrame
 * Gère notamment le fond d'écran, le layout et les bordures
 */

package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Photo.Photo;

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

		double newW = (imageWidth / imageHeight) * frameHeight;
		double newH = (imageHeight / imageWidth) * frameWidth;

		g.drawImage(img, (int) (frameWidth - imageWidth) / 2, (int) (frameHeight - imageHeight) / 2, (int) imageWidth,
				(int) imageHeight, this);

	}
}
