import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.AbstractListModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import login.*;

/**
 * HomeGUI.java
 * @author Yiu Ting Lai
 */

public class HomeGUI {
	// GUI Components
	private JFrame m_frame;
	private JButton m_btnSearch;
	private JButton m_btnRequest;
	private JLabel m_lblLoginUser;
	private JButton m_btnLogoff;
	private JScrollPane m_contactsPane;
	private JList m_contactsList;
	private ActionListener m_searchAction;
	private ActionListener m_requestAction;
	private ActionListener m_logoffAction;
	private MouseListener m_contactMouse;

	private JPanel m_contactPanel;
	private JLabel m_lblImgProf;
	private JLabel m_lblUsername;
	private JLabel m_lblFirstName;
	private JLabel m_lblLastName;
	private JLabel m_lblPhone;
	private JButton m_btnSendMsg;
	private JButton m_btnRemove;
	private ActionListener m_sendMsgAction;
	private ActionListener m_removeAction;

	// Data Components
	private String m_account;
	private String[] m_contacts;
	private int m_noOfRequest;
	private int m_clicksCount = 0;
	
	
	
	/**
	 * Create the application.
	 */
	public HomeGUI() {
		initialize();
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		m_frame = new JFrame();
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.setBounds(100, 100, 800, 600);
		m_frame.getContentPane().setLayout(null);
		
		initializeData();
		initializeMainGUI();
		initializeContactGUI();
		
		m_frame.setVisible(true);

	}
	
	public void initializeData() {
		m_account = MainProgram.getM_ac().getUsername();
		m_contacts = new String[MainProgram.getM_cl().size()];
		for(int i = 0; i < m_contacts.length; i++) {
			m_contacts[i] = ((Contact)MainProgram.getM_cl().get(i)).getM_username();
		}
		m_noOfRequest = MainProgram.getM_rl().size();
	}
	
