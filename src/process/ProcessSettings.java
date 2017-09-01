package process;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connections.MyDbConnection;

public class ProcessSettings {
	public void changePassword(String username, String oldpassword, String newpassword){
		try{
			Connection con = MyDbConnection.getMyConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM mim_users WHERE user_name='"+ username +"' AND password='"+ oldpassword +"'");
			if(!rs.next()){
				JOptionPane.showMessageDialog(null, "Invalid username or password.");
			}
			else{
				rs = stmt.executeQuery("UPDATE mim_users SET password='"+ newpassword +"' WHERE user_name='"+ username +"'");
				JOptionPane.showMessageDialog(null, "Password updated.");
			}
			con.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
