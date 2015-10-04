package springapp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springapp.dbcon.DbCon;
import springapp.domain.Post;
import springapp.domain.User;

@Component
public class DbService {
	
	@Autowired
	private DbCon dbCon;

	public User getUserByEmail(String email) throws SQLException{
		
		String query = "select * from users where LOWER(email) = LOWER('" + email + "');";			

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			String id = rs.getString("id");
			String username = rs.getString("username");
			String passwordStr = rs.getString("password");
            
			return new User(id, username, email, passwordStr);
		}
		
		return null;
	}
	
	public User getUserByUsername(String username) throws SQLException {

		String query = "select * from users where LOWER(username) = LOWER('" + username + "');";

		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		while (rs.next()) {
			String id = rs.getString("id");
			String email = rs.getString("email");
			String passwordStr = rs.getString("password");

			return new User(id, username, email, passwordStr);
		}

		return null;
	}
	
	public void createUser(User user) throws SQLException{
		
		String query = "insert into users (username, email, password) values('" + user.getUsername() + "', '" + user.getEmail() + "', '"
				+ user.getPassword() + "');";		
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public ArrayList<Post> getPostsByTimestamp() throws SQLException, ParseException{
		
		ArrayList<Post> posts = new ArrayList<Post>();
		
		String query = "select * from posts order by timestamp desc;";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("id"));
			String text = rs.getString("text");
			int likes = Integer.parseInt(rs.getString("likes"));
			int dislikes = Integer.parseInt(rs.getString("dislikes"));
			int total = Integer.parseInt(rs.getString("total"));
			Timestamp timestamp = Utils.getTimestamp(rs.getString("timestamp"));
			String username = rs.getString("username");
			
			posts.add(new Post(id, text, likes, dislikes, total, timestamp, username));
		}
		
		return posts;
	}
	
	public ArrayList<Post> getPostsByLikes() throws SQLException, ParseException{
		
		ArrayList<Post> posts = new ArrayList<Post>();
		
		String query = "select * from posts order by likes desc;";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("id"));
			String text = rs.getString("text");
			int likes = Integer.parseInt(rs.getString("likes"));
			int dislikes = Integer.parseInt(rs.getString("dislikes"));
			int total = Integer.parseInt(rs.getString("total"));
			Timestamp timestamp = Utils.getTimestamp(rs.getString("timestamp"));
			String username = rs.getString("username");
			
			posts.add(new Post(id, text, likes, dislikes, total, timestamp, username));
		}
		
		return posts;
	}
	
	public void submitPost(String postText, String username){
		
		String query = "insert into posts (text, username) values ('" + postText + "', '" + username + "');";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public void incrementLikes(String postId){
		
		String query = "update posts set likes = likes + 1 where Id =" + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
		
		String query2 = "update posts set total = total + 1 where Id =" + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query2);
	}
	
	public void decrementLikes(String postId){
		
		String query = "update posts set dislikes = dislikes - 1 where Id =" + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
		
		String query2 = "update posts set total = total - 1 where Id =" + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query2);
	}
	
	public void createLikeRecord(String userId, String postId){
		
		String query = "insert into like_Records (user_id, post_id) values ('" + userId + "', '" + postId + "');";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public void createDislikeRecord(String userId, String postId){
		
		String query = "insert into dislike_Records (user_id, post_id) values ('" + userId + "', '" + postId + "');";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public ResultSet getLikeRecord(String userId, String postId){
		
		String query = "select * from like_records where user_id = " + userId + " and post_id = " + postId + ";";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return rs;
	}

	public ResultSet getDislikeRecord(String userId, String postId) {
		
		String query = "select * from dislike_records where user_id = " + userId + " and post_id = " + postId + ";";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return rs;
	}
}
