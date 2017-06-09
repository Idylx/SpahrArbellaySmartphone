/*
 * Author : Bryan Spahr
 */

/*
 * Panel qui contient un JTextField et utilisé comme barre de recherche google.
 * Ouvre le navigateur web de l'utilisateur.
 * Le panel est implémenté à la frame principale HomeFrame
 */

package Panel;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Buttons.ButtonApplication;
import Photo.Photo;

public class GoogleQueryPanel extends JPanel {

	// Layout
	private FlowLayout fl = new FlowLayout();

	// Buttons
	private ButtonApplication google = new ButtonApplication(new Photo("./src/Pictures/google.png"));
	private ButtonApplication loupe = new ButtonApplication(new Photo("./src/Pictures/loupe.png"));

	// JTextField
	private JTextField searchQuery = new JTextField();

	// Font of the JTextField
	private Font font = new Font("Arial", Font.PLAIN, 20);

	// Constructor
	public GoogleQueryPanel() {

		// Settings of the panel (layout, dimension, transparency)
		setLayout(fl);
		setPreferredSize(new Dimension(480, 80));
		setOpaque(false);

		// Settings of the JTextfield (size, font, color, etc)
		searchQuery.setPreferredSize(new Dimension(260, 48));
		searchQuery.setFont(font);
		searchQuery.setOpaque(false);
		searchQuery.setForeground(Color.WHITE);
		// Set the color of the cursor to white
		searchQuery.setCaretColor(Color.WHITE);

		// Alignement of the google icon
		google.setHorizontalAlignment(SwingConstants.LEFT);

		// Add the action listener to the loupe icon/button
		loupe.addActionListener(new BrowserBouton());

		// Add the component to the panels
		add(google);
		add(searchQuery);
		add(loupe);

	}

	// Open the browser when the button is pressed
	class BrowserBouton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			// get the text typed
			String query = searchQuery.getText();
			// set the URL and add the query text
			String googleQuery = "http://www.google.ch/search?q=" + query;
			// replace the space with a + because an URL can't contain space
			googleQuery = googleQuery.replace(" ", "+");
			/*
			 * Initialization of the class Desktop which allows to access to the
			 * browser for example
			 */
			Desktop d = Desktop.getDesktop();
			// open the browser with the address
			try {
				d.browse(new URI(googleQuery));
			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
