package process;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

import connections.MyDbConnection;

public class ProcessMedicine {
	public void addMedicine(String name){
		Date d = new Date();
		String medid = "MED" + d.getYear() + "" + d.getMonth() + "" + d.getDay() + "" + d.getHours() + "" + d.getMinutes() + "" + d.getSeconds();
		try{
			Connection con = MyDbConnection.getMyConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("INSERT INTO mim_medicines (med_id, med_name, mim_stock) VALUES ('" + medid + "', '" + name + "', 0)");
			JOptionPane.showMessageDialog(null, "Successfully added details.");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
