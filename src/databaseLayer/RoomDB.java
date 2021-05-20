package databaseLayer;

import java.sql.*;
import java.util.ArrayList;

import modelLayer.Room;

/**
 * @author Group 1 dmai0920 This is a database class for Rooms, the handles its
 *         persistence, it is responsible for finding, updating, deleting, and
 *         inserting to the database
 */
public class RoomDB implements RoomDBIF
{

    private Connection connection;

    private static final String GET_ALL = String.format("SELECT * FROM Room");
    private PreparedStatement sqlGetAll;

    public RoomDB() throws SQLException
    {
        connection = DBConnection.getInstance().getConnection();
        sqlGetAll = connection.prepareStatement(GET_ALL,
                Statement.RETURN_GENERATED_KEYS);
    }

    @Override
    public ArrayList<Room> getAll() throws SQLException
    {
        ArrayList<Room> rooms = new ArrayList<Room>();

        ResultSet rs = sqlGetAll.executeQuery();

        while (rs.next())
        {
            Room room = buildObject(rs);
            rooms.add(room);
        }

        return rooms;
    }

    @Override
    public Room buildObject(ResultSet rs) throws SQLException
    {
        Room room = null;

        room = new Room(rs.getString("number"), rs.getInt("capacity"),
                rs.getString("name"), rs.getInt("id"));

        return room;
    }

}
