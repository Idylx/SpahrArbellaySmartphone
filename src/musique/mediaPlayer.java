/*
Author : Olivier Arbellay
Date: 7 juin 2017

Inspiré de https://www.youtube.com/watch?v=LavMuqK5Is0&list=WL&index=8&t=1314s
*/
package musique;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;

import panels.Mp3PlayerPanel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class mediaPlayer {

	
	//déclarations des variables pour le stream
	FileInputStream fis;
	BufferedInputStream bis;

	public Player player;

	
	// déclaration des variables pour retrouver la pause
	public long pauseLocation;
	public long songLength;
	public String fileLocation;

	//déclarations d'un boolean pour savoir s'il le lecteur tourne
	boolean isPlaying;

	
	//méthode stoppant la musique
	public void Stop() {
		if (player != null) {
			player.close();

			pauseLocation = 0;
			songLength = 0;

			isPlaying = false;

		}
	}

	//méthode mettant sur pause
	public void Pause() {
		
		if (player != null) {
			try {
				pauseLocation = fis.available();
				player.close();
				isPlaying = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // avancement de la musique

		}
	}

	
	// methode demmarrant le mp3
	public void play(String path) throws FileNotFoundException {

		try {
			fis = new FileInputStream(path);
			bis = new BufferedInputStream(fis);

			player = new Player(bis);

			songLength = fis.available();
			fileLocation = path + "";

		} catch (JavaLayerException | IOException e) {
			e.printStackTrace();
		}

		//thread pour demmarer
		new Thread() {
			@Override
			public void run() {
				try {
					player.play();

					if (player.isComplete() && Mp3PlayerPanel.count == 1) {
						play(fileLocation);
					}

					if (player.isComplete()) {
						Mp3PlayerPanel.displaySong.setText("");
					}
				} catch (JavaLayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	//recommence la musique en cas de pause

	public void Resume() throws FileNotFoundException {

		if (isPlaying == false) {

			try {
				fis = new FileInputStream(fileLocation);
				bis = new BufferedInputStream(fis);

				player = new Player(bis);
				
				isPlaying = true;

				fis.skip(songLength - pauseLocation);
			} catch (JavaLayerException | IOException e) {
				e.printStackTrace();
			}

			new Thread() {
				@Override
				public void run() {
					try {
						player.play();

					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		}

	}

}
