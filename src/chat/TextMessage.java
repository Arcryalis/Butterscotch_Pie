package chat;


/**
 * This is the Text message class
 * @author Osian
 * @version 0.7
 * date 10/12/16
 */

public class TextMessage extends Message {
	//message itelf
	private String m_message;
	/**
	 * The text message object
	 * @param message the message itself
	 * @param timeStamp timestamp of the message
	 * @param Datestamp the date of the message 
	 */
	public TextMessage(String message,Account sender){
		super(sender,'t');
		this.m_message = message;
	}
	public TextMessage(String message, Account sender, String time) {
		super(sender, 't', time);
		this.m_message = message;
	}
	/**
	 * Returns the message 
	 * @return the message itself
	 */
	public String getMessage() {
		return m_message;
	}
	/**
	 * Displays the message 
	 */
	public void display() {
		System.out.println(m_message);
	}
	
	
	/**
	 * This returns a string of the message 
	 */
	public String getMessageContent() {
		return this.m_message;
	}
	
	

	

}
