/*
 * Author : Bryan Spahr
 */

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
		System.load(Core.NATIVE_LIBRARY_NAME);

		HomeFrame hf1 = new HomeFrame();
		hf1.setVisible(true);

	}

}
