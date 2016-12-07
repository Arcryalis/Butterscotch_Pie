package filereader;

/**	
 * ChatReader.java
 * @author Hywel Williams
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import chat.Message;
import chat.MediaMessage;
import chat.TextMessage;
import java.util.Scanner;

/**
 * A ChatReader handles interactions with a chat file.
 */

public class ChatReader extends FileReader {

	//Constructor
	/**
	 * Creates an ChatReader.
	 * @param filepath
	 */
	public ChatReader(String filepath) {
		super(filepath);
		
	}
	
	//Other methods		
	/**
	 * Reads an input number of the  newest messages in the chat file.
	 * @param numRead The number of messages to read
	 * @return A list of the last numRead messages in the chat file
	 * @throws FileNotFoundException
	 */
	public LinkedList<Message> readSome(int numRead) throws FileNotFoundException {

		LinkedList<Message> chatList = new LinkedList<Message>();
		
		int numReadFrom = getNewestMsgNum() - numRead;
		
		try {	
			Scanner fileIn = openRead();
			
			while(fileIn.hasNextLine()) {
				
				Message message = readLine(fileIn);
				
				if (message.getMessageNumber() == numReadFrom) {
					chatList.add(message);				
				}
				
			}
		} catch (FileNotFoundException e) {
			throw e;
		} 
		return chatList;
	}

	
	/**
	 * Reads the current line of the chat file.
	 * @param fileIn The current file scanner being read from
	 * @return The message on the chat line
	 */
	protected Message readLine(Scanner fileIn) {
		
		Message message = new TextMessage (EMPTY, EMPTY);
		
		String line = fileIn.nextLine();
		
        Scanner lineIn = new Scanner(line);
        lineIn.useDelimiter(DELIMITER);
        
		int msgNum = lineIn.nextInt();
		char msgType = lineIn.next().charAt(0);
		
		String sender = lineIn.next(); 
		String timeSent = lineIn.next();
		
		//Media message
		if (msgType != TEXT) {
			String description = lineIn.next();
			String filepath = lineIn.next();
			File media = new File(filepath); 
			
			try {
				message = new MediaMessage (media, description, sender, timeSent);
			} catch (Exception e) {
				//throw e;
			} finally {
				lineIn.close();
			}
			
		//Text message
		} else {
			String content = lineIn.next();
			message = new TextMessage (content, sender, timeSent);
	        lineIn.close();
		}
		
		message.setMessageNumber(msgNum);
		
        return message ;	 
	}
	
	/**
	 * @return The newest msgNum in the chat file
	 */
	public int getNewestMsgNum() {
		int newMsgNum = 0;
		
		try {	
			Scanner fileIn = openRead();
			
			while(fileIn.hasNextLine()) {
				newMsgNum = readLine(fileIn).getMessageNumber();
			}
			
		} catch (FileNotFoundException e) {
			//throw e;
		} catch (Exception e) {
			//throw e;
		}
		return newMsgNum;
		
	}
	
	/**
	 * Converts a chat to string that can be written to a chat file.
	 * @param message 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String convertToFileString(Message message) {
			
		String line = ""; 
		
		line += message.getMessageNumber() + DELIMITER;
		line += message.getSender() + DELIMITER;
		line += message.getTime() + DELIMITER;
		
		//Media Message
		if (message.getMessageType() == MEDIA) {
			line += message.getMessageType() + DELIMITER;
			line += MEDIA + DELIMITER;
			MediaMessage convMessage = (MediaMessage) message;
			convMessage.getMessage();
			convMessage.getDescription();
			
		//Text Message
		} else {
			line += message.getMessageType() + DELIMITER;
			line += TEXT + DELIMITER;
			TextMessage convMessage = (TextMessage) message;

			line += convMessage.getMessage() + DELIMITER;
		}
		
		return line;
	}
	
	/**	Represents the line as a text message */
	private char TEXT = 't';
	/**	Represents the line as a media message */
	private char MEDIA = 'm';
	
}
