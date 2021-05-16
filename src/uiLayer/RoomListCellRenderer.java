package uiLayer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import config.Config;
import modelLayer.Room;

public class RoomListCellRenderer implements ListCellRenderer<Room>
{
	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
	private Config config = new Config();
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Room> list, Room room, int index, boolean isSelected, boolean cellHasFocus)
	{
		JButton renderer = new JButton();
		renderer.setFont(config.getButtonDefaultFont());
		renderer.setText("  \uD83D\uDDD1  " + room.getName());
		renderer.setHorizontalAlignment(SwingConstants.LEFT);
		renderer.setForeground(config.getButtonColorDeleteForeground());
		renderer.setBackground(config.getButtonColorDeleteBackground());
		renderer.setOpaque(true);
		
		if(index == 0)
		{
			renderer.setBorder(config.getButtonDeleteBorder());
		}
		else
		{
			renderer.setBorder(new MatteBorder(0, 2, 2, 2, new Color(240, 0, 0)));
		}
		
		return renderer;
	}
}
