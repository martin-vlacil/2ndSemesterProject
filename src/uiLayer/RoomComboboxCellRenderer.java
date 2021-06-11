package uiLayer;

import java.awt.Component;

import javax.swing.*;

import config.Config;
import modelLayer.Room;

/**
 * A custom renderer for the select rooms combo boxes
 * @author group1 dmai0920
 */
public class RoomComboboxCellRenderer implements ListCellRenderer<Room>
{
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    private Config config = new Config();

    @Override
    public Component getListCellRendererComponent(JList<? extends Room> list, Room value, int index, boolean isSelected, boolean cellHasFocus)
    {
        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        renderer.setFont(config.getLabelDefaultFont());
        renderer.setText(value.getName());

        if (index == 0)
        {
            renderer.setText(" ");
        }

        return renderer;
    }
}
