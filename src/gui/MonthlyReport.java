package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import process.ProcessReport;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MonthlyReport extends JInternalFrame {
	private JComboBox comboBoxTT;
	private JTextField textFieldSD;
	private JTextField textFieldED;

	/**
	 * Create the frame.
	 */
	public MonthlyReport() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Medical Inventory Report: Monthly Report");
		setBounds(100, 100, 400, 250);
		getContentPane().setLayout(null);
		
		JLabel lblTransactionType = new JLabel("Transaction Type:");
		lblTransactionType.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTransactionType.setBounds(10, 11, 124, 17);
		getContentPane().add(lblTransactionType);
		
		DefaultComboBoxModel<String> DCBMO = new DefaultComboBoxModel<String>();
		DCBMO.addElement("Purchase");
		DCBMO.addElement("Sale");
		comboBoxTT = new JComboBox();
		comboBoxTT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxTT.setBounds(144, 11, 230, 20);
		comboBoxTT.setModel(DCBMO);
		getContentPane().add(comboBoxTT);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStartDate.setBounds(10, 51, 124, 17);
		getContentPane().add(lblStartDate);
		
		Date D = new Date();
		DateFormat DF = new SimpleDateFormat("dd-MMM-yyyy");
		
		textFieldSD = new JTextField();
		textFieldSD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSD.setBounds(144, 51, 230, 20);
		textFieldSD.setText(DF.format(D));
		getContentPane().add(textFieldSD);
		textFieldSD.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEndDate.setBounds(10, 93, 124, 17);
		getContentPane().add(lblEndDate);
		
		textFieldED = new JTextField();
		textFieldED.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldED.setBounds(144, 93, 230, 20);
		textFieldED.setText(DF.format(D));
		getContentPane().add(textFieldED);
		textFieldED.setColumns(10);
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProcessReport PRO = new ProcessReport();
				PRO.generateMonthlyReport(comboBoxTT.getSelectedItem().toString(), textFieldSD.getText(), textFieldED.getText());
			}
		});
		btnGenerateReport.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGenerateReport.setBounds(227, 150, 147, 23);
		getContentPane().add(btnGenerateReport);

	}
}
