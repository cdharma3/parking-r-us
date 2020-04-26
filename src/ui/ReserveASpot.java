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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class ReserveASpot {

	private JFrame frame;

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
	private static JButton btnEnter;
	private JButton btnReserveASpot;
	private JButton btnMonthlySubscription;
	private JLabel lblReserveASpot;
	private JLabel lblAddress;
	private JLabel lblDate;
	private JLabel lblTime;
	private JLabel lblLicensePlate;
	private JTextField txtEnterParkingAddress;
	private JTextField txtEnterDate;
	private JTextField txtEnterTime;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnTemporary;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtEnterLicensePlate;
	private JButton btnProfile;
	private JButton btnStatistics;
	private Boolean viewStatistics;
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
		headerPanel.setLayout(new MigLayout("", "[123px][75px][][][][][][][][][][][][][][][][]", "[29px][]"));
		
		btnParkingRUs = new JButton("Parking R Us");
		btnParkingRUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
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
		
		btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile profile = new Profile();
				frame.setVisible(false);
			}
		});
		
		viewStatistics = true;
		if (viewStatistics) {
			btnStatistics= new JButton("Statistics");
				btnStatistics.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Statistics statistics = new Statistics();
						frame.setVisible(false);
					}
				});
			headerPanel.add(btnStatistics, "cell 16 0");
		}
		headerPanel.add(btnProfile, "cell 17 0");
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
		
		lblReserveASpot = new JLabel("Reserve A Spot");
		lblReserveASpot.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblReserveASpot);
		
		lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblAddress);
		
		txtEnterParkingAddress = new JTextField();
		txtEnterParkingAddress.setText("Enter Parking Address");
		txtEnterParkingAddress.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(txtEnterParkingAddress);
		txtEnterParkingAddress.setColumns(10);
		
		lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblDate);
		
		txtEnterDate = new JTextField();
		txtEnterDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterDate.setText("Enter Date in MM/DD/YY");
		centerPanel.add(txtEnterDate);
		txtEnterDate.setColumns(10);
		
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
				RAS_CC rasCC = new RAS_CC();
				frame.setVisible(false);
			}
		});
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
