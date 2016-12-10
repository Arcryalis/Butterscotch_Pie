package filereader;


/**
 * AccountReader.java
 * @author Hywel Williams
 */

import java.util.LinkedList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;

import login.Account;

/**
 * An AccountReader handles interactions with an account table.
 */

public class AccountReader extends FileReader {

	//Constructor
	/**
	 * Creates an account reader.
	 */
	public AccountReader() {
		super(FILEPATH);
	}
	
	//Public methods
	/**
	 * Reads the account table and converts it to a list of accounts.
	 * @return A list of accounts initialised with data from the line read
	 * @throws Exception
	 */
	public LinkedList<Account> read() throws Exception {
				
		String query = READ_QUERY;
		LinkedList<Account> accountList = new LinkedList<Account>();
		
		try{
			openSQL();
			
			Statement statement = m_connection.createStatement();  
			ResultSet result = statement.executeQuery(query);  
			
			while(result.next()){
				accountList.add(readLine(result));
			}
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}
		
		return accountList;
	}	
	
	/**
	 * Appends a new account to the account table.
	 * @param account The account to be added
	 * @throws Exception
	 */
	public void write(Account account) throws Exception {
		
		String query = WRITE_QUERY;
		
		try{
			openSQL();
			
			query += convertToFileString (account);
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
	 * Deletes an account from the account table.
	 * @param accountUsername The account to delete
	 * @throws Exception
	 */
	public void delete(String accountUsername) throws Exception {		
		try{
			openSQL();
			
			String query = String.format(DELETE_QUERY, accountUsername);
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
	 * Updates an existing account with new values. 
	 * The new and existing accounts are assumed to share the same username
	 * @param updateAccount The updated account details to write to the table
	 * @throws Exception 
	 */
	public void update(Account account) throws Exception {		

		String query = EMPTY;
		
		try{
			openSQL();
			
			query += String.format(UPDATE_QUERY, convertToUpdateString (account));
			query += String.format(CONVERT_FORMAT, account.getUsername()) + END_LINE;
			
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
	public Account getAccount(String convertName) throws Exception {
		
		String query = String.format(TO_ACCOUNT_QUERY, convertName);
		Account account;
		
		try{
			openSQL();
			
			Statement statement = m_connection.createStatement();  
			ResultSet result = statement.executeQuery(query);  
			
			result.next();
			account = readLine(result);
			return account;

		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}
	}
	
	/**
	 * Checks if a username and password are present together in the table
	 * @param username The username to check
	 * @param password The password to check
	 * @return True if pair are present, False otherwise.
	 */
	public Boolean checkUserPass(String username, String password) throws Exception {
		
		String query = String.format(CHECK_USER_PASS_QUERY, username, password);
		
		try{
			openSQL();
			
			Statement statement = m_connection.createStatement();  
			ResultSet result = statement.executeQuery(query);  
			
			//if line present
			if (result.next() == true){
				return true;
			//line not present, login does not exit
			} else {
				return false;
			}

		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}
	}
	
	//Private & Protected methods
	/**
	 * Reads the current line of the account table and converts it to an account.
	 * @param fileIn The current table being read from
	 * @return An account initialised with data from the line read
	 * @throws Exception
	 */
	private Account readLine(ResultSet result) throws Exception {
		
		try {
			String username = result.getString(USERNAME);
			String password = result.getString(PASSWORD);
			String firstName = result.getString(FIRST_NAME);
			String lastName = result.getString(LAST_NAME);
			String ukPhoneNo = result.getString(UK_PHONE_NUM);
					
			Account account = new Account(username, password, firstName, lastName, ukPhoneNo);

			if (result.getString(DOB) != null){
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				Date dob = sdf.parse(result.getString(DOB));
				account.setDob(dob);
			}
			
			if (result.getString(CITY) != null){
				account.setCity(result.getString(CITY));
			}
			
			if (result.getString(PROFILE_PIC) != null){
				account.setCity(result.getString(PROFILE_PIC));
			}
			
			if (result.getString(LAST_LOGIN) != null){
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				Date lastLogin = sdf.parse(result.getString(LAST_LOGIN));
				account.setDtLastLogin(lastLogin);
			}
			
			return account;
			
		} catch (SQLException e) {
			throw new SQLException(SQL_ERROR + e);
		}
	}
	
	/**
	 * Converts an account to string that can be written to the accounts file.
	 * @param account The account to convert
	 * @return The account in the format of the accounts file
	 */
	private String convertToFileString (Account account) {
		
		String line = OPEN_VALUES;
		
		line += String.format(CONVERT_FORMAT, account.getUsername()) + DELIMITER;
		line += String.format(CONVERT_FORMAT, account.getPassword()) + DELIMITER;
		line += String.format(CONVERT_FORMAT, account.getFirstName()) + DELIMITER;
		line += String.format(CONVERT_FORMAT, account.getLastName()) + DELIMITER;
		line += String.format(CONVERT_FORMAT, account.getUkPhoneNo()) + DELIMITER;
		
		//Optional values

		
		if (account.getDob() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			line += String.format(CONVERT_FORMAT, sdf.format(account.getDob())) + DELIMITER;
		} else {
			line += String.format(CONVERT_FORMAT, EMPTY) + DELIMITER;
		}
		
		if (account.getCity() != null) {
			line += String.format(CONVERT_FORMAT, account.getCity()) + DELIMITER;
		} else {
			line += String.format(CONVERT_FORMAT, EMPTY) + DELIMITER;
		}
		
		if (account.getProfilePic() != null) {
			line += String.format(CONVERT_FORMAT, account.getProfilePic()) + DELIMITER;
		} else {
			line += String.format(CONVERT_FORMAT, EMPTY) + DELIMITER;
		}
		
		if (account.getDtLastLogin() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			line += String.format(CONVERT_FORMAT, sdf.format(account.getDtLastLogin()));
		} else {
			line += String.format(CONVERT_FORMAT, EMPTY);
		}
			
		line += CLOSE_VALUES + END_LINE;
			
		return line;
	}
	
	/**
	 * Converts an account to string that can be used in an update query.
	 * @param account The account to convert
	 * @return The account formated for the query
	 */
	private String convertToUpdateString (Account account) {
		
		String line = EMPTY;
		
		line += PASSWORD + EQUALS;
		line += String.format(CONVERT_FORMAT, account.getPassword()) + DELIMITER;
		line += FIRST_NAME + EQUALS;
		line += String.format(CONVERT_FORMAT, account.getFirstName()) + DELIMITER;
		line += LAST_NAME + EQUALS;
		line += String.format(CONVERT_FORMAT, account.getLastName()) + DELIMITER;
		line += UK_PHONE_NUM + EQUALS;
		line += String.format(CONVERT_FORMAT, account.getUkPhoneNo()) + DELIMITER;
		
		line += DOB + EQUALS;
		if (account.getDob() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			line += String.format(CONVERT_FORMAT, sdf.format(account.getDob())) + DELIMITER;
		} else {
			line += String.format(CONVERT_FORMAT, EMPTY) + DELIMITER;
		}
		
		line += CITY + EQUALS;
		if (account.getCity() != null) {
			line += String.format(CONVERT_FORMAT, account.getCity()) + DELIMITER;
		} else {
			line += String.format(CONVERT_FORMAT, EMPTY) + DELIMITER;
		}
		
		line += PROFILE_PIC + EQUALS;
		if (account.getProfilePic() != null) {
			line += String.format(CONVERT_FORMAT, /*account.getProfilePic()*/ "FIX ME") + DELIMITER;
		} else {
			line += String.format(CONVERT_FORMAT, EMPTY) + DELIMITER;
		}
		
		line += LAST_LOGIN + EQUALS;
		if (account.getDtLastLogin() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			line += String.format(CONVERT_FORMAT, sdf.format(account.getDtLastLogin()));
		} else {
			line += String.format(CONVERT_FORMAT, EMPTY);
		}
			
		return line;
	}
	
	//Constants
	/** The filepath for the accounts file */
	private static final String FILEPATH = "jdbc:mysql://localhost:3306/cstest";
	/** The format for converting java date to sql datetime */
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	

	/** Name of the table's attribute for username */
	private static final String USERNAME = "username";
	/** Name of the table's attribute for password */
	private static final String PASSWORD = "password";	
	/** Name of the table's attribute for  first name */
	private static final String FIRST_NAME = "firstname";
	/** Name of the table's attribute for last name */
	private static final String LAST_NAME = "lastname";	
	/** Name of the table's attribute for U.K. phone number */
	private static final String UK_PHONE_NUM = "ukphonenum";
	/** Name of the table's attribute for date of birth */
	private static final String DOB = "dob";
	/** Name of the table's attribute for city */
	private static final String CITY = "city";	
	/** Name of the table's attribute for profile picture filepath */
	private static final String PROFILE_PIC = "profilefilepath";		
	/** Name of the table's attribute for the time of last login */
	private static final String LAST_LOGIN = "lastlogin";

	/** Query used for the read() operation */
	private static final String READ_QUERY = 	"select * from account;";
	/** Query used for the write() operation */
	private static final String WRITE_QUERY =	"insert into account values ";
	/** Query used for the delete() operation */
	private static final String DELETE_QUERY = 	"delete from account " + 
												"where username = \'%s\';";
	/** Query used for the update() operation */
	private static final String UPDATE_QUERY = 	"update account " +
												"set %s " +
												"where username = "	;	
	/** Query used for the read() operation */
	private static final String TO_ACCOUNT_QUERY = 	"select * from account " + 
													"where " + USERNAME + " = '%s';";
	/** Query used for the checkUserPass() operation */
	private static final String CHECK_USER_PASS_QUERY = "select username, password " +
														"from account " +
														"where username = '%s' AND password = '%s';";
}