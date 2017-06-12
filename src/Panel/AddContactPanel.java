/*
Author : Olivier Arbellay
Date: 10 juin 2017
*/
package Panel;

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

import Buttons.ButtonApp;
import Photo.Photo;
import appContact.Contact;
import appContact.RepertoireContact;
import java.awt.CardLayout;

public class AddContactPanel extends JPanel {
	
	 static boolean isAddContact = false;
	
	private CameraPanel camera = new CameraPanel();
	static String pathTronche = "./src/Pictures/camera.png"; 
	
	CardLayout c3 = new CardLayout();

	RepertoireContact rep = new RepertoireContact();

	private JPanel closePanel = new JPanel();
	private JPanel textPanel = new JPanel();
	private JPanel addPanel = new JPanel();
	private JPanel stuff = new JPanel(new BorderLayout());

	ButtonApp closeBtn = new ButtonApp(new Photo("./src/Pictures/close.png"));
	ButtonApp addBtn = new ButtonApp(new Photo("./src/Pictures/addUser.png"));

	JTextField prenom = new JTextField();
	JTextField nom = new JTextField();
	JTextField adresse = new JTextField();
	JTextField email = new JTextField();
	JTextField phone = new JTextField();

	JLabel prenomLabel = new JLabel("Pr�nom");
	JLabel nomLabel = new JLabel("Nom");
	JLabel adresseLabel = new JLabel("Adresse");
	JLabel emailLabel = new JLabel("E-mail");
	JLabel phoneLabel = new JLabel("T�l�phone");

	RepertoirePanel top;

	public AddContactPanel(RepertoirePanel top) {
		rep.deserialize();

		this.top = top;

		setLayout(c3);
		setBackground(Color.black);

	
		addStuff();
		add(stuff);

		

		addJLabel();
		addTextField();
		
		c3.show(AddContactPanel.this, "AddContactPanel");

	}
	
	
	void addStuff(){
		stuff.setBackground(Color.BLACK);
		
		closeBtn.addActionListener(new Close_Button());
		addBtn.addActionListener(new Add_Button());
		
		stuff.add(closePanel, BorderLayout.NORTH);
		closePanel.setOpaque(false);
		closePanel.setLayout(new BorderLayout());
		closePanel.add(closeBtn, BorderLayout.EAST);
		
		stuff.add(addPanel, BorderLayout.SOUTH);
		addPanel.setOpaque(false);
		addPanel.setLayout(new BorderLayout());
		addPanel.add(addBtn, BorderLayout.NORTH);

		stuff.add(textPanel, BorderLayout.CENTER);
		textPanel.setLayout(null);
		textPanel.setBackground(Color.BLACK);
		
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

		// prenom
		// nom
		// adresse
		// email
		// phone

	}

	void addJLabel() {

		Color fColor = Color.white;
		ButtonApp tronche = new ButtonApp(new Photo(pathTronche));

		prenomLabel.setBounds(40, 200, 115, 20);
		nomLabel.setBounds(40, 250, 115, 20);
		adresseLabel.setBounds(40, 300, 115, 20);
		emailLabel.setBounds(40, 350, 115, 20);
		phoneLabel.setBounds(40, 400, 115, 20);
		tronche.setBounds(150, 10, 125, 125);
		
		tronche.addActionListener(new Cam_Button());

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
	
	class Cam_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			top.remove(AddContactPanel.this);
			
			camera.start();
			top.add(camera, "hello");
			c3.show(top, "hello");
			isAddContact= true;

			
		}

		}
	
	

	class Add_Button implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (prenom.getText() == "" && nom.getText() == "" && phone.getText()== "") {

				JOptionPane.showMessageDialog(null, "Veuillez remplir les champs pr�nom, nom, t�l�phone");
				
			} else {
				Contact c = new Contact(prenom.getText(), nom.getText(), adresse.getText(), email.getText(),
						phone.getText(), pathTronche);
				rep.add(c);
				
				top.getContainerContact().removeAll();
				top.remove(AddContactPanel.this);
				top.addStuff();
			}

		}
	}

	class Close_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			top.removePanel(AddContactPanel.this);

		}

	}
	
	
}
