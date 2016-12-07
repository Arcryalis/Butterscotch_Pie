public class ResetPassword {

	
	public ResetPassword(String username, String newPassword, String newRepeatPassword) {
		this.m_username = username;
		this.m_newPassword = newPassword;
		this.m_newRepeatPassword = newRepeatPassword;
	}

	// compares newPassword and newRepeatPassword to see if they are the same.
	public void ValidatePassword(String newPassword, String newRepeatPassword) {
		if (m_newPassword.compareTo(m_newRepeatPassword) == 0) {
			System.out.println("Passwords Match.");
		}
	}

	// GETTERS
	public String getUsername() {
		return m_username;
	}

	public String getNewPassword() {
		return m_newPassword;
	}

	public String getNewRepeatPassword() {
		return m_newRepeatPassword;
	}

	// SETTERS
	public void setUsername(String m_username) {
		this.m_username = m_username;
	}

	public void setNewPassword(String m_newPassword) {
		this.m_newPassword = m_newPassword;
	}

	public void setNewRepeatPassword(String m_newRepeatPassword) {
		this.m_newRepeatPassword = m_newRepeatPassword;
	}

	private String m_username;
	private String m_newPassword;
	private String m_newRepeatPassword;

}
