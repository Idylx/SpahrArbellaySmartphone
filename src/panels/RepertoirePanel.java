/*
Author : Olivier Arbellay
Date: 9 juin 2017
*/
package panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import buttons.ButtonApplication;
import frame.HomeFrame;
import photo.Photo;

import appContact.*;

public class RepertoirePanel extends JPanel  {
	
	
	private CMainPanel containerContact = new CMainPanel();
	
	private CardLayout c2 = new CardLayout();

	JPanel addPanel = new JPanel();
	JPanel stuff = new JPanel();
	
	
	RepertoireContact rep = new RepertoireContact();

	JScrollPane scroll = new JScrollPane(contactListPane());
	
	Color bColor = Color.black;

	ButtonApplication btnAddContact = new ButtonApplication(new Photo("./src/Pictures/addUser.png"));

	
	AddContactPanel adp ;
	ContactPanel cp ;
	
	
	private Photo photo;

	public RepertoirePanel() {
		
		addStuff();
		
		setLayout(c2);
		setBackground(bColor);
		
		
		add(stuff);
		
		c2.show(RepertoirePanel.this, "RepertoireContact");


	}

	void setPhoto() {
		photo = new Photo("./src/Pictures/wallpaperr.jpg");
	}
	
void addStuff(){
	
		stuff.setLayout(new BorderLayout());
		stuff.add(scroll, BorderLayout.CENTER);
		stuff.add(addPanel, BorderLayout.NORTH);
		addPanel.setLayout(new BorderLayout());
		addPanel.setBackground(bColor);
		addPanel.add(btnAddContact, BorderLayout.EAST);
		btnAddContact.addActionListener(new Add_Button());
		
		
	}
	
	private JScrollPane contactListPane(){
		rep.deserialize();
	    String[] columnNames = {"Last Name", "First Name", "Image"} ;
	    DefaultTableModel model = new DefaultTableModel(){
	        @Override
	        public Class<?> getColumnClass(int column) {
	            switch (column) {
	                case 2: return ImageIcon.class;
	                default: return String.class ;
	            }
	        }
	    };

	    model.addColumn("First Name") ;
	    model.addColumn("Last Name") ;
	    model.addColumn("Image") ;
	    for (int i = 0; i< rep.listeContact.size();i++){
	      Object[] currentContactData = {rep.listeContact.get(i).getFirstName(),rep.listeContact.get(i).getLastName(),rep.listeContact.get(i).getPath()} ;
	      model.addRow(currentContactData) ;
	    } 
	    final JTable contactTable = new JTable(model);
	    contactTable.setShowGrid(false);
	    JScrollPane scrollPane = new JScrollPane(contactTable);
	    
	    contactTable.setEnabled(false);

	    contactTable.addMouseListener(new MouseAdapter() {
	      
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int row = contactTable.rowAtPoint(e.getPoint());
	            if (row >= 0) {
	            JPanel individualPanel = new ContactPanel(row, RepertoirePanel.this) ;
	    
	            add(individualPanel, "ip");
				c2.show(RepertoirePanel.this, "ip");
	            
	        }
	    }});
	    
	    scrollPane.setBackground(bColor);
	    return scrollPane ;
	  }
	
	

	public CMainPanel getContainerContact() {
		return containerContact;
	}

	CardLayout getCLayout() {
		return c2;
	}

	class Add_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			adp = new AddContactPanel(RepertoirePanel.this);
			add(adp, "adp");
			c2.show(RepertoirePanel.this, "adp");

		}

	}

	public void removePanel(JPanel panelRemove) {

		remove(panelRemove);
		revalidate();
		repaint();

	}
}
