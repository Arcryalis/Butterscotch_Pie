package login;

public class Request {
	private String m_username;
	private boolean m_isRequestToMe;
	
	
	
	public Request(String username, boolean isRequestToMe) {
		this.m_username = username;
		this.m_isRequestToMe = isRequestToMe;
	}
	
	// GETTERS
	public String getM_username() {
		return m_username;
	}
	public boolean getM_isRequestToMe() {
		return m_isRequestToMe;
	}
	
	// SETTERS
	public void setM_username(String m_username) {
		this.m_username = m_username;
	}
	public void setM_isRequestToMe(boolean m_isRequestToMe) {
		this.m_isRequestToMe = m_isRequestToMe;
	}
	
}