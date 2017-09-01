package process;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import connections.MyDbConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ProcessReport {
	public void generateMonthlyReport(String tt, String sd, String ed){
		try{
			JasperReport jasperReport = JasperCompileManager.compileReport("C:/reports/MonthlyReport.jrxml");
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("transtype", tt);
		    parameters.put("startdate", sd);
		    parameters.put("enddate", ed);
		    
		    Connection conn = MyDbConnection.getMyConnection();
		    
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
		    
		    JasperViewer.viewReport(jasperPrint, false);
		} 
		catch(JRException e){
			e.printStackTrace();
		}
	}
}
