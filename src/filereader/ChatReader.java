package filereader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import chat.Message;
import chat.MediaMessage;
import chat.TextMessage;

/**
 * ChatReader.java
 * @author Hywel Williams
 */

import java.util.Scanner;

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
	 * @return The newest msgNum in the chat file
	 */
	public int getNewestMsgNum() {
		int newMsgNum = 0;
		
		try {	
			Scanner fileIn = openRead();
			
			while(fileIn.hasNextLine()) {
				//newMsgNum = readLine(fileIn);
			}
			
		} catch (FileNotFoundException e) {
			//throw e;
		} 
		return newMsgNum;
		
	}
	
	/**
	 * Reads all chat messages from the chat file.
	 * @return A list of all chat messages in the chat file
	 * @throws FileNotFoundException
	 */
	public LinkedList<Message> readAll() throws FileNotFoundException {

		LinkedList<Message> chatList = new LinkedList<Message>();
		
		try {	
			Scanner fileIn = openRead();

			while(fileIn.hasNextLine()) {
				try {
					chatList.add(readLine(fileIn));
				} catch (Exception e) {
					
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		} 
		return chatList;
	}
	
	/*
	/**
	 * Reads an input number of the  newest messages in the chat file.
	 * @param numRead The number of messages to read
	 * @return A list of the last numRead messages in the chat file
	 * @throws FileNotFoundException
	 *
	public LinkedList<Message> readLastNum(int numRead) throws FileNotFoundException {

		LinkedList<String> chatList = new LinkedList<String>();
		
		int numReadFrom = getNewestMsgNum() - numRead;
		
		try {	
			Scanner fileIn = openRead();
			
			while(fileIn.hasNextLine()) {
				
				Message message = readLine(fileIn);
				
				if (message.getMsgNum() == numReadFrom) {
									
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		} 
		return chatList;
	}
	*/
	
	/**
	 * Reads the current line of the chat file.
	 * @param fileIn The current file scanner being read from
	 * @return The message on the chat line
	 */
	private Message readLine(Scanner fileIn) throws Exception {
		
		Message message = new TextMessage (EMPTY, EMPTY);
		
		String line = fileIn.nextLine();
		
        Scanner lineIn = new Scanner(line);
        lineIn.useDelimiter(DELIMITER);
        
		String msgNum = lineIn.next();
		String msgType = lineIn.next();
		
		String sender = lineIn.next(); 
		String timeSent = lineIn.next();
		
		//Text message
		if (msgType != TEXT) {

			String description = lineIn.next();
			String filepath = lineIn.next();
			File media = new File(filepath); 
			try {
				message = new MediaMessage (media, description, sender);
			} catch (Exception e) {
				throw e;
			} finally {
				lineIn.close();
			}
			
		//Media message
		} else {
			String content = lineIn.next();
			message = new TextMessage (content, sender);
	        lineIn.close();
		}
		
        return message ;	 
	}
	
	
	/**
	 * 
	 * @param content
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void write(String content) throws FileNotFoundException, IOException {
		
		try {	
			BufferedWriter fileOut = openWrite();
			
			String line = ""; 
			
			//line += /* msgNum + */ DELIMITER;
			//line += /* msgType + */ DELIMITER;
			line += content /* + DELIMITER */;
			//line += /* timeSent + */ DELIMITER;
			//line += /* username + */ DELIMITER;

			fileOut.newLine();
			fileOut.write(line);
			
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}
	
	private String TEXT = "Text";
	
}
