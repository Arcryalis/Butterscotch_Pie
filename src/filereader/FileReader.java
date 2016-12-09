package filereader;


/**
 * FileReader.java
 * @author Hywel Williams
 */

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * A file reader reads and returns data from a file at a given database.
 * The constants SQL_USERNAME and SQL_PASSWORD are used for the database's username and password respectively.
 */
public abstract class FileReader {

	//Constructor
	/**
	 * Creates a new FileReader.
	 * @param filepath The filepath of the file to be read
	 */
	public FileReader(String filepath) {
		this.m_filepath = filepath;
	}
	
	//Get & Set methods
	/**
	 * @return The current filepath to read/write to
	 */
	public String getFilepath() {
		return m_filepath;
	}
	
	/**
	 * Changes the current filepath to read/write to. Then updates the file to match.
	 * @param filepath The filepath to change to
	 */
	public void setFilepath(String filepath) {
		this.m_filepath = filepath;
	}	

	//Public methods
	/**
	 * Attempts to connect to a mySQL database.
	 */
	public void openSQL() throws FileNotFoundException {
		
		try {
			m_connection = DriverManager.getConnection(m_filepath,SQL_USERNAME,SQL_PASSWORD);
		} catch (SQLException e) {
			System.out.println(e);
			throw new FileNotFoundException(String.format(NO_FILE_ERROR, m_filepath));
		}
	}	
	
	/**
	 * Attempts to close the connection to the mySQL database.
	 */
	public void closeSQL() {
		try {
			m_connection.close(); 
		} catch (SQLException e) {
			System.out.println(CLOSE_ERROR);
		}
	} 

	//Attributes
	/** The location of the file to be read / written to */
	protected String m_filepath;
	
	/**	Connection to the mySQL database */
	protected Connection m_connection;
	/** The table within the mySQL database to be referred to */
	protected String table;
	
	/** Username for the mySQL database */
	private String SQL_USERNAME = "root";
	/** Password for the mySQL database */
	private String SQL_PASSWORD = "Orange-Squash28";
	
	/** Name of the account table's attribute for username */
	protected static final String USERNAME = "username";
	
	//Constants
	protected static final String DELIMITER = ",";
	protected static final String CONVERT_FORMAT = "'%s'";
	protected static final String QUOTATION_MARK = "\"";
	protected static final String OPEN_VALUES  = "(";
	protected static final String CLOSE_VALUES = ")";
	protected static final String END_LINE = ";";
	protected static final String EQUALS = " = ";
	
	protected static final String EMPTY = "";
	protected static final String SQL_ERROR = "A SQL error occured. ";
	protected static final String NO_FILE_ERROR = "%s could not be found.";
	protected static final String IO_ERROR = "Error writing to file at %s.";
	protected static final String CLOSE_ERROR = "Error closing file.";
	
}