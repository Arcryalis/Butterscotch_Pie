package filereader;

import java.io.File;


/**
 * ChatReader.java
 * @author Hywel Williams
 */

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import chat.Message;
import chat.TextMessage;
import chat.MediaMessage;


/**
 * A ChatReader handles interactions with a chat table.
 * @author Osian
 * @author Hywel
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
		String query = String.format(READ_SOME_QUERY, m_chatName, startReadNum);

		try {	
			openSQL();

			Statement statement = m_connection.createStatement();  
			ResultSet result = statement.executeQuery(query);  

			while(result.next()){
				chatList.add(readLine(result));
			}

		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}
		return chatList;
	}

	/**
	 * @return The newest msgNum in the chat file
	 */
	public int getNewestMsgNum() throws Exception {

		String query = String.format(NEW_NUM_QUERY, m_chatName);

		try {	
			openSQL();

			Statement statement = m_connection.createStatement();  
			ResultSet result = statement.executeQuery(query);  

			result.next();
			//Return the only value from the resulting table
			return result.getInt(1);

		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}
	}

	//Private & Protected methods
	private String toFileFormat(Message message) {

		String line = EMPTY;

		line += MSG_NUM + EQUALS;
		line += String.format(CONVERT_FORMAT, message.getMessageNumber()) + DELIMITER;
		line += SENDER + EQUALS;
		line += String.format(CONVERT_FORMAT, message.getSender()) + DELIMITER;
		line += TIME_SENT + EQUALS;
		line += String.format(CONVERT_FORMAT, message.getDateStamp()) + DELIMITER;

		line += MESSAGE_TYPE + EQUALS;

		String msgType = String.valueOf(message.getMessageType());
		line += String.format(CONVERT_FORMAT, msgType) + DELIMITER;

		if (msgType.equalsIgnoreCase(TEXT)) {
			TextMessage tMessage = (TextMessage) message;
			line += MESSAGE + EQUALS;
			line += String.format(CONVERT_FORMAT, tMessage.getMessage());

		} else {
			MediaMessage mMessage = (MediaMessage) message;
			line += MESSAGE + EQUALS;
			line += String.format(CONVERT_FORMAT, mMessage.getMessage());

			line += DESCRIPTION + EQUALS;
			line += String.format(CONVERT_FORMAT, mMessage.getDescription());
		}
		return line;


	}



	/**
	 * Reads the current line of the table and converts it to a message.
	 * @param fileIn The line being read from
	 * @return A message initialised with data from the line read
	 * @throws Exception
	 */
	private Message readLine(ResultSet result) throws Exception {

		Message message;

		try {
			String msgnum = result.getString(MSG_NUM);
			String sender = result.getString(SENDER);
			String timeSent = result.getString(TIME_SENT);
			String messageType = result.getString(MESSAGE_TYPE);
			String msg = result.getString(MESSAGE);
			String description = result.getString(DESCRIPTION);

			if (messageType.equalsIgnoreCase(TEXT)) {
				message = (Message) new TextMessage(msg, sender, timeSent);
			} else {
				File messageFile = new File(msg);
				message = (Message) new MediaMessage(messageFile, description, sender, timeSent);
			}

			return message;

		} catch (SQLException e) {
			throw new SQLException(SQL_ERROR + e);
		}
	}

	public void wrie(Message writingMessage) throws Exception{
		String query = WRITE_QUERY;
		try {
			openSQL();

			query += convertToFileString(writingMessage);
			Statement statment = m_connection.createStatement();
			statment.executeUpdate(query);


		}

		catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}



	}

	private String convertToFileString(Message message) {
		String line = OPEN_VALUES;
		line += String.format(CONVERT_FORMAT, message.getSender()) + DELIMITER;
		line += String.format(CONVERT_FORMAT, message.getDateStamp()) + DELIMITER;
		line += String.format(CONVERT_FORMAT, message.getMessageType()) + DELIMITER;
		line += String.format(CONVERT_FORMAT, message.getMessageContent()) + DELIMITER;
		if(message.getMessageType() == 'm'){
			line += String.format(CONVERT_FORMAT, ((MediaMessage) message).getDescription()) + DELIMITER;
		}
		else{
			line += "";
		}


		return line;
	}

	//Attributes
	private final String m_chatName;

	//Constants
	/** The filepath for the accounts file */
	private static final String FILEPATH = "jdbc:mysql://localhost:3306/cstest";

	/**	Represents the line as a text message */
	private static final String TEXT = "t";
	/**	Represents the line as a media message */
	private static final String MEDIA = "m";

	/** Name of the char table's attribute for message number */
	private static final String MSG_NUM = "messagenum";	
	/** Name of the char table's attribute for sender */
	private static final String SENDER = "sender";	
	/** Name of the char table's attribute for time sent */
	private static final String TIME_SENT = "timesent";	
	/** Name of the char table's attribute for message type */
	private static final String MESSAGE_TYPE = "messagetype";	
	/** Name of the char table's attribute for message */
	private static final String MESSAGE = "message";	
	/** Name of the char table's attribute for description */
	private static final String DESCRIPTION = "description";	

	/** Query used for the read() operation */
	private static final String READ_SOME_QUERY = 	"select * " + 
			"from %s " +
			"where " + MSG_NUM + ">= %d;";
	/** Query used for the getNewestMsgNum() operation */
	private static final String NEW_NUM_QUERY = 	"select max(" + MSG_NUM + ") " +
			"from %s;";
	//Query used to geneerate a new  write query
	private static final String WRITE_QUERY =	"insert into %s values ";


}
