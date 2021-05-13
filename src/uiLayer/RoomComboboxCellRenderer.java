package uiLayer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import config.StyleConfig;
import modelLayer.Room;

public class RoomComboboxCellRenderer implements ListCellRenderer<Room>
{
	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
	private StyleConfig config = new StyleConfig();
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Room> list, Room value, int index, boolean isSelected, boolean cellHasFocus)
	{
		JLabel renderer = (JLabel)defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		renderer.setFont(config.getLabelDefaultFont());
		renderer.setText(value.getName());
		return renderer;
	}
}
