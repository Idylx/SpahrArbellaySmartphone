package appContact;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
/*
Author : Olivier Arbellay
Date: 31 mai 2017
*/

public class modifyContactFrame extends JFrame {

	private JFrame modifyFrame;
	private JTextField prenom;
	private JTextField nom;
	private JTextField adresse;
	private JTextField email;
	private JTextField phone;

	private JPanel contentPane;
	int pos;
	RepertoireContact rep = new RepertoireContact();

	public modifyContactFrame(int pos) {
		this.pos=pos;
		initialize();
	}

	/**
	 * Create the frame.
	 * 
	 * @return
	 */

	public void initialize() {
		setBounds(100, 100, 450, 576);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		addWindowListener(new Windows_Close());

		rep.deserialize();

		JButton btnModifyContact = new JButton("Modifiy contact");

		btnModifyContact.setBounds(133, 465, 134, 23);
		getContentPane().add(btnModifyContact);

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

		prenom = new JTextField(rep.listeContact.get(pos).getFirstName());
		prenom.setBounds(259, 137, 86, 20);
		getContentPane().add(prenom);
		prenom.setColumns(10);

		nom = new JTextField(rep.listeContact.get(pos).getLastName());
		nom.setBounds(259, 179, 86, 23);
		getContentPane().add(nom);
		nom.setColumns(10);

		adresse = new JTextField(rep.listeContact.get(pos).getAddress());
		adresse.setBounds(259, 230, 86, 20);
		getContentPane().add(adresse);
		adresse.setColumns(10);

		email = new JTextField(rep.listeContact.get(pos).getEmail());
		email.setBounds(259, 284, 86, 20);
		getContentPane().add(email);
		email.setColumns(10);

		phone = new JTextField(rep.listeContact.get(pos).getPhone());
		phone.setBounds(259, 329, 86, 20);
		getContentPane().add(phone);
		phone.setColumns(10);

		btnModifyContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contact c = new Contact(prenom.getText(), nom.getText(), adresse.getText(), email.getText(),
						phone.getText());
				rep.modify(pos, c);
				FrameContact reFrame = new FrameContact();
				reFrame.frame.setVisible(true);
				dispose();
			}
		});

	}

	class Windows_Close extends WindowAdapter {

		public void windowClosing(WindowEvent e) {
			FrameContact reFrame = new FrameContact();
			reFrame.frame.setVisible(true);
			dispose();
		}

	}
}
