package uiLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import config.StyleConfig;
import controlLayer.BookingController;
import modelLayer.Room;
import modelLayer.User;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.text.JTextComponent;
import javax.swing.border.LineBorder;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateBookingDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4971333530173079875L;
	private final JPanel contentPanel = new JPanel();
	private JPanel rightPanel;
	private JPanel leftPanel;
	private StyleConfig config = new StyleConfig();
	private User user;
	private BookingController bookingController;
	private JTextField titleTextField;
	private JTextField organizationDropDownPlaceholder;
	private JTextField attendeesTextField;
	private JTextField nameTextField;
	private JTextField phoneTextField;
	private JTextField emailTextField;
	private JTextField toTimePlaceholder;
	private JLabel titleLabel;
	private JLabel attendeesLabel;
	private JLabel nameLabel;
	private JLabel phoneLabel;
	private JLabel emailLabel;
	private JSpinner startTimePicker;
	private JSpinner endTimePicker;
	private JSpinner datePicker;

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
	 * @throws SQLException 
	 * @wbp.parser.constructor
	 */
	public CreateBookingDialog(User user) throws SQLException {
		bookingController = new BookingController();
		this.user = user;
		
		setBounds(100, 100, 783, 502);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(config.getBackGroundDefaultColor());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{43, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.2, 0.8};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel titlePanel = new JPanel();
			titlePanel.setBackground(config.getBackgroundTitleDefaultColor());
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
			leftPanel = new JPanel();
			leftPanel.setBackground(Color.WHITE);
			GridBagConstraints gbc_leftPanel = new GridBagConstraints();
			gbc_leftPanel.insets = new Insets(0, 20, 0, 0);
			gbc_leftPanel.fill = GridBagConstraints.BOTH;
			gbc_leftPanel.gridx = 0;
			gbc_leftPanel.gridy = 1;
			contentPanel.add(leftPanel, gbc_leftPanel);
			GridBagLayout gbl_leftPanel = new GridBagLayout();
			gbl_leftPanel.columnWidths = new int[]{30, 0, 30};
			gbl_leftPanel.rowHeights = new int[]{30, 0,0,0,0,0,0,0,0,0,0,0,0,0,0, 30};
			gbl_leftPanel.columnWeights = new double[]{0.0, 1.0, 0.0};
			gbl_leftPanel.rowWeights = new double[]{0.0, 0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0, 0.0,Double.MIN_VALUE};
			leftPanel.setLayout(gbl_leftPanel);
			{
				titleLabel = new JLabel("Event Title");
				titleLabel.setName("title");
				GridBagConstraints gbc_titleLabel = new GridBagConstraints();
				gbc_titleLabel.anchor = GridBagConstraints.WEST;
				gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
				gbc_titleLabel.gridx = 1;
				gbc_titleLabel.gridy = 1;
				leftPanel.add(titleLabel, gbc_titleLabel);
			}
			{
				titleTextField = new JTextField();
				titleTextField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						checkInformation(titleLabel, titleTextField);
					}
				});
				formatTextField(titleTextField);
				GridBagConstraints gbc_titleTextField = new GridBagConstraints();
				gbc_titleTextField.insets = new Insets(0, 0, 5, 5);
				gbc_titleTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_titleTextField.gridx = 1;
				gbc_titleTextField.gridy = 2;
				leftPanel.add(titleTextField, gbc_titleTextField);
				titleTextField.setColumns(10);
			}
			{
		
				JLabel organizationLabel = new JLabel("Organization");
				GridBagConstraints gbc_organizationLabel = new GridBagConstraints();
				gbc_organizationLabel.anchor = GridBagConstraints.WEST;
				gbc_organizationLabel.insets = new Insets(0, 0, 5, 5);
				gbc_organizationLabel.gridx = 1;
				gbc_organizationLabel.gridy = 3;
				leftPanel.add(organizationLabel, gbc_organizationLabel);
			}
			{
				organizationDropDownPlaceholder = new JTextField();
				formatTextField(organizationDropDownPlaceholder);
				GridBagConstraints gbc_organizationDropDownPlaceholder = new GridBagConstraints();
				gbc_organizationDropDownPlaceholder.insets = new Insets(0, 0, 5, 5);
				gbc_organizationDropDownPlaceholder.fill = GridBagConstraints.HORIZONTAL;
				gbc_organizationDropDownPlaceholder.gridx = 1;
				gbc_organizationDropDownPlaceholder.gridy = 4;
				leftPanel.add(organizationDropDownPlaceholder, gbc_organizationDropDownPlaceholder);
				organizationDropDownPlaceholder.setColumns(10);
			}
			{
				attendeesLabel = new JLabel("Number of attendees*");
				attendeesLabel.setName("attendees");
				GridBagConstraints gbc_attendeesLabel = new GridBagConstraints();
				gbc_attendeesLabel.anchor = GridBagConstraints.WEST;
				gbc_attendeesLabel.insets = new Insets(0, 0, 5, 5);
				gbc_attendeesLabel.gridx = 1;
				gbc_attendeesLabel.gridy = 5;
				leftPanel.add(attendeesLabel, gbc_attendeesLabel);
			}
			{
				attendeesTextField = new JTextField();
				attendeesTextField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						checkInformation(attendeesLabel, attendeesTextField);
					}
				});
				formatTextField(attendeesTextField);
				GridBagConstraints gbc_attendeesTextField = new GridBagConstraints();
				gbc_attendeesTextField.insets = new Insets(0, 0, 5, 5);
				gbc_attendeesTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_attendeesTextField.gridx = 1;
				gbc_attendeesTextField.gridy = 6;
				leftPanel.add(attendeesTextField, gbc_attendeesTextField);
				attendeesTextField.setColumns(10);
			}
			{
				JLabel contactLabel = new JLabel("Contact Person Information");
				contactLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				GridBagConstraints gbc_contactLabel = new GridBagConstraints();
				gbc_contactLabel.anchor = GridBagConstraints.WEST;
				gbc_contactLabel.insets = new Insets(10, 0, 5, 5);
				gbc_contactLabel.gridx = 1;
				gbc_contactLabel.gridy = 7;
				leftPanel.add(contactLabel, gbc_contactLabel);
			}
			{
				nameLabel = new JLabel("First- and Last Name*");
				nameLabel.setName("contactName");
				GridBagConstraints gbc_nameLabel = new GridBagConstraints();
				gbc_nameLabel.anchor = GridBagConstraints.WEST;
				gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
				gbc_nameLabel.gridx = 1;
				gbc_nameLabel.gridy = 8;
				leftPanel.add(nameLabel, gbc_nameLabel);
			}
			{
				nameTextField = new JTextField();
				nameTextField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						checkInformation(nameLabel, nameTextField);
					}
				});
				formatTextField(nameTextField);
				GridBagConstraints gbc_nameTextField = new GridBagConstraints();
				gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
				gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameTextField.gridx = 1;
				gbc_nameTextField.gridy = 9;
				leftPanel.add(nameTextField, gbc_nameTextField);
				nameTextField.setColumns(10);
			}
			{
				phoneLabel = new JLabel("Phone number*");
				phoneLabel.setName("phoneNumber");
				GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
				gbc_phoneLabel.anchor = GridBagConstraints.WEST;
				gbc_phoneLabel.insets = new Insets(0, 0, 5, 5);
				gbc_phoneLabel.gridx = 1;
				gbc_phoneLabel.gridy = 10;
				leftPanel.add(phoneLabel, gbc_phoneLabel);
			}
			{
				phoneTextField = new JTextField();
				phoneTextField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						checkInformation(phoneLabel, phoneTextField);
					}
				});
				formatTextField(phoneTextField);
				GridBagConstraints gbc_phoneTextField = new GridBagConstraints();
				gbc_phoneTextField.insets = new Insets(0, 0, 5, 5);
				gbc_phoneTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_phoneTextField.gridx = 1;
				gbc_phoneTextField.gridy = 11;
				leftPanel.add(phoneTextField, gbc_phoneTextField);
				phoneTextField.setColumns(10);
			}
			{
				emailLabel = new JLabel("Email*");
				emailLabel.setName("email");
				GridBagConstraints gbc_emailLabel = new GridBagConstraints();
				gbc_emailLabel.anchor = GridBagConstraints.WEST;
				gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
				gbc_emailLabel.gridx = 1;
				gbc_emailLabel.gridy = 12;
				leftPanel.add(emailLabel, gbc_emailLabel);
			}
			{
				emailTextField = new JTextField();
				emailTextField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						checkInformation(emailLabel, emailTextField);
					}
				});
				formatTextField(emailTextField);
				GridBagConstraints gbc_emailTextField = new GridBagConstraints();
				gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
				gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
				gbc_emailTextField.gridx = 1;
				gbc_emailTextField.gridy = 13;
				leftPanel.add(emailTextField, gbc_emailTextField);
				emailTextField.setColumns(10);
			}
		}
		{
			rightPanel = new JPanel();
			rightPanel.setPreferredSize(new Dimension(100,100));
			//TODO maybe add this to the config. idk
			rightPanel.setBackground(Color.WHITE);
			GridBagConstraints gbc_rightPanel = new GridBagConstraints();
			gbc_rightPanel.insets = new Insets(0, 0, 0, 20);
			gbc_rightPanel.fill = GridBagConstraints.BOTH;
			gbc_rightPanel.gridx = 1;
			gbc_rightPanel.gridy = 1;
			contentPanel.add(rightPanel, gbc_rightPanel);
			GridBagLayout gbl_rightPanel = new GridBagLayout();
			gbl_rightPanel.columnWidths = new int[]{30, 0,0, 30};
			gbl_rightPanel.rowHeights = new int[]{30, 0,0,0,0,0,0,0,0,0,0, 30};
			gbl_rightPanel.columnWeights = new double[]{0.0, 1.0,1.0, 0.0};
			gbl_rightPanel.rowWeights = new double[]{0.0, 0,1.0,0,0,0,0,0,0,0,0, 0.0};
			rightPanel.setLayout(gbl_rightPanel);
			{
				JLabel descriptionPanel = new JLabel("Description");
				GridBagConstraints gbc_descriptionPanel = new GridBagConstraints();
				gbc_descriptionPanel.anchor = GridBagConstraints.WEST;
				gbc_descriptionPanel.insets = new Insets(0, 0, 5, 5);
				gbc_descriptionPanel.gridx = 1;
				gbc_descriptionPanel.gridy = 1;
				rightPanel.add(descriptionPanel, gbc_descriptionPanel);
			}
			{
				JTextArea descriptionTextArea = new JTextArea();
				formatTextField(descriptionTextArea);
				descriptionTextArea.setLineWrap(true);
				descriptionTextArea.setBorder(config.getBorderTextArea());
				descriptionTextArea.setWrapStyleWord(true);
				GridBagConstraints gbc_descriptionTextArea = new GridBagConstraints();
				gbc_descriptionTextArea.gridwidth = 2;
				gbc_descriptionTextArea.insets = new Insets(0, 0, 5, 5);
				gbc_descriptionTextArea.fill = GridBagConstraints.BOTH;
				gbc_descriptionTextArea.gridx = 1;
				gbc_descriptionTextArea.gridy = 2;
				rightPanel.add(descriptionTextArea, gbc_descriptionTextArea);
			}
			{
				JButton attachmentButton = new JButton("Add Attachment");
				attachmentButton.setEnabled(false);
				attachmentButton.setBackground(new Color(192, 192, 192));
				GridBagConstraints gbc_attachmentButton = new GridBagConstraints();
				gbc_attachmentButton.anchor = GridBagConstraints.EAST;
				gbc_attachmentButton.insets = new Insets(0, 0, 5, 5);
				gbc_attachmentButton.gridx = 2;
				gbc_attachmentButton.gridy = 3;
				rightPanel.add(attachmentButton, gbc_attachmentButton);
			}
			{
				JLabel roomLabel = new JLabel("Room");
				GridBagConstraints gbc_roomLabel = new GridBagConstraints();
				gbc_roomLabel.anchor = GridBagConstraints.WEST;
				gbc_roomLabel.insets = new Insets(0, 0, 5, 5);
				gbc_roomLabel.gridx = 1;
				gbc_roomLabel.gridy = 4;
				rightPanel.add(roomLabel, gbc_roomLabel);
			}
			{
				ArrayList<Room> allRooms = new ArrayList<Room>();
				try
				{
					allRooms = new BookingController().getAllRooms();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				allRooms.add(0, new Room("", -1, " Select...", -1));
				JComboBox<Room> comboBox = new JComboBox<Room>();
				comboBox.setModel(new DefaultComboBoxModel<Room>(allRooms.toArray(new Room[0])));
				ListCellRenderer<? super Room> renderer = new RoomComboboxCellRenderer();
				
			    comboBox.setRenderer(renderer);
				comboBox.setPreferredSize(new Dimension(100, 30));
				comboBox.setEditable(false);
				comboBox.setFocusable(false);
				comboBox.setFont(new Font("Roboto", Font.PLAIN, 15));
				comboBox.setForeground(Color.GRAY);
				
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 1;
				gbc_comboBox.gridy = 5;
				rightPanel.add(comboBox, gbc_comboBox);
			}
			{
				JLabel dateLabel = new JLabel("Date");
				GridBagConstraints gbc_dateLabel = new GridBagConstraints();
				gbc_dateLabel.anchor = GridBagConstraints.WEST;
				gbc_dateLabel.insets = new Insets(0, 0, 5, 5);
				gbc_dateLabel.gridx = 1;
				gbc_dateLabel.gridy = 6;
				rightPanel.add(dateLabel, gbc_dateLabel);
			}
			{
				Calendar yesterday = Calendar.getInstance();
				yesterday.add(Calendar.DATE, -1);
				datePicker = new JSpinner(new SpinnerDateModel(new Date(), yesterday.getTime(), null, Calendar.DAY_OF_MONTH)); 
				DateEditor dateEditor = new JSpinner.DateEditor(datePicker, "dd/MM/yyyy"); 
				datePicker.setEditor(dateEditor); 
				((DefaultEditor) datePicker.getEditor()).getTextField().setEditable(false);
				datePicker.setFocusable(false);
				datePicker.setFont(config.getLabelDefaultFont());
				GridBagConstraints gbc_datePicker = new GridBagConstraints();
				gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
				gbc_datePicker.anchor = GridBagConstraints.NORTH;
				gbc_datePicker.insets = new Insets(0, 0, 5, 5);
				gbc_datePicker.gridx = 1;
				gbc_datePicker.gridy = 7;
				rightPanel.add(datePicker, gbc_datePicker);
			}
			{
				JLabel fromTimeLabel = new JLabel("From");
				GridBagConstraints gbc_fromTimeLabel = new GridBagConstraints();
				gbc_fromTimeLabel.anchor = GridBagConstraints.WEST;
				gbc_fromTimeLabel.insets = new Insets(0, 0, 5, 5);
				gbc_fromTimeLabel.gridx = 1;
				gbc_fromTimeLabel.gridy = 8;
				rightPanel.add(fromTimeLabel, gbc_fromTimeLabel);
			}
			{
				JLabel toTimeLabel = new JLabel("To");
				GridBagConstraints gbc_toTimeLabel = new GridBagConstraints();
				gbc_toTimeLabel.anchor = GridBagConstraints.WEST;
				gbc_toTimeLabel.insets = new Insets(0, 0, 5, 5);
				gbc_toTimeLabel.gridx = 2;
				gbc_toTimeLabel.gridy = 8;
				rightPanel.add(toTimeLabel, gbc_toTimeLabel);
			}
			{
				Calendar time = Calendar.getInstance();
				time.set(Calendar.HOUR, 1);
				time.set(Calendar.MINUTE, 0);
				startTimePicker = new JSpinner();
				startTimePicker.setModel(new SpinnerDateModel(time.getTime(), null, null, Calendar.HOUR_OF_DAY) {
					@Override
			        public Object getNextValue() { 
			            Date nextValue = (Date)super.getValue();
			            Calendar calendar = Calendar.getInstance();
			            calendar.setTime(nextValue);
			            calendar.add(Calendar.MINUTE, 30);
			            return calendar.getTime();
			        }
					@Override
			        public Object getPreviousValue() { 
			            Date nextValue = (Date)super.getValue();
			            Calendar calendar = Calendar.getInstance();
			            calendar.setTime(nextValue);
			            calendar.add(Calendar.MINUTE, -30);
			            return calendar.getTime();
			        }
			    });
				DateEditor dateEditor = new JSpinner.DateEditor(startTimePicker, "HH:mm");			
				startTimePicker.setEditor(dateEditor); 
				((DefaultEditor) startTimePicker.getEditor()).getTextField().setEditable(false);
				startTimePicker.setFocusable(false);
				startTimePicker.setFont(config.getLabelDefaultFont());
				GridBagConstraints gbc_startTimePicker = new GridBagConstraints();
				gbc_startTimePicker.fill = GridBagConstraints.HORIZONTAL;
				gbc_startTimePicker.insets = new Insets(0, 0, 5, 5);
				gbc_startTimePicker.gridx = 1;
				gbc_startTimePicker.gridy = 9;
				rightPanel.add(startTimePicker, gbc_startTimePicker);
			}
			{
				//Calendar startTime = Calendar.getInstance(); FIXME
				//startTime.set(Calendar.HOUR, 0);
				//startTime.set(Calendar.MINUTE, 0);
				Calendar time = Calendar.getInstance();
				time.set(Calendar.HOUR, 8);
				time.set(Calendar.MINUTE, 0);
				//Calendar endTime = Calendar.getInstance();
				//endTime.set(Calendar.HOUR, 8);
				//endTime.set(Calendar.MINUTE, 0);
				endTimePicker = new JSpinner();
				endTimePicker.setModel(new SpinnerDateModel(time.getTime(), null, null, Calendar.HOUR_OF_DAY) {
					@Override
			        public Object getNextValue() {
			            Date nextValue = (Date)super.getValue();
			            Calendar calendar = Calendar.getInstance();
			            calendar.setTime(nextValue);
			            calendar.add(Calendar.MINUTE, 30);
			            return calendar.getTime();
			        }
					@Override
			        public Object getPreviousValue() { 
			            Date nextValue = (Date)super.getValue();
			            Calendar calendar = Calendar.getInstance();
			            calendar.setTime(nextValue);
			            calendar.add(Calendar.MINUTE, -30);
			            return calendar.getTime();
			        }
			    });
				DateEditor dateEditor = new JSpinner.DateEditor(endTimePicker, "HH:mm"); 
				endTimePicker.setEditor(dateEditor); 
				((DefaultEditor) endTimePicker.getEditor()).getTextField().setEditable(false);
				endTimePicker.setFocusable(false);
				endTimePicker.setFont(config.getLabelDefaultFont());
				GridBagConstraints gbc_endTimePicker = new GridBagConstraints();
				gbc_endTimePicker.insets = new Insets(0, 0, 5, 5);
				gbc_endTimePicker.fill = GridBagConstraints.HORIZONTAL;
				gbc_endTimePicker.gridx = 2;
				gbc_endTimePicker.gridy = 9;
				rightPanel.add(endTimePicker, gbc_endTimePicker);
			}
			{
				JLabel errorMessageRoom = new JLabel(" ");
				errorMessageRoom.setForeground(config.getErrorMessageColor());
				errorMessageRoom.setBackground(new Color(255, 255, 255));
				GridBagConstraints gbc_errorMessageRoom = new GridBagConstraints();
				gbc_errorMessageRoom.anchor = GridBagConstraints.WEST;
				gbc_errorMessageRoom.insets = new Insets(0, 0, 5, 5);
				gbc_errorMessageRoom.gridx = 1;
				gbc_errorMessageRoom.gridy = 10;
				rightPanel.add(errorMessageRoom, gbc_errorMessageRoom);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setBackground(config.getBackGroundDefaultColor());
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.setForeground(Color.WHITE);
				saveButton.setBackground(config.getButtonColorSaved());
				saveButton.setBorder(config.getBorderSaveButton());
				saveButton.setFocusable(false);
				saveButton.setFont(config.getButtonDefaultFont());
				
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
				cancelButton.setForeground(config.getButtonColorCancelForeground());
				cancelButton.setBackground(config.getButtonColorCancelBackground());
				cancelButton.setBorder(config.getBorderCancelButton());
				cancelButton.setFocusable(false);
				cancelButton.setFont(config.getButtonDefaultFont());
	
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
	
	public CreateBookingDialog(User user, LocalDateTime startInterval, LocalDateTime endInterval) throws SQLException
	{
		this(user);
		if (startInterval.compareTo(LocalDateTime.now()) >= 0)
		{
			//startTimePicker.setValue(endInterval.toLocalTime());
			Calendar time = Calendar.getInstance();
			
			time.set(Calendar.HOUR, startInterval.getHour());
			time.set(Calendar.MINUTE, startInterval.getMinute());
			startTimePicker.setValue(time.getTime());
			/*startTimePicker.setModel(new SpinnerDateModel(time.getTime(), null, null, Calendar.HOUR_OF_DAY) {
				@Override
		        public Object getNextValue() {
		            Date nextValue = (Date)super.getValue();
		            Calendar calendar = Calendar.getInstance();
		            calendar.setTime(nextValue);
		            calendar.add(Calendar.MINUTE, 30);
		            return calendar.getTime();
		        }
				@Override
		        public Object getPreviousValue() { 
		            Date nextValue = (Date)super.getValue();
		            Calendar calendar = Calendar.getInstance();
		            calendar.setTime(nextValue);
		            calendar.add(Calendar.MINUTE, -30);
		            return calendar.getTime();
		        }
		    });*/
			time.set(Calendar.HOUR, endInterval.getHour());
			time.set(Calendar.MINUTE, endInterval.getMinute());
			endTimePicker.setValue(time.getTime());
			datePicker.setValue(Date.from(startInterval.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
	}
	
	private void formatTextField(JTextComponent component)
	{
		component.setForeground(config.getButtonDefaultForeground());
		component.setFont(config.getLabelDefaultFont());
		component.setBorder(BorderFactory.createLineBorder(new Color(212, 212, 212), 1));
	}
	
	private void checkInformation(JLabel label, JTextField field) 
	{
		if (bookingController.validateInformation(new String[] {label.getName(),field.getText()}) == false) 
		{
				
			label.setForeground(Color.red);
			field.setBorder(new LineBorder(Color.RED));
		}
		else
		{
			label.setForeground(Color.BLACK);
			field.setBorder(config.getBorderTextFieldDefault());
		}

	}
	
	private void checkRoomAvailability() throws SQLException 
	{
		//errormessage field = bookingController.checkRoomAvailability(null, null, null);
		bookingController.checkAvailability(null, null, null); //Passing starTime, endTime and room
	}
}
