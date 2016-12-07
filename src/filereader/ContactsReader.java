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

/**
 * A ContactReader handles interactions with a contacts file.
 */

public class ContactsReader extends FileReader{
	
	//Constructor
	/**
	 * Creates a ContactsReader
	 */
	public ContactsReader(String searchAccount) {
		super(FILEPATH);
		this.m_searchAccount = searchAccount;
		
	}
	
	//Get & Set methods
	/**
	 * @return The current account being searched for.
	 */
	public String getSearchAccount() {
		return m_searchAccount;
	}
	
	/**
	 * Set the current account being searched for.
	 * @param searchAccount 
	 */
	public void setSearchAccount(String searchAccount) {
		m_searchAccount = searchAccount;
	}
	
	//Other methods
	/**
	 * Finds and returns the contacts of an input account.
	 * @return A list of accounts the searchAccount is friends with
	 */
	@Override
	public LinkedList<Account> read() throws FileNotFoundException{
		try {
			LinkedList<String> contactsList = getContacts();
			AccountReader accountReader = new AccountReader();
			return accountReader.usernamesToAccounts(contactsList);	
			
		} catch (FileNotFoundException e) {
			throw e;
		} 
	}
	
	/**
	 * Finds the contacts of a search account.
	 * @return A list of names, the search account has as a contact
	 */
	public LinkedList<String> getContacts() throws FileNotFoundException{
				
		LinkedList<String> contactsList = new LinkedList<String>();
		
		try {	
			Scanner fileIn = openRead();
			
			while(fileIn.hasNextLine()) {
				String contact[] = readLine(fileIn);
				
		        //If confirmed contact
				if(contact[2] == String.valueOf(CONFIRMED)){
					
					//If searching for first name, add the second
			        if(m_searchAccount.equalsIgnoreCase(contact[0])){
			        	contactsList.add(contact[1]);
					}
	
					//If searching for second name, add the first
					if(m_searchAccount.equalsIgnoreCase(contact[1])){
						contactsList.add(contact[0]);
					}
				}
			}
			
			fileIn.close();
			
		} catch (FileNotFoundException e) {
			throw e;
		} 
		
		return contactsList;
	}
	
	/**
	 * Finds the contacts of a search account.
	 * @return A list of names, the search account has had a request from
	 */
	public LinkedList<String> getRequests() throws FileNotFoundException{
				
		LinkedList<String> contactsList = new LinkedList<String>();
		
		try {	
			Scanner fileIn = openRead();
			
			while(fileIn.hasNextLine()) {
				String contact[] = readLine(fileIn);
				
		        //If contact request
				if(contact[2] == String.valueOf(REQUEST)){
	
					//If second name (receiver) is search account
					if(m_searchAccount.equalsIgnoreCase(contact[1])){
						contactsList.add(contact[0]);
					}
				}
			}
			
			fileIn.close();
			
		} catch (FileNotFoundException e) {
			throw e;
		} 
		
		return contactsList;
	}
		

	/**
	 * Reads the current line of the contacts file, searching for an input account.
	 * @param fileIn The current file scanner being read from
	 * @param searchAccount The account to search for
	 * @return If the search account is present, the other account's name. Otherwise an empty string. 
	 */
	protected String[] readLine(Scanner fileIn) {

		String line = fileIn.nextLine();
		
        Scanner lineIn = new Scanner(line);
        lineIn.useDelimiter(DELIMITER);
        
        String contacts[] = new String[3];
        contacts[0] = lineIn.next();
        contacts[1] = lineIn.next(); 
        contacts[2] = lineIn.next(); 
        
        lineIn.close();

        return contacts;		
	}
	
	/**
	 * Adds a new contact request to the contact file.
	 * @param contactA The first account name
	 * @param contactB The second account name
	 */
	public void write(String sender, String receiver) throws FileNotFoundException, IOException {
		
		try {	
			BufferedWriter fileOut = openWrite();
			
			fileOut.newLine();
			fileOut.write(sender + DELIMITER + receiver);
			fileOut.write(DELIMITER + REQUEST);
			
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {	
			throw e;
		}
	}
	
	/**
	 * Updates contact request to a confirmed contact within the contact file.
	 * @param contactA The first account name
	 * @param contactB The second account name
	 */
	public void confirmContact(String sender, String receiver) throws FileNotFoundException, IOException {
		
		try {	
			Scanner fileIn = openRead();
			BufferedWriter fileOut = openWrite();
			
			while(fileIn.hasNextLine()) {
				
				String contact[] = readLine(fileIn);
				
				fileOut.newLine();
				fileOut.write(sender + DELIMITER + receiver);
				
		        //If contact request
				if(contact[2] == String.valueOf(REQUEST)){
	
					//If first name is sender and second name receiver
					if(sender.equalsIgnoreCase(contact[0]) && receiver.equalsIgnoreCase(contact[1])){
						fileOut.write(DELIMITER + String.valueOf(CONFIRMED));
						
					} else {
						fileOut.write(DELIMITER + String.valueOf(REQUEST));
					}
					
				} else {
					fileOut.write(DELIMITER + String.valueOf(CONFIRMED));
				}		
			}	
			
			fileIn.close();
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {	
			throw e;
		}
	}
	
	/**
	 * Deletes a contact / contact request.
	 * @param contactA The first account name
	 * @param contactB The second account name
	 */
	public void delete(String contactA, String contactB) throws FileNotFoundException, IOException {
		
		try {	
			Scanner fileIn = openRead();
			BufferedWriter fileOut = openWrite();
			
			while(fileIn.hasNextLine()) {
				
				String contact[] = readLine(fileIn);
	
				if(contactA.equalsIgnoreCase(contact[0]) && contactA.equalsIgnoreCase(contact[1])){
					//Write nothing
					
				} else if (contactA.equalsIgnoreCase(contact[1]) && contactA.equalsIgnoreCase(contact[0])){
					//Write nothing
					
				} else {
					fileOut.newLine();
					fileOut.write(contact[0] + DELIMITER + contact[1]);
					fileOut.write(DELIMITER + contact[2]);
				}
			}	
			
			fileIn.close();
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {	
			throw e;
		}
	}
		
	//Constants
	/** The filepath for the accounts file */
	private static final String FILEPATH = "ContactsTest.txt";
	
	/**	Represents the line as a text message */
	private char REQUEST = 'r';
	/**	Represents the line as a text message */
	private char CONFIRMED = 'c';
	
	/** Account whose contacts are to be searched for */
	private String m_searchAccount;
	
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

