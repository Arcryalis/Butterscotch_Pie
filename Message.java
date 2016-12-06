package Chat;

import java.util.Date;

/**
 * This is a message object  that contains general infomation about a message
 * @author Osian
 * @date 29/11/16
 *
 */
public abstract class Message {
	
	protected Date m_dateStamp = new Date();
	protected String m_time;
	protected String m_sender;
	


	/**
	 * This sets the message tune stamp up
	 */
	public Message(String sender){
		this.m_time = m_dateStamp.toString();
		this.m_sender = sender;
	}

//eunumarated type
	

	public Date getDateStamp() {
		return m_dateStamp;
	}



	public String getTime() {
		return m_time;
	}



	public String getSender() {
		return m_sender;
	}
	
	
	public abstract void display();
	
		
	}



