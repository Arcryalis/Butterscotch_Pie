import java.awt.Image;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import filereader.AccountReader;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class LoginGUI extends GUI{

	private JFrame m_frmLogIn;
	private JTextField m_usernametxtfld;
	private JPasswordField m_passwordpwrdfld;
	private JButton m_btnLogIn;
	private JButton m_btnResetPassword;
	private JButton m_btnSignUp;
	private JLabel m_lblLogo;	
	
	public LoginGUI() {
		
	}

	
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void makeGUI() {
		m_frmLogIn = new JFrame();
		setVisible(m_frmLogIn, true);
		m_frmLogIn.setIconImage(Toolkit.getDefaultToolkit().getImage("/res/Skypertawe Icon.png"));
		m_frmLogIn.getContentPane().setBackground(new Color(0, 238, 190));
		
		JLabel lblUsername = new JLabel("Username");
		
		JLabel lblPassword = new JLabel("Password");
		
		m_usernametxtfld = new JTextField();
		m_usernametxtfld.setColumns(10);
		
		m_passwordpwrdfld = new JPasswordField();
		
		m_btnLogIn = new JButton("Log In");
		m_btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uname = m_usernametxtfld.getText().toString();
				String pass = String.valueOf(m_passwordpwrdfld.getPassword());
								
				if(uname.equals("") || m_passwordpwrdfld.getPassword().length == 0){
					JOptionPane.showMessageDialog(null, "Please complete all fields");
				}
				else{
					try {
						if(new AccountReader().checkUserPass(uname, pass)){
							//homeGUI
							login();
							//JOptionPane.showMessageDialog(null, "HomeGUI");
						}
						else{
							JOptionPane.showMessageDialog(null, "Incorrect Username/Password");
							System.out.println("Username: " + uname);
							System.out.println("Password: " + pass);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		m_btnResetPassword = new JButton("Reset Password");
		m_btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_frmLogIn.dispose();
				new ResetPasswordGUI().displayGUI();
			}
		});
		
		m_btnSignUp = new JButton("Sign Up");
		m_btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_frmLogIn.dispose();
				new SignupGUI().displayGUI();
			}
		});
		
		m_lblLogo = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("/res/skypertawelogo.png")).getImage();
		m_lblLogo.setIcon(new ImageIcon(logo));
		GroupLayout groupLayout = new GroupLayout(m_frmLogIn.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(165)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(m_btnResetPassword)
								.addComponent(m_btnLogIn)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(m_btnSignUp)
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPassword)
									.addGap(18)
									.addComponent(m_passwordpwrdfld, 86, 86, 86))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUsername)
									.addGap(18)
									.addComponent(m_usernametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(m_lblLogo, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(72, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(m_btnSignUp)
						.addComponent(m_lblLogo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(m_usernametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(m_passwordpwrdfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(m_btnLogIn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(m_btnResetPassword)
					.addGap(26))
		);
		m_frmLogIn.getContentPane().setLayout(groupLayout);
		m_frmLogIn.setTitle("Log in");
		m_frmLogIn.setBounds(100, 100, 450, 300);
		m_frmLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void login() {
		try{
			MainProgram.setM_ac(new AccountReader().getAccount(m_usernametxtfld.getText()));
		}catch(Exception e) {
		}
		
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
		
		m_frmLogIn.dispose();
	}
	
	public static void main(String[] args) {
		new LoginGUI().displayGUI();
	}

}