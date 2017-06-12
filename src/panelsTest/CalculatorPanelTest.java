/*
 * Author : Bryan Spahr
 */

/*
 * Class that verifies that the operations of the calculator are correct
 */

package panelsTest;

import static org.junit.Assert.*;
import org.junit.Test;

import panels.CalculatorPanel;

public class CalculatorPanelTest {

	double R = 2.0;
	double L = 4.0;

	@Test
	public void testOperationAddition() {

		char op = '+';

		double result = CalculatorPanel.operation(R, L, op);

		assertEquals(6.0, result, 0);

	}

	@Test
	public void testOperationSoustraction() {

		char op = '-';

		double result = CalculatorPanel.operation(R, L, op);

		assertEquals(-2.0, result, 0);

	}

	@Test
	public void testOperationDivision() {

		char op = '/';

		double result = CalculatorPanel.operation(R, L, op);

		assertEquals(0.5, result, 0);

	}

	@Test
	public void testOperationMultiplication() {

		char op = '*';

		double result = CalculatorPanel.operation(R, L, op);

		assertEquals(8.0, result, 0);

	}

}
