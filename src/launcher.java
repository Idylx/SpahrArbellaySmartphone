/*
 * Author : Bryan Spahr
 */

import java.awt.EventQueue;

import org.opencv.core.Core;

import Frame.HomeFrame;

public class launcher { //launcher de l'application

	public static void main(String[] args) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame hf = new HomeFrame();
					hf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
