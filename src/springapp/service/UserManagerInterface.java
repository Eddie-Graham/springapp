package springapp.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import springapp.domain.User;

public interface UserManagerInterface {
	
	public User getUserByEmail(String email) throws SQLException, ParseException;
	public User getUserById(String id) throws SQLException, ParseException;
	public User getUserByUsername(String username) throws SQLException, ParseException;
	public ArrayList<User> getAllUsers() throws SQLException, ParseException;
	public ArrayList<User> getAllUsersWithLatLong() throws SQLException, ParseException;
	public void setHasProfilePic(boolean hasProfilePic, String userId);
	public void setLatLong(String userId, String latitude, String longitude);
	public void createUser(User user);
}
