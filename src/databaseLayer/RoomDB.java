package databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Room;

public class RoomDB implements RoomDBIF
{
	
	private Connection connection;
	
	//private static final String FIND_BY_NUMBER = String.format("SELECT * FROM Room WHERE number = ?");
	//private PreparedStatement sqlFindByNumber;
	
	private static final String GET_ALL = String.format("SELECT * FROM Room");
	private PreparedStatement sqlGetAll;

	public RoomDB() throws SQLException
	{
		connection = DBConnection.getInstance().getConnection();
		
		//sqlFindByNumber = connection.prepareStatement(FIND_BY_NUMBER);
		sqlGetAll = connection.prepareStatement(GET_ALL, Statement.RETURN_GENERATED_KEYS);
	}

	/*
	@Override
	public Room findByNumber(String roomNumber) throws SQLException
	{
		Room room = null;
		
		sqlFindByNumber.setString(1,  roomNumber);
		ResultSet rs = sqlFindByNumber.executeQuery();
		
		if(rs.next())
		{
			room = buildObject(rs);
		}
		
		return room;
	}
	*/
	
	private Room buildObject(ResultSet rs) throws SQLException
	{
		Room room = null;
		
		room = new Room(rs.getString("number"), rs.getInt("capacity"), rs.getString("name"), rs.getInt("id"));
						
		return room;				
	}

	@Override
	public ArrayList<Room> getAll() throws SQLException
	{
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		ResultSet rs = sqlGetAll.executeQuery();
		
		while(rs.next())
		{
			Room room = buildObject(rs);
			rooms.add(room);
		}
		
		return rooms;
	}
}
