package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class Home {

	private JFrame frame;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 902, 503);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[115px]", "[29px][]"));
		
		JButton btnNewButton = new JButton("Parking R Us");
		panel.add(btnNewButton, "cell 0 0,alignx left,aligny top");
		
		JButton btnNewButton_1 = new JButton("About");
		panel.add(btnNewButton_1, "cell 0 1");
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblMadeByGrace = new JLabel("Made by Grace Arnold, Chris Dharma, and Gladys Toledo-Rodriguez");
		panel_1.add(lblMadeByGrace);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new MigLayout("", "[68px][][][][][][][][][][][][][][][][][][][][]", "[20px][][][]"));
		
		JLabel lblWelcome = new JLabel("Welcome!");
		panel_2.add(lblWelcome, "cell 12 1,alignx left,aligny top");
		
		JLabel lblNewLabel = new JLabel("Your reservations are:");
		panel_2.add(lblNewLabel, "cell 4 3");
		
		JLabel lblNewLabel_1 = new JLabel("Your monthly subscriptions are:");
		panel_2.add(lblNewLabel_1, "cell 20 3");
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
