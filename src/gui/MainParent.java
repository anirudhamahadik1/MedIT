package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainParent extends JFrame {

	JDesktopPane JDPO;

	/**
	 * Create the frame.
	 */
	public MainParent() {
		setTitle("Medical Inventory Management: Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(25, 25, 1300, 700);
		JDPO = new JDesktopPane();
		JDPO.setBackground(Color.LIGHT_GRAY);
		setContentPane(JDPO);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu mnCheck = new JMenu("Purchase");
		mnCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Purchase PO = new Purchase();
				PO.setVisible(true);
				JDPO.add(PO);
			}
		});
		menuBar.add(mnCheck);
		
		JMenu mnSale = new JMenu("Sale");
		mnSale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Sale SO = new Sale();
				SO.setVisible(true);
				JDPO.add(SO);
			}
		});
		menuBar.add(mnSale);
		
		JMenu mnMedicines = new JMenu("Medicines");
		mnMedicines.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Medicines MO = new Medicines();
				MO.setVisible(true);
				JDPO.add(MO);
			}
		});
		menuBar.add(mnMedicines);
		
		JMenu mnManufacurer = new JMenu("Manufacurers");
		mnManufacurer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Manufacturers MO = new Manufacturers();
				MO.setVisible(true);
				JDPO.add(MO);
			}
		});
		menuBar.add(mnManufacurer);
		
		JMenu mnSupplier = new JMenu("Suppliers");
		mnSupplier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Suppliers SO = new Suppliers();
				SO.setVisible(true);
				JDPO.add(SO);
			}
		});
		menuBar.add(mnSupplier);
		
		JMenu mnReport = new JMenu("Reports");
		menuBar.add(mnReport);
		
		JMenuItem mntmDailyReport = new JMenuItem("Monthly Report");
		mntmDailyReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MonthlyReport MRO = new MonthlyReport();
				MRO.setVisible(true);
				JDPO.add(MRO);
			}
		});
		mnReport.add(mntmDailyReport);
		
		JMenu mnSetting = new JMenu("Settings");
		mnSetting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Settings SO = new Settings();
				SO.setVisible(true);
				JDPO.add(SO);
			}
		});
		menuBar.add(mnSetting);
		
		JMenu mnAbout = new JMenu("About");
		mnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				About AO = new About();
				AO.setVisible(true);
			}
		});
		menuBar.add(mnAbout);
	}
}
