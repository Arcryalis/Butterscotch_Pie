/**
 * Account.java
 * @author Sevan Boyrazian
 */

import java.util.Date;
import java.awt.Image;

/**
 * Account class creates new accounts to be used in the program.
 */

public class Account {

	/**
	 * Creates an account
	 * 
	 * @param username The username of an account.
	 * @param password The password of an account.
	 * @param firstName The first name of an account.
	 * @param lastName The last name of an account.
	 * @param ukPhoneNo The UK phone number of an account.
	 */
	public Account(String username, String password, String firstName, String lastName, String ukPhoneNo) {
		this.m_username = username;
		this.m_password = password;
		this.m_firstName = firstName;
		this.m_lastName = lastName;
		this.m_ukPhoneNo = ukPhoneNo;
	}

	/**
	 * 
	 * @return Returns Account with all the included details.
	 */
	@Override
	public String toString() {
		String TOSTRING_FORMAT = "Username: %s, Password: %s, First Name: %s, Last Name: %s, UK Phone Number: %s,"
				+ "Date of Birth: %s, City: %s, Date Last Logged in: %s, Profile Picture: %s";
		return String.format(TOSTRING_FORMAT, m_username, m_password, m_firstName, m_lastName, m_ukPhoneNo, m_dob,
				m_city, m_dTLastLogin, m_profilePic);
	}

	/**
	 * Get the username of the account.
	 * 
	 * @return The username of the account.
	 */
	public String getUsername() {
		return m_username;
	}

	/**
	 * Get the password of the account.
	 * 
	 * @return The password of the account.
	 */
	public String getPassword() {
		return m_password;
	}

	/**
	 * Get the first name of the account.
	 * 
	 * @return The first name of the account.
	 */
	public String getFirstName() {
		return m_firstName;
	}

	/**
	 * Get the last name of the account.
	 * 
	 * @return The last name of the account.
	 */
	public String getLastName() {
		return m_lastName;
	}

	/**
	 * Get the UK phone number of the account.
	 * 
	 * @return The UK phone number of the account.
	 */
	public String getUkPhoneNo() {
		return m_ukPhoneNo;
	}

	/**
	 * Get the date of birth of the account.
	 * 
	 * @return The date of birth of the account.
	 */
	public Date getDob() {
		return m_dob;
	}

	/**
	 * Get the city of the account.
	 * 
	 * @return The city of the account.
	 */
	public String getCity() {
		return m_city;
	}

	/**
	 * Get the profile image of the account.
	 * 
	 * @return The profile image of the account.
	 */
	public String getProfilePic() {
		return m_profilePic;
	}

	/**
	 * Get the date of last login.
	 * 
	 * @return The date of the last login.
	 */
	public Date getDtLastLogin() {
		return m_dTLastLogin;
	}

	/**
	 * Set the username of the account.
	 * 
	 * @param m_username The username of the account.
	 */
	public void setUsername(String m_username) {
		this.m_username = m_username;
	}

	/**
	 * Set the password of the account.
	 * 
	 * @param m_password The password of the account.
	 */
	public void setPassword(String m_password) {
		this.m_password = m_password;
	}

	/**
	 * Set the first name of the account.
	 * 
	 * @param m_firstName The first name of the account.
	 */
	public void setFirstName(String m_firstName) {
		this.m_firstName = m_firstName;
	}

	/**
	 * Set the last name of the account.
	 * 
	 * @param m_lastName The last name of the account.
	 */
	public void setLastName(String m_lastName) {
		this.m_lastName = m_lastName;
	}

	/**
	 * Set the UK phone number of the account.
	 * 
	 * @param m_ukPhoneNo The UK phone number of the account.
	 */
	public void setUkPhoneNo(String m_ukPhoneNo) {
		this.m_ukPhoneNo = m_ukPhoneNo;
	}

	/**
	 * Set the date of birth of the account.
	 * 
	 * @param m_dob The date of birth of the account.
	 */
	public void setDob(Date m_dob) {
		this.m_dob = m_dob;
	}

	/**
	 * Set the city of the account.
	 * 
	 * @param m_city The city of the account.
	 */
	public void setCity(String m_city) {
		this.m_city = m_city;
	}

	/**
	 * Set the date of last login of the account.
	 * 
	 * @param m_dTLastLogin The date last logged in of the account.
	 */
	public void setDtLastLogin(Date m_dTLastLogin) {
		this.m_dTLastLogin = m_dTLastLogin;
	}

	/**
	 * Set the profile picture of the account.
	 * 
	 * @param m_profilePic The profile picture of the account.
	 */
	public void setProfilePic(String m_profilePic) {
		this.m_profilePic = m_profilePic;
	}

	private String m_username;
	private String m_password;
	private String m_firstName;
	private String m_lastName;
	private String m_ukPhoneNo;
	private Date m_dob;
	private String m_city;
	private Date m_dTLastLogin;
	private String m_profilePic;

}
