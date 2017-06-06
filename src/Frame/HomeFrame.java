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

import Buttons.ButtonApp;
import Buttons.HomeButton;
import Panel.ApplicationsPanel;
import Panel.CalculatorPanel;
import Panel.ClockPanel;
import Panel.DatePanel;
import Panel.GalleryPanel;
import Panel.GoogleQueryPanel;
import Panel.CameraPanel;
import Panel.TopPanel;
import Photo.Photo;

public class HomeFrame extends JFrame {

	private JPanel west = new JPanel();
	private JPanel est = new JPanel();
	private JPanel south = new JPanel();
	private JPanel north = new TopPanel();

	public CardLayout c1 = new CardLayout();
	public JPanel mainPanel = new JPanel();

	private ApplicationsPanel appsPanel = new ApplicationsPanel();

	private ButtonApp bCalculatrice = new ButtonApp(new Photo("./src/Pictures/calculator.png"));
	private ButtonApp bGallery = new ButtonApp(new Photo("./src/Pictures/gallery.png"));
	private ButtonApp bCamera = new ButtonApp(new Photo("./src/Pictures/camera.png"));
	private ButtonApp app4 = new ButtonApp(new Photo("./src/Pictures/camera.png"));
	private ButtonApp app5 = new ButtonApp(new Photo("./src/Pictures/camera.png"));
	private ButtonApp app6 = new ButtonApp(new Photo("./src/Pictures/camera.png"));

	private HomeButton bHome = new HomeButton();

	private CalculatorPanel calculatrice;
	private GalleryPanel gallery;
	private CameraPanel camera = new CameraPanel();

	JPanel panel = new JPanel(new FlowLayout());
	ClockPanel clock = new ClockPanel();
	GoogleQueryPanel google = new GoogleQueryPanel();

	public HomeFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(480, 800));
		setSize(480, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel.setPreferredSize(new Dimension(440, 150));

		bCalculatrice.addActionListener(new BoutonCalculatrice());
		bHome.addActionListener(new BoutonHome());
		bGallery.addActionListener(new BoutonGallery());
		bCamera.addActionListener(new BoutonCamera());

		panel.add(bCalculatrice);
		panel.add(bGallery);
		panel.add(bCamera);
//		panel.add(app4);
//		panel.add(app5);
//		panel.add(app6);

		panel.setOpaque(false);

		appsPanel.add(clock, BorderLayout.NORTH);
		appsPanel.add(panel, BorderLayout.CENTER);
		appsPanel.add(google, BorderLayout.SOUTH);

		west.setPreferredSize(new Dimension(0, 0));
		est.setPreferredSize(new Dimension(0, 0));
		south.setBackground(Color.BLACK);
		north.setBackground(Color.BLACK);

		south.add(bHome);

		mainPanel.setLayout(c1);
		mainPanel.add(appsPanel, "ApplicationsPanel");
		c1.show(mainPanel, "mainPanel");

		// add panels to container
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(est, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.WEST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);

		pack();

	}
	
	public CardLayout getCL(){
		
		return c1;
	}

	class BoutonHome implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (camera.running == true) {
				camera.myThread.runnable = false;
				camera.webSource.release();
			}

			c1.show(mainPanel, "ApplicationsPanel");

		}

	}

	class BoutonCamera implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
	

			camera.start();
			mainPanel.add(camera, "hello");
			c1.show(mainPanel, "hello");
		}

	}

	class BoutonCalculatrice implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			calculatrice = new CalculatorPanel();

			mainPanel.add(calculatrice, "calculatrice");
			c1.show(mainPanel, "calculatrice");

		}
	}

	class BoutonGallery implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			gallery = new GalleryPanel(HomeFrame.this);

			mainPanel.add(gallery, "gallery");
			c1.show(mainPanel, "gallery");

		}

	}
}
