package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import process.ProcessManufacturer;
import process.ProcessSupplier;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Manufacturers extends JInternalFrame {
	private JTextField textFieldAddName;
	private JTextField textFieldAddCon;
	private JTextField textFieldAddA;

	/**
	 * Create the frame.
	 */
	public Manufacturers() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Medical Inventory Management: Manufacturers");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 414, 249);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Add Details", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(10, 32, 100, 20);
		panel.add(lblName);
		
		JLabel lblContact = new JLabel("Contact:");
		lblContact.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContact.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContact.setBounds(10, 73, 100, 20);
		panel.add(lblContact);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddress.setBounds(10, 113, 100, 20);
		panel.add(lblAddress);
		
		textFieldAddName = new JTextField();
		textFieldAddName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldAddName.setBounds(120, 32, 230, 25);
		panel.add(textFieldAddName);
		textFieldAddName.setColumns(10);
		
		textFieldAddCon = new JTextField();
		textFieldAddCon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldAddCon.setBounds(120, 73, 230, 25);
		panel.add(textFieldAddCon);
		textFieldAddCon.setColumns(10);
		
		textFieldAddA = new JTextField();
		textFieldAddA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldAddA.setBounds(120, 113, 230, 25);
		panel.add(textFieldAddA);
		textFieldAddA.setColumns(10);
		
		JLabel lblANVal = new JLabel("");
		lblANVal.setForeground(Color.RED);
		lblANVal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblANVal.setBounds(360, 37, 46, 14);
		panel.add(lblANVal);
		
		JLabel lblACVal = new JLabel("");
		lblACVal.setForeground(Color.RED);
		lblACVal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblACVal.setBounds(360, 78, 46, 14);
		panel.add(lblACVal);
		
		JLabel lblAAVal = new JLabel("");
		lblAAVal.setForeground(Color.RED);
		lblAAVal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAAVal.setBounds(360, 118, 46, 14);
		panel.add(lblAAVal);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String addname, addcontact, addaddress;
				addname = textFieldAddName.getText();
				addcontact = textFieldAddCon.getText();
				addaddress = textFieldAddA.getText();
				if(addname.equals("") || addcontact.equals("") || addaddress.equals("")){
					if(addname.equals("")){
						lblANVal.setText("*");
					}
					else{
						lblANVal.setText("");
					}
					if(addcontact.equals("")){
						lblACVal.setText("*");
					}
					else{
						lblACVal.setText("");
					}
					if(addaddress.equals("")){
						lblAAVal.setText("*");
					}
					else{
						lblAAVal.setText("");
					}
				}
				else{
					lblANVal.setText("");
					lblACVal.setText("");
					lblAAVal.setText("");
					ProcessManufacturer PMO = new ProcessManufacturer();
					PMO.addDetails(addname, addcontact, addaddress);
					textFieldAddName.setText("");
					textFieldAddCon.setText("");
					textFieldAddA.setText("");
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(261, 164, 89, 25);
		panel.add(btnAdd);

	}
}
