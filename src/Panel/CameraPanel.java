/*
 * Author : Bryan Spahr
 */

package Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import Buttons.ButtonApp;
import Photo.Photo;
import appContact.RepertoireContact;

import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Font;

public class CameraPanel extends JPanel {
	
//	RepertoireContact rep = new RepertoireContact();
	
	public boolean running = false;

	private JPanel west = new JPanel();
	private JPanel est = new JPanel();

	JPanel cam = new JPanel(new FlowLayout());
	JPanel bottomPanel = new JPanel(new FlowLayout());
	JPanel topPanel = new JPanel(new BorderLayout());

	ButtonApp camera = new ButtonApp(new Photo("./src/Pictures/cameraButton.png"));

	Photo photo = new Photo("./src/Pictures/wallpaperr.jpg");

	public DaemonThread myThread = null;
	int count = 0;
	public VideoCapture webSource = null;

	Mat frame = new Mat();
	MatOfByte mem = new MatOfByte();

	private JLabel saved = new JLabel("Saved !");

	public CameraPanel() {

		setLayout(new BorderLayout());
		bottomPanel.setPreferredSize(new Dimension(0, 100));
		topPanel.setPreferredSize(new Dimension(0, 70));
		west.setPreferredSize(new Dimension(20, 0));
		est.setPreferredSize(new Dimension(20, 0));
		west.setOpaque(false);
		est.setOpaque(false);
		topPanel.setOpaque(false);
		camera.setVerticalAlignment(SwingConstants.TOP);

		camera.addActionListener(new BoutonTake());

		bottomPanel.add(camera);

		add(est, BorderLayout.EAST);
		add(west, BorderLayout.WEST);

		add(topPanel, BorderLayout.NORTH);
		add(cam, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		saved.setHorizontalAlignment(SwingConstants.CENTER);
		saved.setFont(new Font("Arial", Font.BOLD, 18));
		saved.setForeground(Color.WHITE);

		topPanel.add(saved);
		saved.setVisible(false);

		bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

		cam.setOpaque(false);

		setBackground(Color.BLACK);

		bottomPanel.setOpaque(false);

		setVisible(true);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = photo.getImage();
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

	public void start() {
		webSource = new VideoCapture(0);
		myThread = new DaemonThread();
		Thread t = new Thread(myThread);
		t.setDaemon(true);
		myThread.runnable = true;
		t.start();
		running = true;
	}

	public class DaemonThread implements Runnable {
		public volatile boolean runnable = false;

		@Override
		public void run() {
			synchronized (this) {
				while (runnable) {
					if (webSource.grab()) {
						try {
							webSource.retrieve(frame);
							Imgcodecs.imencode(".bmp", frame, mem);
							Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

							BufferedImage buff = (BufferedImage) im;
							Graphics g = cam.getGraphics();

							if (g.drawImage(buff, 0, 0, getWidth(), getHeight() - 150, 0, 0, buff.getWidth(),
									buff.getHeight(), null))

								if (runnable == false) {

									this.wait();
								}
						} catch (Exception ex) {
							System.out.println("Error");
						}
					}
				}
			}
		}
	}

	class ButtonQuit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	class BoutonTake implements ActionListener {

		Random rand = new Random();
		int randomName;
		String name;

		boolean reroll = false;

		public void actionPerformed(ActionEvent e) {

			randomName = rand.nextInt(999999);

			name = Integer.toString(randomName);
			name += ".jpg";

			File f = new File("./src/GalleriePhotos/");
			File[] images = f.listFiles();
			String[] imagesName = new String[images.length];

			for (int i = 0; i < images.length; i++) {
				imagesName[i] = images[i].getName();

			}

			for (int i = 0; i < imagesName.length; i++) {

				if (name.equals(imagesName[i])) {
					reroll = true;

				}
			}
			if (reroll == false) {
				Imgcodecs.imwrite("./src/GalleriePhotos/" + name, frame);
				confirmation();
				
				if (AddContactPanel.isAddContact == true){
					
					AddContactPanel.pathTronche ="./src/GalleriePhotos/" + name;
					AddContactPanel.isAddContact = false;
					
//					A 
//						
//						isaddContact false
//						cardlayout show addcontactPanel
//					}	
				}
			} else {
			
				
				actionPerformed(e);
			}
		}

		void confirmation() {

			// create timer to dispose of dialog after 5 seconds
			Timer timer = new Timer(1000, new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					saved.setVisible(false);
				}
			});
			timer.setRepeats(false);// the timer should only go off once

			// start timer to close JDialog as dialog modal we must start the
			// timer before its visible
			timer.start();

			saved.setVisible(true);
		}

	}

}
