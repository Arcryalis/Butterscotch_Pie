package filereader;

/**
 * RoomReader.java
 * @author Hywel Williams
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * A RoomReader handles interactions with the room and roommembers tables.
 * It is used to locate what rooms an account is part of, and what type of room it is.
 */

public class RoomReader extends FileReader {

	//Constructor
	/**
	 * Creates a RoomReader.
	 */
	public RoomReader() {
		super(FILEPATH);
	}

	
	//Public methods
	/**
	 * Searches the roommembers table for a members that are part of a room
	 * @param roomName The room being searched for
	 * @return A list of user names that are part of the room
	 * @throws Exception
	 */
	public LinkedList<String> getRoomMemebers(String roomName) throws Exception {
				
		String query = String.format(ROOM_MEMEBERS_QUERY, roomName);
		LinkedList<String> roomList = new LinkedList<String>();
		
		try{
			openSQL();
			
			Statement statement = m_connection.createStatement();  
			ResultSet result = statement.executeQuery(query);  
			
			while(result.next() == true){
				roomList.add(result.getString(MEMBERS_ROOM_NAME));
			}
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}
		
		return roomList;
	}
	
	
	/**
	 * Searches the roommembers table for rooms an input user is part of.
	 * @param username The user being searched for
	 * @return A list of room names that the user is part of
	 * @throws Exception
	 */
	public LinkedList<String> getRoomsMemeberOf(String username) throws Exception {
				
		String query = String.format(ROOMS_MEMEBER_OF_QUERY, username);
		LinkedList<String> roomList = new LinkedList<String>();
		
		try{
			openSQL();
			
			Statement statement = m_connection.createStatement();  
			ResultSet result = statement.executeQuery(query);  
			
			while(result.next()){
				roomList.add(result.getString(MEMBERS_ROOM_NAME));
			}
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}
		
		return roomList;
	}
	
	/**
	 * Reads the room file and returns a list of rooms.
	 * @return A list of room names that exist in the database
	 * @throws Exception
	 */
	public LinkedList<String> getRooms() throws Exception {
				
		String query = ROOMS_QUERY;
		LinkedList<String> roomList = new LinkedList<String>();
		
		try{
			openSQL();
			
			Statement statement = m_connection.createStatement();  
			ResultSet result = statement.executeQuery(query);  
			
			while(result.next()){
				roomList.add(result.getString(ROOM_NAME));
			}
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}
		
		return roomList;
	}

	/**
	 * Creates a new room in the rooms table
	 * @param roomName The name of the new room
	 * @param roomType The type of room. "c" for chat, "d" for collab drawing enviro
	 * @throws Exception
	 */
	public void newRoom(String roomName, String roomType) throws Exception {
		
		String query = EMPTY;
		
		if (roomType == CHAT) {
			query = String.format(NEW_ROOM_QUERY, roomName, CHAT);
		} else {
			query = String.format(NEW_ROOM_QUERY, roomName, COLLAB_DRAWING_ENVIRO);
		}
			
		try{
			openSQL();
			Statement statement = m_connection.createStatement();  
			statement.executeUpdate(query);  
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}	
		
		if (roomType == CHAT) {
			query = String.format(NEW_TABLE_QUERY, roomName);
			
			try{
				openSQL();
				Statement statement = m_connection.createStatement();  
				statement.executeUpdate(query);  
				
			}catch(SQLException e){ 
				throw new SQLException(SQL_ERROR + e);
			} catch (Exception e) {
				throw e;
			} finally {
				closeSQL();
			}	

		} else {
			System.out.println("Panic");
		}
		
		
	}
	
	/**
	 * Add a user to a room.
	 * @param roomName The name of the room to add the user to
	 * @param username The username of the user
	 * @throws Exception
	 */
	public void newRoomMember(String roomName, String username) throws Exception {
		
		String query = String.format(NEW_MEMBER_QUERY, roomName, username);
			
		try{
			openSQL();
			Statement statement = m_connection.createStatement();  
			statement.executeUpdate(query);  
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}	
				
	}
	
