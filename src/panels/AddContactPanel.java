/*
Author : Olivier Arbellay
Date: 10 juin 2017
*/
package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

public class AddContactPanel extends JPanel {

	// d�claration des variable

	AddContactPhotoPanel camera = new AddContactPhotoPanel();
	// set le logo de base par d�fauten cam�ra
	String pathTronche = "./src/Pictures/camera.png";

	// d�clare le layout
	CardLayout c3 = new CardLayout();

	RepertoireContact rep = new RepertoireContact();

	// panel
	private JPanel closePanel = new JPanel();
	private JPanel textPanel = new JPanel();
	private JPanel addPanel = new JPanel();
	private JPanel stuff = new JPanel(new BorderLayout());

	// bouton
	ButtonApplication closeBtn = new ButtonApplication(new Photo("./src/Pictures/close.png"));
	ButtonApplication addBtn = new ButtonApplication(new Photo("./src/Pictures/addUser.png"));
	ButtonApplication tronche;
	// textfield
	JTextField prenom = new JTextField();
	JTextField nom = new JTextField();
	JTextField adresse = new JTextField();
	JTextField email = new JTextField();
	JTextField phone = new JTextField();

	// label
	JLabel prenomLabel = new JLabel("Prénom");
	JLabel nomLabel = new JLabel("Nom");
	JLabel adresseLabel = new JLabel("Adresse");
	JLabel emailLabel = new JLabel("E-mail");
	JLabel phoneLabel = new JLabel("Téléphone");

	// rep
	RepertoirePanel top;

	public AddContactPanel(RepertoirePanel top) {

		rep.deserialize();

		// met le panel du sommet
		this.top = top;

		setLayout(c3);
		setBackground(Color.black);

		addStuff();
		add(stuff);

		addJLabel();
		addTextField();

		c3.show(AddContactPanel.this, "AddContactPanel");
	}

	// rajoute tout le bordel sur 1 panel
	void addStuff() {

		stuff.setBackground(Color.BLACK);

		closeBtn.addActionListener(new Close_Button());
		addBtn.addActionListener(new Add_Button());

		stuff.add(closePanel, BorderLayout.NORTH);

		closePanel.setLayout(new BorderLayout());
		closePanel.setOpaque(false);
		closePanel.add(closeBtn, BorderLayout.EAST);

		stuff.add(addPanel, BorderLayout.SOUTH);
		addPanel.setLayout(new BorderLayout());
		addPanel.setOpaque(false);
		addPanel.add(addBtn, BorderLayout.NORTH);

		stuff.add(textPanel, BorderLayout.CENTER);
		textPanel.setLayout(null);
		textPanel.setBackground(Color.BLACK);

	}

	// rajoute tout les texfield
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

	}

	// rajoute tout les label
	void addJLabel() {

		Color fColor = Color.white;
		tronche = new ButtonApplication(new Photo(pathTronche));

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

	// d�mare la cam�ra pour prendre une photo pour un contact
	class Cam_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			camera.addPropertyChangeListener(new PropertyChangeListener() {

				// en cas de changement de propri�t�
				
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if (evt.getPropertyName().equals("photoPrise")) {
//						System.out.println("on a pris une photo woohoo");
						pathTronche = (String) evt.getNewValue();

						tronche.setIcon(new Photo(pathTronche));

						AddContactPanel.this.remove(camera);
						// top.remove(camera);
						
//						AddContactPanel.this.removeAll();
						AddContactPanel.this.addStuff();
						AddContactPanel.this.add(stuff);
						AddContactPanel.this.addJLabel();
						AddContactPanel.this.addTextField();

						// revalidate();
						// repaint();
						c3.show(AddContactPanel.this, "AddContactPanel");
						// top.removePanel(ModifyContactPanel.this);
						// top.revalidate();
						// top.repaint();
						// top.addStuff();
					}

				}
			});

			camera.start();

			AddContactPanel.this.removeAll();

			AddContactPanel.this.add(camera, "Camera");

			c3.show(AddContactPanel.this, "camera");

			// top.revalidate();
			// top.repaint();

		}
	}

	// rajoute les informations au contact
	class Add_Button implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (prenom.getText().equals("") && nom.getText().equals("") && phone.getText().equals("")) {

				JOptionPane.showMessageDialog(null, "Veuillez remplir les champs prénom, nom, téléphone");

			} else {
				Contact c = new Contact(prenom.getText(), nom.getText(), adresse.getText(), email.getText(),
						phone.getText(), pathTronche);

				rep.add(c);

				top.removePanel(AddContactPanel.this);
				top.reloadAll();
				// // top.removeAll();
				// top.addStuff();
				// top.revalidate();
				// top.repaint();
			}

		}
	}

	// ferme l'ajout
	class Close_Button implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			top.removePanel(AddContactPanel.this);

		}

	}

}
