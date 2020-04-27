package ui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.EventQueue;
import net.miginfocom.swing.MigLayout;
import user.Customer;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

class SignUp {
	
	public SignUp() {
		initialize();
	}
	
	private static JFrame frame; // main java swing frame

	// header object declarations
	private static JPanel headerPanel; // panel for header
	// center object declarations
	// mainly used for showing the 'action', or the sorting process
	private static JPanel centerPanel; // panel for center

	// footer object declarations
	private static JPanel optionsPanel;

	private static JButton btnParkingRUs;
	private static JButton btnAbout;
	private static JLabel lblMadeByGladys;
	private static JLabel lblSignUp;
	private static JLabel lblPassword;
	private static JLabel lblEmail;
	private static JButton btnEnter;
	private static JTextField txtEmail;
	private static JTextField txtEnterPassword;
	private static String email;
	private static String password;
	private static String fName;
	private static String lName;
	private static String dob;
	private static JLabel lblFirstName;
	private static JLabel lblLastName;
	private static JTextField txtFirstName;
	private static JTextField txtLastName;

	static UIController uic = new UIController();
	static Customer cust;
	static Date dobD;
	
	private static JLabel lblDateOfBirth;
	private static JTextField txtDateOfBirth;
	
	public static void initialize() {
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
		btnParkingRUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI gui = new GUI();
			}
		});
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
		
		txtEmail = new JTextField(50);
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblPassword);
		
		txtEnterPassword = new JTextField(50);
		txtEnterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(txtEnterPassword);
		txtEnterPassword.setColumns(10);
		
		lblFirstName = new JLabel("First Name:");
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblFirstName);
		
		txtFirstName = new JTextField();
		txtFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(txtLastName);
		txtLastName.setColumns(10);
		
		lblDateOfBirth = new JLabel("Date of Birth (YY-MM-DD, include \"-\"):");
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblDateOfBirth);
		
		txtDateOfBirth = new JTextField();
		txtDateOfBirth.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(txtDateOfBirth);
		txtDateOfBirth.setColumns(10);
		
		btnEnter = new JButton("Enter");
		centerPanel.add(btnEnter);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				email = txtEmail.getText();
				password = txtEnterPassword.getText();
				fName = txtFirstName.getText();
				lName = txtLastName.getText();
				dob = txtDateOfBirth.getText();
				if (email.length() < 1 || password.length() < 1|| fName.length() < 1 || lName.length() < 1) {
					NullError err = new NullError();
				}
				else if (email.length() > 50 || password.length() > 50 || fName.length() > 50 || lName.length() > 50) {
					LengthError err = new LengthError();
				}
				else if (dob.length() != 8) {
					LengthError err = new LengthError();
				}
				else {
					String[] dobArr = dob.split("-");
					int Ydob = Integer.valueOf(dobArr[0]);
					int Mdob = Integer.valueOf(dobArr[1]);
					int Ddob = Integer.valueOf(dobArr[2]);
					dobD = new Date(Ydob, Mdob, Ddob);
					cust = new Customer(email,password,fName,lName,dobD,false);
					try {
						uic.addCustomer(cust);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Home home = new Home();
					frame.setVisible(false);
				}
			}
		});
		// turn on frame
		frame.setVisible(true);
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