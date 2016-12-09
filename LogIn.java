import java.util.Scanner;

/*
*Login handles the validation of a users login
*/
public class LogIn {
	private String username;
	private String password;
	public Scanner in = new Scanner(System.in); 
	
	/**
	 * Takes users input and checks if username and passwrod match an account
	 * @param username, the users unique name that they enter
	 * @param password, the users password that they have entered
	 * @return a boolean of whether the account and password match a tuple in the database
	 */
	public Boolean ValidateLogin(String username, String password) {
		if (username.compareTo(Account.getUsername(username)) && password.compareTo(Account.getPassword(password))) {
			return true; 
		}
	}
	
}
