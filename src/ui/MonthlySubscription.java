package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JList;

public class MonthlySubscription {

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
	private static JButton btnEnter;
	private JButton btnReserveASpot;
	private JButton btnMonthlySubscription;
	private JLabel lblAddress;
	private JLabel lblTime;
	private JLabel lblLicensePlate;
	private JTextField txtEnterParkingAddress;
	private JTextField txtEnterTime;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnTemporary;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtEnterLicensePlate;
	private JLabel lblMonth;
	private JTextField txtEnterMonthIn;
	private JLabel lblMonthlySubscription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonthlySubscription window = new MonthlySubscription();
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
	public MonthlySubscription() {
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
		headerPanel.setLayout(new MigLayout("", "[123px][75px][]", "[29px][]"));
		
		btnParkingRUs = new JButton("Parking R Us");
		btnParkingRUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI gui = new GUI();
				frame.setVisible(false);
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
		
		btnReserveASpot = new JButton("Reserve A Spot");
		btnReserveASpot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReserveASpot reserve = new ReserveASpot();
				frame.setVisible(false);
			}
		});
		headerPanel.add(btnReserveASpot, "cell 1 1");
		
		btnMonthlySubscription = new JButton("Monthly Subscription");
		btnMonthlySubscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonthlySubscription mSub = new MonthlySubscription();
				frame.setVisible(false);
			}
		});
		headerPanel.add(btnMonthlySubscription, "cell 2 1");
		frame.getContentPane().add(BorderLayout.SOUTH, optionsPanel);
		
		lblMadeByGladys = new JLabel("Made by Grace Arnold, Chris Dharma, and Gladys Toledo-Rodriguez");
		optionsPanel.add(lblMadeByGladys);
		frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblMonthlySubscription = new JLabel("Monthly Subscription");
		lblMonthlySubscription.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblMonthlySubscription);
		
		lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblAddress);
		
		txtEnterParkingAddress = new JTextField();
		txtEnterParkingAddress.setText("Enter Parking Address");
		txtEnterParkingAddress.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(txtEnterParkingAddress);
		txtEnterParkingAddress.setColumns(10);
		
		lblMonth = new JLabel("Month");
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblMonth);
		
		txtEnterMonthIn = new JTextField();
		txtEnterMonthIn.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterMonthIn.setText("Enter Month in MM Format");
		centerPanel.add(txtEnterMonthIn);
		txtEnterMonthIn.setColumns(10);
		
		lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblTime);
		
		txtEnterTime = new JTextField();
		txtEnterTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterTime.setText("Enter Time in HH:MM");
		centerPanel.add(txtEnterTime);
		txtEnterTime.setColumns(10);
		
		lblLicensePlate = new JLabel("License Plate");
		lblLicensePlate.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblLicensePlate);
		
		rdbtnNewRadioButton = new JRadioButton("Permanent");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(rdbtnNewRadioButton);
		
		rdbtnTemporary = new JRadioButton("Temporary");
		buttonGroup.add(rdbtnTemporary);
		rdbtnTemporary.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(rdbtnTemporary);
		
		txtEnterLicensePlate = new JTextField();
		txtEnterLicensePlate.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterLicensePlate.setText("Enter License Plate");
		centerPanel.add(txtEnterLicensePlate);
		txtEnterLicensePlate.setColumns(10);
		
		btnEnter = new JButton("Enter");
		centerPanel.add(btnEnter);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		// turn on frame
		frame.setVisible(true);
	}
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonthlySubscription window = new MonthlySubscription();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	});
	}

}