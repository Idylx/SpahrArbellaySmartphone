/*
 * Author : Bryan Spahr
 */

/*
 * Class that verifies that the ClockPanel runs correctly
 */

package panelsTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import panelss.ClockPanel;

public class ClockPanelTest {

	ClockPanel c = new ClockPanel();

	// Verifies that the text displayed is not null
	@Test
	public void testClock() {

		Assert.assertNotNull(c.getTimeText());

	}

}
