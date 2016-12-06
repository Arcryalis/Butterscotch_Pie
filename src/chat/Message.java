package chat;

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
	 * This creates a new message in the higherachi. This is intended for new messages
	 * Time is done automatically and that the time is generated when this method
	 * Receives the request to generate time. This may mean that the message may be a 
	 * second or two late but this does not affect any of the system apart from displaying
	 * to the user.
	 * @param sender THe person who sent the message
	 */
	public Message(String sender){
		this.m_time = m_dateStamp.toString();
		this.m_sender = sender;
	}
	
	/**
	 * This creates a new message in the higherachi. This is inteneded for old
	 * messages that are uploaded from the save logs. 
	 * @param sender The sender of the message
	 * @param time Time as String of the message
	 */
	public Message(String sender, String time){
		this.m_time = time;
		this.m_sender = sender;
	}

//eunumarated type 
	

	public String getDateStamp() {
		return m_time;
	}



	public String getTime() {
		return m_time;
	}



	public String getSender() {
		return m_sender;
	}
	
	
	public abstract void display();
	
		
	}



