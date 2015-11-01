package springapp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springapp.dbcon.DbCon;
import springapp.domain.User;

@Component
public class UserManager {

	@Autowired
	private DbCon dbCon;
	
	public User getUserByEmail(String email) throws SQLException {
		
		String query = "select * from users where LOWER(email) = LOWER('" + email + "');";			

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		ArrayList<User> users = getUsersFromResultSet(rs);
		
		if(users.size() > 0)
			return users.get(0);
		
		return null;
	}
	
	public User getUserById(String id) throws SQLException {
		
		String query = "select * from users where id = " + id + ";";			

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		ArrayList<User> users = getUsersFromResultSet(rs);
		
		if(users.size() > 0)
			return users.get(0);
		
		return null;
	}
	
	public User getUserByUsername(String username) throws SQLException {

		String query = "select * from users where LOWER(username) = LOWER('" + username + "');";

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		ArrayList<User> users = getUsersFromResultSet(rs);
		
		if(users.size() > 0)
			return users.get(0);
		
		return null;
	}
	
	public ArrayList<User> getAllUsers() throws SQLException {

		String query = "select * from users;";

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		return getUsersFromResultSet(rs);
	}
	
	public ArrayList<User> getAllUsersWithLatLong() throws SQLException {

		// if lat not null then long is also not null
		String query = "select * from users where latitude is not null;";

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		return getUsersFromResultSet(rs);
	}
	
	public void setHasProfilePic(boolean hasProfilePic, String userId){
		
		String query = "update users set hasProfilePic = " + hasProfilePic + " where id = " + userId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public void setLatLong(String userId, String latitude, String longitude){
		
		String query = "update users set latitude = " + latitude + ", longitude = " + longitude + " where id = " + userId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public void createUser(User user){
		
		String query = "insert into users (username, email, password) values('" + user.getUsername() + "', '" + user.getEmail() + "', '"
				+ user.getPassword() + "');";		
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	private ArrayList<User> getUsersFromResultSet(ResultSet rs) throws SQLException {
		
		ArrayList<User> users = new ArrayList<User>();
		
		while (rs.next()) {
			
			String id = rs.getString("id");
			String username = rs.getString("username");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String authority = rs.getString("authority");
			double latitude = rs.getDouble("latitude");
			double longitude = rs.getDouble("longitude");
			
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
            
			users.add(new User(id, username, email, password, authority, enabled, hasProfilePic, latitude, longitude));
		}
		
		return users;	
	}
}
