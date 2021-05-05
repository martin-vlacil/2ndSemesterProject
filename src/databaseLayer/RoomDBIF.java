package databaseLayer;

import modelLayer.Room;

public interface RoomDBIF
{
	Room findByNumber(String roomNumber);
}
