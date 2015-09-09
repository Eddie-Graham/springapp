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
	
	public static boolean createUser(User user) throws SQLException{
		
		User userTest = getUserByEmail(user.getEmail());
		
		if(userTest != null)
			return false;
		
		String query = "insert into users values('" + user.getUsername() + "', '" + user.getEmail() + "', '"
				+ user.getPassword() + "');";		
		
		DbConImpl.makeConnectionAndExecuteQuery(query);
		
		return true;
	}
}
