/*
Author : Olivier Arbellay
Date: 10 mai 2017
*/
package appContact;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class RepertoireContact {

	ArrayList<Contact> listeContact = new ArrayList<Contact>();

	public String[][] toArray() {
		String[][] array = new String[listeContact.size()][5];
		for (int i = 0; i < array.length; i++) {

			array[i] = listeContact.get(i).getArray();

		}
		return array;
	}

	public void add(Contact c1) {
		this.listeContact.add(c1);
		sortByFirstName();
		sortByLastName();
		serialize();

	}

	public void remove(int pos) {
		this.listeContact.remove(pos);
		sortByFirstName();
		sortByLastName();
		serialize();
	}

	public void modify(int pos, Contact setContact) {
		this.listeContact.remove(pos);
		this.listeContact.add(setContact);
		sortByFirstName();
		sortByLastName();
		serialize();

	}
	

	public void serialize() {// serialize le repertoire
		ObjectOutputStream oos = null;

		try {
			FileOutputStream repContact = new FileOutputStream("listeContact.ser");
			oos = new ObjectOutputStream(repContact);
			oos.writeObject(listeContact);
			oos.flush();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void deserialize() { // deserialize le repertoire
		ObjectInputStream ois = null;

		try {
			FileInputStream repContact = new FileInputStream("listeContact.ser");
			ois = new ObjectInputStream(repContact);
			listeContact = (ArrayList<Contact>) ois.readObject();

		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public void repContactToString() {
		for (int i = 0; i < listeContact.size(); i++)
			System.out.println(this.listeContact.get(i).getFirstName() + " " + this.listeContact.get(i).getLastName());
	}

	public void sortByLastName() {
		Collections.sort(listeContact, compareLastName());
	}

	public void sortByFirstName() {
		Collections.sort(listeContact, compareFirstName());
	}

	public static Comparator<Contact> compareLastName() {
		Comparator comp = new Comparator<Contact>() {
			@Override
			public int compare(Contact c1, Contact c2) {
				return c1.getLastName().toUpperCase().compareTo(c2.getLastName().toUpperCase());
			}
		};
		return comp;
	}

	public static Comparator<Contact> compareFirstName() {
		Comparator comp = new Comparator<Contact>() {
			@Override
			public int compare(Contact c1, Contact c2) {
				return c1.getFirstName().toUpperCase().compareTo(c2.getFirstName().toUpperCase());
			}
		};
		return comp;
	}

}


