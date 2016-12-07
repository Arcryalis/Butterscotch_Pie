package filereader;

/**
 * AccountReader.java
 * @author Hywel Williams
 */

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import login.Account;

/**
 * An AccountReader handles interactions with an account file.
 */

public class AccountReader extends FileReader {

	//Constructor
	/**
	 * Creates an account reader.
	 */
	public AccountReader() {
		super(FILEPATH);
	}
	
	//Other methods
	/**
	 * Reads the current line of the account file and converts it to an account.
	 * @param fileIn The current file scanner being read from
	 * @return An account initialised with data from the line read
	 */
	protected Account readLine(Scanner fileIn) {
		String line = fileIn.nextLine();
		
        Scanner lineIn = new Scanner(line);
        lineIn.useDelimiter(DELIMITER);
        
		String username = lineIn.next();
		String password = lineIn.next();
		String firstName = lineIn.next();
		String lastName = lineIn.next();
		String ukPhoneNo = lineIn.next();      

		String dob = lineIn.next();
		String city = lineIn.next();
		String dLastLogin = lineIn.next();  
		
        lineIn.close();
        
        Account returnAccount = new Account(username, password, firstName, lastName, ukPhoneNo);
        
        returnAccount.setDob(dob);
        returnAccount.setCity(city);
        returnAccount.setDtLastLogin(dLastLogin);
       
        return returnAccount;
	}
	
	
	/**
	 * Appends a new account to the end of the account file.
	 * @param account The account to be added
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void write(Account account) throws FileNotFoundException, IOException {
		
		try {	
			BufferedWriter fileOut = openWrite();
			
			fileOut.newLine();
			fileOut.write(convertToFileString (account));
			
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}
	
	/**
	 * Converts an account to string that can be written to the accounts file.
	 * @param account The account to convert
	 * @return The account in the format of the accounts file
	 */
	private String convertToFileString (Account account) {
		
		String line = "";
		line += account.getUsername() + DELIMITER;
		line += account.getPassword() + DELIMITER;
		line += account.getFirstName() + DELIMITER;
		line += account.getLastName() + DELIMITER;
		line += account.getUkPhoneNo() + DELIMITER;
		
		//Optional sections
		if (account.getDob() != null){
			line += account.getDob();
		} 
		line += DELIMITER;
		
		if (account.getCity() != null){
			line += account.getCity();
		} 
		line += DELIMITER;
		
		//account.getM_profilePic();
		//line += DELIMITER;
		
		if (account.getDtLastLogin() != null){
			line += account.getDtLastLogin();
		} 
		line += DELIMITER;
			
		return line;
	}
	
	
	/**
	 * Converts a list of contact names into a list of contact accounts.
	 * @param nameList A list of contact usernames
	 * @return A list of Accounts with usernames matching the input list of names
	 */
	public LinkedList<Account> usernamesToAccounts(LinkedList<String> nameList) {
		
		LinkedList<Account> accountList = new LinkedList<Account>();
		
		try {	
			Scanner fileIn = openRead();
			
			while(fileIn.hasNextLine()) {
				
				Account account = readLine(fileIn);
				
				if (nameList.contains(account.getUsername())){
					accountList.add(account);
				}
			}
			
			fileIn.close();
			
		} catch (FileNotFoundException e) {
			//throw e;
		} 
		
		return accountList;
		
	}
	
	/**
	 * Deletes an account from the account file.
	 * @param removeAccount The account to delete
	 */
	public void delete(Account removeAccount) throws FileNotFoundException {		
		
		try {	
			Scanner fileIn = openRead();
			BufferedWriter fileOut = openWrite();
			
			while(fileIn.hasNextLine()) {
				Account account = readLine(fileIn);
				
				if (removeAccount.toString() != account.toString()) {
					fileOut.write(convertToFileString (account));
					fileOut.newLine();
				}				
			}
			
			fileIn.close();
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e){
			//
		}
		
	}
	
	/**
	 * Updates an existing account with new values. 
	 * The new and existing accounts are assumed to share the same username.
	 * @param updateAccount The updated account details to write to file 
	 */
	public void update(Account updateAccount) throws FileNotFoundException {		
		
		try {	
			Scanner fileIn = openRead();
			BufferedWriter fileOut = openWrite();
			
			while(fileIn.hasNextLine()) {
				Account account = readLine(fileIn);
				
				if (updateAccount.getUsername() != account.getUsername()) {
					fileOut.write(convertToFileString (account));
				} else {
					fileOut.write(convertToFileString (updateAccount));
				}
				fileOut.newLine();
			}
			
			fileIn.close();
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e){
			//
		}
	
	}
	
	//Constants
	/** The filepath for the accounts file */
	private static final String FILEPATH = "AccountsTest.txt";
	
	
}

	/*
	import java.sql.ResultSet;
	import java.sql.Statement;
	
	FILEPATH = "jdbc:mysql://localhost:3306/cstest"
	
	
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