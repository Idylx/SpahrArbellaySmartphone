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

public class RepertoireContact {

	ArrayList<Contact> listeContact = new ArrayList<Contact>();


	public void add(Contact c1){
		this.listeContact.add(c1);
		
	}
	
	
	public void serialize() {
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

	public void deserialize() {
		ObjectInputStream ois = null;

		try {
			FileInputStream repContact = new FileInputStream("listeContact.ser");
			ois = new ObjectInputStream(repContact);
			listeContact = (ArrayList) ois.readObject();

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

	public String contactToString() {

		System.out.println(this.listeContact.get(0).getFirstName() + " " + this.listeContact.get(0).getLastName());
		return null;

	}
}

// public void listContact(){
// for (int i =0;i>listeContact.size(); i++){
// return listeContact.getClass().get;}
// }
//
//
// }
