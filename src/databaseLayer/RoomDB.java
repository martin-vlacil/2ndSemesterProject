package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelLayer.Room;
import modelLayer.Room.RoomType;

public class RoomDB implements RoomDBIF
{
	
	private Connection connection;
	
	private static final String FIND_BY_NUMBER = String.format("SELECT * FROM Room WHERE number = ?");
	private PreparedStatement sqlFindByNumber;

	public RoomDB() throws SQLException
	{
		connection = DBConnection.getInstance().getConnection();
		
		sqlFindByNumber = connection.prepareStatement(FIND_BY_NUMBER);
	}

	@Override
	public Room findByNumber(String roomNumber) throws SQLException
	{
		Room room = null;
		
		sqlFindByNumber.setString(1,  roomNumber);
		ResultSet resultSet = sqlFindByNumber.executeQuery();
		
		if(resultSet.next())
		{
			room = buildObject(resultSet);
		}
		
		return room;
	}
	
	private Room buildObject(ResultSet resultSet) throws SQLException
	{
		Room room = null;
		
		room = new Room(resultSet.getString("number"), resultSet.getInt("capacity"), RoomType.valueOf(resultSet.getString("type")));
						
		return room;				
	}
}
