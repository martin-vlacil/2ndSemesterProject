package uiLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import config.Config;
import controlLayer.BookingController;
import modelLayer.Room;
import modelLayer.User;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

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

import java.util.*;
import javax.swing.text.JTextComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class CreateBookingDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4971333530173079875L;
	private final JPanel contentPanel = new JPanel();
	private JPanel rightPanel;
	private JPanel leftPanel;
	private Config config = new Config();
	private User user;
	private JComboBox<Room> comboBox;
	private ArrayList<Room> selectedRooms;
	private BookingPanel bookingPanel;
	private BookingController bookingController;
	private JTextField titleTextField;
	private JTextField organizationDropDownPlaceholder;
	private JTextField attendeesTextField;
	private JTextField nameTextField;
	private JTextField phoneTextField;
	private JTextField emailTextField;
	private JLabel titleLabel;
	private JLabel attendeesLabel;
	private JLabel nameLabel;
	private JLabel phoneLabel;
	private JLabel emailLabel;
	private JSpinner startTimePicker;
	private JSpinner endTimePicker;
	private JSpinner datePicker;
	private DefaultListModel<Room> listModel;
	private Calendar startTime;
	private Calendar endTime;
	private JTextArea descriptionTextArea;
	private HashMap<JTextField, Boolean> fields;
	private JTextArea errorMessageRoom;

	/**
	 * Create the dialog.
	 * 
	 * @throws SQLException
	 */
	public CreateBookingDialog(User user, BookingPanel panel) throws SQLException {
		this.bookingPanel = panel;
		fields = new HashMap<>();
		bookingController = new BookingController();
		this.user = user;
		selectedRooms = new ArrayList<>();

		setBounds(100, 100, 783, 502);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(config.getBackGroundDefaultColor());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 43, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.2, 0.8 };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0 };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel titlePanel = new JPanel();
			titlePanel.setBackground(config.getBlueColorDefault());
			GridBagConstraints gbc_titlePanel = new GridBagConstraints();
			gbc_titlePanel.gridwidth = 2;
			gbc_titlePanel.insets = new Insets(0, 0, 10, 0);
			gbc_titlePanel.fill = GridBagConstraints.BOTH;
			gbc_titlePanel.gridx = 0;
			gbc_titlePanel.gridy = 0;
			contentPanel.add(titlePanel, gbc_titlePanel);
			GridBagLayout gbl_titlePanel = new GridBagLayout();
			gbl_titlePanel.columnWidths = new int[] { 0 };
			gbl_titlePanel.rowHeights = new int[] { 0 };
			gbl_titlePanel.columnWeights = new double[] { 0.0 };
			gbl_titlePanel.rowWeights = new double[] { 0.0 };
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
			gbl_leftPanel.columnWidths = new int[] { 30, 0, 30 };
			gbl_leftPanel.rowHeights = new int[] { 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30 };
			gbl_leftPanel.columnWeights = new double[] { 0.0, 1.0, 0.0 };
			gbl_leftPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0, Double.MIN_VALUE };
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
				organizationDropDownPlaceholder.setText(bookingPanel.getUser().getOrganization().getName());
				organizationDropDownPlaceholder.setEditable(false);
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
				attendeesTextField.addCaretListener(new CaretListener() {
					public void caretUpdate(CaretEvent e) {
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
				nameTextField.setText(this.user.getName());
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
				phoneTextField.setText(this.user.getPhone());
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
				emailTextField.setText(this.user.getEmail());
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
			rightPanel.setPreferredSize(new Dimension(100, 100));
			rightPanel.setBackground(config.getFrontPanelDefaultColor());
			GridBagConstraints gbc_rightPanel = new GridBagConstraints();
			gbc_rightPanel.insets = new Insets(0, 0, 0, 20);
			gbc_rightPanel.fill = GridBagConstraints.BOTH;
			gbc_rightPanel.gridx = 1;
			gbc_rightPanel.gridy = 1;
			contentPanel.add(rightPanel, gbc_rightPanel);
			GridBagLayout gbl_rightPanel = new GridBagLayout();
			gbl_rightPanel.columnWidths = new int[] { 30, 0, 0, 30 };
			gbl_rightPanel.rowHeights = new int[] { 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30 };
			gbl_rightPanel.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0 };
			gbl_rightPanel.rowWeights = new double[] { 0.0, 0, 1.0, 0, 0, 0.0, 0, 0, 0, 0, 1.0, 0.0 };
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
				descriptionTextArea = new JTextArea();
				formatTextField(descriptionTextArea);
				descriptionTextArea.setLineWrap(true);
				descriptionTextArea.setBorder(config.getTextAreaBorder());
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
				attachmentButton.setOpaque(true);
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
				gbc_roomLabel.anchor = GridBagConstraints.SOUTHWEST;
				gbc_roomLabel.insets = new Insets(0, 0, 5, 5);
				gbc_roomLabel.gridx = 1;
				gbc_roomLabel.gridy = 4;
				rightPanel.add(roomLabel, gbc_roomLabel);
			}
			{
				ArrayList<Room> allRooms = new ArrayList<Room>();
				try {
					allRooms = new BookingController().getAllRooms();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				allRooms.add(0, new Room("", -1, " Select...", -1));
				comboBox = new JComboBox<Room>();
				comboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addSelectedRoom(((Room) comboBox.getSelectedItem()));
					}
				});
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
				gbc_comboBox.anchor = GridBagConstraints.NORTHWEST;
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 1;
				gbc_comboBox.gridy = 5;
				rightPanel.add(comboBox, gbc_comboBox);

			}
			{
				listModel = new DefaultListModel<>();
				JList<Room> listOfSelectedRooms = new JList<>(listModel);
				listOfSelectedRooms.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						removeSelectedRoom(listOfSelectedRooms.getSelectedValue());

					}
				});
				ListCellRenderer<? super Room> renderer = new RoomListCellRenderer();
				listOfSelectedRooms.setCellRenderer(renderer);
				GridBagConstraints gbc_list = new GridBagConstraints();
				gbc_list.insets = new Insets(20, 5, 5, 5);
				gbc_list.anchor = GridBagConstraints.NORTHEAST;
				gbc_list.fill = GridBagConstraints.BOTH;
				gbc_list.gridx = 2;
				gbc_list.gridy = 4;
				gbc_list.gridheight = 3;
				rightPanel.add(listOfSelectedRooms, gbc_list);
				
				addSelectedRoom(bookingPanel.getSelectedRoom());
			}
			{
				JLabel dateLabel = new JLabel("Date");
				GridBagConstraints gbc_dateLabel = new GridBagConstraints();
				gbc_dateLabel.insets = new Insets(0, 0, 5, 5);
				gbc_dateLabel.anchor = GridBagConstraints.NORTHWEST;
				gbc_dateLabel.gridx = 1;
				gbc_dateLabel.gridy = 6;
				rightPanel.add(dateLabel, gbc_dateLabel);
			}
			{
				Calendar yesterday = Calendar.getInstance();
				yesterday.add(Calendar.DATE, -1);
				datePicker = new JSpinner(
						new SpinnerDateModel(new Date(), yesterday.getTime(), null, Calendar.DAY_OF_MONTH)/* {
					@Override
					public Object getNextValue() {
						LocalDateTime bookingDate = LocalDateTime.ofInstant(yesterday.toInstant(), ZoneId.systemDefault());
						try
						{
							bookingPanel.getRoomsOfOneDay(bookingDate.toLocalDate());
						}
						catch (SQLException e)
						{
							e.printStackTrace();
						}
						//TODO Fix the return
						return null;
					}

					@Override
					public Object getPreviousValue() {
						LocalDateTime bookingDate = LocalDateTime.ofInstant(yesterday.toInstant(), ZoneId.systemDefault());
						try
						{
							bookingPanel.getRoomsOfOneDay(bookingDate.toLocalDate());
						}
						catch (SQLException e)
						{
							e.printStackTrace();
						}
						//TODO FIX the return
						return datePicker.getModel().getPreviousValue();
					}
				}*/);
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
				startTime = Calendar.getInstance();
				startTime.set(Calendar.HOUR_OF_DAY, config.getWorkingHoursStart());
				startTime.set(Calendar.MINUTE, 0);
				startTimePicker = new JSpinner();
				startTimePicker.setModel(new SpinnerDateModel(startTime.getTime(), null, null, Calendar.HOUR_OF_DAY) {
					@Override
					public Object getNextValue() {
						Date nextValue = (Date) super.getValue();
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(nextValue);
						
						Date time = new Date();
						time.setTime(77400000);
						if (calendar.getTime().after(time)) {
							calendar.set(Calendar.HOUR_OF_DAY, config.getWorkingHoursStart());
							calendar.set(Calendar.MINUTE, 00);
						} else {
							calendar.add(Calendar.MINUTE, 30);
						}
						
						return calendar.getTime();
					}

					@Override
					public Object getPreviousValue() {
						Date nextValue = (Date) super.getValue();
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(nextValue);

						Date time = new Date();
						time.setTime(52200000);
						if (calendar.getTime().before(time)) {
							calendar.set(Calendar.HOUR_OF_DAY, config.getWorkingHoursEnd());
							calendar.set(Calendar.MINUTE, 00);
						} else {
							calendar.add(Calendar.MINUTE, -30);
						}
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
				endTime = Calendar.getInstance();
				endTime.set(Calendar.HOUR_OF_DAY, config.getWorkingHoursEnd());
				endTime.set(Calendar.MINUTE, 00);
				endTimePicker = new JSpinner();
				endTimePicker.setModel(new SpinnerDateModel(endTime.getTime(), null, null, Calendar.HOUR_OF_DAY) {
					@Override
					public Object getNextValue() {
						Date nextValue = (Date) super.getValue();
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(nextValue);

						Date time = new Date();
						time.setTime(77400000);
						if (calendar.getTime().after(time)) {
							calendar.set(Calendar.HOUR_OF_DAY, config.getWorkingHoursStart());
							calendar.set(Calendar.MINUTE, 00);
						} else {
							calendar.add(Calendar.MINUTE, 30);
						}
						return calendar.getTime();
					}

					@Override
					public Object getPreviousValue() {
						Date nextValue = (Date) super.getValue();
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(nextValue);

						Date time = new Date();
						time.setTime(52200000);
						if (calendar.getTime().before(time)) {
							calendar.set(Calendar.HOUR_OF_DAY, config.getWorkingHoursEnd());
							calendar.set(Calendar.MINUTE, 00);
						} else {
							calendar.add(Calendar.MINUTE, -30);
						}
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
				errorMessageRoom = new JTextArea();
				errorMessageRoom.setEditable(false);
				errorMessageRoom.setLineWrap(true);
				errorMessageRoom.setWrapStyleWord(true);
				errorMessageRoom.setOpaque(true);
				errorMessageRoom.setBackground(config.getFrontPanelDefaultColor());
				errorMessageRoom.setBorder(BorderFactory.createEmptyBorder());
				errorMessageRoom.setForeground(config.getErrorMessageColor());
				JScrollPane errorMessageScrollPane = new JScrollPane(errorMessageRoom);
				errorMessageScrollPane.setBorder(BorderFactory.createEmptyBorder());
				errorMessageScrollPane.setBackground(config.getFrontPanelDefaultColor());
				errorMessageScrollPane.setOpaque(true);
				GridBagConstraints gbc_errorMessageRoom = new GridBagConstraints();
				gbc_errorMessageRoom.gridwidth = 2;
				gbc_errorMessageRoom.insets = new Insets(0, 0, 5, 5);
				gbc_errorMessageRoom.fill = GridBagConstraints.BOTH;
				gbc_errorMessageRoom.gridx = 1;
				gbc_errorMessageRoom.gridy = 10;
				gbc_errorMessageRoom.gridheight = 4;
				rightPanel.add(errorMessageScrollPane, gbc_errorMessageRoom);
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
				saveButton.setBackground(config.getButtonColorSavedBackground());
				saveButton.setBorder(config.getButtonSaveBorder());
				saveButton.setFocusable(false);
				saveButton.setFont(config.getButtonDefaultFont());
				saveButton.setOpaque(true);
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						confirmBooking();
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
				cancelButton.setBorder(config.getButtonCancelBorder());
				cancelButton.setFocusable(false);
				cancelButton.setFont(config.getButtonDefaultFont());
				cancelButton.setOpaque(true);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		// initializeHashMap();

	}

	public CreateBookingDialog(User user, LocalDateTime startInterval, LocalDateTime endInterval, BookingPanel panel)
			throws SQLException {
		this(user, panel);
		if (startInterval.compareTo(LocalDateTime.now()) >= 0) {
			startTimePicker.setValue(Date.from(startInterval.atZone(ZoneId.systemDefault()).toInstant()));
			endTimePicker.setValue(Date.from(endInterval.atZone(ZoneId.systemDefault()).toInstant()));
			datePicker.setValue(Date.from(startInterval.atZone(ZoneId.systemDefault()).toInstant()));
		}
	}

	/**
	 * private void initializeHashMap() { fields = new HashMap<>();
	 * 
	 * for(Component textField: leftPanel.getComponents()) { if(textField instanceof
	 * JTextField) { fields.put((JTextField)textField, false); } } }
	 **/

	private void formatTextField(JTextComponent component) {
		component.setForeground(config.getLabelDefaultForeground());
		component.setFont(config.getLabelDefaultFont());
		component.setBorder(BorderFactory.createLineBorder(new Color(212, 212, 212), 1));
	}

	private boolean checkInformation(JLabel label, JTextField field) {
		boolean informationCorrect = false;

		if (bookingController.validateInformation(new String[] { label.getName(), field.getText() }) == false) {
			label.setForeground(config.getErrorMessageColor());
			field.setBorder(new LineBorder(config.getErrorMessageColor()));
			fields.put(field, false);
		} else {
			label.setForeground(config.getLabelDefaultForeground());
			field.setBorder(config.getTextFieldDefaultBorder());
			fields.put(field, true);
			informationCorrect = true;
		}

		return informationCorrect;
	}

	private boolean checkRoomAvailability() throws SQLException {
		errorMessageRoom.setVisible(false);
		String errorMessage = "Interference with the following booking(s):";
		boolean bookingInterference = false;
		if (selectedRooms.isEmpty())
		{
			errorMessage = "There are no rooms selected!";
			bookingInterference = true;
		}
		
		for (Room r : selectedRooms)
		{
			String roomCheck = bookingController.checkAvailability(getStartTime(), getEndTime(), r);
			if (!roomCheck.equals("")) {
				errorMessage += (roomCheck);
				bookingInterference = true;
			}
		}
		if (bookingInterference) {
			errorMessageRoom.setText(errorMessage);
			errorMessageRoom.setVisible(true);
			return false;
		} else {
			return true;
		}
	}

	private void addSelectedRoom(Room room) {
		if (!room.getName().equals(" Select...")) {
			selectedRooms.add(room);
			listModel.removeAllElements();
			for (Room e : selectedRooms) {
				listModel.addElement(e);
			}
			for (int e = 0; e < comboBox.getItemCount(); e++)
			{
				
				if (comboBox.getItemAt(e).getId() == room.getId())
				{
					comboBox.removeItem(comboBox.getItemAt(e));
					break;
				}
			}
		}
	}

	private void removeSelectedRoom(Room room) {
		if (selectedRooms.contains(room)) {
			selectedRooms.remove(room);
			listModel.removeAllElements();
			for (Room e : selectedRooms) {
				listModel.addElement(e);
			}
			comboBox.addItem(room);
		}
	}

	private boolean checkUnvalidatedFields() {
		boolean informationCorrect = true;
		JLabel label = null;
		JTextField textField = null;

		for (Component component : leftPanel.getComponents()) {
			if (component instanceof JLabel) {
				label = (JLabel) component;
			}
			if (component instanceof JTextField) {
				textField = (JTextField) component;
				if (!fields.getOrDefault(textField, false)) {
					if (label.getName() != null) {
						if (!checkInformation(label, textField)) {
							informationCorrect = false;
						}
					}
				}
			}
		}

		return informationCorrect;
	}

	private void confirmBooking() {
		try {
			if (checkRoomAvailability()) {
				if (!checkUnvalidatedFields()) {
					JOptionPane.showMessageDialog(null, "Some of the information is not correct.",
							"Invalid information", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// if the email matches the users email, then contact is null
				User contactPerson = null;
				if (!emailTextField.getText().equalsIgnoreCase(user.getEmail())) {
					contactPerson = new User(nameTextField.getText(), phoneTextField.getText(),
							emailTextField.getText());
				}

				LocalDateTime startDateTime = getStartTime();
				LocalDateTime endDateTime = getEndTime();
								
				if (bookingController.confirmBooking(titleTextField.getText(), descriptionTextArea.getText(),
						contactPerson, Integer.parseInt(attendeesTextField.getText()), user, selectedRooms,
						startDateTime, endDateTime)) {
					bookingPanel.getAllBookingsForAWeek(startDateTime);
					MainUI.updateLog();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Failed to confirm your booking.", "Failed confirmation",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private LocalDateTime getStartTime()
	{
		Date startDate = (Date)startTimePicker.getValue();
		LocalTime bookingStartTime = LocalTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
		LocalDate localDate = ((Date)datePicker.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return LocalDateTime.of(localDate, bookingStartTime);
	}
	
	private LocalDateTime getEndTime()
	{
		Date endDate = (Date)endTimePicker.getValue();
		LocalTime bookingEndTime = LocalTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
		LocalDate localDate = ((Date)datePicker.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return LocalDateTime.of(localDate, bookingEndTime);
	}
}