package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
import parking.Reservation;
import java.awt.Color;

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
	private String address;
	private String date;
	private String startTime;
	private String endTime;
	private String licensePlate;
	private JTextField txtEnterEndTime;
	static UIController uic = new UIController();
	private UUID pid;
	private JLabel lblEmail;
	private JTextField txtEnterEmail;
	private String email;
	private Reservation reservation;
	private Date start;
	private Date end;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		this.frame = new JFrame("Parking R Us");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setLocationByPlatform(true);
		this.frame.setSize(900, 500);
		this.frame.setResizable(false);
		System.out.println("GUI Initialized!"); // debug

		// Create panel for top
		headerPanel = new JPanel();
		headerPanel.setBackground(new Color(60, 179, 113));

		// Create panel for center
		centerPanel = new JPanel();
		centerPanel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));


		// Creating menu panel at bottom
		optionsPanel = new JPanel();

		// align and add panes to frame
		this.frame.getContentPane().add(BorderLayout.NORTH, headerPanel);
		headerPanel.setLayout(new MigLayout("", "[123px][75px][][][][][][][][][][][][][][][][]", "[29px][]"));

		btnParkingRUs = new JButton("Parking R Us");
		btnParkingRUs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				ReserveASpot.this.frame.setVisible(false);
			}
		});
		headerPanel.add(btnParkingRUs, "cell 0 0,alignx left,aligny top");

		btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.NewScreen();
			}
		});

		this.btnProfile = new JButton("Profile");
		this.btnProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Profile profile = new Profile();
				ReserveASpot.this.frame.setVisible(false);
			}
		});

		this.viewStatistics = true;
		if (this.viewStatistics) {
			this.btnStatistics= new JButton("Statistics");
			this.btnStatistics.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Statistics statistics = new Statistics();
					ReserveASpot.this.frame.setVisible(false);
				}
			});
			headerPanel.add(this.btnStatistics, "cell 16 0");
		}
		headerPanel.add(this.btnProfile, "cell 17 0");
		headerPanel.add(btnAbout, "cell 0 1,alignx left,aligny top");

		this.btnReserveASpot = new JButton("Reserve A Spot");
		this.btnReserveASpot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReserveASpot reserve = new ReserveASpot();
				ReserveASpot.this.frame.setVisible(false);
			}
		});
		headerPanel.add(this.btnReserveASpot, "cell 1 1");

		this.btnMonthlySubscription = new JButton("Monthly Subscription");
		this.btnMonthlySubscription.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MonthlySubscription mSub = new MonthlySubscription();
				ReserveASpot.this.frame.setVisible(false);
			}
		});
		headerPanel.add(this.btnMonthlySubscription, "cell 2 1");
		this.frame.getContentPane().add(BorderLayout.SOUTH, optionsPanel);

		lblMadeByGladys = new JLabel("Made by Grace Arnold, Chris Dharma, and Gladys Toledo-Rodriguez");
		optionsPanel.add(lblMadeByGladys);
		this.frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));

		this.lblReserveASpot = new JLabel("Reserve A Spot");
		this.lblReserveASpot.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(this.lblReserveASpot);

		this.lblEmail = new JLabel("Email");
		this.lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(this.lblEmail);

		this.txtEnterEmail = new JTextField();
		this.txtEnterEmail.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtEnterEmail.setText("Enter Email");
		centerPanel.add(this.txtEnterEmail);
		this.txtEnterEmail.setColumns(10);

		this.lblAddress = new JLabel("Address");
		this.lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(this.lblAddress);

		this.txtEnterParkingAddress = new JTextField();
		this.txtEnterParkingAddress.setText("Enter Parking Address");
		this.txtEnterParkingAddress.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(this.txtEnterParkingAddress);
		this.txtEnterParkingAddress.setColumns(10);

		this.lblDate = new JLabel("Date");
		this.lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(this.lblDate);

		this.txtEnterDate = new JTextField();
		this.txtEnterDate.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtEnterDate.setText("Enter Date in MM/DD/YY");
		centerPanel.add(this.txtEnterDate);
		this.txtEnterDate.setColumns(10);

		this.lblTime = new JLabel("Time");
		this.lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(this.lblTime);

		this.txtEnterTime = new JTextField();
		this.txtEnterTime.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtEnterTime.setText("Enter Start Time in HH:MM");
		centerPanel.add(this.txtEnterTime);
		this.txtEnterTime.setColumns(10);

		this.txtEnterEndTime = new JTextField();
		this.txtEnterEndTime.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtEnterEndTime.setText("Enter End Time in HH:MM");
		centerPanel.add(this.txtEnterEndTime);
		this.txtEnterEndTime.setColumns(10);

		this.lblLicensePlate = new JLabel("License Plate");
		this.lblLicensePlate.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(this.lblLicensePlate);

		this.rdbtnNewRadioButton = new JRadioButton("Permanent");
		this.buttonGroup.add(this.rdbtnNewRadioButton);
		this.rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(this.rdbtnNewRadioButton);

		this.rdbtnTemporary = new JRadioButton("Temporary");
		this.buttonGroup.add(this.rdbtnTemporary);
		this.rdbtnTemporary.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(this.rdbtnTemporary);

		this.txtEnterLicensePlate = new JTextField();
		this.txtEnterLicensePlate.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtEnterLicensePlate.setText("If temporary, enter License Plate");
		centerPanel.add(this.txtEnterLicensePlate);
		this.txtEnterLicensePlate.setColumns(10);

		btnEnter = new JButton("Enter");
		centerPanel.add(btnEnter);
		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReserveASpot.this.email = ReserveASpot.this.txtEnterEmail.getText();
				ReserveASpot.this.address = ReserveASpot.this.txtEnterParkingAddress.getText();
				ReserveASpot.this.date = ReserveASpot.this.txtEnterDate.getText();
				ReserveASpot.this.startTime = ReserveASpot.this.txtEnterTime.getText();
				ReserveASpot.this.endTime = ReserveASpot.this.txtEnterEndTime.getText();
				if (ReserveASpot.this.rdbtnTemporary.isSelected()) {
					ReserveASpot.this.licensePlate = ReserveASpot.this.txtEnterLicensePlate.getText();
				}
				else if (ReserveASpot.this.rdbtnNewRadioButton.isSelected()) {
					ReserveASpot.this.licensePlate = "";
				}
				else {
					LPError lperror = new LPError();
				}
				if (ReserveASpot.this.address.equals("Enter Parking Address") || ReserveASpot.this.date.equals("Enter Date in MM/DD/YY") || ReserveASpot.this.startTime.equals("Enter Start Time in HH:MM") || ReserveASpot.this.endTime.equals("Enter End Time in HH:MM") || ReserveASpot.this.licensePlate.equals("If temporary, enter License Plate")) {
					NullError nerror = new NullError();
				}
				else if (ReserveASpot.this.address.equals("") || ReserveASpot.this.date.equals("") || ReserveASpot.this.startTime.equals("") || ReserveASpot.this.licensePlate.equals("")) {
					NullError nerror = new NullError();
				}
				else {
					try {
						String pattern = "MM/dd/yyyy kk:mm";
						SimpleDateFormat format = new SimpleDateFormat(pattern);
						Date startDate = format.parse(ReserveASpot.this.date + " " + ReserveASpot.this.startTime);
						Date endDate = format.parse(ReserveASpot.this.date + " " + ReserveASpot.this.endTime);
						//reservation = new Reservation(address, email, licensePlate, 10, startTime, endTime);
						Reservation newReservation = new Reservation(ReserveASpot.this.address, ReserveASpot.this.email, ReserveASpot.this.licensePlate, 10, startDate, endDate);
						UIController.addReservation(newReservation);
					} catch (ParseException pe) {
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				RAS_CC rasCC = new RAS_CC();
				ReserveASpot.this.frame.setVisible(false);
			}

		});
		// turn on frame
		this.frame.setVisible(true);
	}
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
