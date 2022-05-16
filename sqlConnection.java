/*
 * Connects to SQLite database/	
 * 
 * Credit to YouTube Channel Programming Knowledge for
 * 	 this class. From the Java Eclipse GUI Tutorial for 
 * 	 Beginners (For Absolute Beginners) Playlist. 
 * 	 Source: https://www.youtube.com/playlist?list=PLS1QulWo1RIbYMA5Ijb72QHaHvCrPKfS2
 * 
 * Requires rs2xml.jar to be added to libraries for project. 
 * 	rs2xl.jar downloaded from: https://sourceforge.net/projects/finalangelsanddemons/
 */

import java.sql.*;
import javax.swing.*;

public class sqlConnection {
	Connection c = null;																																						//java.sql.Connection
	
	public static Connection dbConnector() {																																	//returns connection
		try {
			Class.forName("org.sqlite.JDBC");																																	//driver
			Connection c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\sethp\\eclipse-workspace\\PassManager\\src\\LoginDB.db");									//database location
			return c;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
