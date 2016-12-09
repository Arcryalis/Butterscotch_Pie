package filereader_tests;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import filereader.ContactsReader;
import login.Account;

public class ContactReaderTests {

	private static final Account SECOND_ACCOUNT = new Account("u1", "p1", "f.name 1", "s.name 1", "01792");
	private static final Account FOURTH_ACCOUNT = new Account("u4", "p4", "f.name 4", "s.name 4", "45136");
	
	/**
	 * Test getContactsOf() executes.
	 */
	@Test  
    public void Test1a () {
		ContactsReader reader = new ContactsReader();
		try {
			reader.getContactsOf(SECOND_ACCOUNT);
		} catch (Exception e) {
			System.out.println(e + " FAIL Test1a");
		}
	}
	
	/**
	 * Test getContactsOf() returns correctly.
	 */
	@Test  
    public void Test1b () {
		ContactsReader reader = new ContactsReader();
		try {
			Boolean truth = reader.getContactsOf(SECOND_ACCOUNT).contains("u2");
			assertEquals(truth, true);
		} catch (Exception e) {
			System.out.println(e + " FAIL Test1b");
		}
	}
	
		
	/**
	 * Test getRequestsRecived() (received) executes.
	 */
	@Test  
    public void Test2a () {
		ContactsReader reader = new ContactsReader();
		try {
			assertEquals(reader.getRequests(SECOND_ACCOUNT, true).contains("u3"), false);
		} catch (Exception e) {
			System.out.println(e + " FAIL Test2a");
		}	
	}
	
	/**
	 * Test getRequests() (request) executes.
	 */
	@Test  
    public void Test2b () {
		ContactsReader reader = new ContactsReader();
		try {
			Boolean truth = reader.getRequests(SECOND_ACCOUNT, false).contains("u3");
			assertEquals(truth, true);
		} catch (Exception e) {
			System.out.println(e + " FAIL Test2b");
		}	
	}
		
	/**
	 * Test getRequests() (add), getRequests() (update), delete() executes.
	 */
	@Test  
    public void Test3a () {
		ContactsReader reader = new ContactsReader();
		
		try {
			reader.updateContacts(SECOND_ACCOUNT, FOURTH_ACCOUNT, true);
		} catch (Exception e) {
			System.out.println(e + " FAIL Test3a add");
		}	
		
		try {
			reader.updateContacts(SECOND_ACCOUNT, FOURTH_ACCOUNT, false);
		} catch (Exception e) {
			System.out.println(e + " FAIL Test3a update");
		}	
		
		try {
			reader.delete(SECOND_ACCOUNT, FOURTH_ACCOUNT);
		} catch (Exception e) {
			System.out.println(e + " FAIL Test3a delete");
		}		
	}

	/**
	 * Test getAccounts() executes.
	 */
	@Test  
    public void Test4a () {
		ContactsReader reader = new ContactsReader();
		
		try {
			Account truth = reader.getAccount(SECOND_ACCOUNT.getUsername());
			assertEquals(truth.toString(), SECOND_ACCOUNT.toString());
		} catch (Exception e) {
			System.out.println(e + " FAIL Test4a add");
		}	
	}

}
