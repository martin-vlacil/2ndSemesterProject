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
package uiLayer.util;

import uiLayer.calendar.JCalendar;
//import events.ModelChangedEvent;
//import events.ModelChangedListener;
import uiLayer.events.SelectionChangedEvent;
import uiLayer.events.SelectionChangedListener;
/**
 * XXX Refactor code without using {@link MultiHashMap}
 */
import org.apache.commons.collections.MultiHashMap;

import modelLayer.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author theodorcostache
 */
class IndexedEventCollection extends Observable implements Observer, EventCollection {

    private final MultiHashMap indexedEvents;
    //private final List<ModelChangedListener> collectionChangedListeners;
    private final List<SelectionChangedListener> selectionChangedListeners;
    //XXX CalendarEvent have been renamed to Booking
    private final Set<Booking> selectedEvents;
    private final JCalendar parent;

    /**
     * Creates a new instance of {@link IndexedEventCollection}
     */
    public IndexedEventCollection(final JCalendar parent) {
        this.parent = parent;
        this.indexedEvents = new MultiHashMap();
        //this.collectionChangedListeners = new ArrayList<ModelChangedListener>();
        this.selectionChangedListeners = new ArrayList<SelectionChangedListener>();
      //XXX CalendarEvent have been renamed to Booking
        this.selectedEvents = new HashSet<Booking>();
    }
  //XXX CalendarEvent have been renamed to Booking
    @Override
    public void add(final Booking calendarEvent) {
    	//XXX Removed CHECK LATER IN CASE
        //calendarEvent.addObserver(this);
        //XXX getStart() renamed to getStartTime() and getEnd() to getEndTime()
        final Collection<LocalDate> dates = CalendarUtil.getDates(calendarEvent.getStartTime(), calendarEvent.getEndTime());
        for (final LocalDate date : dates) {

            if (!indexedEvents.containsValue(calendarEvent)) {
                indexedEvents.put(date, calendarEvent);
            }
        }
        notifyObservers();

        /*final ModelChangedEvent event = new ModelChangedEvent(parent, calendarEvent);
        for (final ModelChangedListener listener : collectionChangedListeners) {
            listener.eventAdded(event);
        }*/
    }
  //XXX CalendarEvent have been renamed to Booking
    @Override
    @SuppressWarnings("unchecked")
    public void remove(final Booking calendarEvent) {
    	//XXX Removed
        //calendarEvent.deleteObserver(this);
        selectedEvents.remove(calendarEvent);

        for (final Object key : new HashSet<Object>(indexedEvents.keySet())) {
        	//XXX CalendarEvent have been renamed to Booking
            final Collection<Booking> events = indexedEvents.getCollection(key);
            if (events.contains(calendarEvent))
                indexedEvents.remove(key, calendarEvent);
        }

        notifyObservers();

        /*final ModelChangedEvent event = new ModelChangedEvent(parent, calendarEvent);
        for (final ModelChangedListener listener : collectionChangedListeners) {
            listener.eventRemoved(event);
        }*/
    }
  //XXX CalendarEvent have been renamed to Booking
    @Override
    @SuppressWarnings("unchecked")
    public void removeAll(final List<Booking> calendarEvents) {
    	//XXX CalendarEvent have been renamed to Booking
        for (Booking calendarEvent : calendarEvents) {
            remove(calendarEvent);
        }
    }
  //XXX CalendarEvent have been renamed to Booking
    @Override
    public void addSelected(final Booking calendarEvent) {
        selectedEvents.add(calendarEvent);
        final SelectionChangedEvent event = new SelectionChangedEvent(calendarEvent);
        for (final SelectionChangedListener listener : selectionChangedListeners) {
            listener.selectionChanged(event);
        }
    }
  //XXX CalendarEvent have been renamed to Booking
    @Override
    public void removeSelected(final Booking calendarEvent) {
        final boolean remove = selectedEvents.remove(calendarEvent);
        if (remove) {
            final SelectionChangedEvent event = new SelectionChangedEvent(calendarEvent);
            for (final SelectionChangedListener listener : selectionChangedListeners) {
                listener.selectionChanged(event);
            }
        }

    }
  //XXX CalendarEvent have been renamed to Booking
    @Override
    public void clearSelected(final Booking toIgnore, final boolean notifyListeners) {
    	//XXX CalendarEvent have been renamed to Booking
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
  //XXX CalendarEvent have been renamed to Booking
    @Override
    public Collection<Booking> getSelectedEvents() {
    	//XXX CalendarEvent have been renamed to Booking
        return Collections.unmodifiableSet(new HashSet<Booking>(selectedEvents));
    }
  //XXX CalendarEvent have been renamed to Booking
    @Override
    public Collection<Booking> getEvents(final LocalDate date) {
        @SuppressWarnings("rawtypes")
        final Collection events = indexedEvents.getCollection(date);
        if (events == null)
        	//XXX CalendarEvent have been renamed to Booking
            return new ArrayList<Booking>();
        @SuppressWarnings("unchecked")
      //XXX CalendarEvent have been renamed to Booking
        final List<Booking> result = new ArrayList<Booking>(events);
        Collections.sort(result);
        return result;
    }
  //XXX CalendarEvent have been renamed to Booking
    @Override
    public Collection<Booking> getAllEvents() {
    	//XXX CalendarEvent have been renamed to Booking
        Collection<Booking> values = indexedEvents.values();
      //XXX CalendarEvent have been renamed to Booking
        Set<Booking> result = new HashSet<Booking>();
      //XXX CalendarEvent have been renamed to Booking
        for (Booking event : values) {
            result.add(event);
        }
        return result;
    }
  //XXX CalendarEvent have been renamed to Booking
    @Override
    public List<Booking> getHolidayEvents(LocalDate date) {
    	//XXX CalendarEvent have been renamed to Booking
        Collection<Booking> events = getEvents(date);
      //XXX CalendarEvent have been renamed to Booking
        List<Booking> result = new ArrayList<Booking>();
      //XXX Removed
        /*
        for(Booking event : events){
        	
            if(event.isHoliday()) {
                result.add(event);
            }
        }
        */

        return result;
    }

    ///XXX REMOVED
    /*
    @SuppressWarnings("unchecked")
    @Override
    public void update(final Observable o, final Object arg) {
    	//XXX CalendarEvent have been renamed to Booking
        if (o instanceof Booking) {
        	//XXX CalendarEvent have been renamed to Booking
            final Booking calendarEvent = (Booking) o;
            final Property property = (Property) arg;
            switch (property) {
                case START:
                case END:

                    for (final Object key : new HashSet<Object>(indexedEvents.keySet())) {
                        indexedEvents.remove(key, calendarEvent);
                    }

                    final Collection<LocalDate> dates = CalendarUtil.getDates(calendarEvent.getStartTime(), calendarEvent.getEndTime());
                    for (final LocalDate date : dates) {
                        indexedEvents.put(date, calendarEvent);
                    }

                    notifyObservers(calendarEvent);

                    final ModelChangedEvent event = new ModelChangedEvent(parent, calendarEvent);
                    for (final ModelChangedListener listener : collectionChangedListeners) {
                        listener.eventChanged(event);
                    }

                default:
                    parent.invalidate();
                    parent.repaint();
                    break;
            }
        }
    }
    */
    
    
    

    /*@Override
    public void addCollectionChangedListener(final ModelChangedListener listener) {
        this.collectionChangedListeners.add(listener);
    }

    @Override
    public void removeCollectionChangedListener(final ModelChangedListener listener) {
        this.collectionChangedListeners.remove(listener);
    }*/

    @Override
    public void addSelectionChangedListener(final SelectionChangedListener listener) {
        this.selectionChangedListeners.add(listener);
    }

    @Override
    public void removeSelectionChangedListener(final SelectionChangedListener listener) {
        this.selectionChangedListeners.remove(listener);
    }

    public int size() {
        return indexedEvents.size();
    }
    
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
