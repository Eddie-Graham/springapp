package springapp.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import springapp.dbcon.DbConInterface;
import springapp.domain.User;
import springapp.service.UserManagerInterface;
import springapp.service.UtilsInterface;

@Component
public class UserManager implements UserManagerInterface{

	@Autowired
	private DbConInterface dbCon;

	@Autowired
	private UtilsInterface utils;

	public User getUserByEmail(String email) throws SQLException, ParseException{

		String query = "select * " +
				"from users " +
				"where LOWER(email) = LOWER('" + email + "');";

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		ArrayList<User> users = getUsersFromResultSet(rs);

		if(users.size() > 0)
			return users.get(0);

		return null;
	}

	public User getUserById(String id) throws SQLException, ParseException{

		String query = "select * " +
				"from users " +
				"where id = " + id + ";";

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		ArrayList<User> users = getUsersFromResultSet(rs);

		if(users.size() > 0)
			return users.get(0);

		return null;
	}

	public User getUserByUsername(String username) throws SQLException, ParseException{

		String query = "select * " +
				"from users " +
				"where LOWER(username) = LOWER('" + username + "');";

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		ArrayList<User> users = getUsersFromResultSet(rs);

		if(users.size() > 0)
			return users.get(0);

		return null;
	}

	public ArrayList<User> getAllUsers() throws SQLException, ParseException{

		String query = "select * " +
				"from users " +
				"order by id;";

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		return getUsersFromResultSet(rs);
	}

	public ArrayList<User> getAllUsersWithLatLong() throws SQLException, ParseException{

		// if lat not null then long is also not null
		String query = "select * " +
				"from users " +
				"where latitude is not null;";

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		return getUsersFromResultSet(rs);
	}

	public void setHasProfilePic(boolean hasProfilePic, String userId){

		String query = "update users set hasProfilePic = " + hasProfilePic + " where id = " + userId + ";";

		dbCon.makeConnectionAndExecuteQuery(query);
	}

	public void setLatLong(String userId, String latitude, String longitude){

		String query = "update users set latitude = " + latitude + ", longitude = " + longitude + " where id = " +
				userId + ";";

		dbCon.makeConnectionAndExecuteQuery(query);
	}

	public void createUser(User user){

		String query = "insert into users (username, email, password) values('" + user.getUsername() + "', '" + user
				.getEmail() + "', '" + user.getPassword() + "');";

		dbCon.makeConnectionAndExecuteQuery(query);
	}

	public void incrementProfileViews(String userId){

		String query = "update users set profileViews = profileViews + 1 where Id = " + userId + ";";

		dbCon.makeConnectionAndExecuteQuery(query);
	}

	public void updateUser(String userId, String username, String email, String authority, String enabled, String
			password){

		String query = "";

		if(password == null)
			query = "update users " +
					"set username = '" + username + "', email = '" + email + "', authority = '" + authority + "', " +
					"enabled = " + enabled + " where id = " + userId + ";";

		else{
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			query = "update users " +
					"set username = '" + username + "', email = '" + email + "', authority = '" + authority + "', " +
					"enabled = " + enabled + ", password = '" + hashedPassword + "' where id = " + userId + ";";
		}

		dbCon.makeConnectionAndExecuteQuery(query);
	}

	private ArrayList<User> getUsersFromResultSet(ResultSet rs) throws SQLException, ParseException{

		ArrayList<User> users = new ArrayList<User>();

		while(rs.next()){

			String id = rs.getString("id");
			String username = rs.getString("username");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String authority = rs.getString("authority");
			double latitude = rs.getDouble("latitude");
			double longitude = rs.getDouble("longitude");

			Timestamp registeredTimestamp = utils.getTimestamp(rs.getString("registeredTimestamp"));
			String registeredTimeString = utils.getTimeString(registeredTimestamp);
			String registeredDateString = utils.getDateString(registeredTimestamp);

			int profileViews = Integer.parseInt(rs.getString("profileViews"));

			boolean enabled;
			if(rs.getString("enabled").equals("t"))
				enabled = true;
			else
				enabled = false;

			boolean hasProfilePic;
			if(rs.getString("hasProfilePic").equals("t"))
				hasProfilePic = true;
			else
				hasProfilePic = false;

			users.add(new User(id, username, email, password, authority, enabled, hasProfilePic, latitude, longitude,
					registeredTimestamp, registeredTimeString, registeredDateString, profileViews));
		}

		return users;
	}
}
