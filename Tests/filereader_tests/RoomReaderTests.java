package filereader_tests;

import static org.junit.Assert.*;
import org.junit.Test;

import filereader.RoomReader;

public class RoomReaderTests {
	
	
	/**
	 * Test getRoomsMemberOf() executes.
	 */
	@Test  
    public void Test1a () {
		RoomReader reader = new RoomReader();
		try {
			Boolean truth = reader.getRoomsMemeberOf("Sev").contains("room1");
			assertEquals(truth, true);
		} catch (Exception e) {
			System.out.println(e + " FAIL Test1a");
		}
	}
	
	/**
	 * Test getRooms() executes.
	 */
	@Test  
    public void Test2a () {
		RoomReader reader = new RoomReader();
		try {
			Boolean truth = reader.getRooms().contains("room2");
			assertEquals(truth, true);
		} catch (Exception e) {
			System.out.println(e + " FAIL Test2a");
		}
	}
	
	/**
	 * Test newRoom() executes.
	 */
	@Test  
    public void Test3a () {
		RoomReader reader = new RoomReader();
		try {
			reader.newRoom("room4", "c");
		} catch (Exception e) {
			System.out.println(e + " FAIL Test3a newRoom");
		}
		
		try {
			reader.newRoomMember("room4", "testuser");
		} catch (Exception e) {
			System.out.println(e + " FAIL Test3a newRoomMemeber");
		}
		
		try {
			reader.removeRoomMember("room4", "testuser");
		} catch (Exception e) {
			System.out.println(e + " FAIL Test3a removeRoomMember");
		}
			
	}
	
	
	/**
	 * Test newRoomMember() executes. newRoom removeRoomMember
	 */
	@Test  
    public void Test4a () {
		RoomReader reader = new RoomReader();
		try {
			reader.newRoomMember("room1", "testuser");
		} catch (Exception e) {
			System.out.println(e + " FAIL Test3a newRoomMemeber");
		}
		
		try {
			reader.removeRoomMember("room1", "testuser");
		} catch (Exception e) {
			System.out.println(e + " FAIL Test3a removeRoomMember");
		}
	}
	
}
