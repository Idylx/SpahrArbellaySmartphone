/*
 * Author : Bryan Spahr
 */

/*
 * Panel qui affiche la date implémenté NORD-EST à la frame principale
 */

package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class DatePanel extends JPanel {

	// JLabel where the dates is written
	private JLabel date;

	// Font of the date
	private Font font = new Font("Arial", Font.BOLD, 30);

	// Constructor
	public DatePanel() {

		// Settings of the panel
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(10, 0, 0, 15));

		// Initialize the label
		date = new JLabel();

		// Settings of the JLabel date
		date.setFont(font);
		date.setForeground(Color.white);

		getDate();

		// Add the JLabel to the panel
		add(date, BorderLayout.EAST);

		// Create a timer
		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getDate();
			}
		});

		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();
	}

	// Write the date on the JLabel
	public void getDate() {
		date.setText(DateFormat.getDateInstance().format(new Date()));
	}

}
