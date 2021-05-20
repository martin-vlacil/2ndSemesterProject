package controlLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import databaseLayer.*;
import modelLayer.Room;

/**
 * @author Group1 dmai0920 This controller handles the logic behind managing
 *         rooms and doing operations on them.
 */
public class RoomController
{
    private RoomDBIF roomDB;
    private ArrayList<Room> rooms;

    public RoomController() throws SQLException
    {
        roomDB = new RoomDB();
        this.rooms = roomDB.getAll();
    }

    /**
     * This method gets all the rooms in the database
     * 
     * @return a list of all the rooms
     * @throws SQLException
     */
    public ArrayList<Room> getAll() throws SQLException
    {
        ArrayList<Room> rooms = new ArrayList<Room>();

        rooms = roomDB.getAll();
        this.rooms = rooms;

        return rooms;
    }

    /**
     * This method goes through the list of all the rooms and returns the one to
     * which the ID matches
     * 
     * @param id of the room
     * @return
     * @throws SQLException
     */
    public Room findByID(int id) throws SQLException
    {
        Room room = null;

        for (Room chosenRoom : rooms)
        {
            if (chosenRoom.getId() == id)
            {
                room = chosenRoom;
            }
        }
        // TODO - create a room not found exception?
        return room;
    }
}
