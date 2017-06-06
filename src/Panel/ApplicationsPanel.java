/*
 * Author : Bryan Spahr
 */

package Panel;

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

public class ApplicationsPanel extends JPanel {

	private FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 10, 10);

	private Photo photo;

	private BorderLayout bl = new BorderLayout();

	public ApplicationsPanel() {
		// setLayout(fl);
		setLayout(bl);
		setBackground(Color.black);
		setPhoto();
		setBorder(new EmptyBorder(20, 10, 0, 20));

	}

	void setPhoto() {
		photo = new Photo("./src/Pictures/wallpaperr.jpg");
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = photo.getImage();
		int frameWidth = this.getWidth();
		int frameHeight = this.getHeight();

		double imageWidth = img.getWidth(this);
		double imageHeight = img.getHeight(this);

		double newW = (imageWidth / imageHeight) * frameHeight;
		double newH = (imageHeight / imageWidth) * frameWidth;

		if (imageWidth > imageHeight) {
			double ratioWidth = imageWidth / frameWidth;
			imageWidth = frameWidth;
			imageHeight = (int) (imageHeight / ratioWidth);
			g.drawImage(img, (int) (frameWidth - imageWidth) / 2, (int) (frameHeight - imageHeight) / 2,
					(int) imageWidth, (int) newH, this);
		} else if (imageHeight > imageWidth) {
			double ratioHeight = imageHeight / frameHeight;
			imageHeight = frameHeight;
			imageWidth = (int) (imageWidth / ratioHeight);
			g.drawImage(img, (int) (frameWidth - imageWidth) / 2, (int) (frameHeight - imageHeight) / 2, (int) newW,
					(int) imageHeight, this);

		}
	}
}
