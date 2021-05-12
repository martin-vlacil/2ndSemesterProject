package uiLayer;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.JPanel;

import controlLayer.BookingController;
import modelLayer.User;

public class CreateBookingPanel extends JPanel {

	
	private User user;
	private BookingController bookingController;
	
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public CreateBookingPanel(User user) throws SQLException 
	{
		this.user = user;
		bookingController = new BookingController();
	}
	
	private void checkInformation(String field, String fieldValue) 
	{
		if (bookingController.validateInformation(new String[] {field,fieldValue}) == false) 
		{
			switch(field) 
			{
				case "title":
					//shows the error field of the title
					break;
				case "attendees":
					//shows the error field of the attendees
					break;
				case "contactName":
					//shows the error field of the contactName
					break;
				case "phoneNumber":
					//shows the error field of the phoneNumber
					break;
				case "email":
					//shows the error field of the email
					break;
			}
		}

	}
	
	private void checkRoomAvailability() 
	{
		//errormessage field = bookingController.checkRoomAvailability(null, null, null);
		bookingController.checkRoomAvailability(null, null, null); //Passing starTime, endTime and room
	}

}
