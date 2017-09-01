package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import process.ProcessSettings;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Settings extends JInternalFrame {

	private JPanel contentPane;
	private JTextField textFieldUN;
	private JTextField textFieldOP;
	private JButton btnChange;
	private JPasswordField textFieldNP;
	private JPasswordField textFieldCP;

	/**
	 * Create the frame.
	 */
	public Settings() {
		setIconifiable(true);
		setClosable(true);
		setResizable(false);
		setTitle("Medical Inventory Management: Settings");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChangePassword.setBounds(10, 11, 200, 30);
		contentPane.add(lblChangePassword);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(34, 52, 150, 25);
		contentPane.add(lblUsername);
		
		JLabel lblOldPassword = new JLabel("Old Password:");
		lblOldPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOldPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOldPassword.setBounds(34, 88, 150, 25);
		contentPane.add(lblOldPassword);
		
		JLabel lblNewPassord = new JLabel("New Password:");
		lblNewPassord.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPassord.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewPassord.setBounds(34, 124, 150, 25);
		contentPane.add(lblNewPassord);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirmPassword.setBounds(34, 160, 150, 25);
		contentPane.add(lblConfirmPassword);
		
		textFieldUN = new JTextField();
		textFieldUN.setBounds(194, 54, 180, 25);
		contentPane.add(textFieldUN);
		textFieldUN.setColumns(10);
		
		textFieldOP = new JTextField();
		textFieldOP.setBounds(194, 90, 180, 25);
		contentPane.add(textFieldOP);
		textFieldOP.setColumns(10);
		
		JLabel lblCPVal = new JLabel("");
		lblCPVal.setForeground(Color.RED);
		lblCPVal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCPVal.setBounds(384, 171, 46, 14);
		contentPane.add(lblCPVal);
		
		JLabel lblUnVal = new JLabel("");
		lblUnVal.setForeground(Color.RED);
		lblUnVal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnVal.setBounds(384, 59, 46, 14);
		contentPane.add(lblUnVal);
		
		JLabel lblOPVal = new JLabel("");
		lblOPVal.setForeground(Color.RED);
		lblOPVal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOPVal.setBounds(384, 95, 46, 14);
		contentPane.add(lblOPVal);
		
		JLabel lblNPVal = new JLabel("");
		lblNPVal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNPVal.setForeground(Color.RED);
		lblNPVal.setBounds(384, 131, 46, 14);
		contentPane.add(lblNPVal);
		
		btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username, oldpassword, newpassword, confirmpassword;
				username = textFieldUN.getText();
				oldpassword = textFieldOP.getText();
				newpassword = textFieldNP.getText();
				confirmpassword = textFieldCP.getText();
				if(username.equals("") || oldpassword.equals("") || newpassword.equals("") || confirmpassword.equals("")){
					if(username.equals("")){
						lblUnVal.setText("*");
					}
					else{
						lblUnVal.setText("");
					}
					if(oldpassword.equals("")){
						lblOPVal.setText("*");
					}
					else{
						lblOPVal.setText("");
					}
					if(newpassword.equals("")){
						lblNPVal.setText("*");
					}
					else{
						lblNPVal.setText("");
					}
					if(confirmpassword.equals("")){
						lblCPVal.setText("*");
					}
					else{
						lblCPVal.setText("");
					}
				}
				else{
					lblUnVal.setText("");
					lblOPVal.setText("");
					lblNPVal.setText("");
					lblCPVal.setText("");
					if(newpassword.equals(confirmpassword)){
						ProcessSettings PSO = new ProcessSettings();
						PSO.changePassword(username, oldpassword, newpassword);
						textFieldUN.setText("");
						textFieldOP.setText("");
						textFieldNP.setText("");
						textFieldCP.setText("");
					}
					else{
						lblCPVal.setText("*");
					}
				}
			}
		});
		btnChange.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChange.setBounds(285, 200, 89, 25);
		contentPane.add(btnChange);
		
		textFieldNP = new JPasswordField();
		textFieldNP.setBounds(194, 126, 180, 25);
		contentPane.add(textFieldNP);
		
		textFieldCP = new JPasswordField();
		textFieldCP.setBounds(194, 162, 180, 25);
		contentPane.add(textFieldCP);
		
	}
}
