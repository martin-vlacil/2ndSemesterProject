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
import javax.swing.border.LineBorder;

import config.Config;
import controlLayer.UserController;
import modelLayer.User;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class LoginDialog extends JDialog {
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private final Config config;
	private UserController userController;
	private JPanel errorPanel;

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
		
		config = new Config();
		
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
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.6, 0.6, 0.6};
		gbl_mainPanel.rowWeights = new double[]{0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		errorPanel = new JPanel();
		errorPanel.setVisible(false);
		errorPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_errorPanel = new GridBagConstraints();
		gbc_errorPanel.insets = new Insets(0, 0, 5, 5);
		gbc_errorPanel.fill = GridBagConstraints.BOTH;
		gbc_errorPanel.gridx = 1;
		gbc_errorPanel.gridy = 1;
		mainPanel.add(errorPanel, gbc_errorPanel);
		
		JPanel errorFrame = new JPanel();
		errorFrame.setBorder(new LineBorder(Color.red));
		errorFrame.setBackground(Color.WHITE);
		errorPanel.add(errorFrame);
		
		JLabel errorLabel = new JLabel("The email or the password is incorrect!");
		errorLabel.setForeground(Color.RED);
		errorFrame.add(errorLabel);
		
		JLabel emailLabel = new JLabel("Email (Miguel@Olivera.dk)");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.WEST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 1;
		gbc_emailLabel.gridy = 2;
		mainPanel.add(emailLabel, gbc_emailLabel);
		
		emailTextField = new JTextField("Miguel@Olivera.dk");
		emailTextField.setForeground(config.getButtonDefaultForeground());
		emailTextField.setFont(new Font("Roboto", Font.PLAIN, 14));
		emailTextField.setBorder(BorderFactory.createLineBorder(new Color(212, 212, 212), 1));
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 1;
		gbc_emailTextField.gridy = 3;
		mainPanel.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password (password1)");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.WEST;
		gbc_passwordLabel.insets = new Insets(30, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 4;
		mainPanel.add(passwordLabel, gbc_passwordLabel);
		
		passwordTextField = new JPasswordField("password1");
		passwordTextField.setForeground(config.getButtonDefaultForeground());
		passwordTextField.setFont(new Font("Roboto", Font.PLAIN, 14));
		passwordTextField.setBorder(BorderFactory.createLineBorder(new Color(212, 212, 212), 1));
		GridBagConstraints gbc_passwordTextField = new GridBagConstraints();
		gbc_passwordTextField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordTextField.gridx = 1;
		gbc_passwordTextField.gridy = 5;
		mainPanel.add(passwordTextField, gbc_passwordTextField);
		passwordTextField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		this.getRootPane().setDefaultButton(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				openMainUI();
			}
		});
		loginButton.setBorder(new EmptyBorder(8, 50, 8, 50));
		loginButton.setFocusable(false);
		loginButton.setBackground(config.getBlueColorDefault());
		//loginButton.setBackground(new Color(40, 41, 82));
		loginButton.setFont(config.getButtonDefaultFont());
		loginButton.setForeground(Color.WHITE);
		loginButton.setOpaque(true);
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.insets = new Insets(0, 0, 0, 5);
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 6;
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
			//TODO Set the errorPanel to fixed position
			passwordTextField.setBorder(new LineBorder(Color.red));
			emailTextField.setBorder(new LineBorder(Color.red));
			errorPanel.setVisible(true);
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
	
	
	private static byte[] encryptionForLife(String password) {
		String newPWD = "";
		int firstLetter = 25;
		boolean wasThereLetter = false;
		int numberCount = 0;
		for (int e = 0 ; e < password.length() ; e++)
		{
			if ((int) password.charAt(e) >= 97 && (int) password.charAt(e) <= 122)
			{
				if (firstLetter == 0 || wasThereLetter == false)
				{
					firstLetter = password.charAt(e);
				}
				if ((((int) password.charAt(e)) - 34) < 65)
				{
					newPWD += (char) ((((((int) password.charAt(e)) - 7)) - ((((int) 'A')) - (((int) password.charAt(e)) - 34))));
				}
				else
				{
					newPWD += (char) (((int) password.charAt(e)) - 34);
				}
				
			}
			else if ((int) password.charAt(e) >= 65 && (int) password.charAt(e) <= 90)
			{
				if (firstLetter == 0 || wasThereLetter == false)
				{
					firstLetter = password.charAt(e);
				}
				if ((((int) password.charAt(e)) + 34) > 122)
				{
					newPWD += (char) ((((((int) password.charAt(e)) + 7)) - ((((int) password.charAt(e)) - 34) - (((int) 'a')))));
				}
				else
				{
					newPWD += (char) (((int) password.charAt(e)) + 34);
				}
			}
			else if (Character.isDigit(password.charAt(e)))
			{
				newPWD += (Character.getNumericValue(password.charAt(e)) + 1) * (Character.getNumericValue(password.charAt(e)) + 1);
				newPWD += (char) ++numberCount;
				newPWD += (Character.getNumericValue(password.charAt(e)) + 1) * (Character.getNumericValue(password.charAt(e)) + 1);
			}
			else
			{
				newPWD += (char) (((int) password.charAt(e) + firstLetter));
				wasThereLetter = true;
			}
		}
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
		} catch (NoSuchAlgorithmException e) {}
		return md.digest(newPWD.getBytes(StandardCharsets.UTF_8));
		
	}
	
}