	private void initializeMainGUI() {
		m_searchAction = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Call to HomeGUI.search for check requests
				System.out.println("m_requestAction: Clicked on: request button");
				search();
			}
		};
		
		m_btnSearch = new JButton("");
		m_btnSearch.setBounds(25, 6, 50, 45);
		m_btnSearch.setIcon(new ImageIcon("res/search.png"));
		m_btnSearch.addActionListener(m_searchAction);
		
		m_requestAction = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Call to HomeGUI.chkRequest for check requests
				System.out.println("m_requestAction: Clicked on: request button");
				chkRequest();
			}
		};

		m_btnRequest = new JButton(m_noOfRequest + "");
		m_btnRequest.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		m_btnRequest.setIcon(new ImageIcon("res/newRequest.png"));
		m_btnRequest.setBounds(82, 6, 100, 45);
		m_btnRequest.addActionListener(m_requestAction);
		if(m_noOfRequest == 0) {
			m_btnRequest.setEnabled(false);
		}

		m_lblLoginUser = new JLabel("Welcome, " + m_account);
		m_lblLoginUser.setBounds(210, 21, 250, 20);
		
		m_logoffAction = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Call to HomeGUI.logoff for log off
				System.out.println("m_logoffAction: Clicked on: Log off button");
				logoff();
			}
		};

		m_btnLogoff = new JButton("Log Off");
		m_btnLogoff.setIcon(new ImageIcon("res/logout.png"));
		m_btnLogoff.setBounds(658, 10, 117, 45);
		m_btnLogoff.addActionListener(m_logoffAction);

		m_contactMouse = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				int index = theList.locationToIndex(mouseEvent.getPoint());
				Object o = theList.getModel().getElementAt(index);
				if (mouseEvent.getClickCount() == 2) {
					if (index >= 0) {
						// Call to HomeGUI.sendMsg() for send message
						System.out.println("m_contactMouse: Double-clicked on: " + o.toString());
						sendMsg();
					}
					
				}else if (mouseEvent.getClickCount() == 1) {
					if (index >= 0) {
						// Call to HomeGUI.showContact() for show contact
						System.out.println("m_contactMouse: Single-clicked on: " + o.toString());
						showContact(index);
					}
					
				}
			}
		};

		m_contactsList = new JList(m_contacts);
		m_contactsList.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		m_contactsList.addMouseListener(m_contactMouse);
		m_contactsPane = new JScrollPane(m_contactsList);
		m_contactsPane.setBounds(25, 55, 250, 500);
		
		m_contactPanel = new JPanel();
		m_contactPanel.setBounds(300, 55, 475, 500);
		
		m_frame.getContentPane().add(m_btnSearch);
		m_frame.getContentPane().add(m_btnRequest);
		m_frame.getContentPane().add(m_lblLoginUser);
		m_frame.getContentPane().add(m_btnLogoff);
		m_frame.getContentPane().add(m_contactsPane);
		//m_frame.getContentPane().add(m_contactPanel);
		
	}
	
	private void initializeContactGUI() {
		m_frame.getContentPane().add(m_contactPanel);
		m_contactPanel.setLayout(null);
		
		m_contactsList.setSelectedIndex(-1);
		
		m_lblImgProf = new JLabel();
		m_lblImgProf.setBounds(5, 5, 250, 250);
		
		m_lblUsername = new JLabel();
		m_lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		m_lblUsername.setBounds(5, 275, 250, 30);
		m_lblUsername.setForeground(new Color(112, 128, 144));
				
		m_lblFirstName = new JLabel();
		m_lblFirstName.setBounds(5, 350, 200, 20);
		m_lblFirstName.setForeground(new Color(112, 128, 144));
						
		m_lblLastName = new JLabel();
		m_lblLastName.setBounds(250, 350, 200, 20);
		m_lblLastName.setForeground(new Color(112, 128, 144));
								
		m_lblPhone = new JLabel();
		m_lblPhone.setBounds(5, 400, 200, 20);
		m_lblPhone.setForeground(new Color(112, 128, 144));
		
		m_removeAction = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Call to HomeGUI.sendMsg for send message
				System.out.println("m_removeAction: Clicked on: remove button");
				remove();
			}
		};
		
		m_btnRemove = new JButton("Remove");
		m_btnRemove.setBounds(5, 460, 200, 35);
		m_btnRemove.setForeground(Color.RED);
		m_btnRemove.setBackground(new Color(255, 0, 0));
		m_btnRemove.addActionListener(m_removeAction);
		m_btnRemove.setEnabled(false);
		
		m_sendMsgAction = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Call to HomeGUI.sendMsg for send message
				System.out.println("m_sendMsgAction: Clicked on: send button");
				sendMsg();
			}
		};
		
		m_btnSendMsg = new JButton("Send Messages");
		m_btnSendMsg.setBounds(270, 460, 200, 35);
		m_btnSendMsg.addActionListener(m_sendMsgAction);
		m_btnSendMsg.setEnabled(false);
		
		m_contactPanel.add(m_lblImgProf);
		m_contactPanel.add(m_lblUsername);
		m_contactPanel.add(m_lblFirstName);
		m_contactPanel.add(m_lblLastName);
		m_contactPanel.add(m_lblPhone);
		m_contactPanel.add(m_btnRemove);
		m_contactPanel.add(m_btnSendMsg);
		
	}
	
	
	
	public void logoff() {
		MainProgram.logout();
		m_frame.dispose();
	}
	
	
	
	public void search() {
		System.out.println("search(): spawn a SearchGUI window");
		
		// call to SearchGUI to spawn a window for searching
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchGUI window = new SearchGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public void chkRequest() {
		System.out.println("chkRequest(): spawn a RequestGUI window");
		
		HomeGUI me = this;
		
		// call to RequestGUI to spawn a window for searching
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RequestGUI window = new RequestGUI(me);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public boolean showContact(int index) {
		m_lblUsername.setText(m_contacts[index]);
		
		m_btnRemove.setEnabled(true);
		m_btnSendMsg.setEnabled(true);
		m_frame.getContentPane().add(m_contactPanel);
		
		Account target;
		// call to ContactsReader and assign result to target
/**/	target = new Account(m_contacts[index], "", "MyFirstName" + index, "MyLastName" + index, "01792-" + index);	/**/
		
		m_lblPhone.setText(target.getUkPhoneNo());
		m_lblFirstName.setText("First Name: " + target.getFirstName());
		m_lblLastName.setText("Last Name: " + target.getLastName());
		m_lblImgProf.setIcon(new ImageIcon("res/profileImg.png"));
		
		// Update window
		m_frame.setVisible(true);
		
		return true;
	}
	
	
	public boolean sendMsg() {
		int target = m_contactsList.getSelectedIndex();
		//Call to ChatGUI for send message
		System.out.println("sendMsg: " + m_contacts[target]);
		
		return true;
	}
	
	
	
	public boolean remove() {
		
		int target = m_contactsList.getSelectedIndex();
		
		int n = JOptionPane.showConfirmDialog(
			    m_frame,
			    "Do you confirm to remove \n" + 
			    m_contacts[target] + "\n" +
			    "from your contact list? \n" +
			    "This action cannot be undo.",
			    "Remove confirmation",
			    JOptionPane.YES_NO_OPTION);
		
		if(n == 0) {
			System.out.println("remove: " + m_contacts[target]);
			//Call to <DB> for remove
/**/		//Call to <DB> for remove	/**/
			
			MainProgram.fetchContacts();
			initializeData();
			
			m_btnRemove.setEnabled(false);
			m_btnSendMsg.setEnabled(false);
		}
		
		
		return true;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeGUI window = new HomeGUI();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}