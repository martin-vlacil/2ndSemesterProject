package controlLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
	private ArrayList<Room> rooms;

	public RoomController() throws SQLException
	{
		roomDB = new RoomDB();
		this.rooms = roomDB.getAll();
	}
	
	public ArrayList<Room> getAll() throws SQLException
	{
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		rooms = roomDB.getAll();
		this.rooms = rooms;
		
		return rooms;
	}

	public Room findByID(int id) throws SQLException
	{
		Room room = null;
		
		for(Room chosenRoom: rooms)
		{
			if(chosenRoom.getId() == id)
			{
				room = chosenRoom;
			}
		}
		//TODO - create a room not found exception?
		return room;
	}

	public boolean checkAvailability(int roomID, LocalDateTime time)
	{
		// TODO Check if room with roomID is available at time
		return true;
	}
}
                                                                                                