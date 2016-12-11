package login;

import java.util.LinkedList;

public class RoomList extends LinkedList {
	
	public RoomList() {
		// Call to request reader for initializing
	}
	
	public boolean add(Room r) {
		return super.add(r);
	}

	public void add(int index, Room r) {
		super.add(index, r);
	}
	
	
	
	public static void main(String args[]) {
		
		//Testing strip
		RoomList test = new RoomList();
		
		System.out.println("Before adding testing strips: " + test.size());
		String[] room1 = {"user1", "Sev"};
		String[] room2 = {"user2", "user3", "user4", "Sev"};
		String[] room3 = {"user5", "user6", "Sev"};
		
		test.add(new Room("room1","c",room1));
		test.add(new Room("room2","c",room2));
		test.add(new Room("room3","d",room3));
		
		System.out.println("After: " + test.size());
		
		for(int i = 0; i < test.size(); i++) {
			Room temp = (Room)test.get(i);
			System.out.println(temp.getM_roomname() + ", consists of: ");
			for(int j = 0; j < temp.getM_names().length; j++) {
				System.out.print(temp.getM_names()[j] + ", ");
			}
			System.out.println("");
		}
		
	}
}
