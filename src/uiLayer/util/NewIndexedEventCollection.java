package uiLayer.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import modelLayer.Booking;
import uiLayer.calendar.JCalendar;
import uiLayer.events.SelectionChangedListener;

public class NewIndexedEventCollection implements EventCollection {

	private final MultiValuedMap<LocalDate, Booking> indexedEvents;
    private final List<SelectionChangedListener> selectionChangedListeners;
    private final Set<Booking> selectedEvents;
    private final JCalendar parent;
	
    public NewIndexedEventCollection(final JCalendar parent) {
        this.parent = parent;
        indexedEvents =  new ArrayListValuedHashMap<LocalDate, Booking>();
        this.selectionChangedListeners = new ArrayList<SelectionChangedListener>();
        this.selectedEvents = new HashSet<Booking>();
    }

	@Override
	public void add(Booking event) {
		
        final Collection<LocalDate> dates = CalendarUtil.getDates(event.getStartTime(), event.getEndTime());
        for (final LocalDate date : dates)
        {
            if (!indexedEvents.containsValue(event))
            {
                indexedEvents.put(date, event);
            }
        }
        
	}

	@Override
	public void remove(Booking event) {
        selectedEvents.remove(event);

        for (final LocalDate key : new HashSet<LocalDate>(indexedEvents.keySet())) {
        	final Collection<Booking> events = indexedEvents.get(key);
            if (events.contains(event))
                indexedEvents.removeMapping(key, event);
        }
	}

	@Override
	public void removeAll(List<Booking> calendarEventList) {
        for (Booking calendarEvent : calendarEventList) {
            remove(calendarEvent);
        }
	}

	@Override
	public void addSelected(Booking event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeSelected(Booking event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearSelected(Booking event, boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Booking> getHolidayEvents(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Booking> getSelectedEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Booking> getEvents(LocalDate date) {
        final Collection events = indexedEvents.get(date);
        if (events == null)
        	//XXX CalendarEvent have been renamed to Booking
            return new ArrayList<Booking>();
      //XXX CalendarEvent have been renamed to Booking
        final List<Booking> result = new ArrayList<Booking>(events);
        Collections.sort(result);
        return result;
	}

	@Override
	public Collection<Booking> getAllEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSelectionChangedListener(SelectionChangedListener selectionChangedListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeSelectionChangedListener(SelectionChangedListener selectionChangedListener) {
		// TODO Auto-generated method stub

	}

}
