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
package uiLayer.calendar.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;

import uiLayer.calendar.JCalendar;

/**
 * 
 * @author theodorcostache
 * 
 */
public class DayPanel {

	private final DateTimeFormatter sdf = DateTimeFormatter.ofPattern("EEE dd MMM");
	private LocalDate date;
	private final DayHeaderPanel headerPanel;
	private final DayContentPanel contentPanel;
	//private final DayCompleteContentPanel completeDayPanel;
	private final JCalendar owner;
	private float headerRatio = 0.0f;

	/**
	 * Creates a new instance of {@link DayPanel}
	 * 
	 * @param date
	 * @param headerRatio
	 */
	public DayPanel(final JCalendar owner, final LocalDate date) {

		this.date = date;
		this.owner = owner;
		this.headerPanel = new DayHeaderPanel(this, date.format(sdf));
		this.contentPanel = new DayContentPanel(this);
		//this.completeDayPanel = new DayCompleteContentPanel(this);
		//XXX disabling header double click for opening day strategy
		/*this.headerPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				if (e.getClickCount() == 2) {
					owner.setSelectedDay(DayPanel.this.date);
					owner.setDisplayStrategy(Type.DAY, DayPanel.this.date);
				}
			}
		});*/
	}

	/**
	 * 
	 * @param date
	 * @param headerRatio
	 */
	public DayPanel(final JCalendar owner, final LocalDate date, final float headerRatio) {

		this(owner, date);
		this.headerRatio = headerRatio;

	}

	public JPanel layout() {
		JPanel panel = new JPanel(true);
		panel.setOpaque(false);
		panel.setLayout(new GridBagLayout());
		final GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = headerRatio;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		panel.add(headerPanel, c);
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0 - headerRatio;
		panel.add(contentPanel, c);

		return panel;
	}

	public JCalendar getOwner() {
		return owner;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(final LocalDate date) {
		this.date = date;
		headerPanel.setHeaderText(sdf.format(date));
	}

	public void setEnabled(final boolean enabled) {
		this.contentPanel.setEnabled(enabled);
	}

	public DayHeaderPanel getHeaderPanel() {
		return headerPanel;
	}

	public DayContentPanel getContentPanel() {
		return contentPanel;
	}

	/*public DayCompleteContentPanel getCompleteDayPanel() {
		return completeDayPanel;
	}*/
}
