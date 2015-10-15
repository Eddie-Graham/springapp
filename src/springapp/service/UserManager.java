package springapp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		return getUsersFromResultSet(rs);
	}
	
	public User getUserById(String id) throws SQLException {
		
		String query = "select * from users where id = " + id + ";";			

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return getUsersFromResultSet(rs);
	}
	
	public User getUserByUsername(String username) throws SQLException {

		String query = "select * from users where LOWER(username) = LOWER('" + username + "');";

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		return getUsersFromResultSet(rs);
	}
	
	public void createUser(User user){
		
		String query = "insert into users (username, email, password) values('" + user.getUsername() + "', '" + user.getEmail() + "', '"
				+ user.getPassword() + "');";		
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	private User getUsersFromResultSet(ResultSet rs) throws SQLException {
		
		while (rs.next()) {
			
			String id = rs.getString("id");
			String username = rs.getString("username");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String authority = rs.getString("authority");
			
			boolean enabled;
			if(rs.getString("enabled").equals("t"))
				enabled = true;
			else
				enabled = false;
            
			return new User(id, username, email, password, authority, enabled);
		}
		
		return null;	
	}
}
