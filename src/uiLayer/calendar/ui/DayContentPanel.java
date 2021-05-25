/**
 * Copyright 2013 Theodor Costache
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. 
 */

/**
 * Edited by Miroslav, Aleks and Tamás for a school project
 * School: University College Of Northern Denmark
 * Project: Booking System
 * Year: 2021
 * 
 */
package uiLayer.calendar.ui;

import uiLayer.calendar.JCalendar;
import uiLayer.calendar.ui.strategy.Config;
import uiLayer.calendar.ui.strategy.DisplayStrategy.Type;
import uiLayer.calendar.util.*;

import javax.swing.*;

import modelLayer.Booking;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author theodorcostache
 */
public class DayContentPanel extends JPanel
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // XXX Converting from date to local date time
    // private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private final DayPanel owner;
    private Point startSelection;
    private Point endSelection;

    /**
     * Creates a new instance of {@link DayContentPanel}
     */
    public DayContentPanel(final DayPanel owner)
    {
        super(true);
        setOpaque(false);
        this.owner = owner;
        addListeners();
    }

    private void addListeners()
    {

        this.addMouseListener(new MouseAdapter()
        {

            final JCalendar calendar = DayContentPanel.this.owner.getOwner();

            @Override
            public void mouseClicked(final MouseEvent e)
            {
                for (final MouseListener ml : DayContentPanel.this.owner.getOwner().getMouseListeners())
                {
                    ml.mouseClicked(e);
                }
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1)
                {
                    // if (e.getButton() == MouseEvent.BUTTON1) {
                    if (startSelection == null || endSelection == null)
                        return;
                    LocalDateTime startDate = CalendarUtil.pixelToDate(owner.getDate(), (int) startSelection.getY(),
                            getHeight());
                    LocalDateTime endDate = CalendarUtil.pixelToDate(owner.getDate(), (int) endSelection.getY(),
                            getHeight());
                    EventRepository.get().triggerIntervalSelection(calendar, startDate, endDate);
                }
            }

            @Override
            public void mouseReleased(final MouseEvent e)
            {

                // XXX removed popup trigger
                /*
                 * if (e.isPopupTrigger() && calendar.getPopupMenu() != null) {
                 * calendar.getPopupMenu().show(DayContentPanel.this, e.getX(),
                 * e.getY()); }
                 */
                for (final MouseListener ml : DayContentPanel.this.owner.getOwner().getMouseListeners())
                {
                    ml.mouseReleased(e);
                }
            }

            @Override
            public void mousePressed(final MouseEvent e)
            {

                /*
                 * XXX disabled check for month view final boolean
                 * isSelectedStrategyMonth = calendar .getDisplayStrategy() ==
                 * Type.MONTH; final CalendarEvent event =
                 * isSelectedStrategyMonth ? getEventForMonth( e.getX(),
                 * e.getY()) : getNotMonthEvent(e.getX(), e.getY());
                 */
                // XXX CalendarEvent changed to Booking
                final Booking event = getEvent(e.getX(), e.getY());
                final EventCollection events = EventCollectionRepository.get(calendar);
                if (e.getClickCount() == 1)
                {
                    if (!e.isControlDown())
                    {
                        events.clearSelected(event, true);
                    }
                    if (event != null)
                    {
                        event.setSelected(true);
                        if (event.isSelected())
                        {
                            events.addSelected(event);
                        }
                        else
                        {
                            events.removeSelected(event);
                        }
                    }

                    calendar.validate();
                    calendar.repaint();

                }
                // XXX added new code to trigger view booking
                else if (e.getClickCount() == 2)
                {
                    if (event != null)
                    {
                        EventRepository.get().triggerIntervalSelection(calendar, event, event.getStartTime(), event.getEndTime());
                    }
                }
                // XXX removed popup trigger
                /*
                 * if (e.isPopupTrigger() && calendar.getPopupMenu() != null) {
                 * calendar.getPopupMenu().show(DayContentPanel.this, e.getX(),
                 * e.getY()); }
                 */
                for (final MouseListener ml : DayContentPanel.this.owner.getOwner().getMouseListeners())
                {
                    ml.mousePressed(e);
                }
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                startSelection = null;
                endSelection = null;
                calendar.validate();
                calendar.repaint();
            }

        });

        addMouseMotionListener(new MouseAdapter()
        {

            final JCalendar calendar = DayContentPanel.this.owner.getOwner();

            @Override
            public void mouseMoved(MouseEvent e)
            {
                /*
                 * final boolean isSelectedStrategyMonth = calendar
                 * .getDisplayStrategy() == Type.MONTH; final CalendarEvent
                 * event = isSelectedStrategyMonth ? getEventForMonth( e.getX(),
                 * e.getY()) : getNotMonthEvent(e.getX(), e.getY());
                 */
                // XXX CalendarEvent changed to Booking
                final Booking event = getEvent(e.getX(), e.getY());
                if (event != null)
                {
                    startSelection = null;
                    endSelection = null;
                    calendar.validate();
                    calendar.repaint();
                    return;
                }
                LocalDateTime startDate = CalendarUtil.pixelToDate(
                        owner.getDate(), (int) e.getY(), getHeight());
                // startDate = CalendarUtil.roundDateToHalfAnHour(startDate,
                // false);
                startDate = startDate.truncatedTo(ChronoUnit.HOURS).plusMinutes(30 * (startDate.getMinute() / 30));

                // Date endDate = CalendarUtil.pixelToDate(owner.getDate(),
                // (int) e.getY(), getHeight());
                LocalDateTime endDate = startDate.plusMinutes(30);
                // endDate = CalendarUtil.roundDateToHalfAnHour(endDate, true);

                startSelection = new Point(e.getX(), CalendarUtil.secondsToPixels(startDate, getHeight()));
                endSelection = new Point(e.getX(), CalendarUtil.secondsToPixels(endDate, getHeight()));
                calendar.validate();
                calendar.repaint();
            }

            @Override
            public void mouseDragged(final MouseEvent e)
            {
                if (startSelection == null)
                    return;
                if (e.getY() > startSelection.getY())
                {
                    // Date endDate =
                    // CalendarUtil.pixelToDate(owner.getDate(),(int) e.getY(),
                    // getHeight());
                    // endDate = CalendarUtil.roundDateToHalfAnHour(endDate,
                    // true);
                    LocalDateTime endDate = CalendarUtil.pixelToDate(owner.getDate(), (int) e.getY(), getHeight());
                    endDate = endDate.truncatedTo(ChronoUnit.HOURS).plusMinutes(30 * (endDate.getMinute() / 30));

                    endSelection = new Point(e.getX(), CalendarUtil.secondsToPixels(endDate, getHeight()));
                    calendar.validate();
                    calendar.repaint();
                }
            }
        });


        /*
         * XXX removed listener for tooltip
         * addMouseMotionListener(new MouseAdapter() {
         * 
         * final JCalendar calendar = DayContentPanel.this.owner .getOwner();
         * 
         * @Override public void mouseMoved(final MouseEvent e) {
         * super.mouseMoved(e);
         * 
         * final boolean isSelectedStrategyMonth = calendar
         * .getDisplayStrategy() == Type.MONTH; final CalendarEvent event =
         * isSelectedStrategyMonth ? getEventForMonth( e.getX(), e.getY()) :
         * getNotMonthEvent(e.getX(), e.getY());
         * 
         * 
         * 
         * if (event != null) {
         * setToolTipText(calendar.getTooltipFormater().format(event)); } else {
         * 
         * setToolTipText(calendar.getTooltipFormater().format(
         * EventCollectionRepository.get(calendar).getHolidayEvents(owner.
         * getDate()))); }
         * 
         * } });
         */
    }

    /**
     * returns the owner
     *
     * @return
     */
    public DayPanel getOwner()
    {
        return owner;
    }

    @Override
    public void paint(final Graphics g)
    {
        super.paint(g);
        drawBackground((Graphics2D) g);
        //XXX removed functionality for changing the model(day, week, month)
        /*
         * if (owner.getOwner().getDisplayStrategy() != Type.MONTH) {
         * drawCalendarEvents((Graphics2D) g); } else {
         * drawCalendarEventsMonth((Graphics2D) g); }
         */
        drawCalendarEvents((Graphics2D) g);

        if (startSelection != null && endSelection != null)
        {
            g.setColor(new Color(173, 216, 230, 50));
            final int height = (int) (endSelection.getY()
                    - startSelection.getY());
            final int xStart = 0;
            final int width = getWidth();
            final int yStart = (int) (height > 0 ? startSelection.getY()
                    : endSelection.getY());
            g.fillRect(xStart, yStart, Math.abs(width), Math.abs(height));
        }
    }

    private void drawBackground(final Graphics2D graphics2d)
    {
        final int height = getHeight();
        final int width = getWidth();
        final JCalendar calendar = owner.getOwner();
        final Config config = calendar.getConfig();
        final Color outsideWorkingHoursColor = config
                .getOutsideWorkingHoursColor();
        final Color dayDisableBackgroundColor = config
                .getDayDisabledBackgroundColor();
        final int workingHoursRectHeight = config.getWorkingHoursStart() * 60;
        final int workingHoursEndRectYStart = config.getWorkingHoursEnd() * 60;
        final int workingHoursEndHeight = height
                - config.getWorkingHoursEnd() * 60;
        final boolean isSelectedStrategyMonth = calendar
                .getDisplayStrategy() == Type.MONTH;
        // XXX CalendarEvent changed to Booking
        // final List<Booking> holidays =
        // EventCollectionRepository.get(calendar).getHolidayEvents(owner.getDate());

        if (isEnabled())
        {

            graphics2d.setColor(outsideWorkingHoursColor);
            graphics2d.fillRect(0, 0, width, workingHoursRectHeight);
            graphics2d.fillRect(0, workingHoursEndRectYStart, width,
                    workingHoursEndHeight);

        }
        else
        {
            graphics2d.setColor(dayDisableBackgroundColor);
            graphics2d.fillRect(0, 0, width, height);
        }

        graphics2d.setColor(config.getLineColor());
        graphics2d.drawRect(0, 0, width, height);

        if (!isSelectedStrategyMonth)
        {
            int y = 0;

            for (int i = 0; i < 24; i++)
            {
                y += 60;
                graphics2d.setColor(config.getMiddleLineColor());
                graphics2d.drawLine(0, y - 30, getWidth(), y - 30);
                graphics2d.setColor(config.getLineColor());
                graphics2d.drawLine(0, y, getWidth(), y);
            }
        }

    }

    private void drawCalendarEvents(final Graphics2D graphics2d)
    {

        // XXX Collection creation moved to custom method
        Collection<Booking> events = getBookings();
        // XXX CalendarEvents changed to Booking
        final Map<Booking, List<Booking>> conflictingEvents = CalendarUtil
                .getConflicting(events);

        final Config config = owner.getOwner().getConfig();
        if (events.size() > 0)
        {
            // XXX CalendarEvent changed to Booking
            for (final Booking event : events)
            {
                // XXX removed
                /*
                 * if (event.isAllDay() || event.isHoliday()) continue;
                 */
                // XXX added functionality for displaying only events for a
                // certain room
                JCalendar calendar = this.owner.getOwner();
                Color bgColor = event.getType().getBackgroundColor();
                bgColor = bgColor == null
                        ? config.getEventDefaultBackgroundColor()
                        : bgColor;
                Color fgColor = event.getType().getForegroundColor();
                fgColor = fgColor == null
                        ? config.getEventDefaultForegroundColor()
                        : fgColor;

                graphics2d.setColor(!event.isSelected() ? bgColor
                        : bgColor.darker().darker());
                int eventStart = 0;

                // XXX Changed from getStart() to getStartTime()
                // XXX Changed to use LocalDate funcitonality instead of
                // external Util class
                final boolean isSameStartDay = event.getStartTime()
                        .toLocalDate().isEqual(owner.getDate());
                if (isSameStartDay)
                {

                    // XXX Changed from getStart() to getStartTime()
                    eventStart = CalendarUtil
                            .secondsToPixels(event.getStartTime(), getHeight());
                }

                int eventYEnd = getHeight();
                // XXX Changed from getEnd() to getEndTime()
                if (event.getEndTime().toLocalDate().isEqual(owner.getDate()))
                {
                    // XXX Changed from getEnd() to getEndTime()
                    eventYEnd = CalendarUtil.secondsToPixels(event.getEndTime(),
                            getHeight());
                }

                final int conflictIndex = conflictingEvents.get(event)
                        .indexOf(event);
                final int conflictingEventsSize = conflictingEvents.get(event)
                        .size();

                graphics2d.fillRoundRect(
                        conflictIndex * (getWidth() - 4)
                                / conflictingEventsSize,
                        eventStart,
                        (getWidth() - 4) / conflictingEventsSize - 2,
                        eventYEnd - eventStart, 12, 12);
                // XXX Changed from getEnd() to getEndTime(), the getStart() to
                // getStartTime() and the getSummary() to getTitle()
                final String eventString = formatter
                        .format(event.getStartTime()) + " "
                        + formatter.format(event.getEndTime()) + " "
                        + event.getTitle();

                graphics2d.setFont(new Font("Verdana", Font.BOLD, 9));
                graphics2d
                        .setColor(!event.isSelected() ? fgColor : Color.white);

                GraphicsUtil.drawString(graphics2d, eventString,
                        conflictIndex * (getWidth() - 4) / conflictingEventsSize
                                + 3,
                        eventStart + 11,
                        (getWidth() - 4) / conflictingEventsSize - 3,
                        eventYEnd - eventStart);
            }
        }
    }

    // XXX CalendarEvent changed to Booking, changed name to getEvent from
    //refactored from getNonMonthEvent to getEvent, because of removed functionality for changing the model(day, week, month)
    private Booking getEvent(final int x, final int y)
    {

        // XXX Collection creation moved to custom method
        Collection<Booking> events = getBookings();
        // XXX CalendarEvent changed to Booking
        final Map<Booking, List<Booking>> conflictingEvents = CalendarUtil
                .getConflicting(events);

        if (events.size() > 0)
        {
            // XXX CalendarEvent changed to Booking
            for (final Booking event : events)
            {
                // XXX Removed
                /*
                 * if (event.isAllDay() || event.isHoliday()) continue;
                 */
                int eventYStart = 0;
                // XXX Changed from getStart() to getStartTime()
                final boolean isSameStartDay = event.getStartTime().toLocalDate().isEqual(owner.getDate());
                if (isSameStartDay)
                {
                    // XXX Changed from getStart() to getStartTime()
                    eventYStart = CalendarUtil.secondsToPixels(event.getStartTime(), getHeight());
                }

                int eventYEnd = getHeight();
                // XXX Changed from getEnd() to getEndTime()
                // XXX Changed to use built in funtionality inseat of
                // CalendarUtil
                if (event.getEndTime().toLocalDate().isEqual(owner.getDate()))
                {
                    // XXX Changed from getEnd() to getEndTime()
                    eventYEnd = CalendarUtil.secondsToPixels(event.getEndTime(), getHeight());
                }

                final int conflictIndex = conflictingEvents.get(event).indexOf(event);
                final int conflictingEventsSize = conflictingEvents.get(event).size();

                final int rectXStart = conflictIndex * (getWidth() - 4) / conflictingEventsSize;
                final int rectYStart = eventYStart;

                final int rectWidth = (getWidth() - 4) / conflictingEventsSize - 2;

                final int rectHeight = eventYEnd - eventYStart;

                final Rectangle r = new Rectangle(rectXStart, rectYStart, rectWidth, rectHeight);
                if (r.contains(x, y))
                {
                    return event;
                }
            }
        }
        return null;
    }

    // XXX Get selected room
    public Collection<Booking> getBookings()
    {
        final EventCollection eventsCollection = EventCollectionRepository.get(owner.getOwner());
        // XXX CalendarEvent changed to Booking
        final Collection<Booking> events = eventsCollection.getEvents(owner.getDate());
        final JCalendar calendar = DayContentPanel.this.owner.getOwner();
        ArrayList<Booking> bookings = new ArrayList<>();
        if (calendar.getRoom() == null)
        {
            return events;
        }
        events.parallelStream()
                .filter(p -> p.getRoom().getId() == calendar.getRoom().getId())
                .forEach(e -> bookings.add(e));

        return bookings;
    }
}
