package controlLayer;

import java.sql.SQLException;

import databaseLayer.RoomDB;
import modelLayer.Room;

/**
 * @author Group1 dmai0920
 * This controller handles the logic behind managing rooms and doing
 * operations on them.
 */
public class RoomController
{
	private RoomDB roomDB;

	public RoomController() throws SQLException
	{
		roomDB = new RoomDB();
	}

	public Room findByNumber(String roomNumber) throws SQLException
	{
		Room room = null;
		
		room = roomDB.findByNumber(roomNumber);
		//TODO - create a room not found exception?
		
		return room;
	}
}
                                                                                                