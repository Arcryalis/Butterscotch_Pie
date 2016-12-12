
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

import java.awt.Color;
import java.awt.Component;

/**
 * SearchGUI.java
 * @author Yiu Ting Lai
 * @author Osian Smith 
 * @author Ahmed Elmi
 * @version .5
 * NOT FUNCIONAL YET!!!!!!
 */

public class NewGroupGUI {
	
	private JFrame m_frame;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JScrollPane m_usersPane;
	private JList m_usersList;
	private JLabel m_lblImgProf;
	private JButton m_btnCanncel;
	private ActionListener m_searchAction;
	private ActionListener m_addAction;
	private ActionListener m_Canncel;
	private MouseAdapter m_addMouse;
	
	private String m_account;
	private String[] m_users;
	private JButton m_btnAdd;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	
	private LinkedList<String> m_group = new LinkedList<String>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGroupGUI window = new NewGroupGUI();
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
	public NewGroupGUI() {
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
		
		m_users = new String[0];
		
	}
	
	private void initializeMainGUI() {
		txtSearch = new JTextField();
		txtSearch.setBounds(5, 6, 200, 30);
		txtSearch.setColumns(10);
		
		String txt = txtSearch.getText();
		m_searchAction = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					try {
						//LinkedList<String> list = new ContactsReader().getContactsOf(MainProgram.getM_ac().getUsername());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Call to RequestGUI.accept for accept request
				System.out.println("m_addAction: Clicked on: accept button");
				search();
			}
		};
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(225, 7, 75, 29);
		btnSearch.addActionListener(m_searchAction);
		
		
		
		m_lblImgProf = new JLabel();
		m_lblImgProf.setBounds(222, 47, 235, 233);
		
		m_addAction = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Call to RequestGUI.accept for accept request
				System.out.println("m_addAction: Clicked on: accept button");
				sendRequest();
			}
		};
		
		
		//creates canncel button
		this.m_btnCanncel = new JButton("WhiteBoard");
		m_btnCanncel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		this.m_btnCanncel.setBounds(300, 293, 89, 45);
		this.m_btnCanncel.setEnabled(true);
		
		
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
		m_frame.getContentPane().add(m_btnCanncel);
		
		btnNewButton = new JButton("Send");
		btnNewButton.setBounds(399, 296, 75, 39);
		m_frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBounds(211, 293, 80, 45);
		m_frame.getContentPane().add(btnNewButton_1);
		m_btnCanncel.addMouseListener(m_addMouse);
		m_frame.setVisible(true);
		
	}
	
	public boolean search() {
		String keyword = txtSearch.getText();
		
		// Call to AccountReader
		LinkedList<String> users;
		try{
			
		}catch(Exception e) {
			System.out.println("SearchGUI: search Exception");
			users = new LinkedList<String>();
		}
		
	
	
		
		m_usersList.setListData(m_users);
		
		return true;
	}
	
	public boolean sendRequest() {
		m_btnAdd.setEnabled(false);
		
		
		int index = m_usersList.getSelectedIndex();
		// Call to AccountReader
		int target = m_usersList.getSelectedIndex();
		
		try {
		
		}catch(Exception e) {
			System.out.println("send request: " + m_users[target] + " <<<Exception!>>>");
		}
		
	
		initializeData();
		
		
		
		return true;
	}
	
	public void addUser(String user){
		try{
			this.m_group = (new ContactsReader().getContactsOf(user));
		}catch(Exception e)  {
			
		}
	}

	public boolean showUser(int index) {
		m_btnAdd.setEnabled(true);
		
		Account target;
		// call to ContactsReader and assign result to target
		try{
		
	
		}catch (Exception e){
			target = new Account("NO NAME", "", "NULL", "NULL", "NULL");
	
		}

	
		
		// Update window
		m_frame.setVisible(true);
		
		return true;
		
	}
}