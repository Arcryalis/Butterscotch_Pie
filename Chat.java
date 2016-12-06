package Chat;

import java.io.File;
import java.util.LinkedList;

public class Chat {
	private File m_chatLocation;
	private boolean m_canUsersTalk;
	private LinkedList<Message> m_listOfMessages = new LinkedList<Message>();
	private int m_messageNumber = 0; // the message number

	public Chat(String chatLocation, boolean isChatElegable){
		//this.m_chatLocation = new File(chatLocation);
		this.m_canUsersTalk = isChatElegable;

	}

	/**
	 * This is the location of the chat
	 * @returnlocation oif the chat
	 */
	public File getChatLocation() {
		return m_chatLocation;
	}


	/** 
	 * This updates the chat location
	 * @param chatLocation the chat location 
	 */
	//	public void setChatLocation(File chatLocation) {
	//		this.m_chatLocation = chatLocation;
	//	}
	/**
	 * This says wether chat is eligable or not between them
	 * @return TRUE if eleigable false otherwise 
	 */
	public boolean isChatEligable() {
		return m_canUsersTalk;
	}

	/**
	 * updates whethet they can talk or not
	 * @param canUsersTalk boolean
	 */
	public void setChatEliliable(boolean canUsersTalk) {
		this.m_canUsersTalk = canUsersTalk;
	}

	/**
	 * This sets the list of messages
	 * @param listOfMessages
	 */
	public void setListOfMessages(LinkedList<Message> listOfMessages) {
		this.m_listOfMessages = listOfMessages;
	}

	/**
	 * This adds a new TextMessage to the Chat
	 * @param message THe text message with   ∞ amount of character set
	 * @param sender The sender of mesage
	 */
	public void newTextMessage(String message, String sender){
		Message load =  new TextMessage(message, sender);
		this.m_listOfMessages.add(load);
		this.m_messageNumber ++;
		
	}
	/**
	 * This adds a new messsage to the linked list 
	 * No safety checks are done here so if a virus is passed it will be displayed to
	 * the user
	 * @param message the message as a file
	 * @param discription The description of the message  (limited to a set 
	 * amount of characters @See MediaMessage
	 * @param sender The person who has sent the object
	 * @throws Exception If message is too long Excpetion is thrown  - @See MediaMessage
	 */
	public void newMediaMessage(File message, String discription, String sender) throws Exception {
		try {
			Message load =  new MediaMessage(message,discription,sender);
			this.m_listOfMessages.add(load);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Lenght of the description is too long");
			
		}
		this.m_messageNumber ++;
		
	}
	/**
	 * THis gets the message of a certain index
	 * @param index the message that is being returned 
	 * @return the message
	 */
	public Message getMessage(int indexMessageNumber) throws Exception {
		if(indexMessageNumber > this.m_messageNumber){
			throw new Exception("This message is out of range"); 
		}
		else {
			return this.getMessage(indexMessageNumber);
		}
	}
	
	public LinkedList<Message> returnAllChats(){
		return this.m_listOfMessages;
	}
	
	public void saveChat(){
		//some code will go here once I have access to the database
	}
	
	public void loadtChat(File Chat){
		//again some code will go here once i have access to the databse
	}

}