	/**
	 * Remove a user from a room.
	 * @param roomName The name of the room to remove the user from
	 * @param username The username of the user to remove
	 * @throws Exception
	 */
	public void removeRoomMember(String roomName, String username) throws Exception {
		
		String query = String.format(DELETE_MEMBER_QUERY, roomName, username);
			
		try{
			openSQL();
			Statement statement = m_connection.createStatement();  
			statement.executeUpdate(query);  
			
			//if room is now empty
			if (getRoomMemebers(roomName).size() == 0){
				removeRoom(roomName);
			}
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}	
				
	}
	
	/**
	 * Get the type of the room
	 * @param roomName The room to check
	 * @return A string representing the room as either a chat or drawing environment
	 * @throws Exception
	 */
	public String getRoomType(String roomName) throws Exception {

		String query = String.format(ROOM_TYPE_QUERY, roomName);
			
		try{
			openSQL();
			Statement statement = m_connection.createStatement();  
			ResultSet result = statement.executeQuery(query);  
			
			result.next();
			return result.getString(ROOM_TYPE);
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}	

	}
	
	//Private methods
	/**
	 * Deletes a room.
	 * @param roomName The name of the room to delete
	 * @throws Exception
	 */
	private void removeRoom(String roomName) throws Exception {
		
		String query = String.format(DELETE_ROOM_QUERY, roomName);
			
		try{
			openSQL();
			Statement statement = m_connection.createStatement();  
			statement.executeUpdate(query);  
			
		}catch(SQLException e){ 
			throw new SQLException(SQL_ERROR + e);
		} catch (Exception e) {
			throw e;
		} finally {
			closeSQL();
		}	
				
	}
	
	//Constants
	/** The filepath for the accounts file */
	private static final String FILEPATH = "jdbc:mysql://localhost:3306/cstest";

	/** Used to mark a room as a chat */
	private static final String CHAT = "c";
	/** Used to mark a room as a collaborative drawing environment */
	private static final String COLLAB_DRAWING_ENVIRO = "d";
	
	/** Name of room's attribute for room name */
	private static final String ROOM_NAME = "roomname";
	/** Name of room's attribute for room type */
	private static final String ROOM_TYPE = "roomtype";
	/** Name of roommember's attribute for room name */
	private static final String MEMBERS_ROOM_NAME = "roomname";
	/** Name of roommember's attribute for username */
	private static final String USER_NAME = "username";
	
	
	/** Query used for the getRooms() operation */
	private static final String ROOMS_QUERY = 	"select distinct " + ROOM_NAME + " " +
												"from room;";
	/** Query used for the getRoomMemebers() operation */
	private static final String ROOM_MEMEBERS_QUERY = 	"select " + USER_NAME + " " +
														"from roommembers " +
														"where " + MEMBERS_ROOM_NAME + " = '%s';";
	/** Query used for the getRoomsMemeberOf() operation */
	private static final String ROOMS_MEMEBER_OF_QUERY = 	"select distinct " + MEMBERS_ROOM_NAME + " " +
															"from roommembers " +
															"where " + USER_NAME + " = '%s';";
	/** Query used for the getRoomsType() operation */
	private static final String ROOM_TYPE_QUERY = 	"select " + ROOM_TYPE + " " +
													"from room " +
													"where " + ROOM_NAME +  "= %s;";
	
	
	/** Query used for the newRoom() operation */
	private static final String NEW_ROOM_QUERY = 	"insert into room " +
													"values ('%s', '%s');";
	/** Query used for creating a new table in newRoom() operation */
	private static final String NEW_TABLE_QUERY = 	"create table %s " +	
													"(roomname char(20), " +
													"roomtype char(1), " + 
													"primary key (roomname));";
	/** Query used for creating a user in the newRoomMember() operation */
	private static final String NEW_MEMBER_QUERY = 	"insert into roommembers " +
													"values ('%s', '%s');";
	/** Query used for removing a user in the removeRoomMember() operation */
	private static final String DELETE_MEMBER_QUERY = 	"delete from roommembers " + 
														"where " + MEMBERS_ROOM_NAME + " = '%s' AND " 
														+ USER_NAME + " = '%s';";
	/** Query used for removing a user in the removeRoomMember() operation */
	private static final String DELETE_ROOM_QUERY = 	"drop table %s;";
	
	
	
}
