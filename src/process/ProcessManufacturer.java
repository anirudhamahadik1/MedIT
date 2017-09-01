package process;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

import connections.MyDbConnection;

public class ProcessManufacturer {
	public void addDetails(String name, String contact, String address){
		Date d = new Date();
		String manid = "MAN" + d.getYear() + "" + d.getMonth() + "" + d.getDay() + "" + d.getHours() + "" + d.getMinutes() + "" + d.getSeconds(); 
		try{
			Connection con = MyDbConnection.getMyConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("INSERT INTO mim_manufacturer (man_id, man_name, man_contact, man_address) VALUES ('" + manid + "', '" + name +"', '" + Long.parseLong(contact) +"', '" + address + "')");
			JOptionPane.showMessageDialog(null, "Successfully added details.");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
