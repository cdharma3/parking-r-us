package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import net.miginfocom.swing.MigLayout;

public class ReserveASpot {

	private JFrame frame;

	// header object declarations
	private static JPanel headerPanel; // panel for header
	// center object declarations
	// mainly used for showing the 'action', or the sorting process
	private static JPanel centerPanel; // panel for center

	// footer object declarations
	private static JPanel optionsPanel;

	private static int[] randData; // stores current array to be sorted
	private static JButton btnParkingRUs;
	private static JButton btnAbout;
	private static JLabel lblMadeByGladys;
	private static JLabel lblSignUp;
	private static JLabel lblPassword;
	private static JLabel lblEmail;
	private static JButton btnEnter;
	private static JTextField txtEmail;
	private static JTextField txtEnterPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReserveASpot window = new ReserveASpot();
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
	public ReserveASpot() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Parking R Us");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setSize(900, 500);
		frame.setResizable(false);
		System.out.println("GUI Initialized!"); // debug

		// Create panel for top
		headerPanel = new JPanel();

		// Create panel for center
		centerPanel = new JPanel();
		centerPanel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));


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
		frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblSignUp = new JLabel("Sign Up");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblSignUp);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setText("Enter Email");
		centerPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblPassword);
		
		txtEnterPassword = new JTextField();
		txtEnterPassword.setText("Enter Password");
		txtEnterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(txtEnterPassword);
		txtEnterPassword.setColumns(10);
		
		btnEnter = new JButton("Enter");
		centerPanel.add(btnEnter);
		// turn on frame
		frame.setVisible(true);
	}
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReserveASpot window = new ReserveASpot();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	});
	}

}