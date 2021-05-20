package uiLayer.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import modelLayer.Booking;
import uiLayer.calendar.JCalendar;
import uiLayer.events.SelectionChangedEvent;
import uiLayer.events.SelectionChangedListener;

/**
 * 
 * XXX Refactored a full class IndexedEventCollection, that contains all Bookings for certain days
 * and All selection listeners that handle event and empty field selection. 
 * @author dmai0920 group 1
 *
 */

public class NewIndexedEventCollection implements EventCollection {

	//XXX removed final property
	private MultiValuedMap<LocalDate, Booking> indexedEvents;
    private final List<SelectionChangedListener> selectionChangedListeners;
	//XXX removed final property
    private Set<Booking> selectedEvents;
    private final JCalendar parent;
	
    public NewIndexedEventCollection(final JCalendar parent) {
        this.parent = parent;
        indexedEvents = new ArrayListValuedHashMap<LocalDate, Booking>();
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
	
	//XXX added method
	@Override
	public void removeAllEvents() {
		selectedEvents = new HashSet<Booking>();
		indexedEvents = new ArrayListValuedHashMap<LocalDate, Booking>();
		
		
	}
	
	

	@Override
	public void addSelected(Booking calendarEvent) {
        	selectedEvents.add(calendarEvent);
            final SelectionChangedEvent event = new SelectionChangedEvent(calendarEvent);
            for (final SelectionChangedListener listener : selectionChangedListeners) {
                listener.selectionChanged(event);
            }
	}

	@Override
	public void removeSelected(Booking calendarEvent) {
        final boolean remove = selectedEvents.remove(calendarEvent);
        if (remove) {
            final SelectionChangedEvent event = new SelectionChangedEvent(calendarEvent);
            for (final SelectionChangedListener listener : selectionChangedListeners) {
                listener.selectionChanged(event);
            }
        }

	}

	@Override
	public void clearSelected(Booking toIgnore, boolean notifyListeners) {
        for (final Booking event : selectedEvents) {
            if (event != toIgnore) {
                event.setSelected(false);
            }
        }
        selectedEvents.clear();

        if (notifyListeners) {
            final SelectionChangedEvent event = new SelectionChangedEvent(null);
            for (final SelectionChangedListener listener : selectionChangedListeners) {
                listener.selectionChanged(event);
            }
        }

	}

	@Override
	public List<Booking> getHolidayEvents(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Booking> getSelectedEvents() {
        return Collections.unmodifiableSet(new HashSet<Booking>(selectedEvents));
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
        Collection<Booking> values = indexedEvents.values();
        Set<Booking> result = new HashSet<Booking>();
        for (Booking event : values) {
            result.add(event);
        }
        return result;
	}

	@Override
		public void addSelectionChangedListener(SelectionChangedListener listener) {
        this.selectionChangedListeners.add(listener);
	}

	@Override
	public void removeSelectionChangedListener(SelectionChangedListener listener) {
        this.selectionChangedListeners.remove(listener);
	}

}
