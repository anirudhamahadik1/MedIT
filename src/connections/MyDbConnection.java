package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class MyDbConnection {
	public static Connection getMyConnection(){
		Connection con = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "oracle");
			return con;
		}
		catch(ClassNotFoundException cnfe){
			JOptionPane.showMessageDialog(null, cnfe.getMessage());
		}
		catch(SQLException se){
			JOptionPane.showMessageDialog(null, se.getMessage());
		}
		return con;
	}
}
