package filereader;

import java.io.FileNotFoundException;

/**
 * ChatReader.java
 * @author Hywel Williams
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Scanner;

import chat.Message;


/**
 * A ChatReader handles interactions with a chat table.
 */
public class ChatReader extends FileReader {
	//Constructor
	/**
	 * Creates a ChatReader.
	 * @param filepath
	 */
	public ChatReader(String chatName) {
		super(FILEPATH);
		this.m_chatName = chatName;
	}
	
	//Public methods	
	/**
	 * Reads an input number of the  newest messages in the chat file.
	 * @param numRead The number of messages to read
	 * @return A list of the last numRead messages in the chat file
	 * @throws FileNotFoundException
	 */
	public LinkedList<Message> readSome(int numRead) throws Exception {

		LinkedList<Message> chatList = new LinkedList<Message>();
		int startReadNum = getNewestMsgNum() - numRead;
		
		try {	
			
		} catch (Exception e) {
			throw e;
		} 
		return chatList;
	}

	//public void write(message) {
	
	
	/**
	 * @return The newest msgNum in the chat file
	 */
	public int getNewestMsgNum() {
		
		return 0;
	}
	
	//Attributes
	private final String m_chatName;
	
	//Constants
	/** The filepath for the accounts file */
	private static final String FILEPATH = "jdbc:mysql://localhost:3306/cstest";
	
	/**	Represents the line as a text message */
	private static final char TEXT = 't';
	/**	Represents the line as a media message */
	private static final char MEDIA = 'm';
}
