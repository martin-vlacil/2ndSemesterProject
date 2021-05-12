package uiLayer;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelLayer.Room;
import uiLayer.calendar.JCalendar;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Dialog.ModalityType;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookingPanel extends JPanel {

	private JCalendar calendar;
	
	/**
	 * Create the panel.
	 */
	public BookingPanel() {
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

		JComboBox<Room> comboBox = new JComboBox<Room>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {" Select..."}));
		comboBox.setPreferredSize(new Dimension(100, 30));
		comboBox.setEditable(false);
		comboBox.setFocusable(false);
		comboBox.setFont(new Font("Roboto", Font.PLAIN, 15));
		comboBox.setForeground(Color.GRAY);
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
	}
	
	private void openCreateBookingDialog()
	{
		try {
			CreateBookingDialog dialog = new CreateBookingDialog(null); // TODO change to logged user
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
