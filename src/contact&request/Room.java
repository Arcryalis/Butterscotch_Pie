package login;

public class Room {
	private String m_roomname;
	private String m_roomtype;
	private String[] m_names;
	
	public Room(String roomname, String roomtype, String[] names) {
		m_roomname = roomname;
		m_roomtype = roomtype;
		m_names = names;
	}
	
	// GETTERS
	public String getM_roomname() {
		return m_roomname;
	}
	
	public String getM_roomtype() {
		return m_roomtype;
	}
	
	public String[] getM_names() {
		return m_names;
	}
	
	// SETTERS
	public void setM_roomname(String m_roomname) {
		this.m_roomname = m_roomname;
	}
	
	public void setM_roomtype(String m_roomtype) {
		this.m_roomtype = m_roomtype;
	}
	
	public void setM_names(String[] m_names) {
		this.m_names = m_names;
	}
	
}