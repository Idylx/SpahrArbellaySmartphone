/*
Author : Olivier Arbellay
Date: 10 mai 2017
*/
package appContact;

public class PersonnalContact extends Contact{
	
	private String dateOfBirth;

	public PersonnalContact(String firstName, String lastName, String address, String email, String phone , String dateOfBirth) {
		super(firstName, lastName, address, email, phone);
		
		this.dateOfBirth=dateOfBirth;
		
	}
	
	

	
	

}
