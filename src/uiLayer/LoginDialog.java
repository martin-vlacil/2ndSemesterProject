package uiLayer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import config.StyleConfig;
import controlLayer.UserController;
import databaseLayer.UserDB;
import databaseLayer.UserDBIF;
import modelLayer.User;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginDialog extends JDialog {
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private final StyleConfig config;
	private UserController userController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Remove the system's scale factor on the UI elements
					System.setProperty("sun.java2d.uiScale", "1.0");
					LoginDialog dialog = new LoginDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					//Centres the dialog
					dialog.setLocationRelativeTo(null);
					dialog.setIconImage(new ImageIcon("src/uiLayer/images/ihndLogo.png").getImage());
					dialog.setTitle("Login - IHND Booking System");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public LoginDialog() {
		try
		{
			userController = new UserController();
		}
		catch (SQLException error)
		{
			error.printStackTrace();
		}
		
		config = new StyleConfig();
		
		setBounds(100, 100, 931, 601);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{43, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(config.getBlueColorDefault());
		GridBagConstraints gbc_titlePanel = new GridBagConstraints();
		gbc_titlePanel.insets = new Insets(0, 0, 5, 0);
		gbc_titlePanel.fill = GridBagConstraints.BOTH;
		gbc_titlePanel.gridx = 0;
		gbc_titlePanel.gridy = 0;
		getContentPane().add(titlePanel, gbc_titlePanel);
		
		JLabel titleLabel = new JLabel("Login");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		titlePanel.add(titleLabel);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.insets = new Insets(30, 30, 80, 30);
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 1;
		getContentPane().add(mainPanel, gbc_mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.3, 0.3, 0.3};
		gbl_mainPanel.rowWeights = new double[]{0.1, 0.0, 0.0, 0.0, 0.0, 0.1, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JLabel emailLabel = new JLabel("Email (Miguel@Olivera.dk)");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.WEST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 1;
		gbc_emailLabel.gridy = 1;
		mainPanel.add(emailLabel, gbc_emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setForeground(config.getButtonDefaultForeground());
		emailTextField.setFont(new Font("Roboto", Font.PLAIN, 14));
		emailTextField.setBorder(BorderFactory.createLineBorder(new Color(212, 212, 212), 1));
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 1;
		gbc_emailTextField.gridy = 2;
		mainPanel.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password (password1)");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.WEST;
		gbc_passwordLabel.insets = new Insets(30, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 3;
		mainPanel.add(passwordLabel, gbc_passwordLabel);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setForeground(config.getButtonDefaultForeground());
		passwordTextField.setFont(new Font("Roboto", Font.PLAIN, 14));
		passwordTextField.setBorder(BorderFactory.createLineBorder(new Color(212, 212, 212), 1));
		GridBagConstraints gbc_passwordTextField = new GridBagConstraints();
		gbc_passwordTextField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordTextField.gridx = 1;
		gbc_passwordTextField.gridy = 4;
		mainPanel.add(passwordTextField, gbc_passwordTextField);
		passwordTextField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				openMainUI();
			}
		});
		loginButton.setBorder(new EmptyBorder(8, 50, 8, 50));
		loginButton.setFocusable(false);
		loginButton.setBackground(config.getBlueColorDefault());
		loginButton.setFont(config.getButtonDefaultFont());
		loginButton.setForeground(Color.WHITE);
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.insets = new Insets(0, 0, 0, 5);
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 5;
		mainPanel.add(loginButton, gbc_loginButton);
	}

	private void openMainUI()
	{
		User loggedUser;
		
		try
		{
			loggedUser = userController.getUser(emailTextField.getText(), String.valueOf(passwordTextField.getPassword()));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return;
		}
		
		if(loggedUser == null)
		{
			//TODO Implement a proper error
			System.out.println("User not found");
			return;
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI(loggedUser);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					frame.setIconImage(new ImageIcon("src/uiLayer/images/ihndLogo.png").getImage());
					frame.setTitle("IHND Booking System");
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	}
	
}
