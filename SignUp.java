import java.util.Date;
import java.util.Scanner;
/*
 * Signup lets a user add an account to the database
 */
public class SignUp {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int ukPhoneNo;
	
	
	public Scanner in = new Scanner(System.in);
	
	/*
	 * Checks whether a username already exists, if so asks them to choose a different name 
	 * otherwise creates the account
	 * @param username, the name they have chosen
	 */
	public void validateSignup(String username){
		if(username.compareTo(Account.getUsername(username)){
			System.out.println("Please choose a diiferent username, the one you have chosen is taken");
			else{
				Account.Account acc = new Account.Account(username, password, firstName, lastName, ukPhoneNo);
			}
				
		}
	}
}
