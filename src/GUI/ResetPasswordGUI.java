import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class ResetPasswordGUI extends GUI{

	private JFrame frmResetPassword;
	private JTextField unametxtfld;
	private JPasswordField pwrdfld;
	private JPasswordField pwrdfld2;
	private JPasswordField opwrdfld;
		
		
	public ResetPasswordGUI() {
	
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	protected void makeGUI() {
		frmResetPassword = new JFrame();
		frmResetPassword.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Documents\\UNI\\Computer Science\\Java Workspace\\A3\\src\\Skypertawe Icon.png"));
		setVisible(frmResetPassword, true);
		frmResetPassword.setTitle("Reset Password");
		frmResetPassword.getContentPane().setBackground(new Color(0, 238, 190));
		
		unametxtfld = new JTextField();
		unametxtfld.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		
		JLabel lblNewPassword = new JLabel("New Password");
		
		JLabel lblRepeatNewPassword = new JLabel("Confirm New Password");
		
		pwrdfld = new JPasswordField();
		
		pwrdfld2 = new JPasswordField();
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uname = unametxtfld.getText();
				String opass = opwrdfld.getPassword().toString();
				String pass1 = pwrdfld.getPassword().toString();
				String pass2 = pwrdfld2.getPassword().toString();
				//error
				if (uname.equals("") || opwrdfld.getPassword().length == 0 || pwrdfld.getPassword().length == 0 || pwrdfld2.getPassword().length == 0){
					JOptionPane.showMessageDialog(null, "Please complete all fields");
				}
				else{
					if(/*change to check if account is real for username and pass*/uname.equals(opass)){
						//query to change password in db
						if(pwrdfld.getPassword().toString().equals(pwrdfld2.getPassword().toString())){
							JOptionPane.showMessageDialog(null, "Password Changed");
						}
						else{
							JOptionPane.showMessageDialog(null, "Passwords don't match");
						}
						
					}
					else{
						JOptionPane.showMessageDialog(null, "Username and/or password is incorrect");
					}
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				frmResetPassword.dispose();
				new LoginGUI().displayGUI();
				
			}});
		
		JLabel lblOldpassword = new JLabel("Old Password");
		
		opwrdfld = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(frmResetPassword.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(95, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnOk)
							.addGap(33))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewPassword)
								.addComponent(lblRepeatNewPassword)
								.addComponent(lblOldpassword, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername))
							.addGap(12)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pwrdfld, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addComponent(pwrdfld2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(opwrdfld, Alignment.LEADING)
							.addComponent(unametxtfld, Alignment.LEADING)))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(101)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(unametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOldpassword, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(opwrdfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pwrdfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewPassword))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepeatNewPassword)
						.addComponent(pwrdfld2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnCancel))
					.addGap(19))
		);
		frmResetPassword.getContentPane().setLayout(groupLayout);
		frmResetPassword.setBounds(100, 100, 450, 300);
		frmResetPassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ResetPasswordGUI().displayGUI();
	}
}
