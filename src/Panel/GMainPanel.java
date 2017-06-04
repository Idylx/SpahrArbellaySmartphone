package Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JPanel;

import Gallerie.Photo;

public class GMainPanel extends JPanel {

	private GridLayout gl = new GridLayout(0,3);

	private Photo photo;
	


	public GMainPanel() {
		setLayout(gl);
		setBackground(Color.black);
		setPhoto();
		
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