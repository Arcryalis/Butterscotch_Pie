import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import login.*;

public class SearchGUI {
	
	private JFrame m_frame;
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
		
		txtSearch = new JTextField();
		txtSearch.setBounds(5, 6, 200, 30);
		txtSearch.setColumns(10);
		
		m_usersList = new JList(m_users);
		m_usersList.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		m_usersList.addMouseListener(m_addMouse);
		m_usersPane = new JScrollPane(m_usersList);
		m_usersPane.setBounds(5, 40, 200, 335);
		
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
		
		m_frame.getContentPane().add(txtSearch);
		m_frame.getContentPane().add(btnSearch);
		m_frame.getContentPane().add(m_usersPane);
		m_frame.getContentPane().add(m_lblImgProf);
		m_frame.getContentPane().add(m_btnAdd);
		
	}
	
	public boolean search() {
		String keyword = txtSearch.getText();
		
		// Call to AccountReader
		
		return true;
	}
	
	public boolean sendRequest() {
		MainProgram.fetchRequests();
		
		int index = m_usersList.getSelectedIndex();
		// Call to AccountReader
		
		
		return true;
	}
	
	public boolean showUser(int index) {
		m_btnAdd.setEnabled(true);
		
		Account target;
		// call to ContactsReader and assign result to target
/**/	target = new Account(m_users[index], "", "MyFirstName" + index, "MyLastName" + index, "01792-" + index);	/**/
		
		m_lblImgProf.setIcon(new ImageIcon("res/profileImg.png"));
		
		// Update window
		m_frame.setVisible(true);
		
		return true;
		
	}
}
