/*
Author : Olivier Arbellay
Date: 10 mai 2017
*/
package appContact;

public class BusinessContact extends Contact{
	
	private String jobTitle;
	private String organization;
	
	public BusinessContact(String firstName, String lastName, String address, String email, String phone, String jobTitle, String organization) {
		super(firstName, lastName, address, email, phone);
		this.jobTitle = jobTitle;
		this.organization = organization;		
		
	}
	
	
	
	
	
	
	

}
