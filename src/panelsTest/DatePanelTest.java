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

import panels.DatePanel;

public class DatePanelTest {

	DatePanel d = new DatePanel();

	// Verifies that the text displayed is not null
	@Test
	public void testDate() {

		Assert.assertNotNull(d.getDateText());

	}

}
