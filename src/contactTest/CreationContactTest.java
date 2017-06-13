/*
Author : Olivier Arbellay
Date: 12 juin 2017
*/
package contactTest;

import appContact.Contact;
import appContact.RepertoireContact;

public class CreationContactTest {

	
	public void testContact(){
		
		Contact c = new Contact("Olivier ", "Arbellay" , "", "", "741852963", "");
	}
	
	public void serializalistionTest(){
		RepertoireContact rep = new RepertoireContact();
		
		rep.deserialize();
		
		rep.repContactToString();
	}
}
