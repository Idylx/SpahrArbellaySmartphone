/*
 * Author : Bryan Spahr

/*
 * Frame principale de l'application
 */

package Frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.opencv.core.Core;

import Buttons.ButtonApplication;
import Buttons.ButtonHome;
import Panel.HomeFramePanel;
import Panel.CalculatorPanel;
import Panel.ClockPanel;
import Panel.DatePanel;
import Panel.GalleryPanel;
import Panel.GoogleQueryPanel;
import Panel.CameraPanel;
import Panel.TopPanel;
import Photo.Photo;

public class HomeFrame extends JFrame {

	// Layout
	private CardLayout c1 = new CardLayout();

	// Panels principaux de la Frame
	private JPanel west = new JPanel();
	private JPanel est = new JPanel();
	private JPanel south = new JPanel();
	private JPanel north = new TopPanel();

	private JPanel panel = new JPanel(new FlowLayout());
	private JPanel mainPanel = new JPanel();

	// Panels d'applications en CardLayout
	private HomeFramePanel hfPanel = new HomeFramePanel();
	private CalculatorPanel calculatrice;
	private GalleryPanel gallery;
	private CameraPanel camera = new CameraPanel();

	// Panel de l'horloge
	private ClockPanel clock = new ClockPanel();

	// Panel de la barre de recherche
	private GoogleQueryPanel google = new GoogleQueryPanel();

	// Boutons des applications
	private ButtonApplication bCalculatrice = new ButtonApplication(new Photo("./src/Pictures/calculator.png"));
	private ButtonApplication bGallery = new ButtonApplication(new Photo("./src/Pictures/gallery.png"));
	private ButtonApplication bCamera = new ButtonApplication(new Photo("./src/Pictures/camera.png"));
	private ButtonApplication app4 = new ButtonApplication(new Photo("./src/Pictures/camera.png"));
	private ButtonApplication app5 = new ButtonApplication(new Photo("./src/Pictures/camera.png"));
	private ButtonApplication app6 = new ButtonApplication(new Photo("./src/Pictures/camera.png"));

	// Bouton pour le south panel
	private ButtonHome bHome = new ButtonHome();

	public HomeFrame() {

		// Divers réglages (dimension, position, etc) de la frame principale
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(480, 800));
		setSize(480, 800);
		setResizable(false);
		setLocationRelativeTo(null);

		// Réglage dimension des panels
		panel.setPreferredSize(new Dimension(440, 150));
		west.setPreferredSize(new Dimension(0, 0));
		est.setPreferredSize(new Dimension(0, 0));

		// Ajout des ActionListener au différents boutons
		bCalculatrice.addActionListener(new BoutonCalculatrice());
		bHome.addActionListener(new BoutonHome());
		bGallery.addActionListener(new BoutonGallery());
		bCamera.addActionListener(new BoutonCamera());

		// Ajout des boutons d'applications au panel
		panel.add(bCalculatrice);
		panel.add(bGallery);
		panel.add(bCamera);
		// panel.add(app4);
		// panel.add(app5);
		// panel.add(app6);

		// Réglage de la transparence du panel
		panel.setOpaque(false);

		/*
		 * Ajout des différents panels (horloge, barre de recherche, etc) de la
		 * frame principale
		 */
		hfPanel.add(clock, BorderLayout.NORTH);
		hfPanel.add(panel, BorderLayout.CENTER);
		hfPanel.add(google, BorderLayout.SOUTH);

		/*
		 * Réglage de la couleur d'arrière plan des panels au cas où le fond
		 * d'écran est redimensioné
		 */
		south.setBackground(Color.BLACK);
		north.setBackground(Color.BLACK);

		// Ajout du bouton Home au panel south
		south.add(bHome);

		// Définition du layout du mainPanel et ajout du panel principal
		mainPanel.setLayout(c1);
		mainPanel.add(hfPanel, "ApplicationsPanel");

		// Définition du panel affiché par le CardLayout
		c1.show(mainPanel, "mainPanel");

		// Ajout des panels de la frame principale
		add(north, BorderLayout.NORTH);
		add(est, BorderLayout.EAST);
		add(west, BorderLayout.WEST);
		add(south, BorderLayout.SOUTH);
		add(mainPanel);

	}

	/*
	 * Permet de revenir à la frame principale lorsque l'utilisateur clique sur
	 * le bouton / Home
	 */
	class BoutonHome implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			/*
			 * Ferme le thread de la camera si l'utilisateur clique sur le
			 * bouton home
			 */

			if (camera.running == true) {
				camera.thread.runnable = false;
				camera.videoCapture.release();
			}

			c1.show(mainPanel, "ApplicationsPanel");

		}

	}

	// Ouvre le panel Camera (en CardLayout) pour prendre une photo
	class BoutonCamera implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			camera.start();
			mainPanel.add(camera, "hello");
			c1.show(mainPanel, "hello");
		}

	}

	// Ouvre le panel Calculatrice (en CardLayout)
	class BoutonCalculatrice implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			calculatrice = new CalculatorPanel();

			mainPanel.add(calculatrice, "calculatrice");
			c1.show(mainPanel, "calculatrice");

		}
	}

	// Ouvre la galerie photo (en CardLayout)
	class BoutonGallery implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			gallery = new GalleryPanel(HomeFrame.this);

			mainPanel.add(gallery, "gallery");
			c1.show(mainPanel, "gallery");

		}

	}
}
