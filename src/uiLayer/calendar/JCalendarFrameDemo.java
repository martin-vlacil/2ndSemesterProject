package uiLayer.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import uiLayer.calendar.JCalendar;
import uiLayer.events.IntervalChangedEvent;
//import events.IntervalChangedEvent;
import uiLayer.events.IntervalChangedListener;
import uiLayer.events.IntervalSelectionEvent;
import uiLayer.events.IntervalSelectionListener;
//import events.ModelChangedEvent;
//import events.ModelChangedListener;
import uiLayer.events.SelectionChangedEvent;
import uiLayer.events.SelectionChangedListener;
import uiLayer.model.CalendarEvent;
import uiLayer.model.EventType;
import uiLayer.ui.strategy.DisplayStrategy;
import uiLayer.ui.strategy.DisplayStrategy.Type;
import uiLayer.util.CalendarUtil;

/**
 * @author costache
 * 
 */
public class JCalendarFrameDemo extends JFrame {

	/**
	 * Requires a serialID, because it extends JFrame
	 */
	private static final long serialVersionUID = 1L;

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm:ss:SSS");
	//private final Random r = new Random();

	//private JMenuBar menuBar;
	//private JMenu fileMenu;
	//private JToolBar toolBar;
	//private JTextArea description;
	//private JMenuItem exitMenuItem;
	private JCalendar jCalendar;
	private JPanel content;
	private JPopupMenu popup;
	private JMenuItem removeMenuItem;

	//private final String[] names = new String[] { "Team meeting", "Code review"};

	public JCalendarFrameDemo() {

		initGui();
		initData();
		bindListeners();

	}

	private void initGui() {

		/*
	 	menuBar = new JMenuBar();

		fileMenu = new JMenu("File");
		exitMenuItem = new JMenuItem("Exit");

		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);

		XXX removed toolbar and add/remove buttons. Possible icon funcitonality can be reused for putting icons inside buttons
		toolBar = new JToolBar("Controls");
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		
		Image addImg = null;
		Image removeImg = null;
		try {
			addImg = ImageIO.read(getClass().getResource("add-icon.png"));
			removeImg = ImageIO.read(getClass().getResource("remove-icon.png"));
			addButton.setIcon(new ImageIcon(addImg));
			removeButton.setIcon(new ImageIcon(removeImg));
		} catch (final Exception e) {

		}
		toolBar.add(addButton);
		toolBar.add(removeButton);
		*/
		
		//used to create the right-click menu
		
		removeMenuItem = new JMenuItem("Remove");
		//removeMenuItem.setIcon(new ImageIcon(removeImg));

		popup = new JPopupMenu();
		popup.add(removeMenuItem);
		//popup.add(new JSeparator());
		//TODO description JTextArea deleted, bottom description pane "Event clicked, removed, added"
		/*description = new JTextArea();
		description.setLineWrap(true);
		description.setRows(10);
		*/
		jCalendar = new JCalendar();
		jCalendar.setPreferredSize(new Dimension(1024, 768));
		jCalendar.setJPopupMenu(popup);
		jCalendar.getConfig().setAllDayPanelVisible(false);
		//jCalendar.getConfig().setHolidays(Arrays.asList(new Date()));

		//content = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(jCalendar);
		//content.add(new JScrollPane(description));

		this.getContentPane().setLayout(new BorderLayout(10, 10));
		
		//this.getContentPane().add(toolBar, BorderLayout.PAGE_START);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

	}

	private void initData() {
		
		//XXX write requests for data population - CRUD Booking?
		/*
		final EventType type1 = new EventType();

		final EventType type2 = new EventType();
		type2.setBackgroundColor(new Color(255, 103, 0, 128));
		
		//TODO
		//final EventType type3 = new EventType();
		//type3.setBackgroundColor(new Color(165, 103, 230, 128));

		final EventType[] types = new EventType[3];
		types[0] = type1;
		types[1] = type2;
		//types[2] = type3;

		CalendarEvent calendarEvent;
		for (int i = 0; i < 100; i++) {
			int hour = r.nextInt(19);
			hour = hour > 17 ? 17 : hour;
			hour = hour < 8 ? 8 : hour;
			final int min = r.nextInt(59);
			final int day = r.nextInt(30);
			final int month = 5;
			final int year = 2021;
			final Date start = CalendarUtil.createDate(year, month, day, hour, min, 0, 0);
			final Date end = CalendarUtil.createDate(year, month, day, hour + 1 + r.nextInt(4), r.nextInt(59), 0, 0);
			calendarEvent = new CalendarEvent(names[r.nextInt(2)], start, end);
			calendarEvent.setType(types[r.nextInt(2)]);
			calendarEvent.setAllDay(i % 2 == 0);
			jCalendar.addCalendarEvent(calendarEvent);
		}

		Date start = CalendarUtil.createDate(2021, 4, 1, 12, 45, 0, 0);
		Date end = CalendarUtil.createDate(2021, 4, 1, 16, 35, 0, 0);
		calendarEvent = new CalendarEvent("Overlapping", start, end);
		jCalendar.addCalendarEvent(calendarEvent);

		start = CalendarUtil.createDate(2021, 4, 1, 8, 45, 0, 0);
		end = CalendarUtil.createDate(2021, 4, 1, 15, 35, 0, 0);
		calendarEvent = new CalendarEvent("Overlapping 2", start, end);
		jCalendar.addCalendarEvent(calendarEvent);
		*/
	}


