/*
 * Main application window for Password Manager
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;

public class pmAppWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;																		//for displaying database 
	private Connection conn = null;
	private JTextField paramField;
	private boolean saved = true;																//flag for saving changes to database
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pmAppWindow frame = new pmAppWindow();
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
	public pmAppWindow() {
		setTitle("Password Manager v0.5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 945, 587);
		
		/*
		 * Menu bar and menu items
		 */
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setBackground(Color.PINK);
		menuBar.add(mnFile);
		
		JMenuItem mntmAddEntry = new JMenuItem("Add New Entry");
		mntmAddEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEntry add = new AddEntry();
				add.setVisible(true);
			}
		});
		mntmAddEntry.setBackground(Color.PINK);
		mnFile.add(mntmAddEntry);
		
		JMenuItem mntmRemoveEntry = new JMenuItem("Remove Entry");
		mntmRemoveEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveEntry remove = new RemoveEntry();
				remove.setVisible(true);
			}
		});
		mntmRemoveEntry.setBackground(Color.PINK);
		mnFile.add(mntmRemoveEntry);
		
		JSeparator separator_2 = new JSeparator();
		mnFile.add(separator_2);
		
		JMenuItem mntmChangeLogin = new JMenuItem("Change Login");
		mntmChangeLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginDetails obj = new loginDetails();
				obj.setVisible(true);
			}
		});
		mntmChangeLogin.setBackground(Color.PINK);
		mnFile.add(mntmChangeLogin);
		
		JSeparator separator_3 = new JSeparator();
		mnFile.add(separator_3);
		
		JMenu mnQuit = new JMenu("Exit");
		mnQuit.setBackground(Color.PINK);
		menuBar.add(mnQuit);
		
		JMenuItem mntmQuit2 = new JMenuItem("Exit");
		mntmQuit2.setBackground(Color.PINK);
		mntmQuit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnQuit.add(mntmQuit2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * Search text box and label
		 */
		
		paramField = new JTextField();
		paramField.setBackground(Color.WHITE);
		paramField.setBounds(10, 79, 249, 20);
		contentPane.add(paramField);
		paramField.setColumns(10);
		
		JLabel lbSearchString = new JLabel("Enter string to search for:");
		lbSearchString.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbSearchString.setBounds(10, 48, 158, 20);
		contentPane.add(lbSearchString);
		
		/*
		 * Radio buttons
		 */
		
		JRadioButton rdbtnPassword = new JRadioButton("Password");
		rdbtnPassword.setBackground(Color.CYAN);
		rdbtnPassword.setBounds(10, 119, 109, 23);
		contentPane.add(rdbtnPassword);
		
		JRadioButton rdbtnWebsite = new JRadioButton("Website");
		rdbtnWebsite.setBackground(Color.CYAN);
		rdbtnWebsite.setBounds(150, 119, 109, 23);
		contentPane.add(rdbtnWebsite);
		
		JRadioButton rdbtnEmail = new JRadioButton("Email");
		rdbtnEmail.setBackground(Color.CYAN);
		rdbtnEmail.setBounds(10, 163, 109, 23);
		contentPane.add(rdbtnEmail);
		
		JRadioButton rdbtnLoginName = new JRadioButton("Login Name");
		rdbtnLoginName.setBackground(Color.CYAN);
		rdbtnLoginName.setBounds(150, 163, 109, 23);
		contentPane.add(rdbtnLoginName);
		
		/*
		 * Buttons
		 */
		
		
		JButton btnShowAll = new JButton("Show All Entries");
		btnShowAll.setBackground(Color.PINK);
		btnShowAll.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = sqlConnection.dbConnector();
				String qry = "select * from Website";			
				try {
					PreparedStatement pst = conn.prepareStatement(qry);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException eList) {
					JOptionPane.showMessageDialog(null, eList);
					
				}
				
			}
		});
		btnShowAll.setBounds(10, 333, 249, 148);
		contentPane.add(btnShowAll);
		
		JButton btnSearch = new JButton("Find Entry");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.setBackground(Color.PINK);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = sqlConnection.dbConnector();
				String userSearch = "";
				String qry = "";
				String searchField = "";
				
				userSearch = paramField.getText();
				
				if (rdbtnPassword.isSelected()) {
					searchField = "pw";
				} else if (rdbtnWebsite.isSelected()) {
					searchField = "webName";
				} else if (rdbtnEmail.isSelected()) {
					searchField = "email";
				} else if (rdbtnLoginName.isSelected()) {
					searchField = "loginName";
				}
				
				/*
				 * query returns all instances of userSearch
				 */
				qry = "select * from Website where " +  searchField + " = \'" + userSearch + "\'";
				try {
					PreparedStatement pst = conn.prepareStatement(qry);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException eList) {
					JOptionPane.showMessageDialog(null, eList);
					
				}
				
				/*
				 * reset radio buttons and textField
				 */
				rdbtnPassword.setSelected(false); rdbtnEmail.setSelected(false);
				rdbtnWebsite.setSelected(false); rdbtnLoginName.setSelected(false);
				paramField.setText("");
			}
		});
		btnSearch.setBounds(10, 221, 249, 51);
		contentPane.add(btnSearch);
		
		/*
		 * Database display
		 */
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, Color.PINK, new Color(255, 175, 175), new Color(255, 175, 175), new Color(255, 175, 175)));
		scrollPane.setBounds(277, 25, 626, 482);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.BLACK);
		table.setBackground(Color.GREEN);
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(18, 110, 233, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 196, 233, 2);
		contentPane.add(separator_1);
	}
}
