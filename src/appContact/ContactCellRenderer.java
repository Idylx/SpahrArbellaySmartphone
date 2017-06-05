/*
Author : Olivier Arbellay
Date: 4 juin 2017
*/
package appContact;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ContactCellRenderer extends DefaultListCellRenderer {
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value instanceof Contact) {
			setText(((Contact) value).getFirstName()+" "+(((Contact) value).getLastName()));
		}
		return this;
	}
}