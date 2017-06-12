/*
Author : Olivier Arbellay
Date: 9 juin 2017
*/
package Panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import Buttons.ButtonApp;
import Photo.Photo;
import appContact.RepertoireContact;

public class ContactPanel extends JPanel {
	
	CardLayout c3 = new CardLayout();

	RepertoireContact rep = new RepertoireContact();

	private JPanel closePanel = new JPanel(new BorderLayout());
	private JPanel textPanel = new JPanel(new BorderLayout());
	private JPanel stuff = new JPanel(new BorderLayout());

	ButtonApp close = new ButtonApp(new Photo("./src/Pictures/close.png"));
	ButtonApp delete = new ButtonApp(new Photo("./src/Pictures/delete.png"));
	ButtonApp modify = new ButtonApp(new Photo("./src/Pictures/pen.png"));

	JLabel prenom = new JLabel();
	JLabel nom= new JLabel();
	JLabel adresse= new JLabel();
	JLabel email= new JLabel();
	JLabel phone= new JLabel();
	

	JLabel prenomLabel = new JLabel("Prénom");
	JLabel nomLabel = new JLabel("Nom");
	JLabel adresseLabel = new JLabel("Adresse");
	JLabel emailLabel = new JLabel("E-mail");
	JLabel phoneLabel = new JLabel("Téléphone");

	RepertoirePanel top;
	int index;

	public ContactPanel(int index, RepertoirePanel top) {
		rep.deserialize();
		this.top = top;
		this.index = index;
		
		setLayout(c3);
		setBackground(Color.black);
		
		
		addStuff();
		add(stuff);
		
		c3.show(ContactPanel.this, "ContactPanel");

		
		
		
	}
	void addStuff(){
		rep.deserialize();
		
		
		stuff.setBackground(Color.BLACK);
		
		stuff.add(closePanel, BorderLayout.NORTH);
		closePanel.setOpaque(false);
		closePanel.add(close, BorderLayout.EAST);
		closePanel.add(delete, BorderLayout.WEST);
		closePanel.add(modify, BorderLayout.CENTER);
		closePanel.setBackground(Color.BLACK);
		
	
		close.addActionListener(new Close_Button());
		delete.addActionListener(new Delete_Button());
		modify.addActionListener(new Modify_Button());

		stuff.add(textPanel, BorderLayout.CENTER);
		textPanel.setLayout(null);
		textPanel.setBackground(Color.BLACK);
		
		addInfoLabel();
		addJLabel();
	}

	void addInfoLabel() {

		Color fColor = Color.white;
		Color bColor = Color.black;
		
		JLabel tronche = new JLabel(new Photo(rep.listeContact.get(index).getPath()));

		prenom.setBounds(250, 200, 115, 20);
		nom.setBounds(250, 250, 115, 20);
		adresse.setBounds(250, 300, 115, 20);
		email.setBounds(250, 350, 115, 20);
		phone.setBounds(250, 400, 115, 20);
		
		tronche.setBounds(150, 10, 125, 125);

		textPanel.add(prenom);
		textPanel.add(nom);
		textPanel.add(adresse);
		textPanel.add(email);
		textPanel.add(phone);
		textPanel.add(tronche);
		
		

		prenom.setText(rep.listeContact.get(index).getFirstName());
		nom.setText(rep.listeContact.get(index).getLastName());
		adresse.setText(rep.listeContact.get(index).getAddress());
		email.setText(rep.listeContact.get(index).getEmail());
		phone.setText(rep.listeContact.get(index).getPhone());
		

		prenom.setForeground(fColor);
		nom.setForeground(fColor);
		adresse.setForeground(fColor);
		email.setForeground(fColor);
		phone.setForeground(fColor);

		prenom.setFont(new FontUIResource(new Font("Arial", 0, 20)));
		nom.setFont(new FontUIResource(new Font("Arial", 0, 20)));
		adresse.setFont(new FontUIResource(new Font("Arial", 0, 20)));
		email.setFont(new FontUIResource(new Font("Arial", 0, 20)));
		phone.setFont(new FontUIResource(new Font("Arial", 0, 20)));

		prenom.setBackground(bColor);
		nom.setBackground(bColor);
		adresse.setBackground(bColor);
		email.setBackground(bColor);
		phone.setBackground(bColor);

	}

	void addJLabel() {

		Color fColor = Color.white;

		prenomLabel.setBounds(40, 200, 115, 20);
		nomLabel.setBounds(40, 250, 115, 20);
		adresseLabel.setBounds(40, 300, 115, 20);
		emailLabel.setBounds(40, 350, 115, 20);
		phoneLabel.setBounds(40, 400, 115, 20);

		textPanel.add(prenomLabel);
		textPanel.add(nomLabel);
		textPanel.add(adresseLabel);
		textPanel.add(emailLabel);
		textPanel.add(phoneLabel);

		prenomLabel.setForeground(fColor);
		nomLabel.setForeground(fColor);
		adresseLabel.setForeground(fColor);
		emailLabel.setForeground(fColor);
		phoneLabel.setForeground(fColor);

		prenomLabel.setFont(new FontUIResource(new Font("Arial", 0, 20)));
		nomLabel.setFont(new FontUIResource(new Font("Arial", 0, 20)));
		adresseLabel.setFont(new FontUIResource(new Font("Arial", 0, 20)));
		emailLabel.setFont(new FontUIResource(new Font("Arial", 0, 20)));
		phoneLabel.setFont(new FontUIResource(new Font("Arial", 0, 20)));

	}
	
	public void removePanel(JPanel panelRemove) {

		remove(panelRemove);
		revalidate();
		repaint();

	}
	
	public void setIndex (int index){
		this.index= index;
	}

	class Delete_Button implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int options = JOptionPane.YES_NO_CANCEL_OPTION;

			options = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce contact ?", null, options);

			if (options == JOptionPane.YES_OPTION) {

				rep.remove(index);
				
				top.getContainerContact().removeAll();
				top.remove(ContactPanel.this);
				top.addStuff();
			}

			else {
				return;
			}
		}

	}

	class Close_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			top.removePanel(ContactPanel.this);
			top.revalidate();
			top.repaint();
			top.addStuff();
		
		}

	}

	class Modify_Button implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			ModifyContactPanel mdp = new ModifyContactPanel(index, ContactPanel.this);
			add(mdp, "adp");
			c3.show(ContactPanel.this, "adp");
		}
	}

}
