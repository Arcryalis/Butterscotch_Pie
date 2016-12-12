import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
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
	private JTextArea textArea;

	public ChatGUI() {

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
		m_frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(m_frmChat, true);

		m_messagetxtfld = new JTextField();
		m_messagetxtfld.setColumns(10);
		
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
					textArea.append(/* getusername */ m_messagetxtfld.getText() + System.lineSeparator());
					m_messagetxtfld.setText("");
				}
			}
		});

		textArea = new JTextArea();
		textArea.setSize(320, 120);
		textArea.setBackground(new Color(240, 255, 240));
		textArea.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		textArea.setLineWrap(true);
		textArea.setEditable(false);

		JScrollPane scrollBar = new JScrollPane();
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBar.setRowHeaderView(textArea);

		m_frmChat.getContentPane().add(scrollBar);
		
		/*
		 * Group Layout for the frame
		 */
		GroupLayout groupLayout = new GroupLayout(m_frmChat.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(70).addComponent(scrollBar,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(10)
						.addComponent(m_messagetxtfld, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(btnSend)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(26)
				.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE).addGap(28)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(m_messagetxtfld,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSend))));
		m_frmChat.getContentPane().setLayout(groupLayout);
	}

	/*
	 * main method for testing purposes
	 */
	/*public static void main(String[] args) {
		new ChatGUI().displayGUI();
	}*/
}
