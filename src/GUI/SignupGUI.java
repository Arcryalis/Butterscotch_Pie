
import java.awt.Image;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class SignupGUI extends GUI{

	private JFrame m_frmSignUp;
	private JTextField m_usernametxtfld;
	private JPasswordField m_pwrdfld;
	private JTextField m_fnametxtfld;
	private JTextField m_lnametxtfld;
	private JTextField m_phonenumtxtfld;
	private JTextField m_dobtxtfld;
	private JTextField m_citytxtfld;
	private JButton m_btnCreateAccount;
	private JButton m_btnCancel;
	private JLabel m_lblLogo;

	
	public SignupGUI() {
		
	}


	/**
	 * @wbp.parser.entryPoint
	 */
	protected void makeGUI() {
		m_frmSignUp = new JFrame();
		m_frmSignUp.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Documents\\UNI\\Computer Science\\Java Workspace\\A3\\src\\Skypertawe Icon.png"));
		m_frmSignUp.setTitle("Sign Up");
		m_frmSignUp.getContentPane().setSize(600, 600);;
		setVisible(m_frmSignUp, true);
		m_frmSignUp.getContentPane().setBackground(new Color(0, 238, 190));
		
		JLabel lblUsername = new JLabel("Username");
		
		JLabel lblPassword = new JLabel("Password");
		
		JLabel lblFirstname = new JLabel("Firstname");
		
		JLabel lblLastname = new JLabel("Lastname");
		
		JLabel lblUkPhoneNumber = new JLabel("UK Phone Number");
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		
		JLabel lblCity = new JLabel("City");
		
		m_usernametxtfld = new JTextField();
		m_usernametxtfld.setColumns(10);
		
		m_pwrdfld = new JPasswordField();
		
		m_fnametxtfld = new JTextField();
		m_fnametxtfld.setColumns(10);
		
		m_lnametxtfld = new JTextField();
		m_lnametxtfld.setColumns(10);
		
		m_phonenumtxtfld = new JTextField();
		m_phonenumtxtfld.setColumns(10);
		
		m_dobtxtfld = new JTextField();
		m_dobtxtfld.setColumns(10);
		
		m_citytxtfld = new JTextField();
		m_citytxtfld.setColumns(10);
		
		m_btnCreateAccount = new JButton("Create Account");
		m_btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String uname = m_usernametxtfld.getText();
				String fname = m_fnametxtfld.getText();
				String lname = m_lnametxtfld.getText();
				String phonenum = m_phonenumtxtfld.getText();
				//String dob = usernametxtfld.getText();
				//String city = usernametxtfld.getText();
				if(uname.equals("") || m_pwrdfld.getPassword().length == 0|| fname.equals("")|| lname.equals("") || phonenum.equals("")){
					JOptionPane.showMessageDialog(null, "Please enter all fields");

				}

				else {
					
					//write(new account(..))
					//new AccountReader().write(new Account(uname, pwrd, fname, lname, phonenum));
					m_frmSignUp.dispose();
					JOptionPane.showMessageDialog(null, "Account Created");
					new LoginGUI().displayGUI();
				}
						
			}
		});
		
		m_btnCancel = new JButton("Cancel");
		m_btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_frmSignUp.dispose();
				new LoginGUI().displayGUI();
			}
		});
		
		m_lblLogo = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("/Skypertawe Logo.png")).getImage();
		m_lblLogo.setIcon(new ImageIcon(logo));
		
		
		
		GroupLayout groupLayout = new GroupLayout(m_frmSignUp.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword)
								.addComponent(lblUsername)
								.addComponent(lblFirstname)
								.addComponent(lblLastname)
								.addComponent(lblDateOfBirth)
								.addComponent(lblCity))
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(m_btnCreateAccount)
									.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
									.addComponent(m_btnCancel)
									.addGap(35))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(m_pwrdfld, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
										.addComponent(m_usernametxtfld, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
										.addComponent(m_fnametxtfld, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
										.addComponent(m_lnametxtfld, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
										.addComponent(m_phonenumtxtfld, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
										.addComponent(m_dobtxtfld, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
										.addComponent(m_citytxtfld, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addComponent(lblUkPhoneNumber))
					.addGap(51))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(110, Short.MAX_VALUE)
					.addComponent(m_lblLogo, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addGap(60))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(m_lblLogo)
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(m_usernametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(m_pwrdfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstname)
						.addComponent(m_fnametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastname)
						.addComponent(m_lnametxtfld, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUkPhoneNumber)
						.addComponent(m_phonenumtxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDateOfBirth)
						.addComponent(m_dobtxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCity)
						.addComponent(m_citytxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(m_btnCancel)
						.addComponent(m_btnCreateAccount))
					.addGap(34))
		);
		m_frmSignUp.getContentPane().setLayout(groupLayout);
		m_frmSignUp.setBounds(100, 100, 450, 530);
		
		m_frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new SignupGUI().displayGUI();
	}

}

