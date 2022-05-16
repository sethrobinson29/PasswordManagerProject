/*
 * 	Window to change login information
 * 	 used to log in to Password Manager
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;

public class loginDetails extends JFrame {

	private JPanel contentPane;
	private JTextField currentField;
	private JTextField newPWField;
	private JTextField confirmPWField;
	private JTextField usernameField;
	private JTextField newUNField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginDetails frame = new loginDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginDetails() {
		setTitle("Change Login Credentials");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 415, 388);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * Text fields
		 */
		
		currentField = new JTextField();
		currentField.setBounds(192, 56, 86, 20);
		contentPane.add(currentField);
		currentField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setBounds(192, 25, 86, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		newPWField = new JTextField();
		newPWField.setBounds(192, 167, 86, 20);
		contentPane.add(newPWField);
		newPWField.setColumns(10);
		
		confirmPWField = new JTextField();
		confirmPWField.setBounds(192, 198, 86, 20);
		contentPane.add(confirmPWField);
		confirmPWField.setColumns(10);
		
		newUNField = new JTextField();
		newUNField.setBounds(192, 229, 86, 20);
		contentPane.add(newUNField);
		newUNField.setColumns(10);
		
		
		
		/*
		 * Labels
		 */
		
		JLabel lblCurrentPassword = new JLabel("Current Password");
		lblCurrentPassword.setBackground(Color.CYAN);
		lblCurrentPassword.setBounds(28, 65, 106, 14);
		contentPane.add(lblCurrentPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBackground(Color.CYAN);
		lblNewPassword.setBounds(28, 170, 106, 14);
		contentPane.add(lblNewPassword);
		
		JLabel lblNewPasswordConfirm = new JLabel("Confim New Password");
		lblNewPasswordConfirm.setBackground(Color.CYAN);
		lblNewPasswordConfirm.setBounds(28, 201, 106, 14);
		contentPane.add(lblNewPasswordConfirm);
		
		JLabel lblUsername = new JLabel("Current Username");
		lblUsername.setBackground(Color.CYAN);
		lblUsername.setBounds(28, 28, 123, 14);
		contentPane.add(lblUsername);
		
		JLabel newUNLabel = new JLabel("New Username");
		newUNLabel.setBounds(28, 232, 89, 14);
		contentPane.add(newUNLabel);
		
		JRadioButton rdbtnKeepUN = new JRadioButton("Keep Current Username");
		rdbtnKeepUN.setBackground(Color.CYAN);
		rdbtnKeepUN.setBounds(189, 100, 149, 23);
		contentPane.add(rdbtnKeepUN);
		
		/*
		 * Buttons
		 */
		
		JButton btnCancelChange = new JButton("Cancel");
		btnCancelChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelChange.setBackground(Color.PINK);
		btnCancelChange.setBounds(266, 295, 106, 32);
		contentPane.add(btnCancelChange);
		
		/*
		 * Changes login credentials for Password Manager.
		 * 
		 * User must enter current username and password to validate the changes.
		 * 
		 * If the user only wishes to change their password, they can select the radio
		 * 	button and will not have to enter text into the bottom username field.
		 * 	Otherwise, the user will also enter the new password, confirm the new password,
		 * 	and enter their new username. A confirmation window allows the user one
		 * 	last chance to user they have entered the information correctly before 
		 * 	the changes are made to creds.txt.
		 * 
		 */
		JButton btnChangeLogin = new JButton("Update");
		btnChangeLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//no blanks
				if (newPWField.getText().isBlank() || confirmPWField.getText().isBlank() || usernameField.getText().isBlank()) {
					JOptionPane.showMessageDialog(null,  "Blank field entry!\nPlease fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					boolean valid = true; //loginScreen.checkCreds(usernameField.getText(), currentField.getText());																		//checks for current username and password
					if (JOptionPane.showConfirmDialog(null, "You would like to change " + currentField.getText() + " to " + newPWField.getText() + "?", "Credential Change",
							JOptionPane.YES_NO_OPTION) ==JOptionPane.YES_NO_OPTION) {
						if ((confirmPWField.getText().equals(newPWField.getText())) && valid) {
							
							String tmp = (rdbtnKeepUN.isSelected()) ? usernameField.getText() : newUNField.getText();																		//sets string to write to file
							
							try {
								File f = new File("C:\\Users\\sethp\\eclipse-workspace\\PassManager\\src\\creds.txt");
								Validation auth = new Validation();
								auth.encryptFile(f, tmp, newPWField.getText());																												//writes new credentials to file
								dispose();
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, ex);
							}
						} else {
							String errMessage = valid ? "Password fields do not match!" : "Invalid Credentials";
							JOptionPane.showMessageDialog(null,  errMessage + valid);
						}
					} 
				}
				currentField.setText(""); newPWField.setText("");
				confirmPWField.setText(""); usernameField.setText("");
			}
		});
		btnChangeLogin.setBackground(Color.PINK);
		btnChangeLogin.setBounds(10, 294, 107, 34);
		contentPane.add(btnChangeLogin);
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 130, 379, 2);
		contentPane.add(separator);
		
		
	}
}
