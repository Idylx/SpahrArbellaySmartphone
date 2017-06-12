/*
 * Author : Bryan Spahr
 */

/*
 *  Panel that displays the hour in real time with this format : HH-MM-SS
 *  and displayed in the HomeFrame
 */

package panelss;

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

public class ClockPanel extends JPanel {

	// JLabel used to display the hour
	private JLabel clock;

	// Font of the clock
	private Font font = new Font("Roboto", Font.PLAIN, 80);

	// Constructor
	public ClockPanel() {

		// Settings of the panel
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(30, 0, 20, 0));

		// Initialize the label
		clock = new JLabel();

		// Settings of the JLabel
		clock.setFont(font);
		clock.setForeground(Color.white);

		// Call to the getTime method
		getTime();

		// Add the clock panel to this panel
		add(clock, BorderLayout.EAST);

		// Set the panel non-opaque
		setOpaque(false);

		// Initialize a timer that refresh every 1/2 second
		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getTime();
			}
		});

		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();
	}

	// Method that formats and writes the hour on the JLabel
	public void getTime() {
		clock.setText(DateFormat.getTimeInstance().format(new Date()));
	}

	// Method that return the text of clock used for the JUnit test
	public String getTimeText() {
		return clock.getText();
	}
}