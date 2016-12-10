import java.util.ArrayList;
import java.util.Scanner;


public class SecurityQuestions {
	
	
 public String getQuestion1(int n){
	 return questions[n];
 }
	
	public void SecurityQuestions(){
		questions[0] = "Mothers maiden name";
		questions[1] = "Name of first pet";
		questions[2] = "Name of primary school";
		questions[3] = "Where you were born";
		questions[4] = "Favorite food";
		questions[5] = "Favorite Animal";
	}
	
	private final String[] questions = new String[6];		
}	

