import java.awt.EventQueue;

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

	private JFrame frmSignUp;
	private JTextField usernametxtfld;
	private JPasswordField pwrdfld;
	private JTextField fnametxtfld;
	private JTextField lnametxtfld;
	private JTextField phonenumtxtfld;
	private JTextField dobtxtfld;
	private JTextField citytxtfld;
	private JButton btnCreateAccount;
	private JButton btnCancel;

	
	public SignupGUI() {
		
	}


	/**
	 * @wbp.parser.entryPoint
	 */
	protected void makeGUI() {
		frmSignUp = new JFrame();
		frmSignUp.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Documents\\UNI\\Computer Science\\Java Workspace\\A3\\src\\Skypertawe Icon.png"));
		frmSignUp.setTitle("Sign Up");
		frmSignUp.getContentPane().setSize(600, 600);;
		setVisible(frmSignUp, true);
		frmSignUp.getContentPane().setBackground(new Color(0, 238, 190));
		
		JLabel lblUsername = new JLabel("Username");
		
		JLabel lblPassword = new JLabel("Password");
		
		JLabel lblFirstname = new JLabel("Firstname");
		
		JLabel lblLastname = new JLabel("Lastname");
		
		JLabel lblUkPhoneNumber = new JLabel("UK Phone Number");
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		
		JLabel lblCity = new JLabel("City");
		
		usernametxtfld = new JTextField();
		usernametxtfld.setColumns(10);
		
		pwrdfld = new JPasswordField();
		
		fnametxtfld = new JTextField();
		fnametxtfld.setColumns(10);
		
		lnametxtfld = new JTextField();
		lnametxtfld.setColumns(10);
		
		phonenumtxtfld = new JTextField();
		phonenumtxtfld.setColumns(10);
		
		dobtxtfld = new JTextField();
		dobtxtfld.setColumns(10);
		
		citytxtfld = new JTextField();
		citytxtfld.setColumns(10);
		
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String uname = usernametxtfld.getText();
				String pwrd = pwrdfld.getPassword().toString();
				String fname = fnametxtfld.getText();
				String lname = lnametxtfld.getText();
				String phonenum = phonenumtxtfld.getText();
				//String dob = usernametxtfld.getText();
				//String city = usernametxtfld.getText();
				if(uname.equals("") || pwrdfld.getPassword().length == 0|| fname.equals("")|| lname.equals("") || phonenum.equals("")){
					JOptionPane.showMessageDialog(null, "Please enter all fields");

				}

				else {
					new Account(uname, pwrd, fname, lname, phonenum);
					frmSignUp.dispose();
					JOptionPane.showMessageDialog(null, "Account Created");
					new LoginGUI().displayGUI();
				}
						
			}
		});
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSignUp.dispose();
				new LoginGUI().displayGUI();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmSignUp.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLastname)
						.addComponent(lblPassword)
						.addComponent(lblUsername)
						.addComponent(lblUkPhoneNumber)
						.addComponent(lblDateOfBirth)
						.addComponent(lblFirstname)
						.addComponent(lblCity))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(phonenumtxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(citytxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(dobtxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(43)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnCancel)
										.addComponent(btnCreateAccount)))))
						.addComponent(fnametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lnametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pwrdfld, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(usernametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(78))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsername)
								.addComponent(usernametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword)
								.addComponent(pwrdfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFirstname)
								.addComponent(fnametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLastname)
								.addComponent(lnametxtfld, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUkPhoneNumber)
								.addComponent(phonenumtxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDateOfBirth)
								.addComponent(dobtxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnCancel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(citytxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnCreateAccount))
						.addComponent(lblCity))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		frmSignUp.getContentPane().setLayout(groupLayout);
		frmSignUp.setBounds(100, 100, 450, 322);
		
		frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new SignupGUI().displayGUI();
	}

}

