package filereader;

/**
 * ContactsReader.java
 * @author Hywel Williams
 */

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import login.Account;

public class ContactsReader extends FileReader{
	
	//Constructor
	/**
	 * Creates a ContactsReader
	 */
	public ContactsReader() {
		super(FILEPATH);
		
	}
	
	//Other methods
	/**
	 * Finds and returns the contacts of an input account.
	 * @param searchAccount The account whose contacts are to be searched for
	 * @return A list of accounts the searchAccount is friends with
	 */
	public LinkedList<Account> read(String searchAccount) {
		
		LinkedList<String> contactsList = getContactsOf(searchAccount);
		AccountReader accountReader = new AccountReader();
		
		return accountReader.usernamesToAccounts(contactsList);		
	}
	
	/**
	 * Finds the contacts of a search account.
	 * @return A list of names, the search account has as a contact
	 */
	public LinkedList<String> getContactsOf(String searchAccount){
				
		LinkedList<String> contactsList = new LinkedList<String>();
		
		try {	
			Scanner fileIn = openRead();
			
			while(fileIn.hasNextLine()) {
				contactsList.add(readLine(fileIn, searchAccount));
			}
		} catch (FileNotFoundException e) {
			//throw e;
		} 
		return contactsList;
	}
		

	/**
	 * Reads the current line of the contacts file, searching for an input account.
	 * @param fileIn The current file scanner being read from
	 * @param searchAccount The account to search for
	 * @return If the search account is present, the other account's name. Otherwise an empty string. 
	 */
	private String readLine(Scanner fileIn, String searchAccount) {
		
		try {
			String line = fileIn.nextLine();
			
	        Scanner lineIn = new Scanner(line);
	        lineIn.useDelimiter(DELIMITER);
	        
			String contactA = lineIn.next();
			String contactB = lineIn.next();    
	        
	        lineIn.close();
	        
	        //Relationships are bi-directional
	        if(searchAccount.equalsIgnoreCase(contactA)){
				return contactB;
			}
			if(searchAccount.equalsIgnoreCase(contactB)){
				return contactA;
			}
	        
		} catch (Exception e) {
			System.out.println(e);
		}
		
        return EMPTY;		
	}
	
	/**
	 * Adds a new contact relationship to the contact file.
	 * @param contactA The first account name
	 * @param contactB The second account name
	 */
	public void writeNew(String contactA, String contactB) {
		
		try {	
			BufferedWriter fileOut = openWrite();
			
			fileOut.newLine();
			fileOut.write(contactA + DELIMITER + contactB);
			
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			//throw e;
		} catch (IOException e) {	
			//throw e;
		}
	}
	
	//Constants
	/** The filepath for the accounts file */
	private static final String FILEPATH = "ContactsTest.txt";	
	
}

/*

	public LinkedList<Account> readSQL() {
		
		String query = "select * from account";
		LinkedList<Account> accountList = new LinkedList<Account>();
		
		try{
			Statement statement = m_connection.createStatement();  
			ResultSet result = statement.executeQuery(query);  
			
			while(result.next()){
				
				String username = result.getString(1);
				String password = result.getString(2);
				String firstName = result.getString(3);
				String lastName = result.getString(4);
				int ukPhoneNo = result.getInt(5);
						
				accountList.add(new Account(username, password, firstName, lastName, ukPhoneNo));
				
			}
			
		}catch(Exception e){ 
			System.out.println(e);
		}  		
		
		return accountList;
		
	}








 */

