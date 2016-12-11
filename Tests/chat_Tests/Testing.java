package chat;

import java.io.File;
import java.util.LinkedList;

public class Tesing {

	public static void main(String[] args) throws Exception {
		print("starting Chat");
		Chat testing = new Chat(null, true);
		print("Chat object successfully started \n adding some chat to it");
		testing.newTextMessage("See you guys tomorrow 10am library", "Sev");
		testing.newTextMessage("I'm going to be a few minutes late guys", "Osian");
		testing.newTextMessage("I'm on the left side", "Sev");
		testing.newTextMessage("See ya there in a bit","Sev");
		File x = new File("hello");
		testing.newMediaMessage(x, "", "bob");
		testing.newTextMessage("Sure", "Osian");
		LinkedList<Message> testMessages = testing.returnAllChats();
		for(int i = 0; i < testMessages.size(); i ++){
			Message m = testMessages.get(i);
			print(m.getMessageContent());
			System.out.println(m.getMessageType());

			
		}
		print("***");
		LinkedList<Message> temp = new LinkedList<Message>();
		print("**");
		int loop = 5;
		temp = testing.getLastXMessages(loop);
		for(int i = 0; i < loop; i ++){
			temp.get(i).getMessageContent();
		}
		print("lets get the fileLocation");
		print(testing.getFileAddressPath(4));
		
	
	}
	
	public static void print(String text){
		System.out.println(text);
	}

}
