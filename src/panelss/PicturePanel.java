/*
 * Author : Bryan Spahr
 */

/*
 * Panel that displays an individual picture with the options of navigation, closing and deleting of the picture.
 * This panel is displayed over the GalleryPanel panel when a picture is clicked
 */

package panelss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import buttonss.ButtonApplication;
import photoo.Photo;

public class PicturePanel extends JPanel {

	// Path of the wallpaper picture (depends on the picture selected)
	private String pathPhoto;

	// Panels
	private JPanel closePanel = new JPanel(new BorderLayout());
	private JPanel previousPanel = new JPanel(new BorderLayout());
	private JPanel nextPanel = new JPanel(new BorderLayout());

	// Buttons
	private ButtonApplication close = new ButtonApplication(new Photo("./src/Pictures/close.png"));
	private ButtonApplication delete = new ButtonApplication(new Photo("./src/Pictures/delete.png"));
	private ButtonApplication previous = new ButtonApplication(new Photo("./src/Pictures/leftArrow.png"));
	private ButtonApplication next = new ButtonApplication(new Photo("./src/Pictures/rightArrow.png"));

	// Reference to the "previous" panel, the panel under this one
	private GalleryPanel gp;

	// Index used to know which pic has to be displayed
	int index;

	// Constructor with indication of the pic selected and the panel under this
	// one
	public PicturePanel(int index, GalleryPanel gp) {

		// Set the local variables with the infos in parameter
		this.gp = gp;
		this.index = index;

		// Set the path of the pic selected to paint it later
		pathPhoto = gp.path.get(index);

		// Settings of the panel
		setLayout(new BorderLayout());
		setBackground(Color.black);

		// Alignment of the buttons
		previous.setHorizontalAlignment(SwingConstants.LEFT);
		next.setHorizontalAlignment(SwingConstants.RIGHT);
		previous.setVerticalAlignment(SwingConstants.CENTER);
		next.setVerticalAlignment(SwingConstants.CENTER);

		// Add the actions listeners to the buttons
		close.addActionListener(new Close_Button());
		delete.addActionListener(new Delete_Button());
		previous.addActionListener(new Previous_Button());
		next.addActionListener(new Next_Button());

		// Verifies if the picture selected is the first or the last one
		checkIdPhoto();

		// Add the buttons to their pannels and set them non-opaque
		previousPanel.add(previous);
		previousPanel.setOpaque(false);
		nextPanel.add(next);
		nextPanel.setOpaque(false);
		closePanel.setOpaque(false);
		closePanel.add(close, BorderLayout.EAST);
		closePanel.add(delete, BorderLayout.WEST);

		// Add the panels to the root panel
		add(closePanel, BorderLayout.NORTH);
		add(previousPanel, BorderLayout.WEST);
		add(nextPanel, BorderLayout.EAST);
		setBackground(Color.black);

	}

	/*
	 * Method that checks if the picture is the first or the last one of the
	 * gallery
	 */
	void checkIdPhoto() {

		// If it's the first one, disable the "previous" button
		if (index == 0) {
			previous.setEnabled(false);
			previous.setVisible(false);
		}

		// If it's the last one, disable the "next" button
		if (index == gp.path.size() - 1) {
			next.setEnabled(false);
			next.setVisible(false);
		}
	}

	/*
	 * Delete the picture when the button is pressed by calling the delete
	 * method of the GalleryPanel
	 */
	public class Delete_Button implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int options = JOptionPane.YES_NO_CANCEL_OPTION;
			// Ask the user if he's sure
			options = JOptionPane.showConfirmDialog(null, "Es-tu sûr de vouloir supprimer cette photo ?", null,
					options);
			// If yes, delete the picture definitely
			if (options == JOptionPane.YES_OPTION) {

				// call to the delete method
				gp.delete(index, gp.path.get(index));
				// remove all the buttons/pics from the container
				gp.getContainerPhotos().removeAll();
				/*
				 * dispose this panel, close the actual panel because the pic
				 * doesn't exist anymore
				 */
				gp.remove(PicturePanel.this);
				// add the buttons to galleryPanel = refresh the galleryPanel
				gp.addButton();
			}
			// If the user select the "no" or "cancel" option
			else {
				return;
			}
		}

	}

	/*
	 * Dispose this panel and returns to the galleryPanel when the "X" button is
	 * pressed
	 */
	class Close_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			gp.removePanel(PicturePanel.this);

		}

	}

	// Display the previous pic in the gallery
	class Previous_Button implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// remove this panel
			gp.remove(PicturePanel.this);
			// initialize a new panel with the previous pic in the gallery
			PicturePanel pp = new PicturePanel(index - 1, gp);
			gp.add(pp, "newPP");
			// show the new panel
			gp.getCLayout().show(gp, "newPP");

		}

	}

	// Display the next pic in the gallery
	class Next_Button implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			gp.remove(PicturePanel.this);
			PicturePanel pp = new PicturePanel(index + 1, gp);
			gp.add(pp, "newPP");
			gp.getCLayout().show(gp, "newPP");
		}

	}

	/*
	 * Paint the panel with the picture selected and RESIZE it to fit 100% in
	 * the panel !
	 */
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

		super.paintComponent(g);

		Photo newPhoto = new Photo(pathPhoto);
		Image img = newPhoto.getImage();

		int frameWidth = this.getWidth();
		int frameHeight = this.getHeight();

		double imageWidth = img.getWidth(this);
		double imageHeight = img.getHeight(this);

		double newWidth = (imageWidth / imageHeight) * frameHeight;
		double newHeigth = (imageHeight / imageWidth) * frameWidth;

		if (imageWidth > imageHeight) {
			double ratioWidth = imageWidth / frameWidth;
			imageWidth = frameWidth;
			imageHeight = (int) (imageHeight / ratioWidth);
			g.drawImage(img, (int) (frameWidth - imageWidth) / 2, (int) (frameHeight - imageHeight) / 2,
					(int) imageWidth, (int) newHeigth, this);
		} else if (imageHeight > imageWidth) {
			double ratioHeight = imageHeight / frameHeight;
			imageHeight = frameHeight;
			imageWidth = (int) (imageWidth / ratioHeight);
			g.drawImage(img, (int) (frameWidth - imageWidth) / 2, (int) (frameHeight - imageHeight) / 2, (int) newWidth,
					(int) imageHeight, this);

		}
	}
}