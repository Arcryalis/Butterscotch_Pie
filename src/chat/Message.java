package chat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a message object  that contains general information about a message
 * @author Osian
 * @date 11/12/16
 * @version 0.8
 *
 */
public abstract class Message {
	//THe variable that converts the 
	protected String m_time; // stores the time that the message was saved
	protected String m_sender; // stores the sender
	protected char m_messageType; //stores the message type
	protected int m_messageNo; // stores the message number



	/**
	 * This creates a new message in the hierarchy. This is intended for new messages
	 * Time is done automatically and that the time is generated when this method
	 * Receives the request to generate time. This may mean that the message may be a 
	 * second or two late but this does not affect any of the system apart from displaying
	 * to the user.
	 * @param sender THe person who sent the message
	 */
	public Message(String sender, char messageType){
		 SimpleDateFormat dateStamp = 
				 new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 // Gets time from system
		this.m_time = dateStamp.format(new Date());
		this.m_sender = sender;
		this.m_messageType = messageType;
		
	}
	
	/**
	 * This creates a new message in the hierarchy. This is intended for old
	 * messages that are uploaded from the save logs. 
	 * @param sender The sender of the message
	 * @param time Time as String of the message
	 */
	public Message(String sender,char messageType, String time){
		this.m_time = time;
		this.m_messageType = messageType;
		this.m_sender = sender;
		
	}


	
	/**
	 * Returns the date
	 * @return date 
	 */
	public String getDateStamp() {
		return m_time;
	}

	/**
	 * This returns the sender of the message
	 * @return sender of the chat
	 */
	public String getSender() {
		return m_sender;
	}
	
	/**
	 * Sets the message number
	 * @param messageNo int type of new message number
	 */ 
	public void setMessageNumber(int messageNo){
		this.m_messageNo = messageNo;
	}
	/**
	 * Returns the message number
	 * @return Message number
	 */
	public int getMessageNumber(){
		return this.m_messageNo;
	}
	
	/**
	 * This gets the message number
	 * @return the message number
	 */
	public char getMessageType(){
		return this.m_messageType;
	}
	
	/**
	 * This gets the main content of the message
	 * @return the content of the message - for media message a too string 
	 */
	public abstract String getMessageContent();
	
		
	}