package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import process.ProcessLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUn;
	private JPasswordField passwordField;
	private JLabel lblUnValid, lblPwdValid, lblError;

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setTitle("Medical Inventory Management: Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(40, 26, 100, 30);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(50, 82, 90, 30);
		contentPane.add(lblPassword);
		
		textFieldUn = new JTextField();
		textFieldUn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setLabelFor(textFieldUn);
		textFieldUn.setBounds(150, 27, 180, 30);
		contentPane.add(textFieldUn);
		textFieldUn.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode() == k.VK_ENTER){
					checkValid();
				}
			}
		});
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setLabelFor(passwordField);
		passwordField.setBounds(150, 83, 180, 30);
		contentPane.add(passwordField);

		lblUnValid = new JLabel();
		lblUnValid.setForeground(Color.RED);
		lblUnValid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnValid.setBounds(340, 26, 46, 30);
		contentPane.add(lblUnValid);
		
		lblPwdValid = new JLabel();
		lblPwdValid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPwdValid.setForeground(Color.RED);
		lblPwdValid.setBounds(340, 82, 46, 30);
		contentPane.add(lblPwdValid);

		lblError = new JLabel("");
		lblError.setFont(new Font("Arial", Font.BOLD, 14));
		lblError.setForeground(Color.RED);
		lblError.setBounds(50, 176, 280, 30);
		contentPane.add(lblError);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode() == k.VK_ENTER){
					checkValid();
				}
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkValid();
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(241, 135, 89, 30);
		contentPane.add(btnLogin);
	}
	
	public void checkValid(){
		try{
			String username, password;
			username = textFieldUn.getText();
			password = passwordField.getText();
			if(username.equals("") || password.equals("")){
				if(username.equals("") && password.equals("")){
					lblUnValid.setText("*");
					lblPwdValid.setText("*");
				}
				else if(username.equals("")){
					lblUnValid.setText("*");
					lblPwdValid.setText("");
				}
				else{
					lblUnValid.setText("");
					lblPwdValid.setText("*");
				}
			}
			else{
				ProcessLogin PLO = new ProcessLogin();
				boolean flag = PLO.getauth(username, password);
				if(flag == true){
					MainParent MPO = new MainParent();
					MPO.setVisible(true);
					setVisible(false);
				}
				else{
					lblError.setText("Invalid username or password.");
				}
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
