package springapp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

import springapp.dbcon.DbConImpl;
import springapp.domain.Post;
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

		while (rs.next()) {
			String email = rs.getString("email");
			String passwordStr = rs.getString("password");

			return new User(username, email, passwordStr);
		}

		return null;
	}
	
	public static void createUser(User user) throws SQLException{
		
		String query = "insert into users values('" + user.getUsername() + "', '" + user.getEmail() + "', '"
				+ user.getPassword() + "');";		
		
		DbConImpl.makeConnectionAndExecuteQuery(query);
	}
	
	public static ArrayList<Post> getPostsByTimestamp() throws SQLException, ParseException{
		
		ArrayList<Post> posts = new ArrayList<Post>();
		
		String query = "select * from posts order by timestamp desc;";
		
		ResultSet rs = DbConImpl.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			String text = rs.getString("text");
			int likes = Integer.parseInt(rs.getString("likes"));
			int dislikes = Integer.parseInt(rs.getString("dislikes"));
			Timestamp timestamp = Utils.getTimestamp(rs.getString("timestamp"));
			String username = rs.getString("username");
			
			posts.add(new Post(text, likes, dislikes, timestamp, username));
		}
		
		return posts;
	}
	
	public static void submitPost(String postText, String username){
		
		String query = "insert into posts (text, username) values ('" + postText + "', '" + username + "');";
		
		DbConImpl.makeConnectionAndExecuteQuery(query);
	}
}
