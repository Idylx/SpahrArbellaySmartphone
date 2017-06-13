/*
Author : Olivier Arbellay
Date: 12 juin 2017
*/
package contactTest;

import org.junit.Test;

import static org.junit.Assert.*;

import appContact.Contact;
import appContact.RepertoireContact;

public class CreationContactTest {
	
	
	
	@Test 
	public void testContact(){
		
		Contact c = new Contact("Olivier", "Arbellay" , "", "", "741852963", "");
		assertTrue(c.getFirstName().equals("Olivier"));
	}
	
	@Test
	public void deserializalistionTest(){
		
		RepertoireContact rep = new RepertoireContact();
		
		rep.deserialize();
		
		assertTrue(rep.listeContact != null);
	}
}
