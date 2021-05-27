package uiLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;

import modelLayer.*;
import config.Config;
import controlLayer.BookingController;
import databus.Databus;
import uiLayer.calendar.JCalendar;
import uiLayer.calendar.events.IntervalSelectionEvent;
import uiLayer.calendar.events.IntervalSelectionListener;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A panel for the Booking page. Used in CardLayout of the MainUI's frame
 * @author Group1 dmai0920
 */
@SuppressWarnings("serial")
public class BookingPanel extends JPanel
{
    private JCalendar calendar;
    private User loggedUser;
    private JComboBox<Room> comboBox;
    private Config config;

    /**
     * Create the panel.
     * 
     * @throws SQLException
     */
    public BookingPanel(User user) throws SQLException
    {
        this.loggedUser = user;
        config = new Config();

        this.setBackground(Color.WHITE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{ 30, 0, 0, 0, 30 };
        gridBagLayout.rowHeights = new int[]{ 30, 0, 0, 0, 60 };
        gridBagLayout.columnWeights = new double[]{ 0.0, 1.0, 1.0, 0.2, 0.0 };
        gridBagLayout.rowWeights = new double[]{ 0.0, 0.0, 0.0, 1.0, 0.0 };
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
        createBookingButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                initialiseBooking();
            }
        });
        createBookingButton.setForeground(Color.WHITE);
        createBookingButton.setBackground(config.getBlueColorDefault());
        createBookingButton.setBorder(new EmptyBorder(8, 10, 8, 10));
        createBookingButton.setFocusable(false);
        createBookingButton.setFont(config.getLabelTitleFont());
        createBookingButton.setOpaque(true);
        GridBagConstraints gbc_createBookingButton = new GridBagConstraints();
        gbc_createBookingButton.gridheight = 2;
        gbc_createBookingButton.anchor = GridBagConstraints.WEST;
        gbc_createBookingButton.insets = new Insets(0, 0, 5, 5);
        gbc_createBookingButton.gridx = 1;
        gbc_createBookingButton.gridy = 1;
        add(createBookingButton, gbc_createBookingButton);

        ArrayList<Room> allRooms = new BookingController().getAllRooms();
        allRooms.add(0, new Room("", -1, " Select...", -1));
        comboBox = new JComboBox<Room>();
        comboBox.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (((Room) comboBox.getSelectedItem()).getName() != " Select...")
                {
                    calendar.setRoom(((Room) comboBox.getSelectedItem()));
                }
                else
                {
                    calendar.setRoom(null);
                }
            }
        });
        comboBox.setModel(new DefaultComboBoxModel<Room>(allRooms.toArray(new Room[0])));
        ListCellRenderer<? super Room> renderer = new RoomComboboxCellRenderer();

        comboBox.setRenderer(renderer);
        comboBox.setPreferredSize(new Dimension(100, 30));
        comboBox.setEditable(false);
        comboBox.setFocusable(false);
        comboBox.setFont(config.getLogSize());
        comboBox.setForeground(Color.GRAY);
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 3;
        gbc_comboBox.gridy = 2;
        add(comboBox, gbc_comboBox);
        calendar = new JCalendar(this);
        getAllBookingsForAWeek(LocalDateTime.now());
        calendar.setPreferredSize(new Dimension(-1, -1));
        calendar.getConfig().setAllDayPanelVisible(false);
        GridBagConstraints gbc_calendar = new GridBagConstraints();
        gbc_calendar.gridwidth = 3;
        gbc_calendar.fill = GridBagConstraints.BOTH;
        gbc_calendar.gridx = 1;
        gbc_calendar.gridy = 3;
        add(calendar, gbc_calendar);
        bindListeners(this);
    }

    /**
     *  Method to open the crate booking dialog using the create booking button
     */
    private void initialiseBooking()
    {
        try
        {
            BookingDialog dialog = new BookingDialog(loggedUser, this);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
            URL url = getClass().getResource("images/ihndLogo.png");
            ImageIcon icon = new ImageIcon(url);
            dialog.setIconImage(icon.getImage());
            dialog.setTitle("Create Booking - IHND Booking System");
            // Centres the dialog
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to bind listeners which open the crate booking dialog by clicking on the calendar
     * @param panel
     */
    private void bindListeners(BookingPanel panel)
    {
        calendar.addIntervalSelectionListener(new IntervalSelectionListener()
        {
            @Override
            public void intervalSelected(IntervalSelectionEvent event)
            {
                try
                {
                    if (event.getBooking() == null)
                    {
                        LocalDateTime bookingStart = event.getIntervalStart();
                        // Checking
                        // 1) if the booking start time is after current time
                        // 2) if booking start time is not before workinghours start and end
                        // 3) if booking start time is after working hours
                        if ((bookingStart.isAfter(LocalDateTime.now())) && (!bookingStart.isBefore(bookingStart.withHour(config.getWorkingHoursStart())))
                                && (bookingStart.isBefore(bookingStart.withHour(config.getWorkingHoursEnd()))))
                        {
                            BookingDialog dialog = new BookingDialog(loggedUser, event.getIntervalStart(), event.getIntervalEnd(), panel);
                            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                            dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
                            URL url = getClass().getResource("images/ihndLogo.png");
                            ImageIcon icon = new ImageIcon(url);
                            dialog.setIconImage(icon.getImage());
                            dialog.setTitle("View Booking - IHND Booking System");
                            // Centres the dialog
                            dialog.setLocationRelativeTo(null);
                            dialog.setVisible(true);
                        }
                    }
                    else
                    {
                        Booking booking = event.getBooking();
                        BookingDialog dialog = new BookingDialog(booking, panel);
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
                        URL url = getClass().getResource("images/ihndLogo.png");
                        ImageIcon icon = new ImageIcon(url);
                        dialog.setIconImage(icon.getImage());
                        dialog.setTitle("View Booking - IHND Booking System");
                        // Centres the dialog
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * A method to get all bookings from the database for the week of a given date
     * @param currentDate
     * @throws SQLException
     */
    public void getAllBookingsForAWeek(LocalDateTime currentDate) throws SQLException
    {
        calendar.removeAllEvents();
        ArrayList<Booking> bookings = Databus.getInstance().getBookings();
        for (Booking booking : bookings)
        {
            calendar.addCalendarEvent(booking);
        }
    }
    
    /**
     * Method to update the calendar view after creating a booking
     * @param currentDate
     * @throws SQLException
     */
    public void confirmBooking(LocalDateTime currentDate) throws SQLException
    {
        calendar.removeAllEvents();
        BookingController bc = new BookingController();
    	ArrayList<Booking> bookings = bc.getAllBookingsForAWeek(currentDate);

        for (Booking booking : bookings)
        {
            calendar.addCalendarEvent(booking);
        }
    }

    /**
     * A getter for the currently selected room in the JComboBox
     * @return selected room
     */
    public Room getSelectedRoom()
    {
        return ((Room) comboBox.getSelectedItem());
    }

    /**
     * A getter for the logged user
     * @return loggedUser
     */
    public User getUser()
    {
        return loggedUser;
    }
}
