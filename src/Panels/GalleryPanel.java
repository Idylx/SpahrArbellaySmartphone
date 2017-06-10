/*
 * Author : Bryan Spahr
 */

/*
 * Panel that represents a Photo Gallery and displayed over the HomeFrame.
 * This panel reads the folder PhotoGallery to display the pictures.
 */

package panels;

import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import buttons.ButtonPictures;
import photo.Photo;

public class GalleryPanel extends JPanel {

	// Panel that contains all the pictures
	private GMainPanel containerPhotos = new GMainPanel();

	// Layout (the individual pictures will be display on the top of this panel)
	private CardLayout c2 = new CardLayout();

	// ArrayList of buttons, images and path of pictures
	private ArrayList<ButtonPictures> boutons = new ArrayList<ButtonPictures>();
	protected ArrayList<String> path = new ArrayList<String>();
	private ArrayList<Image> imgs = new ArrayList<Image>();

	// Panel used to display individual picture
	private PicturePanel pp;

	// JScrollPane that contains all the pictures (that are in the container)
	private JScrollPane scroll = new JScrollPane(containerPhotos, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	public GalleryPanel() {

		// add the buttons to the panel
		addButton();

		// Settings of the panel (Layout, Background color)
		setLayout(c2);
		setBackground(Color.BLACK);

		// Add the scroll bar to the panel
		add(scroll);

		// Set empty border to the scroll pane
		scroll.setBorder(BorderFactory.createEmptyBorder());

		// Show the GalleryPanel
		c2.show(GalleryPanel.this, "GalleryPanel");

	}

	// Return the content of the containerPhotos (used when a pic is deleted)
	public GMainPanel getContainerPhotos() {
		return containerPhotos;
	}

	/*
	 * Initialize the buttons, set the icons, the action commands and the action
	 * listener and finally add the buttons to the container
	 */
	public void addButton() {

		this.path = fillPath();
		this.imgs = fillImgs();

		for (int i = 0; i < imgs.size(); i++) {
			boutons.add(new ButtonPictures(new Photo(imgs.get(i))));
			boutons.get(i).setActionCommand("" + i);
			boutons.get(i).addActionListener(new PhotoBouton());
			containerPhotos.add(boutons.get(i));
		}

	}

	/*
	 * Remove the buttons from the container, used when the panel has to be
	 * refreshed
	 */
	public void removeButton() {
		for (int i = 0; i < boutons.size(); i++)
			containerPhotos.remove(boutons.get(i));
	}

	// Fill the array String with the path of the pictures
	public ArrayList<String> fillPath() {

		ArrayList<String> temp = new ArrayList<String>();
		File folder = new File("./src/PhotoGallery");
		File imgs[] = folder.listFiles();

		for (int i = 0; i < imgs.length; i++)

			temp.add("./src/PhotoGallery/" + imgs[i].getName());

		return temp;
	}

	// Fill the array Image with the images used for the icons of the buttons
	public ArrayList<Image> fillImgs() {

		ArrayList<Image> temp = new ArrayList<Image>();

		for (int i = 0; i < path.size(); i++) {
			Photo photo = new Photo(path.get(i));
			Image img = photo.getImage();
			Image newimg = img.getScaledInstance(100, 100, Image.SCALE_FAST);
			temp.add(newimg);

		}
		return temp;

	}

	// Return the layout of this panel, used navigate between the pics
	CardLayout getCLayout() {
		return c2;
	}

	// Method that deletes a picture and all its references
	public void delete(int index, String pathh) {

		// Delete the file physically
		File f = new File(pathh);
		Path pathFile = f.toPath();
		try {
			Files.delete(pathFile);
		} catch (NoSuchFileException e) {
			e.printStackTrace();
		} catch (DirectoryNotEmptyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Clear the ArrayLists, Removes all of the elements from the lists.
		boutons.clear();
		imgs.clear();
		path.clear();

	}

	// Remove the panel (used when a pic is deleted)
	public void removePanel(JPanel panelRemove) {

		remove(panelRemove);
		revalidate();
		repaint();

	}

	// How to react when a picture/button is pressed
	class PhotoBouton implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// To know which pic was selected
			String index = e.getActionCommand();
			int indx = Integer.parseInt(index);

			/*
			 * open a new panel on the top of this panel, indication on which
			 * pic was selected is given in the parameter
			 */
			pp = new PicturePanel(indx, GalleryPanel.this);
			add(pp, "pp");
			c2.show(GalleryPanel.this, "pp");

		}
	}

	// Getters of the arrays used for the JUnit test
	public ArrayList<ButtonPictures> getBoutons() {
		return boutons;
	}

	public ArrayList<String> getPath() {
		return path;
	}

	public ArrayList<Image> getImgs() {
		return imgs;
	}

}
