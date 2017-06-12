/*
 * Author : Bryan Spahr
 */

/*
 * Class that verifies that the delete of a picture runs correctly
 */

package panelsTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import panelss.GalleryPanel;
import panelss.PicturePanel;
import panelss.PicturePanel.Delete_Button;

public class PicturePanelTest {

	int index = 0;

	GalleryPanel gp = new GalleryPanel();
	PicturePanel pp = new PicturePanel(index, gp);
	Delete_Button db = pp.new Delete_Button();

	// Verfies that the path of the pic deleted is physically deleted
	@Test
	public void deleteTest() {

		String pathFirstPicBefore = gp.getPath().get(index);

		db.actionPerformed(null);

		String pathFirstPicAfter = gp.getPath().get(index);

		// Check that the path are not the same after the delete
		Assert.assertNotEquals(pathFirstPicBefore, pathFirstPicAfter);

	}

}
