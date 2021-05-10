package databaseLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import modelLayer.Room;

public interface RoomDBIF
{
	//Room findByNumber(String roomNumber) throws SQLException;
	ArrayList<Room> getAll() throws SQLException;
}
