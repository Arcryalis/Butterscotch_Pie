package filereader_tests;


import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import filereader.AccountReader;
import login.Account;

public class AccountReaderTests {
	
	private static final Account FIRST_ACCOUNT = new Account("Sev", "p2", "f.name 1", "s.name 1", "1792");
	private static final Account SECOND_ACCOUNT = new Account("u1", "p1", "f.name 1", "s.name 1", "01792");
	private static final Account THIRD_ACCOUNT = new Account("user.t", "pass.t", "f.t.names", "s.t.names", "2016.t");

	/**
	 * Test readAll() executes.
	 */
	@Test  
    public void Test1a () {
		AccountReader reader = new AccountReader();
		try {
			reader.read();
		} catch (Exception e) {
			System.out.println(e + " FAIL Test1a");
		}
	}
	
	/**
	 * Test readAll() returns correct head.
	 */
	@Test  
    public void Test1b() {
		AccountReader reader = new AccountReader();
		
		try {
			assertEquals(FIRST_ACCOUNT.getUsername(), reader.read().peek().getUsername());
		} catch (Exception e) {
			System.out.println(e + " FAIL Test1b");
		}
	}

	/**
	 * Test  writeNew() and delete()
	 */
	@Test  
    public void Test2a () {
		
		AccountReader reader = new AccountReader();
		
		Account testAccount = THIRD_ACCOUNT;
		try {
			reader.write(testAccount);
		} catch (Exception e) {
			System.out.println(e + "FAIL Test2a write");
		}
		
		try {
			reader.delete(testAccount.getUsername());
		} catch (Exception e) {
			System.out.println(e + "FAIL Test2a delete");
		}
	}

	/**
	 * Tests update()
	 */
	@Test  
    public void Test3a () {
		AccountReader reader = new AccountReader();
		
		Account testAccount = SECOND_ACCOUNT;
		testAccount.setDob(new Date());
		testAccount.setCity("New world");
		testAccount.setProfilePic("Mrglglgl.pic");
		testAccount.setDtLastLogin(new Date());
		
		try {
			reader.update(testAccount);
		} catch (Exception e) {
			System.out.println(e + "FAIL Test3a update");
		}

	}
	
	/**
	 * Tests checkUserPass()
	 */
	@Test  
    public void Test4a () {
		AccountReader reader = new AccountReader();
		
		try {
			assertEquals(reader.checkUserPass("Sev","p2"), true);
		} catch (Exception e) {
			System.out.println(e + "FAIL Test4a checkUserPass");
		}

	}
	
	/**
	 * Tests searchFor()
	 */
	@Test  
    public void Test5a () {
		AccountReader reader = new AccountReader();
		
		try {
			Boolean truth = reader.searchFor("u").contains("u2");
			assertEquals(truth,true);
		} catch (Exception e) {
			System.out.println(e + "FAIL Test5a searchFor");
		}

	}
	
	
}
