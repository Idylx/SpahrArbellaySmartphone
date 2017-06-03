package Panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Buttons.PhotoButton;
import Gallerie.Photo;

public class GalleryPanel extends JPanel {
	
	public CardLayout c2 = new CardLayout();
	
	ApplicationsPanel picturePanell = new ApplicationsPanel();
	
	
	GMainPanel mainPanel = new GMainPanel();
	
	//private GridLayout gl = new GridLayout(5, 3, 5, 5);
	
	PhotoButton[] boutons = new PhotoButton[15];
	String[] path = new String[15];

	Image[] imgs = new Image[15];
	
	PicturePanel pp;
	
	private Photo photo;
	
	public GalleryPanel() {
		
				
		path = getPath();
		imgs = fillImgs();
		
		//picturePanell.setLayout(gl);
		
		for (int i = 0; i < boutons.length; i++) {
			boutons[i] = new PhotoButton(new Photo(imgs[i]));
			boutons[i].addActionListener(new PhotoBouton());
			picturePanell.add(boutons[i]);
		}
		
		
		
		mainPanel.setLayout(c2);
		mainPanel.add(picturePanell, "picturePanell");
		c2.show(mainPanel, "mainPanel");
		
		add(mainPanel);
		
		setBackground(Color.BLACK);
		
	}



	String[] getPath() {

		String[] temp = new String[15];
		File folder = new File("./src/GalleriePhotos/");
		File imgs[] = folder.listFiles();

		for (int i = 0; i < imgs.length; i++)

			temp[i] = "./src/GalleriePhotos/" + imgs[i].getName();

		return temp;
	}

	Image [] fillImgs() {
		
		Image [] temp = new Image[15];

		for (int i = 0; i < temp.length; i++) {
			Photo photo = new Photo(path[i]);
			Image img = photo.getImage();
			Image newimg = img.getScaledInstance(100, 100, Image.SCALE_FAST);
			temp[i] = newimg;

		}
		return temp;

	}
	
	class PhotoBouton implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			
			JButton button = (JButton) e.getSource();
			Photo p = (Photo) button.getIcon();
	
			pp = new PicturePanel(p);
			mainPanel.add(pp, "pp");
			c2.show(mainPanel, "pp");
			
		}
		
	}

}
