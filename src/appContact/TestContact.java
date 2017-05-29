/*
Author : Olivier Arbellay
Date: 15 mai 2017
*/
package appContact;

public class TestContact {
	public static void main(String[] args ){
		
		RepertoireContact rep = new RepertoireContact();
		
		
		rep.deserialize();
		
		rep.repContactToString();
		
	}

}
