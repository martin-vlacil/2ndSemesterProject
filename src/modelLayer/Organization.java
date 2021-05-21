package modelLayer;

/**
 * @author Group1 dmai0920 Represents an Organization in the system that groups
 *         their Users, not fully implemented as it is not a big part of any implemented use case
 */
public class Organization
{
    private int id;
    private String name;

    public Organization(int id, String name)
    {
        this.name = name;
    }

    /**
     * Getters and Setters for all class fields
     */
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getID()
    {
        return id;
    }

    public void setID(int id)
    {
        this.id = id;
    }
}
