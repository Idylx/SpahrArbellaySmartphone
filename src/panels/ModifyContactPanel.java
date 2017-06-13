/*
Author : Olivier Arbellay
Date: 11 juin 2017
*/
package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import buttons.ButtonApplication;
import photo.Photo;
import appContact.Contact;
import appContact.RepertoireContact;
import java.awt.CardLayout;

public class ModifyContactPanel extends JPanel {

	RepertoireContact rep = new RepertoireContact();

	private JPanel closePanel = new JPanel();
	private JPanel textPanel = new JPanel();
	private JPanel addPanel = new JPanel();

	ButtonApplication closeBtn = new ButtonApplication(new Photo("./src/Pictures/close.png"));
	ButtonApplication modifyBtn = new ButtonApplication(new Photo("./src/Pictures/pen.png"));

	JTextField prenom = new JTextField();
	JTextField nom = new JTextField();
	JTextField adresse = new JTextField();
	JTextField email = new JTextField();
	JTextField phone = new JTextField();

	JLabel prenomLabel = new JLabel("Prénom");
	JLabel nomLabel = new JLabel("Nom");
	JLabel adresseLabel = new JLabel("Adresse");
	JLabel emailLabel = new JLabel("E-mail");
	JLabel phoneLabel = new JLabel("Téléphone");

	ContactPanel top;
	
	int index;

	public ModifyContactPanel(int index, ContactPanel top) {
		rep.deserialize();

		this.top = top;
		this.index = index;

		setLayout(new BorderLayout());
		setBackground(Color.black);

		closeBtn.addActionListener(new Close_Button());
		modifyBtn.addActionListener(new Modify_Button());

		add(closePanel, BorderLayout.NORTH);
		closePanel.setOpaque(false);
		closePanel.setLayout(new BorderLayout());
		closePanel.add(closeBtn, BorderLayout.EAST);

		add(addPanel, BorderLayout.SOUTH);
		addPanel.setOpaque(false);
		addPanel.setLayout(new BorderLayout());
		addPanel.add(modifyBtn, BorderLayout.NORTH);

		add(textPanel, BorderLayout.CENTER);
		textPanel.setLayout(null);
		textPanel.setBackground(Color.BLACK);

		addJLabel();
		addTextField();

	}

	void addTextField() {

		prenom.setBounds(250, 200, 115, 20);
		nom.setBounds(250, 250, 115, 20);
		adresse.setBounds(250, 300, 115, 20);
		email.setBounds(250, 350, 115, 20);
		phone.setBounds(250, 400, 115, 20);
		
		textPanel.add(prenom);
		textPanel.add(nom);
		textPanel.add(adresse);
		textPanel.add(email);
		textPanel.add(phone);

		prenom.setColumns(10);
		nom.setColumns(10);
		adresse.setColumns(10);
		email.setColumns(10);
		phone.setColumns(10);

		 prenom.setText(rep.listeContact.get(index).getFirstName());
		 nom.setText(rep.listeContact.get(index).getLastName());
		 adresse.setText(rep.listeContact.get(index).getAddress());
		 email.setText(rep.listeContact.get(index).getEmail());
		 phone.setText(rep.listeContact.get(index).getPhone());

		// prenom
		// nom
		// adresse
		// email
		// phone
		// prenom
		// nom
		// adresse
		// email
		// phone
	}

	void addJLabel() {

		Color fColor = Color.white;
		
		ButtonApplication tronche = new ButtonApplication(new Photo(rep.listeContact.get(index).getPath()));

		prenomLabel.setBounds(40, 200, 115, 20);
		nomLabel.setBounds(40, 250, 115, 20);
		adresseLabel.setBounds(40, 300, 115, 20);
		emailLabel.setBounds(40, 350, 115, 20);
		phoneLabel.setBounds(40, 400, 115, 20);
		
		tronche.setBounds(150, 10, 125, 125);
		
		textPanel.add(prenomLabel);
		textPanel.add(nomLabel);
		textPanel.add(adresseLabel);
		textPanel.add(emailLabel);
		textPanel.add(phoneLabel);
		textPanel.add(tronche);

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

	class Modify_Button implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (prenom.getText().equals("") && nom.getText().equals("") && phone.getText().equals("")) {

				JOptionPane.showMessageDialog(null, "Veuillez remplir les champs prénom, nom, téléphone");
				
			} else {
				Contact c = new Contact(prenom.getText(), nom.getText(), adresse.getText(), email.getText(),
						phone.getText(),"");
				rep.modify(index, c);
				
				top.setIndex(rep.getLastIndex());
				top.removePanel(ModifyContactPanel.this);
				top.addStuff();
			
				
			}

		}
	}

	class Close_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			top.remove(ModifyContactPanel.this);

		}

	}
}
