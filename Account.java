import java.util.Date;
import java.awt.Image;

public class Account {

	private String m_username;
	private String m_password;
	private String m_firstName;
	private String m_lastName;
	private String m_ukPhoneNo;
	private Date m_dob;
	private String m_city;
	private Date m_dTLastLogin;
	private Image m_profilePic;

	// using 5 attributes for constructor following coding conventions
	public Account(String username, String password, String firstName, String lastName, String ukPhoneNo) {
		this.m_username = username;
		this.m_password = password;
		this.m_firstName = firstName;
		this.m_lastName = lastName;
		this.m_ukPhoneNo = ukPhoneNo;
	}
	
	@Override
	public String toString() {
	
		String TOSTRING_FORMAT = "Username: %s, Password: %s, First Name: %s, Last Name: %s, UK Phone Number: %s,"
				+ "Date of Birth: %s, City: %s, Date Last Logged in: %s, Profile Picture: %s";
		return String.format(TOSTRING_FORMAT, m_username, m_password, m_firstName, m_lastName, m_ukPhoneNo, m_dob,
				m_city, m_dTLastLogin, m_profilePic);							
	}

	//GETTERS
	public String getUsername() {
		return m_username;
	}

	public String getPassword() {
		return m_password;
	}

	public String getFirstName() {
		return m_firstName;
	}

	public String getLastName() {
		return m_lastName;
	}

	public String getUkPhoneNo() {
		return m_ukPhoneNo;
	}
	
	public Date getDob() {
		return m_dob;
	}
	
	public String getCity() {
		return m_city;
	}
	
	public Image getProfilePic() {
		return m_profilePic;
	}
	
	public Date getDtLastLogin() {
		return m_dTLastLogin;
	}

	//SETTERS	
	public void setUsername(String m_username) {
		this.m_username = m_username;
	}

	public void setPassword(String m_password) {
		this.m_password = m_password;
	}

	public void setFirstName(String m_firstName) {
		this.m_firstName = m_firstName;
	}

	public void setLastName(String m_lastName) {
		this.m_lastName = m_lastName;
	}

	public void setUkPhoneNo(String m_ukPhoneNo) {
		this.m_ukPhoneNo = m_ukPhoneNo;
	}

	public void setDob(Date m_dob) {
		this.m_dob = m_dob;
	}

	public void setCity(String m_city) {
		this.m_city = m_city;
	}

	public void setDtLastLogin(Date m_dTLastLogin) {
		this.m_dTLastLogin = m_dTLastLogin;
	}

	public void setProfilePic(Image m_profilePic) {
		this.m_profilePic = m_profilePic;
	}
}
