/*
 * Author : Bryan Spahr
 */

package Photo;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Photo extends ImageIcon implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public void setPath(String path) {
		this.path = path;
	}

}