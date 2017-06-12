/*
Author : Olivier Arbellay
Date: 24 mai 2017
*/
package appContact;
//TEst

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class addContactFrame extends JFrame{

	private JFrame addframe;
	private JTextField prenom;
	private JTextField nom;
	private JTextField adresse;
	private JTextField email;
	private JTextField phone;
	RepertoireContact rep = new RepertoireContact();
	
	

	public addContactFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 576);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		rep.deserialize();
		
		JButton btnAddContact = new JButton("Add contact");
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contact c = new Contact(prenom.getText(), nom.getText(),adresse.getText(), email.getText(), phone.getText(),"");
				rep.add(c);
				FrameContact reFrame = new FrameContact();
				reFrame.frame.setVisible(true);
				dispose();
				
			
				
			}
		});
		btnAddContact.setBounds(133, 465, 134, 23);
		getContentPane().add(btnAddContact);
		
		
		JLabel prenomLabel = new JLabel("Prénom");
		prenomLabel.setBounds(42, 135, 113, 22);
		getContentPane().add(prenomLabel);

		JLabel nomLabel = new JLabel("Nom");
		nomLabel.setBounds(42, 178, 113, 24);
		getContentPane().add(nomLabel);

		JLabel adresseLabel = new JLabel("Adresse");
		adresseLabel.setBounds(42, 228, 113, 23);
		getContentPane().add(adresseLabel);

		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setBounds(42, 284, 117, 20);
		getContentPane().add(emailLabel);

		JLabel phoneLabel = new JLabel("N° de Téléphone");
		phoneLabel.setBounds(42, 327, 152, 23);
		getContentPane().add(phoneLabel);

		prenom = new JTextField();
		prenom.setBounds(259, 137, 86, 20);
		getContentPane().add(prenom);
		prenom.setColumns(10);
		
		nom = new JTextField();
		nom.setBounds(259, 179, 86, 23);
		getContentPane().add(nom);
		nom.setColumns(10);
		
		adresse = new JTextField();
		adresse.setBounds(259, 230, 86, 20);
		getContentPane().add(adresse);
		adresse.setColumns(10);
		
		email = new JTextField();
		email.setBounds(259, 284, 86, 20);
		getContentPane().add(email);
		email.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(259, 329, 86, 20);
		getContentPane().add(phone);
		phone.setColumns(10);
		
		
	}
}
