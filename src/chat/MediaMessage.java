package chat;

import java.io.File;

/**
 * This is the media message for media 
 * @author Osian
 * @version 0.6
 * @date 06 12 2016
 *
 */

public class MediaMessage extends Message {
	
	// Variables for the Media message 
	private File m_file;
	private String m_Description;
	final int  DESCPITION_LENGHT = 50; // this is the max length of chat messages  
	
	/**
	 * Constructor for MediaMessage 
	 * @param message the message itself
	 * @param discription The description of the message that goes with the message 
	 * @param timeStamp Time of when message was sent
	 * @param dateStamp The date of when message was sent
	 * @throws Exception if message was longer than the set amount of characters a error is thrown.
	 */
	public MediaMessage(File message, String discription, String sender) throws Exception {
		super(sender, 'm');
		this.m_file = message;
		if ((discription.length()) >= this.DESCPITION_LENGHT){
			throw new Exception("Lenght of the description is too long"); 
		}
		this.m_Description = discription;
		//
	}
	
	public MediaMessage(File message, String discription, String sender, String time) throws Exception {
		super(sender,'m', time);
		this.m_file = message;
		if ((discription.length()) >= this.DESCPITION_LENGHT){
			throw new Exception("Lenght of the description is too long"); 
		}
		this.m_Description = discription;
		//
	}

	/**
	 * Them message file
	 * @return message files 
	 */
	public File getMessage() {
		return m_file;
	}
	
	/** 
	 * Gets the Description of the file
	 * @return the description 
	 */
	public String getDescription() {
		return m_Description;
	}
	/**
	 *  sets the descrption
	 * @param discription String of the descpition 
	 */
	public void setDiscription(String discription) {
		m_Description = discription;
	}

	@Override
	public void display() {
		System.out.println(m_Description);
		System.out.println(m_file.toString());
		
	}
	

}
