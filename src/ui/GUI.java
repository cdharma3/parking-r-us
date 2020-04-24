package ui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

class GUI {
	private static JFrame frame; // main java swing frame

	// header object declarations
	private static JPanel headerPanel; // panel for header
	// center object declarations
	// mainly used for showing the 'action', or the sorting process
	private static JPanel centerPanel; // panel for center

	// footer object declarations
	private static JPanel optionsPanel;
	private static JButton randomizeNumbersBtn; // create new random array of ints
	private static JButton sortBtn; // initiate sorting process

	private static int[] randData; // stores current array to be sorted
	private static JButton btnParkingRUs;
	private static JButton btnAbout;

	public static void initializeGUI() {
		// Initialize GUI
		frame = new JFrame("Parking R Us");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setSize(900, 500);
		frame.setResizable(false);
		System.out.println("GUI Initialized!"); // debug

		// Create panel for top
		headerPanel = new JPanel();

		// Create panel for center
		centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));


		// Creating menu panel at bottom
		optionsPanel = new JPanel();

		// Generate new set of random numbers
		randomizeNumbersBtn = new JButton("Randomize Numbers");
		randomizeNumbersBtn.setActionCommand("Randomize");
		randomizeNumbersBtn.addActionListener(new ButtonClickListener());

		// Start sorting array
		sortBtn = new JButton("Sort!");
		sortBtn.setActionCommand("Sort");
		sortBtn.addActionListener(new ButtonClickListener());

		// Add buttons to options panel
		optionsPanel.add(randomizeNumbersBtn);
		optionsPanel.add(sortBtn);

		// align and add panes to frame
		frame.getContentPane().add(BorderLayout.NORTH, headerPanel);
		
		btnParkingRUs = new JButton("Parking R Us");
		headerPanel.add(btnParkingRUs);
		
		btnAbout = new JButton("About");
		headerPanel.add(btnAbout);
		frame.getContentPane().add(BorderLayout.SOUTH, optionsPanel);
		frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
		// turn on frame
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// calls initializeGUI to initialize the window and components
		initializeGUI();
	}

	/*
	 * ButtonClickListener is a class that allows for event handlers to be captured
	 * from the functional buttons. This class is designed to be used with the
	 * JFrame and JTextArea in the main class, thus needs the references to those
	 * objects
	 */
	private static class ButtonClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String command = e.getActionCommand();
				// Switch on command passed
				switch (command) {
				// Generate a new array to sort for both algorithms
				case "Randomize":
					System.out.println("Randomize button pressed");
					// Display message asking user how many elements they want
					String result = (String) JOptionPane.showInputDialog(frame,
							"How many random numbers would you like?", null, JOptionPane.PLAIN_MESSAGE, null, null,
							null);
					// Check if they pressed cancel on the dialog box
					if (result != null) {
						// if result is able to be parsed, then parse
						int resultNum = Integer.parseInt(result);
						if (resultNum > 0 && resultNum < 101) {
							// create new array of size resultNum
							randData = new int[7];
							randData[0] = 21;
							randData[1] = 345;
							randData[2] = 13;
							randData[3] = 101;
							randData[4] = 50;
							randData[5] = 234;
							randData[6] = 1;
							// populate array with random numbers
							//for (int i = 0; i < randData.length; i++) {
								//randData[i] = (int) (Math.random() * randData.length);
								
							//}
							// display message indicating array has be created
							JOptionPane.showMessageDialog(frame, "New array generated!\n");
							// update currentArray label with correct formatting
						} else {
							// number too small or too big
							JOptionPane.showMessageDialog(frame,
									"Please choose a number greater than 0 and less than 101!");
						}
					}
					// Otherwise, they cancelled, and nothing happens
					break;
				case "Sort":
					// Sort button has been pressed, thus the algorithms will
					// sort the current array
					System.out.println("Sort button pressed");
					// clears text areas for new text

					// counting sort called
					// starts timing counting sort
					long startTime = System.nanoTime();
					// sleeps to align timer
					TimeUnit.SECONDS.sleep(1);
					//countingSort(Arrays.copyOf(randData, randData.length));
					// stops timer
					long endTime = System.nanoTime();
					// calculates elapsed time for counting sort to finish

					// radix sort called
					// starts new timer
					startTime = System.nanoTime();
					// new timer alignment
					TimeUnit.SECONDS.sleep(1);
					// stops timer
					endTime = System.nanoTime();
					// calculates elapsed time for radix sort to finish
					break;
				}
			} catch (NumberFormatException nfe) {
				// Catch the user not inputting a number in the dialog box
				JOptionPane.showMessageDialog(null, "Please enter a valid number!");
			} catch (InterruptedException ie) {
				// Catch interrupted execution from sleep function
				System.err.print("Interrupted during execution!");
			}
		}
	}
}