package databaseLayer;

import java.sql.SQLException;

import modelLayer.Room;

public interface RoomDBIF
{
	Room findByNumber(String roomNumber) throws SQLException;
}
