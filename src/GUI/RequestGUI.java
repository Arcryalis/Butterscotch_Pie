import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import filereader.ContactsReader;

import javax.swing.JPanel;

import login.*;

/**
 * RequestGUI.java
 * @author Yiu Ting Lai
 */

public class RequestGUI {

	private JFrame m_frame;
	private JLabel m_lblBottomBG;
	private JScrollPane m_requestsPane;
	private JList m_requestsList;
	private JLabel m_lblImgProf;
	private JButton m_btnAccept;
	private JButton m_btnCancel;
	private ActionListener m_acceptAction;
	private ActionListener m_cancelAction;
	private MouseAdapter m_requestMouse;
	
	private HomeGUI parent;
	
	private String m_account;
	private String[] m_requests;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public RequestGUI(HomeGUI parent) {
		this.parent = parent;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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
		m_requests = new String[MainProgram.getM_rl().size()];
		for(int i = 0; i < m_requests.length; i++) {
			if(((Request)MainProgram.getM_rl().get(i)).getM_isRequestToMe()) {
				m_requests[i] = ((Request)MainProgram.getM_rl().get(i)).getM_username();
			}
		}
	}
	
	private void initializeMainGUI() {
		m_lblBottomBG = new JLabel("");
		m_lblBottomBG.setBackground(new Color(255, 255, 255));
		m_lblBottomBG.setBounds(0, 0, 500, 375);
		m_lblBottomBG.setIcon(new ImageIcon("res/requestBG.png"));
		
		m_lblImgProf = new JLabel();
		m_lblImgProf.setBounds(245, 5, 250, 250);
		
		m_acceptAction = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Call to RequestGUI.accept for accept request
				System.out.println("m_acceptAction: Clicked on: accept button");
				accept();
			}
		};
		
		m_btnAccept = new JButton("Accept");
		m_btnAccept.setIcon(new ImageIcon("res/add.png"));
		m_btnAccept.setBounds(289, 325, 100, 45);
		m_btnAccept.addActionListener(m_acceptAction);
		m_btnAccept.setEnabled(false);
		
		m_cancelAction = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// Call to RequestGUI.accept for accept request
				System.out.println("m_cancelAction: Clicked on: cancel button");
				cancel();
			}
		};
		
		m_btnCancel = new JButton("Cancel");
		m_btnCancel.setIcon(new ImageIcon("res/remove.png"));
		m_btnCancel.setBounds(394, 325, 100, 45);
		m_btnCancel.addActionListener(m_cancelAction);
		m_btnCancel.setEnabled(false);
		
		m_requestMouse = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				int index = theList.locationToIndex(mouseEvent.getPoint());
				Object o = theList.getModel().getElementAt(index);
				if (mouseEvent.getClickCount() == 1) {
					if (index >= 0) {
						// Call to HomeGUI.showContact() for show contact
						System.out.println("m_contactMouse: Single-clicked on: " + o.toString());
						showRequest(index);
						
					}
				}
				
			}
		};
		
		m_requestsList = new JList(m_requests);
		m_requestsList.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		m_requestsList.addMouseListener(m_requestMouse);
		m_requestsPane = new JScrollPane(m_requestsList);
		m_requestsPane.setBounds(5, 5, 200, 370);
		
		m_frame.getContentPane().add(m_requestsPane);
		m_frame.getContentPane().add(m_lblImgProf);
		m_frame.getContentPane().add(m_btnAccept);
		m_frame.getContentPane().add(m_btnCancel);
		m_frame.getContentPane().add(m_lblBottomBG);
		
	}
	
	public boolean accept() {
		
		int target = m_requestsList.getSelectedIndex();
		
		try {
			new ContactsReader().updateContacts(m_requests[target], MainProgram.getM_ac().getUsername(), false);
		}catch(Exception e) {
			System.out.println("remove: " + m_requests[target] + " <<<Exception!>>>");
		}
		
		MainProgram.fetchContacts();
		MainProgram.fetchRequests();
		initializeData();
		m_requestsList.setListData(m_requests);
		m_requestsList.setSelectedIndex(-1);
		
		parent.refreshGUI();
		
		m_btnAccept.setEnabled(false);
		m_btnCancel.setEnabled(false);
		
		return true;
	}
	
	public boolean cancel() {
		
		int target = m_requestsList.getSelectedIndex();
		
		try {
			new ContactsReader().delete(MainProgram.getM_ac().getUsername(), m_requests[target]);
		}catch(Exception e) {
			System.out.println("remove: " + m_requests[target] + " <<<Exception!>>>");
		}
		
		MainProgram.fetchRequests();
		initializeData();
		m_requestsList.setListData(m_requests);
		m_requestsList.setSelectedIndex(-1);
		parent.refreshGUI();
		
		m_btnAccept.setEnabled(false);
		m_btnCancel.setEnabled(false);
		
		return true;
	}
	
	public boolean showRequest(int index) {
		m_btnAccept.setEnabled(true);
		m_btnCancel.setEnabled(true);
		
		Account target;
		// call to ContactsReader and assign result to target
/**/	try{
			target = new ContactsReader().getAccount(m_requests[index]);
			
		}catch (Exception e){
			target = new Account("NO NAME", "", "NULL", "NULL", "NULL");
			
		}
/**/
		target.setProfilePic("res/profileImg.png");
		m_lblImgProf.setIcon(new ImageIcon(target.getProfilePic()));
		
		
		// Update window
		m_frame.setVisible(true);
		
		return true;
		
	}
}
