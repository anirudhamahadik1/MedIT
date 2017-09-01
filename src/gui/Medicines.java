package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import process.ProcessMedicine;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Medicines extends JInternalFrame {
	private JTextField textFieldName;

	/**
	 * Create the frame.
	 */
	public Medicines() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Medical Inventory Management: Medicines");
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout(null);
		
		JLabel lblAddMedicineDetails = new JLabel("Add Medicine Details");
		lblAddMedicineDetails.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAddMedicineDetails.setBounds(10, 11, 250, 25);
		getContentPane().add(lblAddMedicineDetails);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(19, 63, 90, 25);
		getContentPane().add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldName.setBounds(119, 65, 250, 25);
		getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblMNVal = new JLabel("");
		lblMNVal.setForeground(Color.RED);
		lblMNVal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMNVal.setBounds(370, 70, 46, 14);
		getContentPane().add(lblMNVal);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textFieldName.getText();
				if(name.equals("")){
					lblMNVal.setText("*");
				}
				else{
					lblMNVal.setText("");
					ProcessMedicine PMO = new ProcessMedicine();
					PMO.addMedicine(name);
					textFieldName.setText("");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(280, 130, 89, 25);
		getContentPane().add(btnNewButton);

	}
}