	 private void bindListeners() {
		 //XXX removed exit button binder
		/*exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				System.exit(0);
			}
		});

		 XXX Used by add/remove button listeners
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {

				final int hour = r.nextInt(19);
				final int min = r.nextInt(59);
				final int day = r.nextInt(28);
				final int month = r.nextInt(11);
				final int year = 2010 + r.nextInt(8);
				final Date start = CalendarUtil.createDate(year, month, day, hour, min, 0, 0);
				final Date end = CalendarUtil.createDate(year, month, day, hour + 1 + r.nextInt(4), r.nextInt(59), 0, 0);
				final CalendarEvent calendarEvent = new CalendarEvent("Added ", start, end);

				jCalendar.addCalendarEvent(calendarEvent);
				jCalendar.setDisplayStrategy(DisplayStrategy.Type.DAY, start);
			}
		});

		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
		final Collection<CalendarEvent> selected = jCalendar.getSelectedCalendarEvents();
				for (final CalendarEvent event : selected) {
					jCalendar.removeCalendarEvent(event);
				}
			}
		});
		 */
		
		
		removeMenuItem.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				final Collection<CalendarEvent> selected = jCalendar.getSelectedCalendarEvents();
				for (final CalendarEvent event : selected)
				{
					jCalendar.removeCalendarEvent(event);
				}
			}
		});

		/*jCalendar.addCollectionChangedListener(new ModelChangedListener() {

			@Override
			public void eventRemoved(final ModelChangedEvent event) {
				//XXX handle event removed
				//description.append("Event removed " + event.getCalendarEvent() + "\n");
			}

			@Override
			public void eventChanged(final ModelChangedEvent event) {
				//XXX handle event changed
				//description.append("Event changed " + event.getCalendarEvent() + "\n");
			}

			@Override
			public void eventAdded(final ModelChangedEvent event) {
				//XXX handle event added
				//description.append("Event added " + event.getCalendarEvent() + "\n");
			}
		});*/

		jCalendar.addSelectionChangedListener(new SelectionChangedListener() {

			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				if (event.getCalendarEvent() != null) {
					if (event.getCalendarEvent().isSelected()) {
						//XXX triggered if you select an event after another is selected
						//description.append("Event selected " + event.getCalendarEvent());
					} else {
						//XXX triggered if you select an event before any are 
						//description.append("Event deselected " + event.getCalendarEvent());
					}
				} else {
					//XXX triggered when deselecting an event
					//description.append("Selection cleared");
					System.out.println("Selection");
				}
				//description.append("\n");
			}
		});

		jCalendar.addIntervalChangedListener(new IntervalChangedListener() {

			@Override
			public void intervalChanged(IntervalChangedEvent event) {
				/*description.append("Interval changed " + sdf.format(event.getIntervalStart()) + " "
						+ sdf.format(event.getIntervalEnd()) + "\n");*/
			}
		});
		
		jCalendar.addIntervalSelectionListener(new IntervalSelectionListener() {
			
			@Override
			public void intervalSelected(IntervalSelectionEvent event) {
				/*description.append("Interval selection changed " + sdf.format(event.getIntervalStart()) + " "
						+ sdf.format(event.getIntervalEnd()) + "\n");*/
				System.out.println("Interval selection changed " + sdf.format(event.getIntervalStart()) + " "
						+ sdf.format(event.getIntervalEnd()) + "\n");
			}
		});

		popup.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			//XXX Refactored to disable Right click menu if you click on an empty space
			removeMenuItem.setEnabled(jCalendar.getSelectedCalendarEvents().size() > 0);
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	public static void main(final String[] args) throws MalformedObjectNameException, NullPointerException,
			InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
		
		Locale.setDefault(Locale.UK);
		LocalDate start = LocalDate.now().with(DayOfWeek.MONDAY);
		start = start.plusDays(1);
		System.out.println(start);
		final JCalendarFrameDemo frameTest = new JCalendarFrameDemo();
		frameTest.setVisible(true);
	}
}
