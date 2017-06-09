/*
 * Author : Bryan Spahr
 */

/*
 * Panel qui affiche l'heure au format HH:MM:SS et implémenté à la frame principale
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
import javax.swing.SwingConstants;

public class ClockPanel extends JPanel {

	// JLabel qui affiche l'heure
	private JLabel clock;

	// Police de l'horloge
	private Font font = new Font("Roboto", Font.PLAIN, 80);

	// Constructeur
	public ClockPanel() {

		// Réglages du panel
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(30, 0, 20, 0));

		// Initialise le JLabel
		clock = new JLabel();

		// Réglages du JLabel clock
		clock.setFont(new Font("Roboto Condensed", Font.BOLD, 80));
		clock.setForeground(Color.white);

		// Appel à la méthode getTime
		getTime();

		// Ajout du JLabel au panel
		add(clock, BorderLayout.EAST);

		// Rend le panel transparent
		setOpaque(false);

		// Instanciation d'un timer qui se rafraichît chaque 1/2 seconde
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

	// Méthode qui écrit l'heure sur le JLabel
	public void getTime() {
		clock.setText(DateFormat.getTimeInstance().format(new Date()));
	}
}