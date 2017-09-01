package process;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

import connections.MyDbConnection;

public class ProcessSupplier {
	public void addDetails(String name, String contact, String address){
		Date d = new Date();
		String suppid = "SUPP" + d.getYear() + "" + d.getMonth() + "" + d.getDay() + "" + d.getHours() + "" + d.getMinutes() + "" + d.getSeconds(); 
		try{
			Connection con = MyDbConnection.getMyConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("INSERT INTO mim_suppliers (supp_id, supp_name, supp_contact, supp_address) VALUES ('" + suppid + "', '" + name +"', '" + Long.parseLong(contact) +"', '" + address + "')");
			JOptionPane.showMessageDialog(null, "Successfully added details.");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
