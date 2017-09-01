package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Position;

import process.ProcessSale;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sale extends JInternalFrame {
	private JTextField textFieldTN, textFieldTD, textFieldMed, textFieldBN, textFieldED, textFieldPack, textFieldMRP, textFieldTax, textFieldNetA;
	private JList listMed, listBN;
	private JScrollPane scrollPaneMed, scrollPaneBN, scrollPaneTable;
	private ProcessSale PSO;
	private DefaultTableModel DTMO;
	private Vector<String> row, columnNames;
	private Vector<Vector> rowData;
	private JTable tableSE;
	private JLabel lblStock;
	private String medicine, batchno, expirydate, pack, mrp, transno, transdate, tax, amt;
	private float netamt;

	/**
	 * Create the frame.
	 */
	public Sale() {
		setTitle("Medical Inventory Management: Sale Entry");
		setIconifiable(true);
		setClosable(true);
		setBounds(50, 50, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblSaleEntry = new JLabel("Sale Entry");
		lblSaleEntry.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblSaleEntry.setBounds(10, 11, 223, 61);
		getContentPane().add(lblSaleEntry);
		
		JLabel lblTransactionNo = new JLabel("Transaction No:");
		lblTransactionNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTransactionNo.setBounds(243, 11, 122, 17);
		getContentPane().add(lblTransactionNo);
		
		JLabel lblTransactionDate = new JLabel("Transaction Date:");
		lblTransactionDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTransactionDate.setBounds(243, 50, 122, 17);
		getContentPane().add(lblTransactionDate);
		
		Date D = new Date();
		String TN = "TN" + D.getYear() + "" + D.getMonth() + "" + D.getDay() + "" + D.getHours() + "" + D.getMinutes() + "" + D.getSeconds();
		
		textFieldTN = new JTextField();
		textFieldTN.setEditable(false);
		textFieldTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldTN.setBounds(375, 11, 150, 20);
		textFieldTN.setText(TN);
		getContentPane().add(textFieldTN);
		textFieldTN.setColumns(10);
		
		DateFormat DF = new SimpleDateFormat("yyyy-M-dd");
		textFieldTD = new JTextField();
		textFieldTD.setEditable(false);
		textFieldTD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldTD.setBounds(375, 47, 150, 20);
		textFieldTD.setText(DF.format(D));
		getContentPane().add(textFieldTD);
		textFieldTD.setColumns(10);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listMed.setModel(PSO.getMedicinesList());
				listBN.setModel(PSO.getBatchList());
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRefresh.setBounds(535, 10, 89, 23);
		getContentPane().add(btnRefresh);
		
		JLabel lblMedicine = new JLabel("Medicine:");
		lblMedicine.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMedicine.setBounds(10, 83, 75, 17);
		getContentPane().add(lblMedicine);
		
		textFieldMed = new JTextField();
		textFieldMed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMed.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				lblStock.setText(PSO.getStock(textFieldMed.getText()));
			}
		});
		textFieldMed.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent k) {
				if(textFieldMed.getText().equals("")){
					scrollPaneMed.setVisible(false);
				}
				else{
					scrollPaneMed.setVisible(true);
					String str = textFieldMed.getText();
					int index = listMed.getNextMatch(str, 0, Position.Bias.Forward);
					if(index != -1){
						listMed.ensureIndexIsVisible(index);
						listMed.setSelectedIndex(index);
					}
					if(k.getKeyCode() == k.VK_ENTER){
						textFieldMed.setText(listMed.getSelectedValue().toString());
						scrollPaneMed.setVisible(false);
					}
					if(k.getKeyCode() == k.VK_DOWN){
						listMed.grabFocus();
					}
				}
			}
		});
		textFieldMed.setBounds(10, 111, 150, 20);
		getContentPane().add(textFieldMed);
		textFieldMed.setColumns(10);

		PSO = new ProcessSale();
		listMed = new JList();
		listMed.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode() == k.VK_ENTER){
					textFieldMed.setText(listMed.getSelectedValue().toString());
					textFieldMed.grabFocus();
					scrollPaneMed.setVisible(false);
				}
			}
		});
		listMed.setModel(PSO.getMedicinesList());
		
		scrollPaneMed = new JScrollPane(listMed);
		scrollPaneMed.setBounds(10, 130, 150, 50);
		scrollPaneMed.setVisible(false);
		getContentPane().add(scrollPaneMed);
		
		JLabel lblBatchNo = new JLabel("Batch No:");
		lblBatchNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBatchNo.setBounds(170, 83, 75, 17);
		getContentPane().add(lblBatchNo);
		
		textFieldBN = new JTextField();
		textFieldBN.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!(textFieldBN.getText().equals(""))){
					String[] temp = new String[2];
					temp = PSO.getMedDet(textFieldMed.getText(), textFieldBN.getText());
					textFieldED.setText(temp[0]);
					textFieldMRP.setText(temp[1]);
				}
			}
		});
		textFieldBN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent k) {
				if(textFieldBN.getText().equals("")){
					scrollPaneBN.setVisible(false);
				}
				else{
					scrollPaneBN.setVisible(true);
					String str = textFieldBN.getText();
					int index = listBN.getNextMatch(str, 0, Position.Bias.Forward);
					if(index != -1){
						listBN.ensureIndexIsVisible(index);
						listBN.setSelectedIndex(index);
					}
					if(k.getKeyCode() == k.VK_ENTER){
						textFieldBN.setText(listBN.getSelectedValue().toString());
						scrollPaneBN.setVisible(false);
					}
					if(k.getKeyCode() == k.VK_DOWN){
						listBN.grabFocus();
					}
				}
			}
		});
		textFieldBN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBN.setBounds(170, 111, 150, 20);
		getContentPane().add(textFieldBN);
		textFieldBN.setColumns(10);
		
		listBN = new JList();
		listBN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode() == k.VK_ENTER){
					textFieldBN.setText(listBN.getSelectedValue().toString());
					textFieldBN.grabFocus();
					scrollPaneBN.setVisible(false);
				}
			}
		});
		listBN.setModel(PSO.getBatchList());
		
		scrollPaneBN = new JScrollPane(listBN);
		scrollPaneBN.setBounds(170, 130, 150, 50);
		scrollPaneBN.setVisible(false);
		getContentPane().add(scrollPaneBN);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date:");
		lblExpiryDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExpiryDate.setBounds(330, 83, 89, 17);
		getContentPane().add(lblExpiryDate);
		
		textFieldED = new JTextField();
		textFieldED.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldED.setBounds(330, 111, 150, 20);
		getContentPane().add(textFieldED);
		textFieldED.setColumns(10);
		
		JLabel lblPack = new JLabel("Pack:");
		lblPack.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPack.setBounds(490, 83, 46, 17);
		getContentPane().add(lblPack);
		
		textFieldPack = new JTextField();
		textFieldPack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPack.setBounds(490, 111, 75, 20);
		getContentPane().add(textFieldPack);
		textFieldPack.setColumns(10);
		
		JLabel lblMrp = new JLabel("MRP:");
		lblMrp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMrp.setBounds(575, 83, 46, 17);
		getContentPane().add(lblMrp);
		
		textFieldMRP = new JTextField();
		textFieldMRP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMRP.setBounds(575, 111, 75, 20);
		getContentPane().add(textFieldMRP);
		textFieldMRP.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean checkvalid = checkValid();
				if(checkvalid == true){
					addTableData();
					DTMO.fireTableDataChanged();
					calNetAmount();
					textFieldMed.setText("");
					textFieldBN.setText("");
					textFieldED.setText("");
					textFieldPack.setText("");
					textFieldMRP.setText("");
				}
				else{
					JOptionPane.showMessageDialog(null, "all fields are mandatory.");
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(660, 111, 89, 23);
		getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableSE.getSelectedRow() != -1){
					DTMO.removeRow(tableSE.getSelectedRow());
					calNetAmount();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(759, 110, 89, 23);
		getContentPane().add(btnDelete);
		
		row = new Vector<String>();
		rowData = new Vector<Vector>();
		columnNames = new Vector<String>();
		columnNames.addElement("Medicines");
		columnNames.addElement("Batch no");
		columnNames.addElement("Expiry date");
		columnNames.addElement("Pack");
		columnNames.addElement("MRP");
		columnNames.addElement("Amount");
		DTMO = new DefaultTableModel(rowData, columnNames);
		tableSE = new JTable(DTMO);
		tableSE.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableSE.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		
		scrollPaneTable = new JScrollPane(tableSE);
		scrollPaneTable.setBounds(10, 142, 850, 300);
		getContentPane().add(scrollPaneTable);
		
		lblStock = new JLabel("Stock: 0");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblStock.setBounds(10, 453, 200, 50);
		getContentPane().add(lblStock);
		
		JLabel lblTax = new JLabel("Tax:");
		lblTax.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTax.setBounds(600, 453, 100, 17);
		getContentPane().add(lblTax);
		
		textFieldTax = new JTextField();
		textFieldTax.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				calNetAmount();
			}
		});
		textFieldTax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldTax.setBounds(710, 453, 150, 20);
		textFieldTax.setText("0");
		getContentPane().add(textFieldTax);
		textFieldTax.setColumns(10);
		
		JLabel lblNetAmount = new JLabel("Net Amount:");
		lblNetAmount.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNetAmount.setBounds(600, 480, 100, 17);
		getContentPane().add(lblNetAmount);
		
		textFieldNetA = new JTextField();
		textFieldNetA.setEditable(false);
		textFieldNetA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNetA.setBounds(710, 480, 150, 20);
		getContentPane().add(textFieldNetA);
		textFieldNetA.setColumns(10);
		
		JButton btnSale = new JButton("Sale");
		btnSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				transno = textFieldTN.getText();
				transdate = textFieldTD.getText();
				tax = textFieldTax.getText();
				amt = textFieldNetA.getText();
				if(tax.equals("") || amt.equals("")){
					JOptionPane.showMessageDialog(null, "All fields are mandatory.");
				}
				else{
					processData();
				}
			}
		});
		btnSale.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSale.setBounds(710, 511, 150, 23);
		getContentPane().add(btnSale);
		

	}
	
	public boolean checkValid(){
		boolean flag = false;
		medicine = textFieldMed.getText();
		batchno = textFieldBN.getText();
		expirydate = textFieldED.getText();
		mrp = textFieldMRP.getText();
		pack = textFieldPack.getText();
		if(medicine.equals("") || batchno.equals("") || expirydate.equals("") || mrp.equals("") || pack.equals("")){
			flag = false;
		}
		else{
			flag = true;
		}
		return flag;
	}
	
	public void addTableData(){
		row = new Vector<String>();
		row.addElement(medicine);
		row.addElement(batchno);
		row.addElement(expirydate);
		row.addElement(pack);
		row.addElement(mrp);
		row.addElement(""+(Integer.parseInt(pack) * Float.parseFloat(mrp)));
		rowData.addElement(row);
	}
	
	public void calNetAmount(){
		netamt = 0;
		for(int i = 0; i < tableSE.getRowCount(); i++){
			float temp = Float.parseFloat(tableSE.getValueAt(i, 5).toString());
			netamt += temp;
		}
		float tax = Float.parseFloat(textFieldTax.getText().toString());
		netamt += ((tax / 100) * netamt);
		textFieldNetA.setText("" + netamt);
	}
	
	public void processData(){
		PSO.addTransaction(transno, transdate, tax, amt);
		PSO.addSale(tableSE);
		JOptionPane.showMessageDialog(null, "Trasaction successfull.");
		Date D = new Date();
		String TN = "TN" + D.getYear() + "" + D.getMonth() + "" + D.getDay() + "" + D.getHours() + "" + D.getMinutes() + "" + D.getSeconds();
		textFieldTN.setText(TN);
		textFieldNetA.setText("");
		rowData.removeAllElements();
		DTMO.fireTableDataChanged();
	}
}
