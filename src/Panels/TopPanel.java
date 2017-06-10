/*
 * Author : Bryan Spahr
 */

/*
 * Basic personalized panel wich goes in the north of the HomeFrame
 * Panel that contains DatePanel which display the date
 */

package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class TopPanel extends JPanel {

	// Initialize the class DatePanel
	private DatePanel date = new DatePanel();

	// Dimension
	private Dimension dimension = new Dimension(450, 65);

	// Constructor
	public TopPanel() {

		// set the color of the background
		setBackground(Color.BLACK);
		// Set the dimension of the panel
		setPreferredSize(dimension);
		// set the layout
		setLayout(new BorderLayout());
		// add the panel date to this panel
		add(date, BorderLayout.EAST);
	}
}
