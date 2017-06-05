package Panel;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Buttons.ButtonApp;
import Gallerie.Photo;


public class GoogleQueryPanel extends JPanel {
	
	private FlowLayout fl = new FlowLayout();
	
	ButtonApp google = new ButtonApp(new Photo("./src/Pictures/google.png"));
	ButtonApp loupe = new ButtonApp(new Photo("./src/Pictures/loupe.png"));
	JTextField searchQuery = new JTextField();
	
	Font font = new Font("Arial", Font.PLAIN, 20);
	
	public GoogleQueryPanel()
	{
		setLayout(fl);
		setPreferredSize(new Dimension(480,80));
		
		searchQuery.setPreferredSize(new Dimension(260, 48));
		searchQuery.setFont(font);
		searchQuery.setOpaque(false);
		searchQuery.setForeground(Color.WHITE);
		searchQuery.setCaretColor(Color.WHITE);
		
		
		google.setHorizontalAlignment(SwingConstants.LEFT);
		add(google);
		add(searchQuery);
		add(loupe);
		loupe.addActionListener(new BrowserBouton());
		setOpaque(false);
		
		
	}
	
	class BrowserBouton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String query = searchQuery.getText();
			String googleQuery = "http://www.google.ch/search?q="+query;
			googleQuery = googleQuery.replace(" ", "+");
			Desktop d = Desktop.getDesktop();
			try {
				d.browse(new URI(googleQuery));
			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
