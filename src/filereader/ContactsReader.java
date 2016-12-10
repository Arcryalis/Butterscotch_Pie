package filereader;

/**
 * ContactsReader.java
 * @author Hywel Williams
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import login.Account;


/**
 * A ContactsReader handles interactions with the contacts table.
 */

public class ContactsReader extends FileReader {

	//Constructor
	/**
	 * Creates an account reader.
	 */
	public ContactsReader() {
		super(FILEPATH);
	}
	
	//Public methods
	/**
	 * Consults the database for a list of the contacts an account has.
	 * @param account The account being searched for
	 * @return A list of account usernames that are contacts of the search account
	 * @throws Exception
	 */
	public LinkedList<String> getContactsOf(Account account) throws Exception {
				
		String searchName = account.getUsername();
		String query = String.format(SENDER_QUERY, searchName);
		query += UNION + String.format(RECEIVER_QUERY, searchName);
		LinkedList<String> contactList = new LinkedList<String>();
		
		try{
			openSQL();
			
			Statement statement = m_connection.createStatement();  
			ResultSet result = statement.executeQuery(query);  
			
			while(result.next()){
				String[] contact = readLine(result);
				
				if (contact[2].equalsIgnoreCase(CONTACT)){
					//If search name is sender add receiver
					if (contact[0].equalsIgnoreCase(searchName)) {
						contactList.add(contact[1]);
					}
					//If search name is receiver add sender
					if (contact[1].equalsIgnoreCase(searchName)) {
						contactList.add(contact[0]);
					}
				}
			}
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}
		
		return contactList;
	}
	
	/**
	 * Consults the database for a list of contact requests an account has received / sent.
	 * @param account The username of the account being searched for
	 * @param isReceived True if searching for received requests, False for sent requests.
	 * @return A list of account usernames that have made contact requests to the search account
	 * @throws Exception
	 */
	public LinkedList<String> getRequests(String searchName, Boolean isReceived) throws Exception {
				
		String query = EMPTY;
		
		if (isReceived) {
			query = String.format(RECEIVER_QUERY, searchName);
		} else {
			query = String.format(SENDER_QUERY, searchName);
		}
			
		LinkedList<String> contactList = new LinkedList<String>();
		
		try{
			openSQL();
			
			Statement statement = m_connection.createStatement();  
			ResultSet result = statement.executeQuery(query);  
			
			while(result.next()){
				String[] contact = readLine(result);
				
				if (isReceived) {
					//Return senders
					contactList.add(contact[0]);
				} else {
					//Return receivers
					contactList.add(contact[1]);
				}
			}
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}
		
		return contactList;
	}
	
	/**
	 * Adds a new contact request to the database, or updates a request to a contact
	 * @param sender The username of the sender's account
	 * @param reciever The username of the reciever's account
	 * @param isRequest True if new contact being added, False if updating to contact
	 * @throws Exception
	 */
	public void updateContacts(String senderName, String recieverName, Boolean isRequest) throws Exception {
		
		String query = EMPTY;
		
		if (isRequest) {
			query = String.format(WRITE_QUERY, senderName, recieverName, REQUEST);
		} else {
			query = String.format(UPDATE_QUERY, CONTACT, senderName, recieverName);
		}
			
		try{
			openSQL();
			Statement statement = m_connection.createStatement();  
			statement.executeUpdate(query);  
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}
	}
	
	/**
	 * Deletes a contact / contact request in the database
	 * @param usernameA One of the usernames in the contact
	 * @param usernameB The other username in the contact
	 * @throws Exception
	 */
	public void delete(String usernameA, String usernameB) throws Exception {
		
		String query = String.format(DELETE_QUERY, usernameA, usernameB, usernameA, usernameB);
			
		try{
			openSQL();
			Statement statement = m_connection.createStatement();  
			statement.executeUpdate(query);  
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}
	}

	/**
	 * Converts a username into an account with that username.
	 * @param username The username to convert
	 * @return An account from the database sharing the input username
	 */
	public Account getAccount(String username) throws Exception {
		
		Account account;
		
		try{
			AccountReader accountReader = new AccountReader();
			account = accountReader.getAccount(username);
			return account;
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} 
	}
	
	//Private & Protected methods
	/**
	 * Reads the current line of the account file and converts it to an account.
	 * @param fileIn The current file scanner being read from
	 * @return An account initialised with data from the line read
	 * @throws Exception
	 */
	private String[] readLine(ResultSet result) throws Exception {
		
		String[] contact = new String[3];
		
		try {
			contact[0] = result.getString(SENDER);
			contact[1] = result.getString(RECIEVER);
			contact[2] = result.getString(APPROVED);
			return contact;
		} catch (SQLException e) {
			throw new SQLException(SQL_ERROR + e);
		}
	}
	
	
	//Constants
	/** The filepath for the accounts file */
	private static final String FILEPATH = "jdbc:mysql://localhost:3306/cstest";
	
	/** Used to mark a line as a contact */
	private static final String CONTACT = "c";
	/** Used to mark a line as a relation */
	private static final String REQUEST = "r";
	
	/** Name of the table's attribute for username */
	private static final String SENDER = "sender";
	/** Name of the table's attribute for password */
	private static final String RECIEVER = "reciever";	
	/** Name of the table's attribute for  first name */
	private static final String APPROVED = "approved";
	/** The mySQL union operation to combine two sets */
	private static final String UNION = "union ";
	
	/** Query used for the read() operation */
	private static final String SENDER_QUERY = 	"(select * from contacts " +
												"where sender = '%s') ";
	/** Query used for the getRequests() operation */
	private static final String RECEIVER_QUERY ="(select * from contacts " +
												"where reciever = '%s')";
	/** Query used for the getRequests() operation */
	private static final String DELETE_QUERY = 	"delete from contacts " + 
												"where (" + SENDER + " = '%s' AND " 
													+ RECIEVER + " = '%s') OR " +
												"(" + RECIEVER + " = '%s' AND " 
													+ SENDER + " = '%s');";
	/** Query used for the updateContacts() operation */
	private static final String WRITE_QUERY = 	"insert into contacts " +
												"values ('%s','%s','%s');";
	/** Query used for the updateContacts() operation */
	private static final String UPDATE_QUERY =	"update contacts " +
												"set approved = '%s' " +
												"where " + SENDER + " = '%s' AND "
													+ RECIEVER + " = '%s';";

		
}