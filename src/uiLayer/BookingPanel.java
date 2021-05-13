package uiLayer;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import controlLayer.BookingController;
import modelLayer.Room;
import modelLayer.User;
import uiLayer.calendar.JCalendar;
import uiLayer.events.IntervalChangedEvent;
import uiLayer.events.IntervalChangedListener;
import uiLayer.events.IntervalSelectionEvent;
import uiLayer.events.IntervalSelectionListener;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BookingPanel extends JPanel {

	private JCalendar calendar;
	private User loggedUser;
	//private HashMap<String, Room> rooms; TODO remove if custom renderet was accepted
	
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public BookingPanel(User user) throws SQLException {
		this.loggedUser = user;
		//this.rooms = new HashMap<String, Room>(); TODO remove if custom renderet was accepted
		
		
		this.setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 0, 0, 0, 30};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0, 60};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.2, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel roomLabel = new JLabel("Room");
		roomLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
		GridBagConstraints gbc_roomLabel = new GridBagConstraints();
		gbc_roomLabel.anchor = GridBagConstraints.WEST;
		gbc_roomLabel.insets = new Insets(0, 0, 5, 0);
		gbc_roomLabel.gridx = 3;
		gbc_roomLabel.gridy = 1;
		add(roomLabel, gbc_roomLabel);
		
		JButton createBookingButton = new JButton("Create Booking \uFF0B");
		createBookingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openCreateBookingDialog();
			}
		});
		createBookingButton.setForeground(Color.WHITE);
		createBookingButton.setBackground(new Color(40, 41, 82));
		createBookingButton.setBorder(new EmptyBorder(8, 10, 8, 10));
		createBookingButton.setFocusable(false);
		createBookingButton.setFont(new Font("Roboto", Font.BOLD, 15));
		GridBagConstraints gbc_createBookingButton = new GridBagConstraints();
		gbc_createBookingButton.gridheight = 2;
		gbc_createBookingButton.anchor = GridBagConstraints.WEST;
		gbc_createBookingButton.insets = new Insets(0, 0, 5, 5);
		gbc_createBookingButton.gridx = 1;
		gbc_createBookingButton.gridy = 1;
		add(createBookingButton, gbc_createBookingButton);
		
		Room[] rooms = new BookingController().getAllRooms().toArray(new Room[0]);
		JComboBox<Room> comboBox = new JComboBox<Room>();
		comboBox.setModel(new DefaultComboBoxModel<Room>(rooms));
		ListCellRenderer<? super Room> renderer = new RoomComboboxCellRenderer();
		
	    comboBox.setRenderer(renderer);
		comboBox.setPreferredSize(new Dimension(100, 30));
		comboBox.setEditable(false);
		comboBox.setFocusable(false);
		comboBox.setFont(new Font("Roboto", Font.PLAIN, 15));
		comboBox.setForeground(Color.GRAY);
		//getAllRooms(comboBox); TODO remove if custom renderet was accepted
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 2;
		add(comboBox, gbc_comboBox);
		calendar = new JCalendar();
		calendar.setPreferredSize(new Dimension(-1, -1));
		calendar.getConfig().setAllDayPanelVisible(false);
		GridBagConstraints gbc_calendar = new GridBagConstraints();
		gbc_calendar.gridwidth = 3;
		gbc_calendar.fill = GridBagConstraints.BOTH;
		gbc_calendar.gridx = 1;
		gbc_calendar.gridy = 3;
		add(calendar, gbc_calendar);
		bindListeners();
	}
	
	private void openCreateBookingDialog()
	{
		try {
			CreateBookingDialog dialog = new CreateBookingDialog(null); // TODO change to logged user
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
			//Centers the dialog
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO Remove if custom renderer has been accepted
	/*private void getAllRooms(JComboBox<String> box) throws SQLException
	{
		ArrayList<Room> allRooms = new BookingController().getAllRooms();
		for (Room e : allRooms)
		{
			box.addItem(e.getName());
			rooms.add(...)
		}
	}*/
	
	private void bindListeners()
	{
		calendar.addIntervalChangedListener(new IntervalChangedListener() {

			@Override
			public void intervalChanged(IntervalChangedEvent event) {
				openCreateBookingDialog();
			}
		});
		calendar.addIntervalSelectionListener(new IntervalSelectionListener() {
			
			@Override
			public void intervalSelected(IntervalSelectionEvent event) {
				openCreateBookingDialog();
			}
		});
	}
}
