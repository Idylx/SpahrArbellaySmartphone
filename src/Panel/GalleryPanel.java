package Panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Buttons.PhotoButton;
import Frame.HomeFrame;
import Photo.Photo;

public class GalleryPanel extends JPanel {
	

	public CardLayout c2 = new CardLayout();

	ApplicationsPanel picturePanell = new ApplicationsPanel();

	GMainPanel mainPanel = new GMainPanel();
	
	GMainPanel panel = new GMainPanel();
	GMainPanel containerPhotos = new GMainPanel();

	// private GridLayout gl = new GridLayout(5, 3, 5, 5);

	ArrayList<PhotoButton> boutons = new ArrayList<PhotoButton>();
	ArrayList<String> path = new ArrayList<String>();

	ArrayList<Image> imgs = new ArrayList<Image>();

	PicturePanel pp;

	private Photo photo;
	
	JScrollPane scroll = new JScrollPane(containerPhotos, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	HomeFrame hf;
	

	public GalleryPanel(HomeFrame hf) {
		
		this.hf = hf;
		
		addButton();
		
		
	
		setLayout(c2);
		add(scroll);
		c2.show(GalleryPanel.this, "GalleryPanel");
		
		setBackground(Color.BLACK);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		
		

	}
	
	public GMainPanel getContainerPhotos()
	{
		return containerPhotos;
	}
	
	void addButton() {
		
		this.path = getPath();
		this.imgs = fillImgs();
		
		for (int i = 0; i < imgs.size(); i++) {
			boutons.add(new PhotoButton(new Photo(imgs.get(i))));
			boutons.get(i).setActionCommand("" + i);
			boutons.get(i).addActionListener(new PhotoBouton());
			containerPhotos.add(boutons.get(i));
		}
		
	}
	
	void removeButton()
	{
		for(int i = 0 ; i < boutons.size(); i++)
			containerPhotos.remove(boutons.get(i));
	}
	
	

	ArrayList<String> getPath() {

		ArrayList<String> temp = new ArrayList<String>();
		File folder = new File("./src/GalleriePhotos");
		File imgs[] = folder.listFiles();

		for (int i = 0; i < imgs.length; i++)

			temp.add("./src/GalleriePhotos/" + imgs[i].getName());

		return temp;
	}

	ArrayList<Image> fillImgs() {

		ArrayList<Image> temp = new ArrayList<Image>();

		for (int i = 0; i < path.size(); i++) {
			Photo photo = new Photo(path.get(i));
			Image img = photo.getImage();
			Image newimg = img.getScaledInstance(100, 100, Image.SCALE_FAST);
			temp.add(newimg);

		}
		return temp;

	}
	
	CardLayout getCLayout()
	{
		return c2;
	}

	public void delete(int index, String pathh)
	{
		
		
		File f = new File(pathh);
		Path pathFile = f.toPath();
		try {
			Files.delete(pathFile);
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n", pathFile);
		} catch (DirectoryNotEmptyException x) {
			System.err.format("%s not empty%n", pathFile);
		} catch (IOException x) {
			// File permission problems are caught here.
			System.err.println(x);
		}
		
		boutons.clear();
		imgs.clear();
		path.clear();
		
	
		
		
		
	}
	
	
	public void removePanel(JPanel panelRemove) {
	
		
		remove(panelRemove);
		revalidate();
		repaint();
		
	}

	class PhotoBouton implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			JButton button = (JButton) e.getSource();
			String index = e.getActionCommand();
			int indx = Integer.parseInt(index);

			pp = new PicturePanel(indx, GalleryPanel.this);
			add(pp, "pp");
			c2.show(GalleryPanel.this, "pp");

		}

	}

}
