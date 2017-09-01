import javax.swing.JOptionPane;

import gui.Login;

public class SystemStart {
	public static void main(String args[]){
		try{
			Login LO = new Login();
			LO.setVisible(true);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
