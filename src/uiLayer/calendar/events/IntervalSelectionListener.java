/* ************************************************************************ *
 * Copyright 2011 costache for MSD                                            *
 * ************************************************************************ */
package uiLayer.calendar.events;

import java.util.EventListener;

/**
 * @author costache
 * 
 */
public interface IntervalSelectionListener extends EventListener {

	public void intervalSelected(IntervalSelectionEvent event);
}
