/*
 * Author : Bryan Spahr
 */

package Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TopPanel extends JPanel {

	DatePanel date = new DatePanel();

	Dimension dimension = new Dimension(450, 65);

	public TopPanel() {

		setBackground(Color.BLACK);
		setPreferredSize(dimension);
		setLayout(new BorderLayout());
		add(date, BorderLayout.EAST);

	}

}
