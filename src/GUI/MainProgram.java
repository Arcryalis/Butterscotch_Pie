import java.awt.EventQueue;

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
	
	public static void main(String[] args) {
		
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
	}
	
	public static void fetchRequest() {
		// Call to ContactsReader
	}
	
	public static void testStups() {
		System.out.print("MainProgram: Using testing Stups");
		
		m_ac = new Account("testAccount_1", "1234", "MyFirstName", "MyLastName", "01792-123-456");
		m_cl = new ContactList();
		m_rl = new RequestList();
		m_LoginState = true;
		
		for(int i = 2; i < 30; i++) {
			m_cl.add(new Contact("testAccount_" + i));
		}
		
		for(int i = 30; i < 40; i++) {
			m_rl.add(new Request("testAccount_" + i, true));
		}
		
	}
}
