package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelLayer.Room;

public class RoomDB implements RoomDBIF
{
	
	private Connection connection;
	
	private static final String FIND_BY_NUMBER = String.format("");
	private PreparedStatement sqlFindByNumber;

	public RoomDB() throws SQLException
	{
		connection = DBConnection.getInstance().getConnection();
		
		sqlFindByNumber = connection.prepareStatement(FIND_BY_NUMBER);
	}

	@Override
	public Room findByNumber(String roomNumber)
	{
		Room room = null;
		
		// TODO Auto-generated method stub
		
		return room;
	}

}
