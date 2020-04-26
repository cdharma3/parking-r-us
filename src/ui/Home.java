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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.ButtonGroup;

public class Home {

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
	private JButton btnReserveASpot;
	private JButton btnMonthlySubscription;
	private JLabel lblWelcome;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnProfile;
	private JButton btnStatistics;
	private Boolean viewStatistics;
	private Boolean isMember;
	private String reservations;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
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
	public Home() {
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
				MonthlySubscription sub = new MonthlySubscription();
				frame.setVisible(false);
			}
		});

		headerPanel.add(btnMonthlySubscription, "cell 2 1");
		
		frame.getContentPane().add(BorderLayout.SOUTH, optionsPanel);
		
		lblMadeByGladys = new JLabel("Made by Grace Arnold, Chris Dharma, and Gladys Toledo-Rodriguez");
		optionsPanel.add(lblMadeByGladys);
		frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblWelcome);
		
		reservations = "";
		lblNewLabel_1 = new JLabel("Your reservations are: " + reservations);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		centerPanel.add(lblNewLabel_1);
		
		isMember = true;
		if (isMember) {
			lblNewLabel = new JLabel("Thank you for being a member!");
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			centerPanel.add(lblNewLabel);
		}
		// turn on frame
		frame.setVisible(true);
	}
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	});
	}

}