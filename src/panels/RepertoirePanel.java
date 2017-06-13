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

public class RepertoirePanel extends JPanel {
	
//créer un panel de base avesc un fond d'écran 
	private CMainPanel containerContact = new CMainPanel();

	private CardLayout c2 = new CardLayout();

	// panell du stuff et bouton
	JPanel addPanel;
	JPanel stuff;
	// déclare le repertoire

	RepertoireContact rep ;

	Color bColor = Color.black;
// bouton 
	ButtonApplication btnAddContact;
// créer les panel suivant 
	AddContactPanel adp;
	ContactPanel cp;

	public RepertoirePanel() {
		// charge tout le stuff
		reloadAll();
		
	}
	// charge le stuff
	void addStuff() {
		removeAll();
		rep = new RepertoireContact();
		rep.deserialize();
		stuff = new JPanel();
		addPanel= new JPanel();
		btnAddContact  = new ButtonApplication(new Photo("./src/Pictures/addUser.png"));
//		System.out.println("jai chargé du stuff");
		
		
		// créer une jlist et un scrollpane lié 
		JList<?> list = new JList<Object>(rep.listeContact.toArray());
		JScrollPane scroll = new JScrollPane(list);

		stuff.setLayout(new BorderLayout());
		// ajoute le reste 
		stuff.add(addPanel, BorderLayout.NORTH);
		stuff.add(scroll, BorderLayout.CENTER);
		
		
		addPanel.setLayout(new BorderLayout());
		addPanel.setBackground(bColor);
		addPanel.add(btnAddContact, BorderLayout.EAST);
		btnAddContact.addActionListener(new Add_Button());

		// créer un listener de double click sur la liste 
		list.setCellRenderer(new ContactCellRenderer());
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detecté
					int index = list.locationToIndex(evt.getPoint());

					JPanel individualPanel = new ContactPanel(index, RepertoirePanel.this);

					add(individualPanel, "ip");
					c2.show(RepertoirePanel.this, "ip");
				} else if (evt.getClickCount() == 3) {

					// Triple-click detecté
					int index = list.locationToIndex(evt.getPoint());
				}
			}
		});

	}

//	private JScrollPane contactListPane() {
//		rep.deserialize();
//		// String[] columnNames = {"Last Name", "First Name", "Image"} ;
//		// DefaultTableModel model = new DefaultTableModel(){
//		// @Override
//		// public Class<?> getColumnClass(int column) {
//		// switch (column) {
//		// case 2: return ImageIcon.class;
//		// default: return String.class ;
//		// }
//		// }
//		// };
//		//
//		// model.addColumn("First Name") ;
//		// model.addColumn("Last Name") ;
//		// model.addColumn("Image") ;
//		// for (int i = 0; i< rep.listeContact.size();i++){
//		// Object[] currentContactData =
//		// {rep.listeContact.get(i).getFirstName(),rep.listeContact.get(i).getLastName(),rep.listeContact.get(i).getPath()}
//		// ;
//		// model.addRow(currentContactData) ;
//		// }
//		// final JTable contactTable = new JTable(model);
//
////		JScrollPane scrollPane = new JScrollPane(list);
//		//
//		// contactTable.setEnabled(false);
//
//		// contactTable.addMouseListener(new MouseAdapter() {
//		//
//		//
//		// @Override
//		// public void mouseClicked(MouseEvent e) {
//		// int row = contactTable.rowAtPoint(e.getPoint());
//		// if (row >= 0) {
//		// JPanel individualPanel = new ContactPanel(row, RepertoirePanel.this)
//		// ;
//		//
//		// add(individualPanel, "ip");
//		// c2.show(RepertoirePanel.this, "ip");
//		//
//		// }
//		// }});
//
//		scrollPane.setBackground(bColor);
//		return scrollPane;
//	}

	// methode permettant de raffraichir le panel
	public void reloadAll(){
		
		addStuff();

		setLayout(c2);
		setBackground(bColor);

		add(stuff);

		c2.show(RepertoirePanel.this, "RepertoireContact");
		revalidate();
		repaint();
	}
	
	// retourne le panel
	public CMainPanel getContainerContact() {
		return containerContact;
	}

	CardLayout getCLayout() {
		return c2;
	}
// rajoute
	
	// bouton ouvrant la création de contact
	class Add_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			adp = new AddContactPanel(RepertoirePanel.this);
			add(adp, "adp");
			c2.show(RepertoirePanel.this, "adp");
			adp.pathTronche = "./src/Pictures/camera.png";

		}

	}

	public void removePanel(JPanel panelRemove) {

		remove(panelRemove);
		revalidate();
		repaint();

	}
}
