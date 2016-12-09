package chat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import filereader.ChatReader;
/**
 * This is the main class of CHat that contains the main logit of chat and contains
 * any interactation that the GUI would do with Chat
 * @author Osian 
 * @date 09 12 16
 * @version 0.65
 *
 */
public class Chat {
	private String m_chatLocation;
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
	public String getChatLocation() {
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
	 * Creates a new message that is inteneded to be created from the database
	 * @param message the message that is being created
	 * @param sender the name of the person who sent the message
	 * @param time the time that the message was sent.
	 */
	private void newTextMessage(String message, String sender, String time){
		Message load =  new TextMessage(message, sender, time);
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
	 * This adds a new media message to the linked list. This will also add time stamp to
	 *  database and is intended to be use to load old data into the database
	 * @param message The message contnet as a file
	 * @param discription The description of the message  (limited to a set 
	 * amount of characters @See MediaMessage
	 * @param sender The person who has sent the object
	 * @param Time The time of the message as a String 
	 * @throws Exception Exception If message is too long Excpetion is thrown  - @See MediaMessage
	 */
	public void newMediaMessage(File message, String discription, String sender,String Time) throws Exception {
		try {
			Message load =  new MediaMessage(message,discription,sender,Time);
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
	
	/**
	 * This  returns the message type of a message. this is returned as a char
	 * @param messageNumber the message that is being queried as a int 
	 * @return message type of message
	 * @throws Exception if message does not return
	 */
	public char getMessageType(int messageNumber) throws Exception{
		return this.getMessage(messageNumber).getMessageType();
	}
	
	/**
	 * This returns all chats
	 * @return all chats as a linked list 
	 */
	public LinkedList<Message> returnAllChats(){
		return this.m_listOfMessages;
	}
	
	
	/**
	 * Gets the  last X of messages
	 * @param amountOfMessages the amount of messages you need
	 * @return X-n of messages
	 */
	public LinkedList<Message> getLastXMessages(int amountOfMessages){
		LinkedList<Message> returnMessages = new LinkedList<Message>();
		if(amountOfMessages > this.m_messageNumber){
			System.out.println("This message is out of range");
			return null;
		}
		else {
			int x =  this.getChatSize() - amountOfMessages;
			for (int i = x; 
					i < this.getChatSize(); i++  ){
				returnMessages.add(this.m_listOfMessages.get(i));
			}
			return returnMessages;
		}
	}
	
	public void saveChat(){
		//some code will go here once I have access to the database
	}
	
	/**
	 * This method loads in the chat from memory from a specific file.
	 * @param Chat The File that contains the chat itself 
	 * @throws FileNotFoundException  If file is not found this error is thrown
	 */
	public void loadChat(String Chat) throws FileNotFoundException{
		ChatReader chat = new ChatReader(m_chatLocation);
		this.m_listOfMessages = chat.readAll();
	}
	
	/**
	 * Returns the chat size of the message
	 * @return
	 */
	public int getChatSize(){
		return this.m_listOfMessages.size();
	}

}
