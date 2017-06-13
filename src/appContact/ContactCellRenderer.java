/*
Author : Olivier Arbellay
Date: 4 juin 2017
*/
package appContact;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.FontUIResource;


//classe de rendu de la liste de contact

public class ContactCellRenderer extends DefaultListCellRenderer {
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value instanceof Contact) {
			
			
			setText("\t "+((Contact) value).getFirstName()+"  \t  "+(((Contact) value).getLastName()));
			setBackground(Color.BLACK);
			setForeground(Color.white);
			setFont(new FontUIResource(new Font("Arial", 0, 20)));
			setSize(115, 30);
		}
		
		
		return this;
	}
}