/*
 *	Window to delete entry from database used in 
 *	 Password Manager. All entries will be listed in ComboBox
 *	 and the user must select the entry they wish to 
 *	 delete.
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import java.sql.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoveEntry extends JFrame {

	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox = new JComboBox();;
	private Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveEntry frame = new RemoveEntry();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
	}
	
	/*
	 * fills comboBox with valid entries for removal
	 */
	public void fillComboBox() {
		try {
			conn = sqlConnection.dbConnector();
			String qry = "select * from Website";
			PreparedStatement pst = conn.prepareStatement(qry);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				comboBox.addItem(rs.getString("webName"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Create the frame.
	 */
	public RemoveEntry() {
		setTitle("Remove Entry from Database");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 237);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		comboBox.setBackground(Color.PINK);
		
		comboBox.setBounds(152, 69, 138, 22);
		contentPane.add(comboBox);
		
		JLabel lblSelection = new JLabel("Select Website entry to delete");
		lblSelection.setBounds(151, 44, 149, 14);
		contentPane.add(lblSelection);
		
		/*
		 * Buttons
		 */
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBackground(Color.PINK);
		btnCancel.setBounds(346, 139, 89, 23);
		contentPane.add(btnCancel);
		
		/*
		 * Deletes entry from database that is selected from 
		 * 	comboBox after confirmation.
		 */
		JButton btnConfirmDelete = new JButton("Remove");
		btnConfirmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete " + (String)comboBox.getSelectedItem() + "?" , "Confirm Deletion",
						JOptionPane.YES_NO_OPTION) ==JOptionPane.YES_NO_OPTION) {
				
					String qry = "delete from Website where webName = \'" + (String)comboBox.getSelectedItem() + "\'";
					try {
						PreparedStatement pst = conn.prepareStatement(qry);
						pst.execute();
						dispose();
						JOptionPane.showMessageDialog(null,  (String)comboBox.getSelectedItem() + " removed!", "Success!", JOptionPane.ERROR_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		});
		btnConfirmDelete.setBackground(Color.PINK);
		btnConfirmDelete.setBounds(33, 139, 89, 23);
		contentPane.add(btnConfirmDelete);
		
		
		fillComboBox();
	}
}
