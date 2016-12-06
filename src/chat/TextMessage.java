package chat;


/**
 * This is the Text message class
 * @author Osian
 * @version 1
 * date 28/11/16
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
	public TextMessage(String message,String sender){
		super(sender);
		this.m_message = message;
	}
	/**
	 * Returns the message 
	 * @return the message itself
	 */
	public String getMessage() {
		return m_message;
	}
	public void display() {
		System.out.println(m_message);
	}
	
	

	

}
