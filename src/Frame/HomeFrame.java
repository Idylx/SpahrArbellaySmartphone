package Frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Buttons.ButtonApp;
import Buttons.HomeButton;
import Gallerie.Photo;
import Panel.ApplicationsPanel;
import Panel.CalculatorPanel;
import Panel.TopPanel;


public class HomeFrame extends JFrame {

	private JPanel west = new JPanel();
	private JPanel est = new JPanel();
	private JPanel south = new JPanel();
	private JPanel north = new TopPanel();

	public CardLayout c1 = new CardLayout();
	public JPanel mainPanel = new JPanel();

	private ApplicationsPanel appsPanel = new ApplicationsPanel();

	private ButtonApp bCalculatrice = new ButtonApp(new Photo("./src/Pictures/home.png"));
	private JButton bGallery = new JButton("Gallery");
	private JButton app3 = new JButton("Appli 3");
	private JButton app4 = new JButton("Appli 4");

	private HomeButton bHome = new HomeButton();

	private CalculatorPanel calculatrice = new CalculatorPanel();
	//private GalleryPanel gallery = new GalleryPanel(appsPanel);
	

	public HomeFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(480, 800));
		setSize(480, 800);
		setResizable(false);
		setLocationRelativeTo(null);
	

		bCalculatrice.addActionListener(new BoutonCalculatrice());
		bHome.addActionListener(new BoutonHome());
	//	bGallery.addActionListener(new BoutonGallery());

		appsPanel.add(bCalculatrice);
		appsPanel.add(bGallery);
		appsPanel.add(app3);
		appsPanel.add(app4);


		west.setPreferredSize(new Dimension(0,0));
		est.setPreferredSize(new Dimension(0,0));
		south.setBackground(Color.BLACK);
		north.setBackground(Color.BLACK);
		
		south.add(bHome);
		
		mainPanel.setLayout(c1);
		mainPanel.add(appsPanel, "ApplicationsPanel");
		c1.show(mainPanel, "mainPanel");

		// add panels to container
		add(north, BorderLayout.NORTH);
		add(est, BorderLayout.EAST);
		add(west, BorderLayout.WEST);
		add(south, BorderLayout.SOUTH);
		add(mainPanel);

		pack();

	}

	class BoutonHome implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			c1.show(mainPanel, "ApplicationsPanel");

		}

	}

	class BoutonCalculatrice implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			mainPanel.add(calculatrice, "calculatrice");
			c1.show(mainPanel, "calculatrice");

		}
	}
	
//	class BoutonGallery implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			
//			mainPanel.add(gallery, "gallery");
//			c1.show(mainPanel, "gallery");
//			
//		}
//		
//		
//	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame frame = new HomeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
