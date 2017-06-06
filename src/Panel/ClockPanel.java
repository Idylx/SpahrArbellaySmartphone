package Panel;

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
import javax.swing.SwingConstants;

public class ClockPanel extends JPanel{

	private JLabel clock;
	
	Font font = new Font("Roboto", Font.PLAIN, 80);

	public ClockPanel() {
		
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(30, 0, 20, 0));

		clock = new JLabel();
		
	
		clock.setFont(font);
		clock.setForeground(Color.white);
		getTime();
		add(clock, BorderLayout.EAST);
		
		setOpaque(false);


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
	
	public void getTime() {
		clock.setText(DateFormat.getTimeInstance().format(new Date()));
	}
}