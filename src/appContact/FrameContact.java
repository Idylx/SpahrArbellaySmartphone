/*
Author : Olivier Arbellay
Date: 24 mai 2017
*/
package appContact;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class FrameContact extends JFrame {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameContact window = new FrameContact();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public FrameContact() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {

		RepertoireContact rep = new RepertoireContact();
		rep.deserialize();

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 107, 414, 419);
		frame.getContentPane().add(scrollPane);

		JList<?> list = new JList<Object>(rep.listeContact.toArray());
		list.getModel();
		list.setCellRenderer(new ContactCellRenderer());
		scrollPane.setViewportView(list);

		JButton btnDeleteContact = new JButton("Delete ");
		btnDeleteContact.setBounds(202, 57, 67, 39);
		frame.getContentPane().add(btnDeleteContact);

		JButton btnModifyContact = new JButton("Modify ");
		btnModifyContact.setBounds(105, 57, 75, 39);
		frame.getContentPane().add(btnModifyContact);

		JButton btnNewButton = new JButton("Add ");
		btnNewButton.setBounds(10, 57, 85, 39);
		frame.getContentPane().add(btnNewButton);

		JButton btnShow = new JButton("Show");
		btnShow.setBounds(279, 57, 75, 39);
		frame.getContentPane().add(btnShow);
		
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showContactFrame show = new showContactFrame(list.getSelectedIndex());
				show.setVisible(true);
				frame.setVisible(false);
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addContactFrame add = new addContactFrame();
				add.setVisible(true);
				frame.setVisible(false);

			}
		});
		btnModifyContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyContactFrame modify = new modifyContactFrame(list.getSelectedIndex());
				modify.setVisible(true);
				frame.setVisible(false);

			}
		});
		btnDeleteContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ret = JOptionPane.showConfirmDialog(list, "Etes-vous sur de vouloir supprimer ce contact ?");

				if (ret == JOptionPane.YES_OPTION) {
					rep.remove(list.getSelectedIndex());
					scrollPane.revalidate();
					scrollPane.repaint();
					
				}
				
				
				
			}
		}

		);
	}
}
