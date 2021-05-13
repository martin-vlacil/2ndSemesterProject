package databaseLayer;

import modelLayer.User;

public interface UserDBIF
{
	User getUser(String email, String password);
}
