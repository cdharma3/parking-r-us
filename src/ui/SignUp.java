package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

public class SignUp {

	private JFrame frame;
	// header object declarations
		private static JPanel headerPanel; // panel for header
		// footer object declarations
		private static JPanel optionsPanel;
		
		private static JButton btnParkingRUs;
		private static JButton btnAbout;
		private JLabel lblMadeByGladys;
		private JPanel centerPanel;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Initialize GUI
		frame = new JFrame("Parking R Us");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setSize(900, 500);
		frame.setResizable(false);
		System.out.println("GUI Initialized!"); // debug

		// Create panel for top
		headerPanel = new JPanel();

		// Creating menu panel at bottom
		optionsPanel = new JPanel();

		// align and add panes to frame
		frame.getContentPane().add(BorderLayout.NORTH, headerPanel);
		headerPanel.setLayout(new MigLayout("", "[123px][75px]", "[29px][]"));
		
		btnParkingRUs = new JButton("Parking R Us");
		headerPanel.add(btnParkingRUs, "cell 0 0,alignx left,aligny top");
		
		btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.NewScreen();
			}
		});
		headerPanel.add(btnAbout, "cell 0 1,alignx left,aligny top");
		frame.getContentPane().add(BorderLayout.SOUTH, optionsPanel);
		
		lblMadeByGladys = new JLabel("Made by Grace Arnold, Chris Dharma, and Gladys Toledo-Rodriguez");
		optionsPanel.add(lblMadeByGladys);
		
		centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
	}
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	});
	}

}
