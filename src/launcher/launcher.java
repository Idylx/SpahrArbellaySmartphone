/*
 * Author : Bryan Spahr
 */
//test
/*
 * Launcher of the app, initialize HomeFrame and make it visible
 */

package launcher;

import java.awt.EventQueue;

import org.opencv.core.Core;

import frame.HomeFrame;

public class launcher {

	public static void main(String[] args) {

		// Load the specified library by default (used for the cam app)
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame hf1 = new HomeFrame();
					hf1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
