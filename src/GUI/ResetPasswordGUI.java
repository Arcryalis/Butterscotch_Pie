
import java.awt.Image;
import filereader.AccountReader;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
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

/**
 * ResetPasswordGUI.java
 * @author Ahmed Elmi
 *
 */
public class ResetPasswordGUI extends GUI {

	private JFrame frmResetPassword;
	private JTextField unametxtfld;
	private JPasswordField pwrdfld;
	private JPasswordField pwrdfld2;
	private JPasswordField opwrdfld;

	public ResetPasswordGUI() {

	}

	
	/* 
	 * Implements ResetPasswordGUI
	 */
	protected void makeGUI() {
		frmResetPassword = new JFrame();
		frmResetPassword.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"/Skypertawe Icon.png"));
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
		
		
		/*
		 * OK button will change the password 
		 */
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uname = unametxtfld.getText();
				String opass = String.valueOf(opwrdfld.getPassword());
						
				// error
				if (uname.equals("") || opwrdfld.getPassword().length == 0 || pwrdfld.getPassword().length == 0
						|| pwrdfld2.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, "Please complete all fields");
				} else {
					try {
						if (new AccountReader().checkUserPass(uname, opass)){
							// query to change password in db
							if (String.valueOf(pwrdfld.getPassword()).equals(String.valueOf(pwrdfld2.getPassword()))) {
								Account updatedAccount = new AccountReader().getAccount(uname);
								updatedAccount.setPassword(String.valueOf(pwrdfld2.getPassword()));
								new AccountReader().update(updatedAccount);
								JOptionPane.showMessageDialog(null, "Password Changed");
							} else {
								JOptionPane.showMessageDialog(null, "Passwords don't match");
							}

						} else {
							JOptionPane.showMessageDialog(null, "Username and/or password is incorrect");
						}
					} catch (HeadlessException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
					
		/*
		 * Cancel button returns the user to the loginGUI
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmResetPassword.dispose();
				new LoginGUI().displayGUI();

			}
		});

		JLabel lblOldpassword = new JLabel("Old Password");

		opwrdfld = new JPasswordField();

		JLabel lblLogo = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("/sss.png")).getImage();
		lblLogo.setIcon(new ImageIcon(logo));
		
		/*
		 * GroupLayout for the GUI contents
		 */
		GroupLayout groupLayout = new GroupLayout(frmResetPassword.getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap(72,
												Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(Alignment.TRAILING,
														groupLayout.createSequentialGroup().addGroup(groupLayout
																.createParallelGroup(Alignment.TRAILING, false)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(btnOk).addGap(33))
																.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(lblNewPassword)
																				.addComponent(lblUsername)
																				.addComponent(lblOldpassword,
																						GroupLayout.PREFERRED_SIZE, 88,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(lblRepeatNewPassword))
																		.addGap(12)))
																.addGap(18)
																.addGroup(
																		groupLayout
																				.createParallelGroup(Alignment.LEADING,
																						false)
																				.addComponent(pwrdfld2)
																				.addComponent(btnCancel)
																				.addComponent(unametxtfld, 201, 201,
																						Short.MAX_VALUE)
																				.addComponent(opwrdfld)
																				.addComponent(pwrdfld))
																.addContainerGap())
												.addGroup(Alignment.TRAILING,
														groupLayout.createSequentialGroup()
																.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 243,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(119)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE).addGap(33)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblUsername).addComponent(
						unametxtfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(8)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblOldpassword, GroupLayout.PREFERRED_SIZE, 18,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(opwrdfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pwrdfld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewPassword))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblRepeatNewPassword)
						.addComponent(pwrdfld2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnOk).addComponent(btnCancel))
				.addGap(19)));
		frmResetPassword.getContentPane().setLayout(groupLayout);
		frmResetPassword.setBounds(100, 100, 450, 350);
		frmResetPassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * main method used for testing
	 */
	public static void main(String[] args) {
		new ResetPasswordGUI().displayGUI();
	}
}
