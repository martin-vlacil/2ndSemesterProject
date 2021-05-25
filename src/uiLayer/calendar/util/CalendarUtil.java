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
package uiLayer.calendar.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import modelLayer.Booking;

/**
 * @author theodorcostache
 */
public class CalendarUtil {
	
	//XXX deleted unused methods

    /**
     * XXX changed from Date to LocalDate/LocalDateTime. gets localdatetime and provides all LocalDates between them.
     */
    public static Collection<LocalDate> getDates(final LocalDateTime start, final LocalDateTime end) {

        final Set<LocalDate> result = new HashSet<LocalDate>();
        final LocalDate endDay = end.toLocalDate();
        LocalDate date = start.toLocalDate();
        result.add(date);
        while (date.plusDays(1).isBefore(endDay))
            result.add(date);

        result.add(endDay);
        return result;
    }
    
    /* XXX Not used method
    public static long getTotalSeconds(final Date date) {
        final Calendar c = CalendarUtil.getCalendar(date, false);
        long seconds = c.get(Calendar.HOUR_OF_DAY) * 60 * 60;
        seconds += c.get(Calendar.MINUTE) * 60;
        seconds += c.get(Calendar.SECOND);
        return seconds;
    }*/

    public static int secondsToPixels(final LocalDateTime date, final int maxHeight) {
        //final long seconds = getTotalSeconds(date);
        final long seconds = date.toLocalTime().toSecondOfDay();
    	return Math.round(seconds * maxHeight / 86400.0f);
    }

    /*
     * XXX removed code, concerning Date.
     */
    public static LocalDateTime pixelToDate(final LocalDate day, final int posY, final int maxHeight) {
        final long seconds = Math.round(posY * 86400.0f / maxHeight);
        /*final Calendar c = CalendarUtil.getCalendar(day, false);
        c.set(Calendar.HOUR_OF_DAY, (int) (seconds / 3600));
        c.set(Calendar.MINUTE, (int) (seconds % 3600) / 60);
        c.set(Calendar.SECOND, (int) (seconds % 3600) % 60);
        return c.getTime();*/
        return day.atTime((int) (seconds / 3600), (int) (seconds % 3600) / 60, (int) (seconds % 3600) % 60);
    }
  //XXX CalendarEvent changed to Booking
    public static Map<Booking, List<Booking>> getConflicting(final Collection<Booking> calendarEvents) {
    	//XXX CalendarEvent changed to Booking
        final List<Booking> clonedCollection = new ArrayList<Booking>(calendarEvents);
      //XXX CalendarEvent changed to Booking
        final Map<Booking, List<Booking>> conflictingEvents = new HashMap<Booking, List<Booking>>();

        for (int i = 0; i < clonedCollection.size(); i++) {
        	//XXX CalendarEvent changed to Booking
            final Booking event1 = clonedCollection.get(i);
          //XXX CalendarEvent changed to Booking
             conflictingEvents.put(event1, new ArrayList<Booking>());
            for (int j = 0; j < clonedCollection.size(); j++) {
            	//XXX CalendarEvent changed to Booking
                final Booking event2 = clonedCollection.get(j);
                //XXX Removed
                /*
                if (event2.isAllDay() || event2.isHoliday())
                    continue;
                */
                //XXX getStart() and getEnd() renamed to getStartTime() and getEndTime()
                final LocalDateTime startA = event1.getStartTime();
                final LocalDateTime endA = event1.getEndTime();
                final LocalDateTime startB = event2.getStartTime();
                final LocalDateTime endB = event2.getEndTime();

                final boolean isStartABeforeEndB = (startA.compareTo(endB)) < 0;
                final boolean isEndAAfterStartB = (endA.compareTo(startB)) > 0;

                boolean isCurrentPairOverlap = false;

                isCurrentPairOverlap = isStartABeforeEndB && isEndAAfterStartB;

                if (isCurrentPairOverlap) {
                    conflictingEvents.get(event1).add(event2);
                }
            }

            Collections.sort(conflictingEvents.get(event1));
        }
      //XXX CalendarEvents changed to Booking
        final Set<Booking> keys = new HashSet<Booking>(conflictingEvents.keySet());
      //XXX CalendarEvents changed to Booking
        final Map<Booking, List<Booking>> result = new HashMap<Booking, List<Booking>>();
      //XXX CalendarEvent have been renamed to Booking
        for (final Booking event : keys) {
        	//XXX CalendarEvents changed to Booking
            final Set<Booking> visitedEvents = new HashSet<Booking>();
          //XXX CalendarEvents changed to Booking
            final Set<Booking> tempSet = new HashSet<Booking>();
            copyAll(visitedEvents, tempSet, conflictingEvents, event);
          //XXX CalendarEvents changed to Booking
            final List<Booking> newConflictingEventsList = new ArrayList<Booking>(tempSet);
            Collections.sort(newConflictingEventsList);
            result.put(event, newConflictingEventsList);
        }

        return result;
    }
  //XXX CalendarEvents changed to Booking
    private static void copyAll(final Set<Booking> visitedEvents, final Set<Booking> tempSet,
                                final Map<Booking, List<Booking>> conflictingEvents, final Booking event) {
    	//XXX CalendarEvents changed to Booking
        for (final Booking ce : conflictingEvents.get(event)) {

            if (!visitedEvents.contains(ce)) {
                visitedEvents.add(ce);
                tempSet.addAll(conflictingEvents.get(ce));
                copyAll(visitedEvents, tempSet, conflictingEvents, ce);
            }
        }
    }

}
