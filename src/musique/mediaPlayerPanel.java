/*
Author : Olivier Arbellay
Date: 8 juin 2017
Inspiré de https://www.youtube.com/watch?v=LavMuqK5Is0&list=WL&index=8&t=1314s
*/
package musique;

import java.awt.Dimension;
import java.awt.DisplayMode;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.JLabel;

public class mediaPlayerPanel extends JFrame {

	/**
	 * Create the panel.
	 */
	public mediaPlayerPanel() {

		mediaPlayer mp = new mediaPlayer();
		setPreferredSize(new Dimension(480, 800));
		setSize(480, 800);
		getContentPane().setLayout(null);

		JLabel displaySong = new JLabel("Choose a song");
		displaySong.setBounds(77, 107, 244, 23);
		getContentPane().add(displaySong);

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mp.Stop();
				displaySong.setText("");
			}
		});
		btnStop.setBounds(34, 302, 89, 23);
		getContentPane().add(btnStop);

		JButton btnPlay = new JButton("Play");// faire un if song play -->
												// restart
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					mp.Resume();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnPlay.setBounds(133, 302, 89, 23);
		getContentPane().add(btnPlay);

		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.Pause();
			}
		});
		btnPause.setBounds(232, 302, 89, 23);
		getContentPane().add(btnPause);

		JButton btnSelectfile = new JButton("SelectFile");
		btnSelectfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 Files", "mp3", "mpeg3");
				JFileChooser chooser = new JFileChooser("D:/Workplace/SpahrArbellaySmartphone/Musique");
				chooser.addChoosableFileFilter(filter);

				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
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
		});
		btnSelectfile.setBounds(211, 408, 89, 23);
		getContentPane().add(btnSelectfile);

	}
}
