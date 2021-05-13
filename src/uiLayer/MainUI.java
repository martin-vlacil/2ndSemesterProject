/**
 * This class is a part of the Booking System
 * developed for International House North Denmark.
 * It contains the MainUI which represents the
 * way the User interacts with the system.
 */

package uiLayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import databaseLayer.LogEntryDB;
import modelLayer.LogEntry;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainUI extends JFrame {

	private JPanel contentPane;
	private CardLayout cards;
	private BookingPanel bookingPanel;
	private JPanel mainPanel;
	private JButton bookingButton;
	private JButton usersButton;
	private JButton roomsButton;
	private JButton selectedPageButton;
	private JTextArea logTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					frame.setIconImage(new ImageIcon("src/uiLayer/images/ihndLogo.png").getImage());
					frame.setTitle("IHND Booking System");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MainUI() throws SQLException {
		//TODO PASS USER
		// Remove the system's scale factor on the UI elements
		System.setProperty("sun.java2d.uiScale", "1.0");
		
		bookingPanel = new BookingPanel(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 500);
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0}; 
		gbl_contentPane.rowHeights = new int[]{0};
		gbl_contentPane.columnWeights = new double[]{0.1, 1.0};
		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		//Creates the sidebar containing the menu
		JPanel sidebarPanel = new JPanel();
		GridBagConstraints gbc_sidebarPanel = new GridBagConstraints();
		sidebarPanel.setBackground(new Color(40, 41, 82));
		sidebarPanel.setPreferredSize(new Dimension(140, 0));
		
		gbc_sidebarPanel.fill = GridBagConstraints.BOTH;
		gbc_sidebarPanel.gridx = 0;
		gbc_sidebarPanel.gridy = 0;
		contentPane.add(sidebarPanel, gbc_sidebarPanel);
		GridBagLayout gbl_sidebarPanel = new GridBagLayout();
		gbl_sidebarPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_sidebarPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_sidebarPanel.columnWeights = new double[]{0.1, 1.0, 0.0, Double.MIN_VALUE};
		gbl_sidebarPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.4, Double.MIN_VALUE};
		sidebarPanel.setLayout(gbl_sidebarPanel);
		
		// Set the image icon of the button and scale is to 50x50
		ImageIcon icon = new ImageIcon("src/uiLayer/images/Icon.png", "YOU");
		icon = new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		JButton avatarButton = new JButton(icon);
		GridBagConstraints gbc_avatarButton = new GridBagConstraints();
		avatarButton.setContentAreaFilled(false);
		avatarButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		avatarButton.setFocusable(false);
		gbc_avatarButton.insets = new Insets(10, 10, 10, 10);
		gbc_avatarButton.fill = GridBagConstraints.BOTH;
		gbc_avatarButton.gridheight = 2;
		gbc_avatarButton.gridx = 0;
		gbc_avatarButton.gridy = 0;
		sidebarPanel.add(avatarButton, gbc_avatarButton);
		
		//User name label
		JLabel userNameLabel = new JLabel("User Name");
		userNameLabel.setForeground(Color.WHITE);
		userNameLabel.setFont(new Font("Roboto", Font.BOLD, 15));
		GridBagConstraints gbc_userNameLabel = new GridBagConstraints();
		gbc_userNameLabel.insets = new Insets(25, 0, 0, 0);
		gbc_userNameLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_userNameLabel.gridx = 1;
		gbc_userNameLabel.gridy = 0;
		sidebarPanel.add(userNameLabel, gbc_userNameLabel);
		
		//User type label
		JLabel userTypeLabel = new JLabel("User Type");
		userTypeLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
		userTypeLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_userTypeLabel = new GridBagConstraints();
		gbc_userTypeLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_userTypeLabel.gridx = 1;
		gbc_userTypeLabel.gridy = 1;
		sidebarPanel.add(userTypeLabel, gbc_userTypeLabel);
		
		//Logout button
		JButton logOutButton = new JButton("Log Out");
		formatSidebarButton(logOutButton);
		GridBagConstraints gbc_logOutButton = new GridBagConstraints();
		gbc_logOutButton.anchor = GridBagConstraints.WEST;
		gbc_logOutButton.insets = new Insets(20, 0, 20, 10);
		gbc_logOutButton.gridheight = 2;
		gbc_logOutButton.fill = GridBagConstraints.VERTICAL;
		gbc_logOutButton.gridx = 2;
		gbc_logOutButton.gridy = 0;
		sidebarPanel.add(logOutButton, gbc_logOutButton);
		
		//Line that separates User section from Pages
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		separator.setForeground(new Color(196, 196, 196));
		separator.setOpaque(true);
		gbc_separator.gridwidth = 3;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		sidebarPanel.add(separator, gbc_separator);
		
		//Booking page button
		bookingButton = new JButton("Booking");
		bookingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectPage("Bookings");
			}
		});
		formatSidebarButton(bookingButton);
		bookingButton.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_bookingButton = new GridBagConstraints();
		gbc_bookingButton.insets = new Insets(20, 20, 5, 60);
		gbc_bookingButton.gridwidth = 3;
		gbc_bookingButton.fill = GridBagConstraints.BOTH;
		gbc_bookingButton.gridx = 0;
		gbc_bookingButton.gridy = 3;
		sidebarPanel.add(bookingButton, gbc_bookingButton);
		
		//User page button
		usersButton = new JButton("Users");
		usersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectPage("Users");
			}
		});
		GridBagConstraints gbc_usersButton = new GridBagConstraints();
		gbc_usersButton.insets = new Insets(0, 20, 5, 60);
		gbc_usersButton.gridwidth = 3;
		gbc_usersButton.fill = GridBagConstraints.BOTH;
		formatSidebarButton(usersButton);
		usersButton.setHorizontalAlignment(SwingConstants.LEFT);
		gbc_usersButton.gridx = 0;
		gbc_usersButton.gridy = 4;
		sidebarPanel.add(usersButton, gbc_usersButton);
		
		//Room page button
		roomsButton = new JButton("Rooms");
		roomsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectPage("Rooms");
			}
		});
		GridBagConstraints gbc_roomsButton = new GridBagConstraints();
		gbc_roomsButton.insets = new Insets(0, 20, 5, 60);
		gbc_roomsButton.anchor = GridBagConstraints.NORTH;
		gbc_roomsButton.gridwidth = 3;
		gbc_roomsButton.fill = GridBagConstraints.HORIZONTAL;
		formatSidebarButton(roomsButton);
		roomsButton.setHorizontalAlignment(SwingConstants.LEFT);
		gbc_roomsButton.gridx = 0;
		gbc_roomsButton.gridy = 5;
		sidebarPanel.add(roomsButton, gbc_roomsButton);
		
		//Line that separates Pages section from Logs
		JSeparator logSeparator = new JSeparator();
		GridBagConstraints gbc_logSeparator = new GridBagConstraints();
		gbc_logSeparator.anchor = GridBagConstraints.SOUTH;
		logSeparator.setForeground(new Color(196, 196, 196));
		logSeparator.setOpaque(true);
		logSeparator.setPreferredSize(new Dimension(logSeparator.getWidth(), 4));
		gbc_logSeparator.fill = GridBagConstraints.HORIZONTAL;
		gbc_logSeparator.gridwidth = 3;
		gbc_logSeparator.gridx = 0;
		gbc_logSeparator.gridy = 6;
		sidebarPanel.add(logSeparator, gbc_logSeparator);
		
		JPanel logPanel = new JPanel();
		GridBagConstraints gbc_logPanel = new GridBagConstraints();
		logPanel.setBackground(new Color(40, 41, 82));
		gbc_logPanel.gridwidth = 3;
		gbc_logPanel.fill = GridBagConstraints.BOTH;
		gbc_logPanel.gridx = 0;
		gbc_logPanel.gridy = 7;
		sidebarPanel.add(logPanel, gbc_logPanel);
		GridBagLayout gbl_logPanel = new GridBagLayout();
		gbl_logPanel.columnWidths = new int[]{0};
		gbl_logPanel.rowHeights = new int[]{0, 0, 0};
		gbl_logPanel.columnWeights = new double[]{1.0};
		gbl_logPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		logPanel.setLayout(gbl_logPanel);
		
		JLabel logLabel = new JLabel("LOG");
		logLabel.setFont(new Font("Roboto", Font.BOLD, 15));
		logLabel.setForeground(Color.WHITE);
		logLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_logLabel = new GridBagConstraints();
		gbc_logLabel.insets = new Insets(5, 0, 0, 0);
		gbc_logLabel.gridx = 0;
		gbc_logLabel.gridy = 0;
		logPanel.add(logLabel, gbc_logLabel);
		
		logTextArea = new JTextArea();
		logTextArea.setWrapStyleWord(true);
		logTextArea.setLineWrap(true);
		logTextArea.setForeground(Color.WHITE);
		logTextArea.setFont(new Font("Roboto", Font.PLAIN, 15));
		logTextArea.setEditable(false);
		logTextArea.setBackground(new Color(40, 41, 82));
		logTextArea.setPreferredSize(sidebarPanel.getSize());
		GridBagConstraints gbc_logTextArea = new GridBagConstraints();
		gbc_logTextArea.insets = new Insets(5, 10, 0, 10);
		gbc_logTextArea.fill = GridBagConstraints.BOTH;
		gbc_logTextArea.gridx = 0;
		gbc_logTextArea.gridy = 1;
		logPanel.add(logTextArea, gbc_logTextArea);
		
		mainPanel = new JPanel();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 1;
		gbc_mainPanel.gridy = 0;
		contentPane.add(mainPanel, gbc_mainPanel);
		cards = new CardLayout(0, 0);
		mainPanel.setLayout(cards);
		mainPanel.add(bookingPanel, "Bookings");
		formatSelectedSidebarButton(bookingButton);
		selectedPageButton = bookingButton;
	}

	private void selectPage(String page)
	{
		formatSidebarButton(selectedPageButton);
		switch(page)
		{
		case "Bookings":
			cards.show(mainPanel, page);
			selectedPageButton = bookingButton;
			break;
		case "Users":
			cards.show(mainPanel, page);
			selectedPageButton = usersButton;
			break;
		case "Rooms":
			cards.show(mainPanel, page);
			selectedPageButton = roomsButton;
			break;
		}
		formatSelectedSidebarButton(selectedPageButton);
	}
	
	private void formatSidebarButton(JButton button)
	{
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(40, 41, 82));
		button.setBorder(new EmptyBorder(15, 10, 15, 10));
		button.setFocusable(false);
		button.setFont(new Font("Roboto", Font.BOLD, 15));
		//button.setContentAreaFilled(false);
	}
	
	private void formatSelectedSidebarButton(JButton button)
	{
		button.setForeground(new Color(40, 41, 82));
		button.setBackground(new Color(234, 234, 238));
	}
	
	private void updateLog() throws SQLException
	{
		LogEntry[] logDB = new LogEntryDB().getLogs();
		for(int i = 0; i < logDB.length; i++)
		{
			logTextArea.append(logDB[i].getAction() + "\n");
		}
	}
}
