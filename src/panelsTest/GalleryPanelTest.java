/*
 * Author : Bryan Spahr
 */

/*
 *  Class that verifies that the different arrays are correct
 */

package panelsTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import panels.GalleryPanel;

public class GalleryPanelTest extends GalleryPanel {

	GalleryPanel gp = new GalleryPanel();

	// Check if the path array is well filled
	@Test
	public void fillPathTest() {

		gp.fillPath();
		Assert.assertNotNull(gp.getPath());

	}

	// Check if the imgs array is well filled
	@Test
	public void fillImgsTest() {

		gp.fillImgs();
		Assert.assertNotNull(gp.getImgs());

	}

	// Check if the boutons array is well filled
	@Test
	public void addButtonTest() {

		gp.addButton();
		Assert.assertNotNull(gp.getBoutons());

	}

	/*
	 * Check that the method .clear() works correctly the array must be emtpy
	 * after the call of the method delete(). The test is run on 1 array only
	 * (imgs here) because all the arrays call the same method.
	 */
	@Test
	public void deleteTest() {

		int index = 0;
		String path = gp.getPath().get(index);

		// Call to the method delete
		gp.delete(index, path);

		int arraySize = gp.getImgs().size();

		// Verifies if the size of the array is effectively 0
		Assert.assertTrue(arraySize == 0);

	}

}
