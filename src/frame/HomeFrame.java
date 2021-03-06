/*
 * Author : Bryan Spahr

/*
 * Home/Main Frame of the applications. Used as base for the app.
 * The different panels come over this frame.
 */

package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import appContact.RepertoireContact;
import buttons.ButtonApplication;
import buttons.ButtonHome;
import panels.CalculatorPanel;
import panels.CameraPanel;
import panels.ClockPanel;
import panels.GalleryPanel;
import panels.GoogleQueryPanel;
import panels.HomeFramePanel;
import panels.Mp3PlayerPanel;
import panels.RepertoirePanel;
import panels.TopPanel;
import photo.Photo;

public class HomeFrame extends JFrame {

	// Layout
	private CardLayout c1 = new CardLayout();

	// Main panels of the frame
	private JPanel west = new JPanel();
	private JPanel est = new JPanel();
	private JPanel south = new JPanel();
	private JPanel north = new TopPanel();

	//Panel that contains the app buttons
	private JPanel panel = new JPanel(new GridLayout(2,3,10,10));
	
	//Main Panel of the frame
	private JPanel mainPanel = new JPanel();

	// Apps panels in CardLayout
	private HomeFramePanel hfPanel = new HomeFramePanel();
	private CalculatorPanel calculatrice;
	private GalleryPanel gallery;
	private CameraPanel camera = new CameraPanel();
	private RepertoirePanel repContact = new RepertoirePanel();
	private Mp3PlayerPanel player= new Mp3PlayerPanel();

	// Clock Panel
	private ClockPanel clock = new ClockPanel();

	// Google search bar panel
	private GoogleQueryPanel google = new GoogleQueryPanel();

	// Apps Buttons
	private ButtonApplication bCalculatrice = new ButtonApplication(new Photo("./src/Pictures/calculator.png"));
	private ButtonApplication bGallery = new ButtonApplication(new Photo("./src/Pictures/gallery.png"));
	private ButtonApplication bCamera = new ButtonApplication(new Photo("./src/Pictures/camera.png"));
	private ButtonApplication bContact = new ButtonApplication(new Photo("./src/Pictures/contact.png"));
	private ButtonApplication bPlayer = new ButtonApplication(new Photo("./src/Pictures/music.png"));

	// Home Button for the south panel
	private ButtonHome bHome = new ButtonHome();

	public HomeFrame() {

		// Different settings for the frame (dimension, position, etc)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(480, 800));
		setSize(480, 800);
		setResizable(false);
		setLocationRelativeTo(null);

		// Dimension settigs of the panels
		west.setPreferredSize(new Dimension(0, 0));
		est.setPreferredSize(new Dimension(0, 0));
		
		//Set empty border for the design
		panel.setBorder(new EmptyBorder(10,0,160,0));

		// Add action listener to buttons
		bCalculatrice.addActionListener(new BoutonCalculatrice());
		bHome.addActionListener(new BoutonHome());
		bGallery.addActionListener(new BoutonGallery());
		bCamera.addActionListener(new BoutonCamera());
		bContact.addActionListener(new BoutonContact());
		bPlayer.addActionListener(new BoutonPlayer());

		// Add buttons to the panel
		panel.add(bCalculatrice);
		panel.add(bGallery);
		panel.add(bCamera);
		panel.add(bContact);
		panel.add(bPlayer);

		// Set the panel non-opaque
		panel.setOpaque(false);

		/*
		 * Add the different panels (clock, google search bar) to the homeframe
		 * panel
		 */
		hfPanel.add(clock, BorderLayout.NORTH);
		hfPanel.add(panel, BorderLayout.CENTER);
		hfPanel.add(google, BorderLayout.SOUTH);

		/*
		 * Set the background color of the panels south & north
		 */
		south.setBackground(Color.BLACK);
		north.setBackground(Color.BLACK);

		// Add button home to south panel
		south.add(bHome);

		// Set the layout of mainPanel and add the hfpanel to the mainPanel
		mainPanel.setLayout(c1);
		mainPanel.add(hfPanel, "ApplicationsPanel");

		// Display of the mainPanel
		c1.show(mainPanel, "mainPanel");

		// add the differents panels to this frame
		add(north, BorderLayout.NORTH);
		add(est, BorderLayout.EAST);
		add(west, BorderLayout.WEST);
		add(south, BorderLayout.SOUTH);
		add(mainPanel);

	}

	/*
	 * Allows the user to come back at the Home Frame when the Home Button is
	 * pressed
	 */
	class BoutonHome implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			/*
			 * Close the thread of the camera if the user click on the home
			 * button and the camera is running
			 */
			if (camera.running == true) {
				camera.thread.runnable = false;
				camera.videoCapture.release();
			}

			c1.show(mainPanel, "ApplicationsPanel");
		}

	}

	// Open the camera panel to take a picture (over the mainPanel)
	class BoutonCamera implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			camera.start();
			mainPanel.add(camera, "hello");
			c1.show(mainPanel, "hello");
		}

	}

	// Open the calculator panel (over the mainPanel)
	class BoutonCalculatrice implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			calculatrice = new CalculatorPanel();

			mainPanel.add(calculatrice, "calculatrice");
			c1.show(mainPanel, "calculatrice");

		}
	}

	// Open the PhotoGallery over the mainPanel
	class BoutonGallery implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			gallery = new GalleryPanel();

			mainPanel.add(gallery, "gallery");
			c1.show(mainPanel, "gallery");

		}

	}

	//Open the RepertoirePanel over the mainPanel
	class BoutonContact implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			mainPanel.add(repContact, "repContact");
			c1.show(mainPanel, "repContact");

		}

	}
	
	//Open the Mp3PlayerPanel over the mainPanel
	class BoutonPlayer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			mainPanel.add(player, "player");
			c1.show(mainPanel, "player");

		}

	}
}
