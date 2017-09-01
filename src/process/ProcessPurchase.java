package process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import connections.MyDbConnection;

public class ProcessPurchase {
	private DefaultListModel<String> DLS;
	private String transno;
	
	public DefaultListModel<String> getSuppliersList(){
		DLS = new DefaultListModel<String>();
		try{
			Connection con = MyDbConnection.getMyConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM mim_suppliers ORDER BY supp_name");
			while(rs.next()){
				DLS.addElement(rs.getString(2));
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return DLS;
	}
	
	public DefaultListModel<String> getMedicinesList(){
		DLS = new DefaultListModel<String>();
		try{
			Connection con = MyDbConnection.getMyConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM mim_medicines ORDER BY med_name");
			while(rs.next()){
				DLS.addElement(rs.getString(2));
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return DLS;
	}
	
	public DefaultListModel<String> getManufacturersList(){
		DLS = new DefaultListModel<String>();
		try{
			Connection con = MyDbConnection.getMyConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM mim_manufacturer ORDER BY man_name");
			while(rs.next()){
				DLS.addElement(rs.getString(2));
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return DLS;
	}
	
	public String getStock(String med){
		String stock = "Stock: 0";
		try{
			Connection con = MyDbConnection.getMyConnection();
			PreparedStatement select = con.prepareStatement("SELECT * FROM mim_medicines WHERE med_name = ?");
			select.setString(1, med);
			ResultSet rs = select.executeQuery();
			if(rs.next()){
				stock = "Stock: " + rs.getInt(3);
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return stock;
	}
	
	public void addTransaction(String tno, String tdate, String ptype, String cno, String tax, String amt){
		transno = tno;
		try{
			Connection con = MyDbConnection.getMyConnection();
			PreparedStatement insert = con.prepareStatement("INSERT INTO mim_transactions (trans_id, trans_date, trans_type, payment_type, cheque_no, trans_tax, trans_amount) VALUES (?, ?, ?, ?, ?, ?, ?)");
			insert.setString(1, transno);
			insert.setDate(2, java.sql.Date.valueOf(tdate));
			insert.setString(3, "Purchase");
			insert.setString(4, ptype);
			insert.setString(5, cno);
			insert.setFloat(6, Float.parseFloat(tax));
			insert.setFloat(7, Float.parseFloat(amt));
			insert.executeUpdate();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void addPurchase(JTable temp, String supp){
		try{
			Connection con = MyDbConnection.getMyConnection();
			PreparedStatement insert = con.prepareStatement("INSERT INTO mim_purchase (med_id, man_id, supp_id, batch_no, exp_date, rate, mrp, qty, pack, rack_no, trans_id) VALUES ((SELECT med_id FROM mim_medicines WHERE med_name = ?), (SELECT man_id FROM mim_manufacturer WHERE man_name = ?), (SELECT supp_id FROM mim_suppliers WHERE supp_name = ?), ?, ?, ?, ?, ?, ?, ?, ?)");
			PreparedStatement select = con.prepareStatement("SELECT mim_stock FROM mim_medicines WHERE med_name = ?");
			PreparedStatement update = con.prepareStatement("UPDATE mim_medicines SET mim_stock = ? WHERE med_name = ?");
			int rows = temp.getRowCount();
			for(int row = 0; row < rows; row++){
				insert.setString(1, temp.getValueAt(row, 0).toString());
				insert.setString(2, temp.getValueAt(row, 1).toString());
				insert.setString(3, supp);
				insert.setString(4, temp.getValueAt(row, 2).toString());
				insert.setString(5, temp.getValueAt(row, 3).toString());
				insert.setFloat(6, Float.parseFloat(temp.getValueAt(row, 4).toString()));
				insert.setFloat(7, Float.parseFloat(temp.getValueAt(row, 5).toString()));
				insert.setInt(8, Integer.parseInt(temp.getValueAt(row, 6).toString()));
				insert.setInt(9, Integer.parseInt(temp.getValueAt(row, 7).toString()));
				insert.setString(10, temp.getValueAt(row, 8).toString());
				insert.setString(11, transno);
				insert.executeUpdate();
				
				select.setString(1, temp.getValueAt(row, 0).toString());
				ResultSet RS = select.executeQuery();
				int stock = 0;
				if(RS.next()){
					stock = RS.getInt(1);
				}
				
				stock += (Integer.parseInt(temp.getValueAt(row, 6).toString()) * Integer.parseInt(temp.getValueAt(row, 7).toString()));
				update.setInt(1, stock);
				update.setString(2, temp.getValueAt(row, 0).toString());
				update.executeUpdate();
				
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
}
