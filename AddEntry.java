/*
 *	Window to add entry to database used in 
 *	 Password Manager. 
 *	
 *	LoginDB is a single table database, all columns
 *	 are text fields, the username field for each instance
 *	 is optional.
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class AddEntry extends JFrame {

	private JPanel contentPane;
	private JTextField webNameField;
	private JTextField emailField;
	private JTextField pwField;
	private JTextField loginField;
	
	private Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEntry frame = new AddEntry();
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
	public AddEntry() {
		setTitle("Add Entry to Database");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 515);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * Text fields
		 */
		
		webNameField = new JTextField();
		webNameField.setBounds(172, 57, 217, 35);
		contentPane.add(webNameField);
		webNameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(172, 127, 217, 35);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		pwField = new JTextField();
		pwField.setBounds(172, 188, 217, 35);
		contentPane.add(pwField);
		pwField.setColumns(10);
		
		loginField = new JTextField();
		loginField.setBounds(172, 266, 217, 35);
		contentPane.add(loginField);
		loginField.setColumns(10);
		
		/*
		 * Labels
		 */
		
		JLabel lblwebName = new JLabel("Website ");
		lblwebName.setBackground(Color.CYAN);
		lblwebName.setBounds(29, 66, 150, 14);
		contentPane.add(lblwebName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBackground(Color.CYAN);
		lblEmail.setBounds(29, 136, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBackground(Color.CYAN);
		lblPassword.setBounds(29, 198, 46, 14);
		contentPane.add(lblPassword);
		
		JLabel lblLoginName = new JLabel("Login Name (Optional)");
		lblLoginName.setBackground(Color.CYAN);
		lblLoginName.setBounds(29, 276, 133, 14);
		contentPane.add(lblLoginName);
		
		/*
		 * Buttons
		 */
		
		JButton btnCancelAdd = new JButton("Cancel");
		btnCancelAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelAdd.setBackground(Color.PINK);
		btnCancelAdd.setBounds(383, 386, 133, 60);
		contentPane.add(btnCancelAdd);
		
		/*
		 * Adds entry to database
		 * 
		 * Entries consist of the name of the website,
		 * 	the email associated with the account, 
		 * 	the password associate with the account,
		 * 	and the username associated with the account, if applicable.
		 */
		JButton btnAddEntry = new JButton("Add");
		btnAddEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (webNameField.getText().isBlank() || emailField.getText().isBlank() || pwField.getText().isBlank()) {
					JOptionPane.showMessageDialog(null,  "Blank field entry!\nPlease fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
				conn = sqlConnection.dbConnector();
				String qry = "insert into Website (webName, email, pw, loginName) values (?, ?, ?, ?)";
				
				try {
					PreparedStatement pst = conn.prepareStatement(qry);
					pst.setString(1, webNameField.getText());
					pst.setString(2, emailField.getText());
					pst.setString(3, pwField.getText());
					pst.setString(4, loginField.getText());
					
					pst.execute();
					dispose();
					JOptionPane.showMessageDialog(null,  webNameField.getText() + " added!", "Success!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException eList) {
					JOptionPane.showMessageDialog(null, eList);
					
				}
				}
			}
		});
		btnAddEntry.setBackground(Color.PINK);
		btnAddEntry.setBounds(29, 386, 133, 60);
		contentPane.add(btnAddEntry);
		
		
	}
}
