import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import filereader.AccountReader;
import filereader.ContactsReader;
import login.*;
import java.awt.Color;

/**
 * SearchGUI.java
 * @author Yiu Ting Lai
 */

public class SearchGUI {
	
	private JFrame m_frame;
	private JLabel m_lblBottomBG;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JScrollPane m_usersPane;
	private JList m_usersList;
	private JLabel m_lblImgProf;
	private JButton m_btnAdd;
	private ActionListener m_searchAction;
	private ActionListener m_addAction;
	private MouseAdapter m_addMouse;
	
	private String m_account;
	private String[] m_users;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchGUI window = new SearchGUI();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		m_frame = new JFrame();
		m_frame.setBounds(100, 100, 500, 400);
		m_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		m_frame.getContentPane().setLayout(null);
		
		initializeData();
		initializeMainGUI();
	    
	    m_frame.setVisible(true);
	}
	
	private void initializeData() {
		m_account = MainProgram.getM_ac().getUsername();
		m_users = new String[0];
		
	}
	
	private void initializeMainGUI() {
		m_lblBottomBG = new JLabel("");
		m_lblBottomBG.setBackground(new Color(255, 255, 255));
		m_lblBottomBG.setBounds(0, 0, 500, 375);
		m_lblBottomBG.setIcon(new ImageIcon("res/searchBG.png"));
		
		m_searchAction = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Call to RequestGUI.accept for accept request
				System.out.println("m_addAction: Clicked on: accept button");
				search();
			}
		};
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(205, 8, 75, 29);
		btnSearch.addActionListener(m_searchAction);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(5, 6, 200, 30);
		txtSearch.setColumns(10);
		
		m_lblImgProf = new JLabel();
		m_lblImgProf.setBounds(245, 40, 250, 250);
		
		m_addAction = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Call to RequestGUI.accept for accept request
				System.out.println("m_addAction: Clicked on: accept button");
				sendRequest();
			}
		};
		
		m_btnAdd = new JButton("Add");
		m_btnAdd.setIcon(new ImageIcon("res/add.png"));
		m_btnAdd.setBounds(395, 330, 100, 45);
		m_btnAdd.addActionListener(m_addAction);
		m_btnAdd.setEnabled(false);
		
		m_addMouse = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				int index = theList.locationToIndex(mouseEvent.getPoint());
				Object o = theList.getModel().getElementAt(index);
				if (mouseEvent.getClickCount() == 1) {
					if (index >= 0) {
						// Call to HomeGUI.showContact() for show contact
						System.out.println("m_contactMouse: Single-clicked on: " + o.toString());
						showUser(index);
						
					}
				}
				
			}
		};
		
		m_usersList = new JList(m_users);
		m_usersList.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		m_usersList.addMouseListener(m_addMouse);
		m_usersPane = new JScrollPane(m_usersList);
		m_usersPane.setBounds(5, 40, 200, 335);
		
		m_frame.getContentPane().add(txtSearch);
		m_frame.getContentPane().add(btnSearch);
		m_frame.getContentPane().add(m_usersPane);
		m_frame.getContentPane().add(m_lblImgProf);
		m_frame.getContentPane().add(m_btnAdd);
		m_frame.getContentPane().add(m_lblBottomBG);
		
	}
	
	public boolean search() {
		String keyword = txtSearch.getText();
		
		// Call to AccountReader
		LinkedList<String> users;
		try{
			users = new AccountReader().searchFor(keyword);
		}catch(Exception e) {
			System.out.println("SearchGUI: search Exception");
			users = new LinkedList<String>();
		}
		
		m_users = new String[users.size()];
		
		for(int i=0; i< users.size(); i++) {
			m_users[i] = users.get(i);
		}
		
		m_usersList.setListData(m_users);
		
		return true;
	}
	
	public boolean sendRequest() {
		m_btnAdd.setEnabled(false);
		
		MainProgram.fetchRequests();
		
		int index = m_usersList.getSelectedIndex();
		// Call to AccountReader
		int target = m_usersList.getSelectedIndex();
		
		try {
			new ContactsReader().updateContacts(MainProgram.getM_ac().getUsername(), m_users[target], true);
		}catch(Exception e) {
			System.out.println("send request: " + m_users[target] + " <<<Exception!>>>");
		}
		
		MainProgram.fetchContacts();
		MainProgram.fetchRequests();
		initializeData();
		
		
		
		return true;
	}
	
	public boolean showUser(int index) {
		m_btnAdd.setEnabled(true);
		
		Account target;
		// call to ContactsReader and assign result to target
		try{
			target = new ContactsReader().getAccount(m_users[index]);
	
		}catch (Exception e){
			target = new Account("NO NAME", "", "NULL", "NULL", "NULL");
	
		}

		target.setProfilePic("res/profileImg.png");
		m_lblImgProf.setIcon(new ImageIcon(target.getProfilePic()));
		
		// Update window
		m_frame.setVisible(true);
		
		return true;
		
	}
}
