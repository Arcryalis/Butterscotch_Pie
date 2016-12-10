import java.awt.EventQueue;
import java.util.LinkedList;

import filereader.ContactsReader;
import login.*;

/**
 * MainProgram.java
 * @author Yiu Ting Lai
 */

public class MainProgram {
	
	private static boolean m_LoginState = false;
	private static Account m_ac;
	private static ContactList m_cl;
	private static RequestList m_rl;
	
	public static boolean isM_LoginState() {	return m_LoginState;	}
	public static Account getM_ac() {			return m_ac;			}
	public static ContactList getM_cl() {		return m_cl;			}
	public static RequestList getM_rl() {		return m_rl;			}

	public static void setM_LoginState(boolean m_LoginState) {	MainProgram.m_LoginState = m_LoginState;	}
	public static void setM_ac(Account m_ac) {					MainProgram.m_ac = m_ac;					}
	public static void setM_cl(ContactList m_cl) {				MainProgram.m_cl = m_cl;					}
	public static void setM_rl(RequestList m_rl) {				MainProgram.m_rl = m_rl;					}
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		testStups();
		
		// spawn LoginGUI window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//LoginGUI window = new LoginGUI();
					HomeGUI window = new HomeGUI();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void logout() {
		m_LoginState = false;
		
		// spawn LoginGUI window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//LoginGUI window = new LoginGUI();
					HomeGUI window = new HomeGUI();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void fetchContacts() {
		// Call to ContactsReader
		try {
			m_cl = new ContactList();
			LinkedList<String> contacts = new ContactsReader().getContactsOf(m_ac);
			
			for(int i=0; i<contacts.size(); i++) {
				m_cl.add(new Contact(contacts.get(i)));
			}
			
		}catch(Exception e) {
			for(int i = 2; i < 30; i++) {
				m_cl.add(new Contact("NullAccount_" + i));
			}
		}
	}
	
	public static void fetchRequests() {
		// Call to ContactsReader
		try {
			m_rl = new RequestList();
			LinkedList<String> contacts = new ContactsReader().getRequests(m_ac.getUsername(), true);
			
			for(int i=0; i<contacts.size(); i++) {
				m_rl.add(new Request(contacts.get(i), true));
			}
			
		}catch(Exception e) {
			for(int i = 30; i < 40; i++) {
				m_rl.add(new Request("NullAccount_" + i, true));
			}
		}
	}
	
	public static void testStups() {
		System.out.print("MainProgram: Using testing Stups");
		
		m_ac = new Account("Sev", "p2", "Seven", "s.name 4", "59648");
		m_cl = new ContactList();
		m_rl = new RequestList();
		m_LoginState = true;
		
		fetchContacts();
		fetchRequests();
		
	}
}
