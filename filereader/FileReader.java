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
import java.util.Scanner;

/**
 * A file reader reads and returns data from a file at a given location.
 */
public abstract class FileReader {

	//Constructor
	/**
	 * Creates a new FileReader.
	 * @param filepath The filepath of the file to be read
	 */
	public FileReader(String filepath) {
		this.m_filepath = filepath;
		setFile();
	}
	
	// Get & Set methods
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
		setFile();
	}
	
	/**
	 * Updates the current file to match the filepath.
	 */
	private void setFile() {
		m_file = new File(m_filepath);
	}
	
	//Other methods
	/**
	 * Attempts to open the file to read.
	 * @return Scanner to read the file from
	 * @throws FileNotFoundException
	 */
	public Scanner openRead() throws FileNotFoundException {
		try {
			return new Scanner(m_file);
			
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(String.format(NO_FILE_ERROR, m_filepath));
		}
	}
	
	/**
	 * Attempts to open the file to write. 
	 * If no file can be found, creates one at the current filepath.
	 * @return PrintWriter to write to the file
	 * @throws FileNotFoundException
	 */
	public  BufferedWriter openWrite() throws IOException {
		  
		if(!m_file.exists()) {
			m_file.createNewFile();
		}
		
		try {
			FileWriter outputFile = new FileWriter(m_file, true);
			return (new BufferedWriter(outputFile));
			
		} catch (IOException e) {
			throw new IOException(String.format(IO_ERROR, m_filepath));
		}
	}
	

	/** Reads data from the file */
	//public abstract Object read() throws FileNotFoundException;

	/** Writes data to the file */
	//public abstract void write(Object content) throws FileNotFoundException;

	//Attributes
	/** The location of the file to be read / written to */
	protected String m_filepath;
	/** The file to be read from / written to */
	protected File m_file;
	
	//Constants
	protected static final String DELIMITER = ",";
	protected static final String EMPTY = "";
	protected static final String NO_FILE_ERROR = "%s could not be found.";
	protected static final String IO_ERROR = "Error opening file to write at %s.";
	
}



/*

	import java.sql.Connection;
	import java.sql.DriverManager;

	/**
	 * Attempts to connect to a mySQL database.
	 /
	public void openSQL() throws FileNotFoundException {
		
		try {
			m_connection = DriverManager.getConnection(m_filepath,USERNAME,PASSWORD);
		} catch (Exception e) {
			System.out.println(e);
			throw new FileNotFoundException(String.format(NO_FILE_ERROR, m_filepath));
		}
	}	
	
	/**
	 * Attempts to close the connection to the mySQL database.
	 /
	public void closeSQL() {
		try {
			m_connection.close(); 
		} catch (Exception e) {
			System.out.println(CLOSE_ERROR);
		}
	} 
	
	
	/**	Connection to the mySQL database /
	protected Connection m_connection;
	/** The table within the mySQL database to be referred to /
	protected String table;
	
	/** Username for the mySQL database /
	private String USERNAME = "root";
	/** Password for the mySQL database /
	private String PASSWORD = "Orange-Squash28";
	
*/