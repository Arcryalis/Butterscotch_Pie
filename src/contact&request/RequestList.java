
import java.util.LinkedList;

public class RequestList extends LinkedList {
	
	public RequestList() {
		// Call to request reader for initializing
	}
	
	public boolean add(Request r) {
		return super.add(r);
	}

	public void add(int index, Request r) {
		super.add(index, r);
	}
	
	
	
	public static void main(String args[]) {
		
		//Testing strip
		RequestList test = new RequestList();
		
		System.out.println("Before adding testing strips: " + test.size());
		
		test.add(new Request("Sam", true));
		test.add(new Request("Seven", true));
		test.add(new Request("Med", false));
		test.add(new Request("Tim", true));
		
		System.out.println("After: " + test.size());
		
		for(int i = 0; i < test.size(); i++) {
			Request temp = (Request)test.get(i);
			if(temp.getM_isRequestToMe())
				System.out.println(temp.getM_username());
		}
		
	}
}
