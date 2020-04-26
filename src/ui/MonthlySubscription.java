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
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class MonthlySubscription {

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
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblMonthlySubscription;
	private JButton btnProfile;

	private JButton btnYes;
	private JTextArea txtrAboutMonthySubscription;

	private JButton btnStatistics;
	private Boolean viewStatistics;


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
		
		lblMonthlySubscription = new JLabel("Monthly Subscription");
		lblMonthlySubscription.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblMonthlySubscription);
		
		txtrAboutMonthySubscription = new JTextArea();
		txtrAboutMonthySubscription.setEditable(false);
		txtrAboutMonthySubscription.setLineWrap(true);
		txtrAboutMonthySubscription.setText("By paying a price of $10 per month you will be able to reserve a spot where ever you want, as long as its not already reserved.  When reserving a spot you will still be sent to the enter credit card details page but do not worry we will not be charging more prices.");
		centerPanel.add(txtrAboutMonthySubscription);
		
		btnYes = new JButton("Yes");
		centerPanel.add(btnYes);
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
