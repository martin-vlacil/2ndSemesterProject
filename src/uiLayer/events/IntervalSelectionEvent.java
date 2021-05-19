/* ************************************************************************ *
 * Copyright 2011 costache for MSD                                            *
 * ************************************************************************ */
package uiLayer.events;

import java.time.LocalDateTime;

import modelLayer.Booking;
import uiLayer.calendar.JCalendar;

/**
 * @author costache
 * 
 */
public class IntervalSelectionEvent {

	private JCalendar owner;
	private LocalDateTime intervalStart;
	private LocalDateTime intervalEnd;
	private Booking booking;

	/**
	 * Creates a new instance of {@link IntervalSelectionEvent}
	 */
	public IntervalSelectionEvent() {
	}

	/**
	 * Creates a new instance of {@link IntervalSelectionEvent}
	 * 
	 * @param intervalStart
	 *           the interval start
	 * @param intervalEnd
	 *           the interval end
	 * 
	 */
	public IntervalSelectionEvent(final JCalendar owner, final LocalDateTime intervalStart, final LocalDateTime intervalEnd) {
		super();
		this.owner = owner;
		this.intervalStart = intervalStart;
		this.intervalEnd = intervalEnd;
	}

	/**
	 * @return the intervalStart
	 */
	public LocalDateTime getIntervalStart() {
		return intervalStart;
	}

	/**
	 * @param intervalStart
	 *           the intervalStart to set
	 */
	public void setIntervalStart(final LocalDateTime intervalStart) {
		this.intervalStart = intervalStart;
	}

	/**
	 * @return the intervalEnd
	 */
	public LocalDateTime getIntervalEnd() {
		return intervalEnd;
	}

	/**
	 * @param intervalEnd
	 *           the intervalEnd to set
	 */
	public void setIntervalEnd(final LocalDateTime intervalEnd) {
		this.intervalEnd = intervalEnd;
	}

	/**
	 * @return the owner
	 */
	public JCalendar getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *           the owner to set
	 */
	public void setOwner(final JCalendar owner) {
		this.owner = owner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IntervalSelectionEvent [owner=" + owner + ", intervalStart=" + intervalStart + ", intervalEnd="
				+ intervalEnd + "]";
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
