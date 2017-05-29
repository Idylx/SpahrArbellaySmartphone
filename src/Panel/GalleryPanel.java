package Panel;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class GalleryPanel extends JPanel{
	
	private GridLayout gl = new GridLayout(5,3,5,5);
	
	public GalleryPanel(ApplicationsPanel ap)
	{
		setLayout(gl);
		
	}

}
