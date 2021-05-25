/* ************************************************************************ *
 * Copyright 2011 costache for MSD                                            *
 * ************************************************************************ */

/**
 * Edited by Miroslav for a school project
 * School: University College Of Northern Denmark
 * Project: Booking System
 * Year: 2021
 * 
 */


package uiLayer.calendar.util;

import java.time.LocalDateTime;
import java.util.*;

import modelLayer.Booking;
import uiLayer.calendar.JCalendar;
import uiLayer.calendar.events.IntervalSelectionEvent;
import uiLayer.calendar.events.IntervalSelectionListener;

/**
 * @author costache
 * 
 */
public class EventRepository {

	private final Map<JCalendar, List<IntervalSelectionListener>> intervalSelectionListeners = new HashMap<JCalendar, List<IntervalSelectionListener>>();

	private static final EventRepository instance = new EventRepository();

	/**
	 * 
	 */
	private EventRepository() {

	}

	public static EventRepository get() {
		return instance;
	}

	public void addIntervalSelectionListener(final JCalendar owner,
			final IntervalSelectionListener intervalSelectionListener) {
		if (!intervalSelectionListeners.containsKey(owner)) {
			intervalSelectionListeners.put(owner, new ArrayList<IntervalSelectionListener>());
		}
		this.intervalSelectionListeners.get(owner).add(intervalSelectionListener);
	}

	public void removeIntervalSelectionListener(final JCalendar owner,
			final IntervalSelectionListener intervalSelectionListener) {
		if (intervalSelectionListeners.containsKey(owner))
			this.intervalSelectionListeners.get(owner).remove(intervalSelectionListener);
	}

	public void triggerIntervalSelection(final JCalendar owner, final LocalDateTime start, final LocalDateTime end) {
		if (!intervalSelectionListeners.containsKey(owner))
			return;
		final IntervalSelectionEvent selectionEvent = new IntervalSelectionEvent(owner, start, end);
		for (final IntervalSelectionListener listener : intervalSelectionListeners.get(owner)) {
			listener.intervalSelected(selectionEvent);
		}
	}
	//XXX added new trigger for accepting a booking action.
	public void triggerIntervalSelection(final JCalendar owner, Booking booking, final LocalDateTime start, final LocalDateTime end) {
		if (!intervalSelectionListeners.containsKey(owner))
			return;
		final IntervalSelectionEvent selectionEvent = new IntervalSelectionEvent(owner, start, end);
		selectionEvent.setBooking(booking);
		for (final IntervalSelectionListener listener : intervalSelectionListeners.get(owner)) {
			listener.intervalSelected(selectionEvent);
		}
	}
}
