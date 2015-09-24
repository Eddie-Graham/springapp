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
		
		String query = "select * from users where LOWER(email) = LOWER('" + email + "');";			
		
		ResultSet rs = DbConImpl.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			String id = rs.getString("id");
			String username = rs.getString("username");
			String passwordStr = rs.getString("password");
            
			return new User(id, username, email, passwordStr);
        }
		
		return null;
	}
	
	public static User getUserByUsername(String username) throws SQLException {

		String query = "select * from users where LOWER(username) = LOWER('" + username + "');";

		ResultSet rs = DbConImpl.makeConnectionAndRunQuery(query);

		while (rs.next()) {
			String id = rs.getString("id");
			String email = rs.getString("email");
			String passwordStr = rs.getString("password");

			return new User(id, username, email, passwordStr);
		}

		return null;
	}
	
	public static void createUser(User user) throws SQLException{
		
		String query = "insert into users (username, email, password) values('" + user.getUsername() + "', '" + user.getEmail() + "', '"
				+ user.getPassword() + "');";		
		
		DbConImpl.makeConnectionAndExecuteQuery(query);
	}
	
	public static ArrayList<Post> getPostsByTimestamp() throws SQLException, ParseException{
		
		ArrayList<Post> posts = new ArrayList<Post>();
		
		String query = "select * from posts order by timestamp desc;";
		
		ResultSet rs = DbConImpl.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("id"));
			String text = rs.getString("text");
			int likes = Integer.parseInt(rs.getString("likes"));
			int dislikes = Integer.parseInt(rs.getString("dislikes"));
			Timestamp timestamp = Utils.getTimestamp(rs.getString("timestamp"));
			String username = rs.getString("username");
			
			posts.add(new Post(id, text, likes, dislikes, timestamp, username));
		}
		
		return posts;
	}
	
	public static ArrayList<Post> getPostsByLikes() throws SQLException, ParseException{
		
		ArrayList<Post> posts = new ArrayList<Post>();
		
		String query = "select * from posts order by likes desc;";
		
		ResultSet rs = DbConImpl.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("id"));
			String text = rs.getString("text");
			int likes = Integer.parseInt(rs.getString("likes"));
			int dislikes = Integer.parseInt(rs.getString("dislikes"));
			Timestamp timestamp = Utils.getTimestamp(rs.getString("timestamp"));
			String username = rs.getString("username");
			
			posts.add(new Post(id, text, likes, dislikes, timestamp, username));
		}
		
		return posts;
	}
	
	public static void submitPost(String postText, String username){
		
		String query = "insert into posts (text, username) values ('" + postText + "', '" + username + "');";
		
		DbConImpl.makeConnectionAndExecuteQuery(query);
	}
	
	public static void incrementLikes(String postId){
		
		String query = "update posts set likes = likes + 1 where Id =" + postId + ";";
		
		DbConImpl.makeConnectionAndExecuteQuery(query);
	}
	
	public static void decrementLikes(String postId){
		
		String query = "update posts set dislikes = dislikes - 1 where Id =" + postId + ";";
		
		DbConImpl.makeConnectionAndExecuteQuery(query);
	}
	
	public static void createLikeRecord(String userId, String postId){
		
		String query = "insert into like_Records (user_id, post_id) values ('" + userId + "', '" + postId + "');";
		
		DbConImpl.makeConnectionAndExecuteQuery(query);
	}
	
	public static void createDislikeRecord(String userId, String postId){
		
		String query = "insert into dislike_Records (user_id, post_id) values ('" + userId + "', '" + postId + "');";
		
		DbConImpl.makeConnectionAndExecuteQuery(query);
	}
	
	public static ResultSet getLikeRecord(String userId, String postId){
		
		String query = "select * from like_records where user_id = " + userId + " and post_id = " + postId + ";";
		
		ResultSet rs = DbConImpl.makeConnectionAndRunQuery(query);
		
		return rs;
	}

	public static ResultSet getDislikeRecord(String userId, String postId) {
		
		String query = "select * from dislike_records where user_id = " + userId + " and post_id = " + postId + ";";
		
		ResultSet rs = DbConImpl.makeConnectionAndRunQuery(query);
		
		return rs;
	}
}
