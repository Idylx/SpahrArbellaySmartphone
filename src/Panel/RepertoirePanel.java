/*
Author : Olivier Arbellay
Date: 9 juin 2017
*/
package Panel;

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
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Buttons.ButtonApp;
import Frame.HomeFrame;
import Photo.Photo;

import appContact.*;

public class RepertoirePanel extends JPanel  {
	
	CardLayout c2 = new CardLayout();

	

	
	JPanel addPanel = new JPanel();
	JPanel listPanel = new JPanel();
	
	RepertoireContact rep = new RepertoireContact();
	
	CMainPanel containerContact = new CMainPanel();

	
	JScrollPane scrollPane = new JScrollPane();
	
	
	Color bColor = Color.black;

	ButtonApp btnAddContact = new ButtonApp(new Photo("./src/Pictures/addUser.png"));

	HomeFrame hf;
	AddContactPanel adp ;
	ContactPanel cp ;
	JPanel stuff = new JPanel();
	
	private Photo photo;

	public RepertoirePanel(HomeFrame hf) {
		
		
		this.hf = hf;
		
		setLayout(c2);
		addStuff();
		add(stuff);
		
		c2.show(RepertoirePanel.this, "RepertoireContact");
		
		setBackground(bColor);
		


	}

	void setPhoto() {
		photo = new Photo("./src/Pictures/wallpaperr.jpg");
	}
	
	void addStuff(){
		rep.deserialize();
		stuff.setLayout(new BorderLayout());
		stuff.add(containerContact, BorderLayout.CENTER);
		stuff.setBackground(bColor);
		containerContact.setLayout(new BorderLayout());
		containerContact.add(addPanel, BorderLayout.NORTH);
		containerContact.setBackground(bColor);
		addPanel.setLayout(new BorderLayout());
		addPanel.setBackground(bColor);
		addPanel.add(btnAddContact, BorderLayout.EAST);
		btnAddContact.addActionListener(new Add_Button());
//		btnAddContact.setBackground(Color.BLACK);
		
		containerContact.add(listPanel, BorderLayout.CENTER);
		listPanel.setLayout(new BorderLayout());
		listPanel.setBackground(bColor);
		listPanel.add(scrollPane);
		
		JList<?> list = new JList<Object>(rep.listeContact.toArray());	
		list.getModel();
		
		list.setCellRenderer(new ContactCellRenderer());
		scrollPane.setViewportView(list);
		scrollPane.setBackground(bColor);
		
		
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JList list = (JList) e.getSource();

				if (e.getClickCount() == 2) {
					// Double-click detected
					int index = list.locationToIndex(e.getPoint());

					ContactPanel cp = new ContactPanel(index, RepertoirePanel.this);
					add(cp, "cp");
					c2.show(RepertoirePanel.this, "cp");

				} else if (e.getClickCount() == 3) {

					// Triple-click detected

				}
			}
		});
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
