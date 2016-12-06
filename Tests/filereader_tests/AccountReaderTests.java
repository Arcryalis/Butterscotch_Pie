package filereader_tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import filereader.AccountReader;
import login.Account;

public class AccountReaderTests {
	
	private static final Account FIRST_ACCOUNT = new Account("Sev", "p1", "f.name 1", "s.name 1", "1792");

	/**
	 * Test readAll() executes.
	 */
	@Test  
    public void Test1a () {
		AccountReader reader = new AccountReader();
		try {
			reader.read();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Test readAll() returns correct head.
	 */
	@Test  
    public void Test1b() {
		AccountReader reader = new AccountReader();
		
		try {
			assertEquals(FIRST_ACCOUNT.toString(), reader.read().peek().toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	/**
	 * Test readNameList()
	 */
	@Test  
    public void Test2a () {
		AccountReader reader = new AccountReader();
		
		LinkedList<String> nameList = new LinkedList<String>();
		nameList.add("John");
		nameList.add("Tim");
		
		try {
			reader.readNameList(nameList);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Tests writeNew()
	 */
	@Test  
    public void Test3a () {
		AccountReader reader = new AccountReader();
		
		//ACCOUNT STORES AN INT - WHICH CHOPS OFF THE 0 in 01792
		Account testAccount = new Account("user", "pass", "f.names", "s.names", "2016");
		try {
			reader.writeNew(testAccount);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
