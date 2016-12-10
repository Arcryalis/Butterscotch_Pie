package chat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a message object  that contains general infomation about a message
 * @author Osian
 * @date 10/12/16
 * @version 0.75
 *
 */
public abstract class Message {
	
	protected SimpleDateFormat m_dateStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected String m_time;
	protected String m_sender;
	protected char m_messageType;
	protected int m_messageNo;



	/**
	 * This creates a new message in the higherachi. This is intended for new messages
	 * Time is done automatically and that the time is generated when this method
	 * Receives the request to generate time. This may mean that the message may be a 
	 * second or two late but this does not affect any of the system apart from displaying
	 * to the user.
	 * @param sender THe person who sent the message
	 */
	public Message(String sender, char messageType){
		this.m_time = m_dateStamp.format(new Date());
		this.m_sender = sender;
		this.m_messageType = messageType;
		
	}
	
	/**
	 * This creates a new message in the higherachi. This is inteneded for old
	 * messages that are uploaded from the save logs. 
	 * @param sender The sender of the message
	 * @param time Time as String of the message
	 */
	public Message(String sender,char messageType, String time){
		this.m_time = time;
		this.m_messageType = messageType;
		this.m_sender = sender;
		
	}

//eunumarated type 
	
	/**
	 * Returns the date
	 * @return date 
	 */
	public String getDateStamp() {
		return m_time;
	}

	public String getSender() {
		return m_sender;
	}
	
	public void setMessageNumber(int messageNo){
		this.m_messageNo = messageNo;
	}
	public int getMessageNumber(){
		return this.m_messageNo;
	}
	
	public char getMessageType(){
		return this.m_messageType;
	}
	
	
	public abstract String getMessageContent();
	
		
	}



