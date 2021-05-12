/* ************************************************************************ *
 * Copyright 2011 costache for MSD                                            *
 * ************************************************************************ */
package uiLayer.events;

import java.time.LocalDate;
import java.time.LocalDateTime;

import uiLayer.calendar.JCalendar;

/**
 * @author costache
 * 
 */
public class IntervalSelectionEvent {

	private JCalendar owner;
	private LocalDate intervalStart;
	private LocalDate intervalEnd;

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
	public IntervalSelectionEvent(final JCalendar owner, final LocalDate intervalStart, final LocalDate intervalEnd) {
		super();
		this.owner = owner;
		this.intervalStart = intervalStart;
		this.intervalEnd = intervalEnd;
	}

	/**
	 * @return the intervalStart
	 */
	public LocalDate getIntervalStart() {
		return intervalStart;
	}

	/**
	 * @param intervalStart
	 *           the intervalStart to set
	 */
	public void setIntervalStart(final LocalDate intervalStart) {
		this.intervalStart = intervalStart;
	}

	/**
	 * @return the intervalEnd
	 */
	public LocalDate getIntervalEnd() {
		return intervalEnd;
	}

	/**
	 * @param intervalEnd
	 *           the intervalEnd to set
	 */
	public void setIntervalEnd(final LocalDate intervalEnd) {
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

}
