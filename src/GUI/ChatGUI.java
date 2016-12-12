import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import chat.Chat;
import chat.Message;
import login.Account;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.Toolkit;

/**
 * ChatGUI.java
 * @author Ahmed Elmi
 */
public class ChatGUI extends GUI {

	private JFrame m_frmChat;
	private JTextField m_messagetxtfld;
	private JTextArea m_textArea;
	private Chat m_chatObject;
	private String m_roomname;
	
	public String getM_roomname() {
		return m_roomname;
	}

	public void setM_roomname(String roomname) {
		this.m_roomname = roomname;
	}

	public ChatGUI(String roomname) {
		this.m_roomname = roomname;
	}

	//Implementing the ChatGUI
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void makeGUI() {
		m_frmChat = new JFrame();
		/*
		 * Setting an icon for the frame
		 */
		m_frmChat.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"/Skypertawe Icon.png"));
		m_frmChat.setTitle("Chat");
		m_frmChat.getContentPane().setBackground(new Color(0, 238, 190));
		m_frmChat.setBounds(100, 100, 450, 250);
		m_frmChat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(m_frmChat, true);

		m_messagetxtfld = new JTextField();
		m_messagetxtfld.setColumns(10);
		
		String chatlocation = m_roomname;
		boolean isChatEliable = true;
		m_chatObject = new Chat(chatlocation,isChatEliable);
		/*
		 * Send button takes string from textfield and appends it to the text area
		 */
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nothing = "";
				if (m_messagetxtfld.getText().equals(nothing)) {
					return;
				} else {
					send();
					//this.m_chatObject.newTextMessage( m_messagetxtfld.getText(), /*getusername*/);
				}
			}
		});

		m_textArea = new JTextArea();
		m_textArea.setEditable(false);
		m_textArea.setSize(320, 120);
		m_textArea.setBackground(new Color(240, 255, 240));
		m_textArea.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		m_textArea.setLineWrap(true);
		
		//for(int i=0; i < m_chatObject.getListOfMessages().size(); i++) {
		//	m_textArea.append(((Message)(m_chatObject.getListOfMessages().get(i))).getSender() + ": " + ((Message)(m_chatObject.getListOfMessages().get(i))).getMessageContent() + System.lineSeparator());
		//}

		JScrollPane scrollBar = new JScrollPane();
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBar.setRowHeaderView(m_textArea);
		scrollBar.setViewportView(m_textArea);

		m_frmChat.getContentPane().add(scrollBar);
		
		/*
		 * Group Layout for the frame
		 */
		GroupLayout groupLayout = new GroupLayout(m_frmChat.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(m_messagetxtfld, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSend))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(59)
							.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(2, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(m_messagetxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSend)))
		);
		m_frmChat.getContentPane().setLayout(groupLayout);
	}
	
	public void send() {
		m_textArea.append(MainProgram.getM_ac().getUsername() + ": " + m_messagetxtfld.getText() + System.lineSeparator());
		
		//UPDATE DB
		m_chatObject.newTextMessage(m_messagetxtfld.getText(), MainProgram.getM_ac().getUsername());
		
		m_messagetxtfld.setText("");
	}

	/*
	 * main method for testing purposes
	 */
	public static void main(String[] args) {
		MainProgram.setM_ac(new Account("Sev", "p2", "S", "ev", "01792012345"));
		new ChatGUI("room1").displayGUI();
	}
}