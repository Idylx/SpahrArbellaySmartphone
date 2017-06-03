package Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Gallerie.Photo;


public class PicturePanel extends JPanel {
	
	private Photo photo;
	

	// for the paintComponenent
		private BufferedImage buffImage;

		

	
	public PicturePanel(Photo photo)
	{
		this.photo = photo;
		
		
		setLayout(new BorderLayout());
		setBackground(Color.black);
	
	}
	
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		/**
		 * m�thode qui va peindre la photo sur tout le panel en la
		 * redimensionant � la taille du panel
		 **/
		super.paintComponent(g);

		Photo newPhoto = new Photo(photo.getPath());
		Image img = newPhoto.getImage();

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
