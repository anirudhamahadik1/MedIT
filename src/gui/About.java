package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


public class About extends JFrame {
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public About() {
		setResizable(false);
		setTitle("Medical Inventory Management: About");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSoftwareName = new JLabel("Sofware Name:");
		lblSoftwareName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoftwareName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSoftwareName.setBounds(10, 11, 130, 25);
		contentPane.add(lblSoftwareName);
		
		JLabel lblVerrsion = new JLabel("Software Version:");
		lblVerrsion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVerrsion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVerrsion.setBounds(10, 47, 130, 25);
		contentPane.add(lblVerrsion);
		
		JLabel lblCompanyName = new JLabel("Company Name:");
		lblCompanyName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCompanyName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCompanyName.setBounds(10, 83, 130, 25);
		contentPane.add(lblCompanyName);
		
		JLabel lblContact = new JLabel("Contact:");
		lblContact.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContact.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContact.setBounds(10, 119, 130, 25);
		contentPane.add(lblContact);
		
		JLabel lblMedicalInventoryManagement = new JLabel("Medical Inventory Management");
		lblMedicalInventoryManagement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMedicalInventoryManagement.setBounds(150, 11, 200, 25);
		contentPane.add(lblMedicalInventoryManagement);
		
		JLabel label = new JLabel("1.0.0");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(150, 47, 200, 25);
		contentPane.add(label);
		
		JLabel lblItmGroup = new JLabel("iT&M GROUP");
		lblItmGroup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItmGroup.setBounds(150, 83, 200, 25);
		contentPane.add(lblItmGroup);
		
		JLabel label_1 = new JLabel("(+91) 8793 298 455");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(150, 119, 200, 25);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("(+91) 9850 446 300");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(150, 155, 200, 25);
		contentPane.add(label_2);
	}

}
