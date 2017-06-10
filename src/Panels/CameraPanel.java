/*
 * Author : Bryan Spahr
 */

/*
 * Panel that access to the webcam of the computer and activate it to take pictures.
 * The pictures are immedialty saved in the photo gallery. Panel displayed over
 * the HomeFrame as an application
 */

package panels;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import buttons.ButtonApplication;
import photo.Photo;

import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Font;

public class CameraPanel extends JPanel {

	// Panels
	private JPanel west = new JPanel();
	private JPanel est = new JPanel();
	private JPanel cam = new JPanel(new FlowLayout());
	private JPanel bottomPanel = new JPanel(new FlowLayout());
	private JPanel topPanel = new JPanel(new BorderLayout());

	// Button to take a pic
	private ButtonApplication camera = new ButtonApplication(new Photo("./src/Pictures/cameraButton.png"));

	// CLasses
	public DaemonThread thread = null;
	public VideoCapture videoCapture = null;

	private Mat frame = new Mat();
	private MatOfByte mem = new MatOfByte();

	// JLabel that confirms when a pic is taken
	private JLabel saved = new JLabel("Saved !");

	// Wallpaper
	private Photo wallpaper = new Photo("./src/Pictures/wallpaper.jpg");

	// Boolean
	public boolean running = false;

	// Constructor
	public CameraPanel() {

		// Setting of the panel
		setLayout(new BorderLayout());

		// Dimensions of the differents panels
		bottomPanel.setPreferredSize(new Dimension(0, 100));
		topPanel.setPreferredSize(new Dimension(0, 70));
		west.setPreferredSize(new Dimension(20, 0));
		est.setPreferredSize(new Dimension(20, 0));
		bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

		// Set the panels non-opaque
		west.setOpaque(false);
		est.setOpaque(false);
		topPanel.setOpaque(false);
		cam.setOpaque(false);
		bottomPanel.setOpaque(false);

		// Alignment of the button
		camera.setVerticalAlignment(SwingConstants.TOP);

		// Add the ActionsLister to the button
		camera.addActionListener(new BoutonTake());

		// Add the button to the panel
		bottomPanel.add(camera);

		// Add the different panels to the root Panel
		add(est, BorderLayout.EAST);
		add(west, BorderLayout.WEST);
		add(topPanel, BorderLayout.NORTH);
		add(cam, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		// Add the label saved to the top panel
		topPanel.add(saved);

		// Settings of the JLabel saved
		saved.setHorizontalAlignment(SwingConstants.CENTER);
		saved.setFont(new Font("Arial", Font.BOLD, 18));
		saved.setForeground(Color.WHITE);

		// Set the JLabel invisible by default
		saved.setVisible(false);

		// Settings of the panel
		setBackground(Color.BLACK);
		setVisible(true);

	}

	// Paint the background with the wallpaper picture
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = wallpaper.getImage();
		int frameWidth = this.getWidth();
		int frameHeight = this.getHeight();

		double imageWidth = img.getWidth(this);
		double imageHeight = img.getHeight(this);

		g.drawImage(img, (int) (frameWidth - imageWidth) / 2, (int) (frameHeight - imageHeight) / 2, (int) imageWidth,
				(int) imageHeight, this);

	}

	// Start the thread of the camera
	public void start() {

		videoCapture = new VideoCapture(0);
		thread = new DaemonThread();
		Thread t = new Thread(thread);
		t.setDaemon(true);
		thread.runnable = true;
		t.start();
		running = true;
	}

	// Class DaemonThred
	public class DaemonThread implements Runnable {
		public volatile boolean runnable = false;

		@Override
		public void run() {
			synchronized (this) {
				while (runnable) {
					if (videoCapture.grab()) {
						try {
							videoCapture.retrieve(frame);
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

	/*
	 * Classes that implements ActionListener to indicate what to do when a
	 * button is pressed
	 */

	class BoutonTake implements ActionListener {

		// Give the picture a random name
		Random rand = new Random();
		int randomName;
		String name;

		// True if a pic with this name already exists
		boolean reroll = false;

		public void actionPerformed(ActionEvent e) {

			randomName = rand.nextInt(999999);

			name = Integer.toString(randomName);
			name += ".jpg";

			File f = new File("./src/PhotoGallery/");
			File[] images = f.listFiles();
			String[] imagesName = new String[images.length];

			for (int i = 0; i < images.length; i++) {
				imagesName[i] = images[i].getName();
			}

			// Verifies if a picture with this name already exists
			for (int i = 0; i < imagesName.length; i++) {
				if (name.equals(imagesName[i]))
					reroll = true;
			}
			// Save/write the picture in the Photo Gallery
			if (reroll == false) {
				Imgcodecs.imwrite("./src/PhotoGallery/" + name, frame);
				confirmation();
			} else {
				actionPerformed(e);
			}

		}

		// Method that will confirm that the pic has been taken
		void confirmation() {

			// Create a timer to display the JLabel for 1 second
			Timer timer = new Timer(1000, new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					saved.setVisible(false);
				}
			});
			timer.setRepeats(false);

			timer.start();

			saved.setVisible(true);
		}

	}

}
