package Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import Gallerie.Photo;

public class GMainPanel extends JPanel {

	private FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 10, 10);

	private Photo photo;
	
	Dimension dim = new Dimension(480,640);

	public GMainPanel() {
		setLayout(fl);
		setBackground(Color.black);
	
		setPreferredSize(dim);
		
	}
}