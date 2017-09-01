package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Position;

import process.ProcessPurchase;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Purchase extends JInternalFrame {
	private JTextField textFieldTN, textFieldTD, textFieldCN, textFieldSupp, textFieldMed, textFieldMan, textFieldBN, textFieldED, textFieldRate, textFieldMRP, textFieldQTY, textFieldPack, textFieldRNo, textFieldTax, textFieldNetA;
	private JComboBox comboBoxTT;
	private DefaultComboBoxModel<String> DCTT;
	private ProcessPurchase PPO;
	private JScrollPane scrollPaneSL, scrollPaneMed, scrollPaneMan, scrollPaneTable;
	private JList listSL, listMed, listMan;
	private JTable tablePE;
	private DefaultTableModel DTMO;
	private Vector<String> row, columnNames;
	private Vector<Vector> rowData;
	private String medicine, manufacturer, batchno , expirydate, rate, mrp, qty, pack, rackno, transno, transdate, paytype, chequeno, supp, tax, amt;
	private JLabel lblStock;
	private float netamt;
	
	/**
	 * Create the frame.
	 */
	public Purchase() {
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle("Medical Inventory Management: Purchase Entry");
		setBounds(25, 25, 1200, 500);
		getContentPane().setLayout(null);
		
		JLabel lblPurchaseEntry = new JLabel("Purchase Entry");
		lblPurchaseEntry.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblPurchaseEntry.setBounds(10, 11, 331, 61);
		getContentPane().add(lblPurchaseEntry);
		
		JLabel lblTransactionNo = new JLabel("Transaction No:");
		lblTransactionNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTransactionNo.setBounds(351, 11, 122, 17);
		getContentPane().add(lblTransactionNo);
		
		JLabel lblTransactionDate = new JLabel("Transaction Date:");
		lblTransactionDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTransactionDate.setBounds(351, 50, 122, 17);
		getContentPane().add(lblTransactionDate);
		
		// Generating transaction number.
		Date D = new Date();
		String TN = "TN" + D.getYear() + "" + D.getMonth() + "" + D.getDay() + "" + D.getHours() + "" + D.getMinutes() + "" + D.getSeconds();
		
		textFieldTN = new JTextField();
		textFieldTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldTN.setEditable(false);
		textFieldTN.setBounds(483, 11, 150, 20);
		textFieldTN.setText(TN);
		getContentPane().add(textFieldTN);
		textFieldTN.setColumns(10);
		
		// Generating transaction date.
		DateFormat DF = new SimpleDateFormat("yyyy-M-dd");
		textFieldTD = new JTextField();
		textFieldTD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldTD.setEditable(false);
		textFieldTD.setBounds(483, 47, 150, 20);
		textFieldTD.setText(DF.format(D));
		getContentPane().add(textFieldTD);
		textFieldTD.setColumns(10);
		
		JLabel lblTransactionType = new JLabel("Transaction Type:");
		lblTransactionType.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTransactionType.setBounds(643, 11, 130, 17);
		getContentPane().add(lblTransactionType);
		
		JLabel lblChequeNo = new JLabel("Cheque No:");
		lblChequeNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChequeNo.setBounds(643, 50, 130, 17);
		getContentPane().add(lblChequeNo);
		
		// Generating transaction type data.
		DCTT = new DefaultComboBoxModel<String>();
		DCTT.addElement("Cash");
		DCTT.addElement("Cheque");
		
		comboBoxTT = new JComboBox();
		comboBoxTT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxTT.setBounds(783, 11, 150, 20);
		comboBoxTT.setModel(DCTT);
		getContentPane().add(comboBoxTT);
		
		textFieldCN = new JTextField();
		textFieldCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldCN.setBounds(783, 47, 150, 20);
		getContentPane().add(textFieldCN);
		textFieldCN.setColumns(10);
		
		JLabel lblSupplier = new JLabel("Supplier:");
		lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSupplier.setBounds(943, 11, 61, 17);
		getContentPane().add(lblSupplier);
		
		textFieldSupp = new JTextField();
		textFieldSupp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent k) {
				if(textFieldSupp.getText().equals("")){
					scrollPaneSL.setVisible(false);
				}
				else{
					scrollPaneSL.setVisible(true);
					String str = textFieldSupp.getText();
					int index = listSL.getNextMatch(str, 0, Position.Bias.Forward);
					if(index != -1){
						listSL.ensureIndexIsVisible(index);
						listSL.setSelectedIndex(index);
					}
					if(k.getKeyCode() == k.VK_ENTER){
						textFieldSupp.setText(listSL.getSelectedValue().toString());
						scrollPaneSL.setVisible(false);
					}
					if(k.getKeyCode() == k.VK_DOWN){
						listSL.grabFocus();
					}
				}
			}
		});
		textFieldSupp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSupp.setBounds(1014, 11, 150, 20);
		getContentPane().add(textFieldSupp);
		textFieldSupp.setColumns(10);
		
		PPO = new ProcessPurchase();
		
		listSL = new JList();
		listSL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode() == k.VK_ENTER){
					textFieldSupp.setText(listSL.getSelectedValue().toString());
					textFieldSupp.grabFocus();
					scrollPaneSL.setVisible(false);
				}
			}
		});
		listSL.setModel(PPO.getSuppliersList());
		
		scrollPaneSL = new JScrollPane(listSL);
		scrollPaneSL.setBounds(1014, 30, 150, 50);
		scrollPaneSL.setVisible(false);
		getContentPane().add(scrollPaneSL);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listSL.setModel(PPO.getSuppliersList());
				listMed.setModel(PPO.getMedicinesList());
				listMan.setModel(PPO.getManufacturersList());
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRefresh.setBounds(1174, 10, 89, 23);
		getContentPane().add(btnRefresh);
		
		JLabel lblMedicine = new JLabel("Medicine:");
		lblMedicine.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMedicine.setBounds(10, 83, 64, 17);
		getContentPane().add(lblMedicine);
		
		textFieldMed = new JTextField();
		textFieldMed.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				lblStock.setText(PPO.getStock(textFieldMed.getText()));
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
		textFieldMed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMed.setBounds(10, 111, 150, 20);
		getContentPane().add(textFieldMed);
		textFieldMed.setColumns(10);
		
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
		listMed.setModel(PPO.getMedicinesList());
		
		scrollPaneMed = new JScrollPane(listMed);
		scrollPaneMed.setBounds(10, 129, 150, 50);
		scrollPaneMed.setVisible(false);
		getContentPane().add(scrollPaneMed);
		
		JLabel lblManufacturer = new JLabel("Manufacturer:");
		lblManufacturer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblManufacturer.setBounds(170, 83, 98, 17);
		getContentPane().add(lblManufacturer);
		
		textFieldMan = new JTextField();
		textFieldMan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent k) {
				if(textFieldMan.getText().equals("")){
					scrollPaneMan.setVisible(false);
				}
				else{
					scrollPaneMan.setVisible(true);
					String str = textFieldMan.getText();
					int index = listMan.getNextMatch(str, 0, Position.Bias.Forward);
					if(index != -1){
						listMan.ensureIndexIsVisible(index);
						listMan.setSelectedIndex(index);
					}
					if(k.getKeyCode() == k.VK_ENTER){
						textFieldMan.setText(listMan.getSelectedValue().toString());
						scrollPaneMan.setVisible(false);
					}
					if(k.getKeyCode() == k.VK_DOWN){
						listMan.grabFocus();
					}
				}
			}
		});
		textFieldMan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMan.setBounds(170, 111, 150, 20);
		getContentPane().add(textFieldMan);
		textFieldMan.setColumns(10);
		
		listMan = new JList();
		listMan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode() == k.VK_ENTER){
					textFieldMan.setText(listMan.getSelectedValue().toString());
					textFieldMan.grabFocus();
					scrollPaneMan.setVisible(false);
				}
			}
		});
		listMan.setModel(PPO.getManufacturersList());
		
		scrollPaneMan = new JScrollPane(listMan);
		scrollPaneMan.setBounds(170, 129, 150, 50);
		scrollPaneMan.setVisible(false);
		getContentPane().add(scrollPaneMan);
		
		JLabel lblBatchNo = new JLabel("Batch No:");
		lblBatchNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBatchNo.setBounds(330, 83, 69, 17);
		getContentPane().add(lblBatchNo);
		
		textFieldBN = new JTextField();
		textFieldBN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBN.setBounds(330, 111, 150, 20);
		getContentPane().add(textFieldBN);
		textFieldBN.setColumns(10);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date:");
		lblExpiryDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExpiryDate.setBounds(490, 83, 84, 17);
		getContentPane().add(lblExpiryDate);
		
		textFieldED = new JTextField();
		textFieldED.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldED.setBounds(490, 111, 150, 20);
		getContentPane().add(textFieldED);
		textFieldED.setColumns(10);
		
		JLabel lblRate = new JLabel("Rate:");
		lblRate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRate.setBounds(652, 83, 46, 17);
		getContentPane().add(lblRate);
		
		textFieldRate = new JTextField();
		textFieldRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldRate.setBounds(650, 111, 75, 20);
		getContentPane().add(textFieldRate);
		textFieldRate.setColumns(10);
		
		JLabel lblMrp = new JLabel("MRP:");
		lblMrp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMrp.setBounds(735, 83, 46, 17);
		getContentPane().add(lblMrp);
		
		textFieldMRP = new JTextField();
		textFieldMRP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldMRP.setBounds(735, 111, 75, 20);
		getContentPane().add(textFieldMRP);
		textFieldMRP.setColumns(10);
		
		JLabel lblQty = new JLabel("QTY:");
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQty.setBounds(820, 83, 46, 17);
		getContentPane().add(lblQty);
		
		textFieldQTY = new JTextField();
		textFieldQTY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldQTY.setBounds(820, 111, 75, 20);
		getContentPane().add(textFieldQTY);
		textFieldQTY.setColumns(10);
		
		JLabel lblPack = new JLabel("Pack:");
		lblPack.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPack.setBounds(905, 83, 46, 17);
		getContentPane().add(lblPack);
		
		textFieldPack = new JTextField();
		textFieldPack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPack.setBounds(905, 111, 75, 20);
		getContentPane().add(textFieldPack);
		textFieldPack.setColumns(10);
		
		JLabel lblRackNo = new JLabel("Rack No:");
		lblRackNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRackNo.setBounds(990, 83, 69, 17);
		getContentPane().add(lblRackNo);
		
		textFieldRNo = new JTextField();
		textFieldRNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldRNo.setBounds(990, 111, 75, 20);
		getContentPane().add(textFieldRNo);
		textFieldRNo.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean checkvalid = checkValid();
				if(checkvalid == true){
					addTableData();
					DTMO.fireTableDataChanged();
					calNetAmount();
					textFieldMed.setText("");
					textFieldMan.setText("");
					textFieldBN.setText("");
					textFieldED.setText("");
					textFieldRate.setText("");
					textFieldMRP.setText("");
					textFieldQTY.setText("");
					textFieldPack.setText("");
					textFieldRNo.setText("");
				}
				else{
					JOptionPane.showMessageDialog(null, "All fields are mandatory.");
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(1075, 111, 89, 23);
		getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tablePE.getSelectedRow() != -1){
					DTMO.removeRow(tablePE.getSelectedRow());
					calNetAmount();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(1174, 112, 89, 23);
		getContentPane().add(btnDelete);
		
		row = new Vector<String>();
		rowData = new Vector<Vector>();
		columnNames = new Vector<String>();
		columnNames.addElement("Medicines");
		columnNames.addElement("Manufacturer");
		columnNames.addElement("Batch No.");
		columnNames.addElement("Expiry Date");
		columnNames.addElement("Rate");
		columnNames.addElement("MRP");
		columnNames.addElement("QTY");
		columnNames.addElement("Pack");
		columnNames.addElement("Rack no.");
		columnNames.addElement("Amount");
		DTMO = new DefaultTableModel(rowData, columnNames);
		tablePE = new JTable(DTMO);
		tablePE.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablePE.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		
		scrollPaneTable = new JScrollPane(tablePE);
		scrollPaneTable.setBounds(10, 142, 1300, 300);
		getContentPane().add(scrollPaneTable);
		
		lblStock = new JLabel("Stock: 0");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblStock.setBounds(10, 453, 200, 50);
		getContentPane().add(lblStock);
		
		JLabel lblTax = new JLabel("Tax:");
		lblTax.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTax.setBounds(1104, 453, 46, 17);
		getContentPane().add(lblTax);
		
		textFieldTax = new JTextField();
		textFieldTax.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!(textFieldTax.getText().equals(""))){
					calNetAmount();
				}
			}
		});
		textFieldTax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldTax.setBounds(1160, 453, 150, 20);
		textFieldTax.setText("0");
		getContentPane().add(textFieldTax);
		textFieldTax.setColumns(10);
		
		JLabel lblNetAmount = new JLabel("Net Amount:");
		lblNetAmount.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNetAmount.setBounds(1052, 480, 98, 17);
		getContentPane().add(lblNetAmount);
		
		textFieldNetA = new JTextField();
		textFieldNetA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNetA.setBounds(1160, 480, 150, 20);
		textFieldNetA.setEditable(false);
		getContentPane().add(textFieldNetA);
		textFieldNetA.setColumns(10);
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				transno = textFieldTN.getText();
				transdate = textFieldTD.getText();
				paytype = comboBoxTT.getSelectedItem().toString();
				chequeno = textFieldCN.getText();
				supp = textFieldSupp.getText();
				tax = textFieldTax.getText();
				amt = textFieldNetA.getText();
				if(supp.equals("") || tax.equals("") ||amt.equals("")){
					JOptionPane.showMessageDialog(null, "All fields are mandatory.");
				}
				else{
					if(paytype.equals("Cheque")){
						if(chequeno.equals("")){
							JOptionPane.showMessageDialog(null, "All fields are mandatory.");
						}
						else{
							processData();
						}
					}
					else{
						processData();
					}
				}
			}
		});
		btnPurchase.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPurchase.setBounds(1160, 511, 150, 23);
		getContentPane().add(btnPurchase);

	}
	
	public void addTableData(){
		row = new Vector<String>();
		row.addElement(medicine);
		row.addElement(manufacturer);
		row.addElement(batchno);
		row.addElement(expirydate);
		row.addElement(rate);
		row.addElement(mrp);
		row.addElement(qty);
		row.addElement(pack);
		row.addElement(rackno);
		row.addElement(""+(Float.parseFloat(rate) * Float.parseFloat(qty)));
		rowData.addElement(row);
	}
	
	public boolean checkValid(){
		boolean flag = false;
		medicine = textFieldMed.getText();
		manufacturer = textFieldMan.getText();
		batchno = textFieldBN.getText();
		expirydate = textFieldED.getText();
		rate = textFieldRate.getText();
		mrp = textFieldMRP.getText();
		qty = textFieldQTY.getText();
		pack = textFieldPack.getText();
		rackno = textFieldRNo.getText();
		if(medicine.equals("") || manufacturer.equals("") || batchno.equals("") || expirydate.equals("") || rate.equals("") || mrp.equals("") || qty.equals("") || pack.equals("") || rackno.equals("")){
			flag = false;
		}
		else{
			flag = true;
		}
		return flag;
	}
	
	public void calNetAmount(){
		netamt = 0;
		for(int i = 0; i < tablePE.getRowCount(); i++){
			float temp = Float.parseFloat(tablePE.getValueAt(i, 9).toString());
			netamt += temp;
		}
		float tax = Float.parseFloat(textFieldTax.getText().toString());
		netamt += ((tax / 100) * netamt);
		textFieldNetA.setText("" + netamt);
	}
	
	public void processData(){
		PPO.addTransaction(transno, transdate, paytype, chequeno, tax, amt);
		PPO.addPurchase(tablePE, supp);
		JOptionPane.showMessageDialog(null, "Trasaction successfull.");
		Date D = new Date();
		String TN = "TN" + D.getYear() + "" + D.getMonth() + "" + D.getDay() + "" + D.getHours() + "" + D.getMinutes() + "" + D.getSeconds();
		textFieldTN.setText(TN);
		comboBoxTT.setSelectedIndex(0);
		textFieldCN.setText("");
		textFieldSupp.setText("");
		textFieldNetA.setText("");
		rowData.removeAllElements();
		DTMO.fireTableDataChanged();
	}
}
