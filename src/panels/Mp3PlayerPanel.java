/*
Author : Olivier Arbellay
Date: 11 juin 2017
*/
package panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import buttons.ButtonApplication;
import frame.HomeFrame;
import photo.Photo;
import musique.mediaPlayer;
import javax.swing.UIManager;

public class Mp3PlayerPanel extends JPanel {

	mediaPlayer mp = new mediaPlayer();
	
	private CardLayout c2 = new CardLayout();

	public static int count;

	JPanel mainPanel = new JPanel();
	JPanel infoPanel = new JPanel();
	JPanel playPanel = new JPanel();
	JPanel choosePanel = new JPanel();
	JPanel displayPanel = new JPanel();

	public static JLabel displaySong = new JLabel("Choose a song");

	Photo noLoop = new Photo("./src/Pictures/noLoop.png");
	Photo Loop = new Photo("./src/Pictures/loop.png");

	ButtonApplication play = new ButtonApplication(new Photo("./src/Pictures/play.png"));
	ButtonApplication stop = new ButtonApplication(new Photo("./src/Pictures/stop.png"));
	ButtonApplication pause = new ButtonApplication(new Photo("./src/Pictures/pause.png"));
	ButtonApplication choose = new ButtonApplication(new Photo("./src/Pictures/choose.png"));
	ButtonApplication loop = new ButtonApplication(noLoop);

	CMainPanel c = new CMainPanel();


	public Mp3PlayerPanel() {
		
		setLayout(c2);
		 addStuff();
		add(c);
		
		
		c2.show(Mp3PlayerPanel.this, "MP3");
		

	}

	void addStuff() {

		c.setLayout(new BorderLayout());
		c.add(infoPanel, BorderLayout.CENTER);

		infoPanel.setLayout(new BorderLayout());
		infoPanel.add(displayPanel, BorderLayout.NORTH);
		infoPanel.add(playPanel, BorderLayout.CENTER);
		infoPanel.setOpaque(false);
		infoPanel.add(choosePanel, BorderLayout.SOUTH);

		displayPanel.setLayout(new BorderLayout());
		displayPanel.add(displaySong, BorderLayout.CENTER);
		displaySong.setForeground(Color.white);
		displaySong.setFont(new Font("28-Segment LED Display", Font.BOLD, 30));
		displayPanel.setOpaque(false);

		playPanel.setLayout(new BorderLayout());
		playPanel.add(play, BorderLayout.CENTER);
		playPanel.setOpaque(false);
		play.addActionListener(new Play_Button());
		playPanel.add(stop, BorderLayout.WEST);
		stop.addActionListener(new Stop_Button());
		playPanel.add(pause, BorderLayout.EAST);
		pause.addActionListener(new Pause_Button());

		choosePanel.setLayout(new BorderLayout());
		choosePanel.add(loop, BorderLayout.WEST);
		choosePanel.setOpaque(false);
		loop.addActionListener(new Loop_Button());
		choosePanel.add(choose, BorderLayout.CENTER);
		choose.addActionListener(new Choose_Button());

	}

	class Play_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			try {
				mp.Resume();

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	class Pause_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			mp.Pause();

		}
	}

	class Stop_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			mp.Stop();
			displaySong.setText("");
		}
	}

	class Choose_Button implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 Files", "mp3", "mpeg3");
			JFileChooser chooser = new JFileChooser("./src/music");
			chooser.addChoosableFileFilter(filter);

			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {

				mp.Stop();

				File myFile = chooser.getSelectedFile();
				String song = myFile + "";

				String name = chooser.getSelectedFile().getName();
				displaySong.setText(name);

				try {

					mp.play(song);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

	class Loop_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			switch (count) {
			case 0:

				count = 1;
				loop.setIcon(Loop);
				break;
			case 1:
				count = 0;
				loop.setIcon(noLoop);
				break;
			}

		}
	}

}
