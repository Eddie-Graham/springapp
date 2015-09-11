package springapp.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import springapp.dbcon.DbConImpl;
import springapp.domain.User;

public class DbService {

	public static User getUserByEmail(String email) throws SQLException{
		
		String query = "select * from users where email = '" + email + "';";			
		
		ResultSet rs = DbConImpl.makeConnectionAndRunQuery(query);
		
		String username = "";
		String passwordStr = "";
		
		while (rs.next()) {
			username = rs.getString("username");
            passwordStr = rs.getString("password");
            
            return new User(username, email, passwordStr);
        }
		
		return null;
	}
	
	public static User getUserByUsername(String username) throws SQLException {

		String query = "select * from users where username = '" + username + "';";

		ResultSet rs = DbConImpl.makeConnectionAndRunQuery(query);

		String email = "";
		String passwordStr = "";

		while (rs.next()) {
			email = rs.getString("email");
			passwordStr = rs.getString("password");

			return new User(username, email, passwordStr);
		}

		return null;
	}
	
	public static void createUser(User user) throws SQLException{
		
		String query = "insert into users values('" + user.getUsername() + "', '" + user.getEmail() + "', '"
				+ user.getPassword() + "');";		
		
		DbConImpl.makeConnectionAndExecuteQuery(query);
	}
}
