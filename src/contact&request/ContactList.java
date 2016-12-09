package login;

import java.util.LinkedList;

public class ContactList extends LinkedList {
	
	public ContactList() {
		String username;	// Call to Account.getUserName()
		// Call to contact reader for initializing
	}
	
	public boolean add(Contact c) {
		return super.add(c);
	}

	public void add(int index, Contact c) {
		super.add(index, c);
	}
	
	
	
	public static void main(String[] args) {
		//Testing strip
		ContactList test = new ContactList();
		
		System.out.println("Before adding testing strips: " + test.size());
		
		test.add(new Contact("sam1234"));
		test.add(new Contact("seven5678"));
		test.add(new Contact("med9012"));
		test.add(new Contact("tim3456"));
		
		System.out.println("After: " + test.size());
		
		for(int i = 0; i < test.size(); i++) {
			Contact temp = (Contact)test.get(i);
			System.out.println(temp.getM_username());
		}
	}
	
}