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

public class DatePanel extends JPanel{

	private JLabel date;
	
	Font font = new Font("Arial", Font.BOLD, 30);

	public DatePanel() {
		
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(10, 0, 0, 15));

		date = new JLabel();
		date.setOpaque(false);
	
		date.setFont(font);
		date.setForeground(Color.white);
		getDate();
		add(date, BorderLayout.EAST);
//		setBackground(new Color(0,0,0,0));
//		
//		add(container);

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
	
	public void getDate() {
		date.setText(DateFormat.getDateInstance().format(new Date()));
	}

}
