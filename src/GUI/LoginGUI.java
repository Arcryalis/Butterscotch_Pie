import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class LoginGUI extends GUI{

	private JFrame frmLogIn;
	private JTextField usernametxtfld;
	private JPasswordField passwordpwrdfld;
	private JButton btnLogIn;
	private JButton btnResetPassword;
	private JButton btnSignUp;
	private JLabel lblLogo;

		
	public LoginGUI() {
		
	}

	
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void makeGUI() {
		frmLogIn = new JFrame();
		setVisible(frmLogIn, true);
		frmLogIn.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Documents\\UNI\\Computer Science\\Java Workspace\\A3\\src\\Skypertawe Icon.png"));
		frmLogIn.getContentPane().setBackground(new Color(0, 238, 190));
		
		JLabel lblUsername = new JLabel("Username");
		
		JLabel lblPassword = new JLabel("Password");
		
		usernametxtfld = new JTextField();
		usernametxtfld.setColumns(10);
		
		passwordpwrdfld = new JPasswordField();
		
		btnLogIn = new JButton("Log In");
		
		btnResetPassword = new JButton("Reset Password");
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogIn.dispose();
				new ResetPasswordGUI().displayGUI();
			}
		});
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogIn.dispose();
				new SignupGUI().displayGUI();
			}
		});
		
		lblLogo = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("/ss.png")).getImage();
		lblLogo.setIcon(new ImageIcon(logo));
		GroupLayout groupLayout = new GroupLayout(frmLogIn.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(165)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnResetPassword)
								.addComponent(btnLogIn)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSignUp)
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLogo)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPassword)
									.addGap(18)
									.addComponent(passwordpwrdfld, 86, 86, 86))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUsername)
									.addGap(18)
									.addComponent(usernametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSignUp)
						.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(usernametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordpwrdfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnLogIn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnResetPassword)
					.addGap(26))
		);
		frmLogIn.getContentPane().setLayout(groupLayout);
		frmLogIn.setTitle("Log in");
		frmLogIn.setBounds(100, 100, 450, 300);
		frmLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new LoginGUI().displayGUI();
	}

}
