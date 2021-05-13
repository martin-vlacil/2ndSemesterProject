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
package uiLayer.ui;

import java.awt.FlowLayout;
import javax.swing.*;


/**
 * 
 * @author theodorcostache
 * 
 */
public class HeaderPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton scrollLeftButton;

	private JButton scrollRightButton;

	private JLabel intervalLabel;

	/**
	 * Creates a new instance of {@link HeaderPanel}
	 */
	public HeaderPanel() {
		init();
	}

	private void init() {

		this.setOpaque(false);
		//XXX Removed model buttons
		scrollLeftButton = new JButton();
		scrollRightButton = new JButton();

		intervalLabel = new JLabel();
		scrollLeftButton.setBorderPainted(false);
		scrollLeftButton.setFocusPainted(false);
		scrollLeftButton.setContentAreaFilled(false);

		scrollRightButton.setBorderPainted(false);
		scrollRightButton.setFocusPainted(false);
		scrollRightButton.setContentAreaFilled(false);
		//XXX changed layout and removed models
			scrollLeftButton.setText("<");
			scrollRightButton.setText(">");
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.add(scrollLeftButton);
		this.add(intervalLabel);
		this.add(scrollRightButton);
	}

	/**
	 * @return the scrollLeft
	 */
	public JButton getScrollLeft() {
		return scrollLeftButton;
	}

	/**
	 * @return the scrollRight
	 */
	public JButton getScrollRight() {
		return scrollRightButton;
	}

	/**
	 * @return the intervalLabel
	 */
	public JLabel getIntervalLabel() {
		return intervalLabel;
	}

}
