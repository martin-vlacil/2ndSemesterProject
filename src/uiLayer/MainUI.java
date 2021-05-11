package uiLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 500);
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{0.1, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel sidebarPanel = new JPanel();
		GridBagConstraints gbc_sidebarPanel = new GridBagConstraints();
		sidebarPanel.setBackground(new Color(40, 41, 82));
		
		gbc_sidebarPanel.insets = new Insets(0, 0, 0, 5);
		gbc_sidebarPanel.fill = GridBagConstraints.BOTH;
		gbc_sidebarPanel.gridx = 0;
		gbc_sidebarPanel.gridy = 0;
		contentPane.add(sidebarPanel, gbc_sidebarPanel);
		GridBagLayout gbl_sidebarPanel = new GridBagLayout();
		gbl_sidebarPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_sidebarPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_sidebarPanel.columnWeights = new double[]{0.1, 1.0, 0.0, Double.MIN_VALUE};
		gbl_sidebarPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		sidebarPanel.setLayout(gbl_sidebarPanel);
		
		JLabel userNameLabel = new JLabel("User Name");
		userNameLabel.setForeground(Color.WHITE);
		userNameLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
		
		GridBagConstraints gbc_userNameLabel = new GridBagConstraints();
		gbc_userNameLabel.anchor = GridBagConstraints.WEST;
		gbc_userNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userNameLabel.gridx = 1;
		gbc_userNameLabel.gridy = 0;
		sidebarPanel.add(userNameLabel, gbc_userNameLabel);
		
		JButton logOutButton = new JButton("Log Out");
		logOutButton.setForeground(Color.WHITE);
		logOutButton.setOpaque(true);
		logOutButton.setBackground(new Color(40, 41, 82));
		logOutButton.setFocusable(false);
		GridBagConstraints gbc_logOutButton = new GridBagConstraints();
		gbc_logOutButton.gridheight = 2;
		gbc_logOutButton.fill = GridBagConstraints.BOTH;
		gbc_logOutButton.insets = new Insets(0, 0, 5, 0);
		gbc_logOutButton.gridx = 2;
		gbc_logOutButton.gridy = 0;
		sidebarPanel.add(logOutButton, gbc_logOutButton);
		
		JLabel userTypeLabel = new JLabel("User Type");
		userTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 7));
		userTypeLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_userTypeLabel = new GridBagConstraints();
		gbc_userTypeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userTypeLabel.anchor = GridBagConstraints.WEST;
		gbc_userTypeLabel.gridx = 1;
		gbc_userTypeLabel.gridy = 1;
		sidebarPanel.add(userTypeLabel, gbc_userTypeLabel);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		separator.setForeground(new Color(196, 196, 196));
		separator.setOpaque(true);
		gbc_separator.gridwidth = 3;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		sidebarPanel.add(separator, gbc_separator);
		
		JButton bookingButton = new JButton("Booking");
		bookingButton.setForeground(Color.WHITE);
		bookingButton.setOpaque(true);
		bookingButton.setBackground(new Color(40, 41, 82));
		bookingButton.setFocusable(false);
		GridBagConstraints gbc_bookingButton = new GridBagConstraints();
		gbc_bookingButton.gridwidth = 3;
		gbc_bookingButton.fill = GridBagConstraints.BOTH;
		gbc_bookingButton.insets = new Insets(0, 0, 5, 5);
		gbc_bookingButton.gridx = 0;
		gbc_bookingButton.gridy = 3;
		sidebarPanel.add(bookingButton, gbc_bookingButton);
		
		JButton usersButton = new JButton("Users");
		GridBagConstraints gbc_usersButton = new GridBagConstraints();
		gbc_usersButton.gridwidth = 3;
		gbc_usersButton.fill = GridBagConstraints.BOTH;
		usersButton.setForeground(Color.WHITE);
		usersButton.setOpaque(true);
		usersButton.setBackground(new Color(40, 41, 82));
		usersButton.setFocusable(false);
		gbc_usersButton.insets = new Insets(0, 0, 5, 5);
		gbc_usersButton.gridx = 0;
		gbc_usersButton.gridy = 4;
		sidebarPanel.add(usersButton, gbc_usersButton);
		
		JButton roomsButton = new JButton("Rooms");
		GridBagConstraints gbc_roomsButton = new GridBagConstraints();
		gbc_roomsButton.gridwidth = 3;
		gbc_roomsButton.fill = GridBagConstraints.BOTH;
		roomsButton.setForeground(Color.WHITE);
		roomsButton.setOpaque(true);
		roomsButton.setBackground(new Color(40, 41, 82));
		roomsButton.setFocusable(false);
		gbc_roomsButton.insets = new Insets(0, 0, 5, 5);
		gbc_roomsButton.gridx = 0;
		gbc_roomsButton.gridy = 5;
		sidebarPanel.add(roomsButton, gbc_roomsButton);
		
		JSeparator logSeparator = new JSeparator();
		GridBagConstraints gbc_logSeparator = new GridBagConstraints();
		gbc_logSeparator.fill = GridBagConstraints.BOTH;
		gbc_logSeparator.insets = new Insets(0, 0, 5, 0);
		gbc_logSeparator.gridwidth = 3;
		gbc_logSeparator.gridx = 0;
		gbc_logSeparator.gridy = 6;
		sidebarPanel.add(logSeparator, gbc_logSeparator);
		
		JPanel logPanel = new JPanel();
		GridBagConstraints gbc_logPanel = new GridBagConstraints();
		gbc_logPanel.gridwidth = 3;
		gbc_logPanel.insets = new Insets(0, 0, 0, 5);
		gbc_logPanel.fill = GridBagConstraints.BOTH;
		gbc_logPanel.gridx = 0;
		gbc_logPanel.gridy = 7;
		sidebarPanel.add(logPanel, gbc_logPanel);
		
		JPanel mainPanel = new JPanel();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 1;
		gbc_mainPanel.gridy = 0;
		contentPane.add(mainPanel, gbc_mainPanel);
	}

}
