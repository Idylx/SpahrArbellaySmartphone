package Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Buttons.ButtonApp;
import Gallerie.Photo;

public class PicturePanel extends JPanel {

	
	private String pathPhoto;
	
	
	
	private JPanel closePanel = new JPanel(new BorderLayout());
	private JPanel previousPanel = new JPanel(new BorderLayout());
	private JPanel nextPanel = new JPanel(new BorderLayout());
	
	ButtonApp close = new ButtonApp(new Photo("./src/Pictures/close.png"));
	ButtonApp previous = new ButtonApp(new Photo("./src/Pictures/leftArrow.png"));
	ButtonApp next = new ButtonApp(new Photo("./src/Pictures/rightArrow.png"));

	GalleryPanel top;
	
	int index;

	public PicturePanel(int index, GalleryPanel top) {
		//this.pathPhoto = pathPhoto;
		this.top = top;
		this.index = index;
		
		pathPhoto = top.path.get(index);

		setLayout(new BorderLayout());
		setBackground(Color.black);
		
		previous.setHorizontalAlignment(SwingConstants.LEFT);
		next.setHorizontalAlignment(SwingConstants.RIGHT);

		close.addActionListener(new Close_Button());

		
		previous.addActionListener(new Previous_Button());
		next.addActionListener(new Next_Button());
		
		if(index == 0){
			previous.setEnabled(false);
			previous.setVisible(false);
		}
		if(index == top.path.size()-1)
		{
			next.setEnabled(false);
			next.setVisible(false);
		}
		
		
		previous.setVerticalAlignment(SwingConstants.CENTER);
		next.setVerticalAlignment(SwingConstants.CENTER);

		previousPanel.add(previous);
		previousPanel.setOpaque(false);
		nextPanel.add(next);
		nextPanel.setOpaque(false);

		closePanel.setOpaque(false);
		closePanel.add(close, BorderLayout.EAST);
		
		add(closePanel, BorderLayout.NORTH);
		add(previousPanel, BorderLayout.WEST);
		add(nextPanel, BorderLayout.EAST);
		setBackground(Color.BLACK);

	}

	class Close_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			top.removePanel(PicturePanel.this);

		}

	}
	
	class Previous_Button implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			top.remove(PicturePanel.this);
			PicturePanel pp = new PicturePanel(index-1, top);
			top.add(pp, "newPP");
			top.getCLayout().show(top, "newPP");
			
		}
		
	}
	
	class Next_Button implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			top.remove(PicturePanel.this);
			PicturePanel pp = new PicturePanel(index+1, top);
			top.add(pp, "newPP");
			top.getCLayout().show(top, "newPP");
			
		}
		
	}

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		/**
		 * m�thode qui va peindre la photo sur tout le panel en la
		 * redimensionant � la taille du panel
		 **/
		super.paintComponent(g);

		Photo newPhoto = new Photo(top.path.get(index));
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
