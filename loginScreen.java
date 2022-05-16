/*
 * Login screen for Password Manager
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

public class loginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	private JFrame frmLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginScreen frame = new loginScreen();
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
	public loginScreen() {
		setTitle("Password Manager Login");
		
		/*
		 * sets JOptionPane for entire application 
		 */
		UIManager.put("OptionPane.background", Color.CYAN);
		UIManager.put("OptionPane.messagebackground", Color.PINK);
		UIManager.put("Panel.background", Color.CYAN);
		UIManager.put("Button.background", Color.PINK);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * text fields
		 */
		
		userField = new JTextField();
		userField.setBounds(229, 60, 105, 20);
		contentPane.add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(229, 122, 105, 20);
		contentPane.add(passwordField);
		
		/*
		 * labels
		 */
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBackground(Color.LIGHT_GRAY);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(68, 63, 83, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(68, 125, 68, 14);
		contentPane.add(lblPassword);
		
		/*
		 * buttons
		 */
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.PINK);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pw = passwordField.getText();					
				String un = userField.getText();
				
				/*
				 * Checks user credentials against the stored credentials 
				 * 	and runs the main application if they match.
				 */
				if (checkCreds(un, pw)) {
					dispose();
					pmAppWindow obj1 = new pmAppWindow();																									//run application
					obj1.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,  "Invalid Login Details", "Login Error", JOptionPane.ERROR_MESSAGE);
				}
				userField.setText(""); passwordField.setText(""); 
			}
		});
		btnLogin.setBounds(37, 198, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(Color.PINK);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userField.setText("");
				passwordField.setText("");
			}
		});
		btnReset.setBounds(163, 198, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.PINK);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(new JFrame(), "Confirm if you want to exit", "login",
						JOptionPane.YES_NO_OPTION) ==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(293, 198, 89, 23);
		contentPane.add(btnExit);
		
	}
	
	/*
	 *  Validates username and password for login by creating
	 *   Validation object and converting the user provided credentials
	 *   to integers with the same algorithm used on the stored credentials.
	 *   The stored credentials are then read in and compared to the
	 *   user's values. If the values match, the function returns true. 
	 */
	public static boolean checkCreds(String u, String p) {
		Validation auth = new Validation();
		int uInt = auth.charToInt(auth.toCharArray(u));
		int pInt = auth.charToInt(auth.toCharArray(p));
		System.out.println("User entered values: " + uInt + " || " + pInt);
		
		try {
			File f = new File("C:\\Users\\sethp\\eclipse-workspace\\PassManager\\src\\creds.txt");
			System.out.println("Filefound");
			
			ArrayList<Integer> tmp = auth.decryptFile(f);
			
			if ((tmp.get(0) == uInt) && (tmp.get(1) == pInt)) {
				return true;
			}
			
		} catch (Exception e) {	
			JOptionPane.showMessageDialog(null, e);
		}
		
		return false;
	}

}
