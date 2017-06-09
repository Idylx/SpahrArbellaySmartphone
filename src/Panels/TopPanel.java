/*
 * Author : Bryan Spahr
 */

/*
 * Panel de base qui va se placer au nord de la frame principale HomeFrame.
 * Panel qui sert à contenir le panel DatePanel qui affiche la date
 */

package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
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
