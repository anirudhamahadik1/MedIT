package process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connections.MyDbConnection;
import gui.Login;
import gui.MainParent;

public class ProcessLogin {
	
	public boolean getauth(String username, String password){
		boolean flag = false;
		try{
			Connection con = MyDbConnection.getMyConnection();
			PreparedStatement select = con.prepareStatement("SELECT * FROM mim_users WHERE user_name = ? AND password = ?");
			select.setString(1, username);
			select.setString(2, password);
			ResultSet rs = select.executeQuery();
			if(!rs.next()){
				flag = false;
			}
			else{
				flag = true;
			}
			con.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return flag;
	}
}
