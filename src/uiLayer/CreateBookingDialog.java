package uiLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controlLayer.BookingController;
import modelLayer.User;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class CreateBookingDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private User user;
	private BookingController bookingController;
	private JTextField titleTextField;
	private JTextField organizationDropDownPlaceholder;
	private JTextField textField_2;
	private JTextField nameTextField;
	private JTextField phoneTextField;
	private JTextField emailTextField;
	private JTextField roomPlaceholder;
	private JTextField fromTimePlaceholder;
	private JTextField toTimePlaceholder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateBookingDialog dialog = new CreateBookingDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateBookingDialog(User user) {
		setBounds(100, 100, 783, 502);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 240, 240));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{43, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel titlePanel = new JPanel();
			titlePanel.setBackground(new Color(40, 41, 82));
			GridBagConstraints gbc_titlePanel = new GridBagConstraints();
			gbc_titlePanel.gridwidth = 2;
			gbc_titlePanel.insets = new Insets(0, 0, 10, 0);
			gbc_titlePanel.fill = GridBagConstraints.BOTH;
			gbc_titlePanel.gridx = 0;
			gbc_titlePanel.gridy = 0;
			contentPanel.add(titlePanel, gbc_titlePanel);
			GridBagLayout gbl_titlePanel = new GridBagLayout();
			gbl_titlePanel.columnWidths = new int[]{0};
			gbl_titlePanel.rowHeights = new int[]{0};
			gbl_titlePanel.columnWeights = new double[]{0.0};
			gbl_titlePanel.rowWeights = new double[]{0.0};
			titlePanel.setLayout(gbl_titlePanel);
			{
				JLabel titleLabel = new JLabel("Create Booking");
				titleLabel.setForeground(new Color(255, 255, 255));
				titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
				GridBagConstraints gbc_titleLabel = new GridBagConstraints();
				gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
				gbc_titleLabel.gridx = 0;
				gbc_titleLabel.gridy = 0;
				titlePanel.add(titleLabel, gbc_titleLabel);
			}
		}
		{
			JPanel leftPanel = new JPanel();
			leftPanel.setBackground(Color.WHITE);
			GridBagConstraints gbc_leftPanel = new GridBagConstraints();
			gbc_leftPanel.insets = new Insets(0, 20, 0, 0);
			gbc_leftPanel.fill = GridBagConstraints.BOTH;
			gbc_leftPanel.gridx = 0;
			gbc_leftPanel.gridy = 1;
			contentPanel.add(leftPanel, gbc_leftPanel);
			GridBagLayout gbl_leftPanel = new GridBagLayout();
			gbl_leftPanel.columnWidths = new int[]{30, 0, 60};
			gbl_leftPanel.rowHeights = new int[]{30, 0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			gbl_leftPanel.columnWeights = new double[]{0.0, 1.0, 0.0};
			gbl_leftPanel.rowWeights = new double[]{0.0, 0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,Double.MIN_VALUE};
			leftPanel.setLayout(gbl_leftPanel);
			{
				JLabel titleLabel = new JLabel("Event Title*");
				GridBagConstraints gbc_titleLabel = new GridBagConstraints();
				gbc_titleLabel.anchor = GridBagConstraints.WEST;
				gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
				gbc_titleLabel.gridx = 1;
				gbc_titleLabel.gridy = 0;
				leftPanel.add(titleLabel, gbc_titleLabel);
			}
			{
				titleTextField = new JTextField();
				GridBagConstraints gbc_titleTextField = new GridBagConstraints();
				gbc_titleTextField.insets = new Insets(0, 0, 5, 5);
				gbc_titleTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_titleTextField.gridx = 1;
				gbc_titleTextField.gridy = 1;
				leftPanel.add(titleTextField, gbc_titleTextField);
				titleTextField.setColumns(10);
			}
			{
				JLabel organizationLabel = new JLabel("Organization");
				GridBagConstraints gbc_organizationLabel = new GridBagConstraints();
				gbc_organizationLabel.insets = new Insets(0, 0, 5, 5);
				gbc_organizationLabel.gridx = 1;
				gbc_organizationLabel.gridy = 2;
				leftPanel.add(organizationLabel, gbc_organizationLabel);
			}
			{
				organizationDropDownPlaceholder = new JTextField();
				GridBagConstraints gbc_organizationDropDownPlaceholder = new GridBagConstraints();
				gbc_organizationDropDownPlaceholder.insets = new Insets(0, 0, 5, 5);
				gbc_organizationDropDownPlaceholder.fill = GridBagConstraints.HORIZONTAL;
				gbc_organizationDropDownPlaceholder.gridx = 1;
				gbc_organizationDropDownPlaceholder.gridy = 3;
				leftPanel.add(organizationDropDownPlaceholder, gbc_organizationDropDownPlaceholder);
				organizationDropDownPlaceholder.setColumns(10);
			}
			{
				JLabel attendeesLabel = new JLabel("Number of attendees*");
				GridBagConstraints gbc_attendeesLabel = new GridBagConstraints();
				gbc_attendeesLabel.insets = new Insets(0, 0, 5, 5);
				gbc_attendeesLabel.gridx = 1;
				gbc_attendeesLabel.gridy = 4;
				leftPanel.add(attendeesLabel, gbc_attendeesLabel);
			}
			{
				textField_2 = new JTextField();
				GridBagConstraints gbc_textField_2 = new GridBagConstraints();
				gbc_textField_2.insets = new Insets(0, 0, 5, 5);
				gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_2.gridx = 1;
				gbc_textField_2.gridy = 5;
				leftPanel.add(textField_2, gbc_textField_2);
				textField_2.setColumns(10);
			}
			{
				JLabel contactLabel = new JLabel("Contact Person Information");
				contactLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				GridBagConstraints gbc_contactLabel = new GridBagConstraints();
				gbc_contactLabel.insets = new Insets(0, 0, 5, 5);
				gbc_contactLabel.gridx = 1;
				gbc_contactLabel.gridy = 6;
				leftPanel.add(contactLabel, gbc_contactLabel);
			}
			{
				JLabel nameLabel = new JLabel("First- and Last Name*");
				GridBagConstraints gbc_nameLabel = new GridBagConstraints();
				gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_nameLabel.gridx = 1;
				gbc_nameLabel.gridy = 7;
				leftPanel.add(nameLabel, gbc_nameLabel);
			}
			{
				nameTextField = new JTextField();
				GridBagConstraints gbc_nameTextField = new GridBagConstraints();
				gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
				gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameTextField.gridx = 1;
				gbc_nameTextField.gridy = 8;
				leftPanel.add(nameTextField, gbc_nameTextField);
				nameTextField.setColumns(10);
			}
			{
				JLabel phoneLabel = new JLabel("Phone number");
				GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
				gbc_phoneLabel.insets = new Insets(0, 0, 5, 5);
				gbc_phoneLabel.gridx = 1;
				gbc_phoneLabel.gridy = 9;
				leftPanel.add(phoneLabel, gbc_phoneLabel);
			}
			{
				phoneTextField = new JTextField();
				GridBagConstraints gbc_phoneTextField = new GridBagConstraints();
				gbc_phoneTextField.insets = new Insets(0, 0, 5, 5);
				gbc_phoneTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_phoneTextField.gridx = 1;
				gbc_phoneTextField.gridy = 10;
				leftPanel.add(phoneTextField, gbc_phoneTextField);
				phoneTextField.setColumns(10);
			}
			{
				JLabel emailLabel = new JLabel("Email");
				GridBagConstraints gbc_emailLabel = new GridBagConstraints();
				gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
				gbc_emailLabel.gridx = 1;
				gbc_emailLabel.gridy = 11;
				leftPanel.add(emailLabel, gbc_emailLabel);
			}
			{
				emailTextField = new JTextField();
				GridBagConstraints gbc_emailTextField = new GridBagConstraints();
				gbc_emailTextField.insets = new Insets(0, 0, 0, 5);
				gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_emailTextField.gridx = 1;
				gbc_emailTextField.gridy = 12;
				leftPanel.add(emailTextField, gbc_emailTextField);
				emailTextField.setColumns(10);
			}
		}
		{
			JPanel rightPanel = new JPanel();
			rightPanel.setPreferredSize(new Dimension(100,100));
			rightPanel.setBackground(Color.WHITE);
			GridBagConstraints gbc_rightPanel = new GridBagConstraints();
			gbc_rightPanel.insets = new Insets(0, 0, 0, 20);
			gbc_rightPanel.fill = GridBagConstraints.BOTH;
			gbc_rightPanel.gridx = 1;
			gbc_rightPanel.gridy = 1;
			contentPanel.add(rightPanel, gbc_rightPanel);
			GridBagLayout gbl_rightPanel = new GridBagLayout();
			gbl_rightPanel.columnWidths = new int[]{0,0};
			gbl_rightPanel.rowHeights = new int[]{0,0,0,0,0,0,0,0,0,0};
			gbl_rightPanel.columnWeights = new double[]{1.0,1.0};
			gbl_rightPanel.rowWeights = new double[]{0,1.0,0,0,0,0,0,0,0,0};
			rightPanel.setLayout(gbl_rightPanel);
			{
				JLabel descriptionPanel = new JLabel("Description");
				GridBagConstraints gbc_descriptionPanel = new GridBagConstraints();
				gbc_descriptionPanel.insets = new Insets(0, 0, 5, 5);
				gbc_descriptionPanel.gridx = 0;
				gbc_descriptionPanel.gridy = 0;
				rightPanel.add(descriptionPanel, gbc_descriptionPanel);
			}
			{
				JTextArea descriptionTextArea = new JTextArea();
				descriptionTextArea.setLineWrap(true);
				descriptionTextArea.setBorder(new LineBorder(new Color(0,0,0)));
				descriptionTextArea.setWrapStyleWord(true);
				GridBagConstraints gbc_descriptionTextArea = new GridBagConstraints();
				gbc_descriptionTextArea.gridwidth = 2;
				gbc_descriptionTextArea.insets = new Insets(0, 0, 5, 5);
				gbc_descriptionTextArea.fill = GridBagConstraints.BOTH;
				gbc_descriptionTextArea.gridx = 0;
				gbc_descriptionTextArea.gridy = 1;
				rightPanel.add(descriptionTextArea, gbc_descriptionTextArea);
			}
			{
				JButton attachmentButton = new JButton("Add Attachment");
				attachmentButton.setEnabled(false);
				attachmentButton.setBackground(new Color(192, 192, 192));
				GridBagConstraints gbc_attachmentButton = new GridBagConstraints();
				gbc_attachmentButton.insets = new Insets(0, 0, 5, 5);
				gbc_attachmentButton.gridx = 0;
				gbc_attachmentButton.gridy = 2;
				rightPanel.add(attachmentButton, gbc_attachmentButton);
			}
			{
				JLabel roomLabel = new JLabel("Room");
				GridBagConstraints gbc_roomLabel = new GridBagConstraints();
				gbc_roomLabel.insets = new Insets(0, 0, 5, 5);
				gbc_roomLabel.gridx = 0;
				gbc_roomLabel.gridy = 3;
				rightPanel.add(roomLabel, gbc_roomLabel);
			}
			{
				roomPlaceholder = new JTextField();
				GridBagConstraints gbc_roomPlaceholder = new GridBagConstraints();
				gbc_roomPlaceholder.insets = new Insets(0, 0, 5, 5);
				gbc_roomPlaceholder.fill = GridBagConstraints.HORIZONTAL;
				gbc_roomPlaceholder.gridx = 0;
				gbc_roomPlaceholder.gridy = 4;
				rightPanel.add(roomPlaceholder, gbc_roomPlaceholder);
				roomPlaceholder.setColumns(10);
			}
			{
				JLabel dateLabel = new JLabel("Date");
				GridBagConstraints gbc_dateLabel = new GridBagConstraints();
				gbc_dateLabel.insets = new Insets(0, 0, 5, 5);
				gbc_dateLabel.gridx = 0;
				gbc_dateLabel.gridy = 5;
				rightPanel.add(dateLabel, gbc_dateLabel);
			}
			{
				JButton datePlaceholder = new JButton("<DatePlaceHolder>");
				GridBagConstraints gbc_datePlaceholder = new GridBagConstraints();
				gbc_datePlaceholder.insets = new Insets(0, 0, 5, 5);
				gbc_datePlaceholder.gridx = 0;
				gbc_datePlaceholder.gridy = 6;
				rightPanel.add(datePlaceholder, gbc_datePlaceholder);
			}
			{
				JLabel fromTimeLabel = new JLabel("From");
				GridBagConstraints gbc_fromTimeLabel = new GridBagConstraints();
				gbc_fromTimeLabel.insets = new Insets(0, 0, 5, 5);
				gbc_fromTimeLabel.gridx = 0;
				gbc_fromTimeLabel.gridy = 7;
				rightPanel.add(fromTimeLabel, gbc_fromTimeLabel);
			}
			{
				JLabel toTimeLabel = new JLabel("To");
				GridBagConstraints gbc_toTimeLabel = new GridBagConstraints();
				gbc_toTimeLabel.insets = new Insets(0, 0, 5, 0);
				gbc_toTimeLabel.gridx = 1;
				gbc_toTimeLabel.gridy = 7;
				rightPanel.add(toTimeLabel, gbc_toTimeLabel);
			}
			{
				fromTimePlaceholder = new JTextField();
				GridBagConstraints gbc_fromTimePlaceholder = new GridBagConstraints();
				gbc_fromTimePlaceholder.insets = new Insets(0, 0, 5, 5);
				gbc_fromTimePlaceholder.fill = GridBagConstraints.HORIZONTAL;
				gbc_fromTimePlaceholder.gridx = 0;
				gbc_fromTimePlaceholder.gridy = 8;
				rightPanel.add(fromTimePlaceholder, gbc_fromTimePlaceholder);
				fromTimePlaceholder.setColumns(10);
			}
			{
				toTimePlaceholder = new JTextField();
				GridBagConstraints gbc_toTimePlaceholder = new GridBagConstraints();
				gbc_toTimePlaceholder.insets = new Insets(0, 0, 5, 0);
				gbc_toTimePlaceholder.fill = GridBagConstraints.HORIZONTAL;
				gbc_toTimePlaceholder.gridx = 1;
				gbc_toTimePlaceholder.gridy = 8;
				rightPanel.add(toTimePlaceholder, gbc_toTimePlaceholder);
				toTimePlaceholder.setColumns(10);
			}
			{
				JLabel errorMessageRoom = new JLabel("Room not available");
				errorMessageRoom.setForeground(Color.RED);
				errorMessageRoom.setBackground(new Color(255, 255, 255));
				GridBagConstraints gbc_errorMessageRoom = new GridBagConstraints();
				gbc_errorMessageRoom.insets = new Insets(0, 0, 0, 5);
				gbc_errorMessageRoom.gridx = 0;
				gbc_errorMessageRoom.gridy = 9;
				rightPanel.add(errorMessageRoom, gbc_errorMessageRoom);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setBackground(new Color(240, 240, 240));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.setForeground(Color.WHITE);
				saveButton.setBackground(new Color(86, 197, 104));
				saveButton.setBorder(new EmptyBorder(5, 30, 5, 30));
				saveButton.setFocusable(false);
				saveButton.setFont(new Font("Roboto", Font.BOLD, 15));
				
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//TODO create booking
					}
				});
				saveButton.setActionCommand("Save");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setForeground(new Color(149, 149, 149));
				cancelButton.setBackground(new Color(234, 234, 238));
				cancelButton.setBorder(new EmptyBorder(5, 30, 5, 30));
				cancelButton.setFocusable(false);
				cancelButton.setFont(new Font("Roboto", Font.BOLD, 15));
	
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	private void checkInformation(String field, String fieldValue) 
	{
		if (bookingController.validateInformation(new String[] {field,fieldValue}) == false) 
		{
			switch(field) 
			{
				case "title":
					//shows the error field of the title
					break;
				case "attendees":
					//shows the error field of the attendees
					break;
				case "contactName":
					//shows the error field of the contactName
					break;
				case "phoneNumber":
					//shows the error field of the phoneNumber
					break;
				case "email":
					//shows the error field of the email
					break;
			}
		}

	}
	
	private void checkRoomAvailability() throws SQLException 
	{
		//errormessage field = bookingController.checkRoomAvailability(null, null, null);
		bookingController.checkRoomAvailability(null, null, null); //Passing starTime, endTime and room
	}
}
