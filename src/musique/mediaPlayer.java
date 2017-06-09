/*
Author : Olivier Arbellay
Date: 7 juin 2017
*/
package musique;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class mediaPlayer {

	FileInputStream fis;
	BufferedInputStream bis;

	public Player player;
	
	public long pauseLocation ;
	public long songLength ;
	public String fileLocation ;

	public void Stop() {
		if (player != null) {
			player.close();
			
			pauseLocation= 0;
			songLength = 0 ;
		}
	}

	public void Pause() {
		if (player != null) {
			try {
				pauseLocation=fis.available();
				player.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // avancement de la musique
			
		}
	}

	public void play(String path) throws FileNotFoundException {

		try {
			fis = new FileInputStream(path);
			bis = new BufferedInputStream(fis);

			player = new Player(bis);
			
			songLength = fis.available() ;
			fileLocation = path+"";
			
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
		public void Resume() throws FileNotFoundException {

			try {
				fis = new FileInputStream(fileLocation);
				bis = new BufferedInputStream(fis);

				player = new Player(bis);
				
				fis.skip(songLength-pauseLocation);
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


