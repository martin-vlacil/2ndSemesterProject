package databaseLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface BookingDBIF
{
	boolean create(String title, String description, String contactName, String phoneNumber,
					String email, LocalDateTime startTime, LocalDateTime endTime, 
					int numberOfParticipants) throws SQLException;
}
