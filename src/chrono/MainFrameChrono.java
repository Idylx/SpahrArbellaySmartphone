/*
Author : Olivier Arbellay
Date: 14 mai 2017
*/
package chrono;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrameChrono extends JFrame{

	Chrono chrono = new Chrono();
	JPanel Panel1 = new JPanel();
	JLabel Label1 = new JLabel();
	JButton debut = new JButton("Start");
	JButton fin = new JButton("Remise à zéro");
	JButton pause = new JButton("Pause");
	JButton resume = new JButton("Resume");
	JFrame fenetre = new JFrame("Chronomètre");
	

	public MainFrameChrono() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		add(Panel1, BorderLayout.NORTH);
		add(Label1);
		
		Panel1.add(debut);
		Panel1.add(fin);
		Panel1.add(pause);
		Panel1.add(resume);
		
		Label1.setBorder(new EmptyBorder(10, 135, 10, 10));
		fenetre.getContentPane().add(Label1, "Center");
		fenetre.getContentPane().add(Panel1, "South");

		debut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chrono.start();
			}
		});

		fin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chrono.stop();
			}
		});
		
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chrono.pause();;
			}
		});
		
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chrono.resume();
			}
		});

		pack();
	}
}
