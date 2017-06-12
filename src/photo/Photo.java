/*
 * Author : Bryan Spahr
 */

/*
 * Class that extends ImageIcon and is used to create personalized buttons
 */

package photo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Photo extends ImageIcon {

	private String path;

	public Photo(String path) {
		super(path);
		this.path = path;
	}

	public Photo(Image newimg) {
		super(newimg);
	}

	public String getPath() {
		return path;
	}

}
