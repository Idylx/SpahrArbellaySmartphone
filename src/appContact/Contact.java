/*
Author : Olivier Arbellay
Date: 10 mai 2017
*/
package appContact;

import java.io.Serializable;

public class Contact implements Serializable{
	
	private String firstName;
	private String lastName;
    private String address;
    private String email;
    private String phone;
    
    //photo contact ??
    
    
    public Contact(String firstName, String lastName, String address, String email, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
	public String[] getArray(){
		String[] array = {firstName, lastName, address, email, phone};
		return array;
	}
}
