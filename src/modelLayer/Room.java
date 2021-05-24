package modelLayer;

import java.awt.Color;

/**
 * @author Group1 dmai0920
 * Represents the physical Rooms to be booked in the system.
 */
public class Room
{
	private int capacity;
    private int id;
    private String number;// Room number
    private String name;
    private Color backgroundColor;
    private Color foregroundColor;
    // This will be changed later on when developing Room CRUD
    private Color[] colors = new Color[]{new Color(40, 41, 82), new Color(214, 65, 65)};

    public Room(String number, int capacity, String name, int id)
    {
        this.number = number;
        this.capacity = capacity;
        this.name = name;
        this.id = id;

        // This will be changed later on when developing Room CRUD
        if (id != -1)
        {
            backgroundColor = colors[id - 1];
            foregroundColor = Color.WHITE;
        }
    }

    /**
     * Getters and Setters for all class fields
     */
    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    // XXX added based on the library
    public Color getBackgroundColor()
    {
        return backgroundColor;
    }

    public Color getForegroundColor()
    {
        return foregroundColor;
    }
}
