package springapp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import springapp.dbcon.DbCon;
import springapp.domain.Post;
import springapp.domain.User;

@Component
public class PostManager {

	@Autowired
	private DbCon dbCon;
	
	@Autowired
	private UserManager userManager;
	
	public void incrementLikes(String postId){
		
		String query = "update posts set likes = likes + 1 where Id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
		
		String query2 = "update posts set total = total + 1 where Id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query2);
	}
	
	public void decrementLikes(String postId){
		
		String query = "update posts set likes = likes - 1 where Id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
		
		String query2 = "update posts set total = total - 1 where Id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query2);
	}
	
	public void decrementDislikes(String postId){
		
		String query = "update posts set dislikes = dislikes - 1 where Id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
		
		String query2 = "update posts set total = total - 1 where Id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query2);
	}
	
	public void incrementDislikes(String postId){
		
		String query = "update posts set dislikes = dislikes + 1 where Id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
		
		String query2 = "update posts set total = total + 1 where Id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query2);
	}
	
	public ArrayList<Post> getPostsByTimestamp() throws SQLException, ParseException{
		
		String query = "select * from posts order by timestamp desc;";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		return getPostsFromResultSet(rs);
	}
	
	public ArrayList<Post> getPostsByLikes() throws SQLException, ParseException{
		
		String query = "select * from posts order by likes desc;";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return getPostsFromResultSet(rs);
	}
	
	public ArrayList<Post> getUsersPostsByTimestamp(String id) throws SQLException, ParseException{
		
		String query = "select * from posts where user_id = '" + id + "' order by timestamp desc;";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return getPostsFromResultSet(rs);
	}
	
	public void submitPost(String postText, String user_id){
		
		String query = "insert into posts (text, user_id) values ('" + postText + "', '" + user_id + "');";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public ArrayList<Post> getPostsByDislikes() throws SQLException, ParseException{
		
		String query = "select * from posts order by dislikes;";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return getPostsFromResultSet(rs);
	}
	
	public ArrayList<Post> getPostsByTotal() throws SQLException, ParseException{
		
		String query = "select * from posts order by total desc;";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return getPostsFromResultSet(rs);
	}
	
	public String getNoOfPostsByUser(String id) throws SQLException {
		
		String query = "select count(user_id) from posts where user_id = '" + id + "';";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			String count = rs.getString("count");
			
			return count;
		}
		
		return null;
	}
	
	public int getTotalLikes(String id) throws SQLException, ParseException{
		
		int totalLikes = 0;
		
		String query = "select likes from posts where user_id = '" + id + "';";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			int likes = Integer.parseInt(rs.getString("likes"));
			totalLikes += likes;
		}
		
		return totalLikes;
	}
	
	public int getTotalDislikes(String id) throws SQLException, ParseException{
		
		int totalDislikes = 0;
		
		String query = "select dislikes from posts where user_id = '" + id + "';";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			int dislikes = Integer.parseInt(rs.getString("dislikes"));
			totalDislikes += dislikes;
		}
		
		return totalDislikes;
	}
	
	private ArrayList<Post> getPostsFromResultSet(ResultSet rs) throws NumberFormatException, SQLException, ParseException{
		
		ArrayList<Post> posts = new ArrayList<Post>();
		
		while (rs.next()) {
			
			String id = rs.getString("id");
			String text = rs.getString("text");
			int likes = Integer.parseInt(rs.getString("likes"));
			int dislikes = Integer.parseInt(rs.getString("dislikes"));
			int total = Integer.parseInt(rs.getString("total"));
			
			Timestamp timestamp = Utils.getTimestamp(rs.getString("timestamp"));
			String timeString = Utils.getTimeString(timestamp);
			String dateString = Utils.getDateString(timestamp);
			
			String user_id = rs.getString("user_id");
			
			////////////////////////////////////////
			// Can logged in user rate this post? //
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			User loggedInUser =  (User) attr.getRequest().getSession().getAttribute("user");
			String loggedInId = loggedInUser.getId();

			boolean canRate;
			
			if(loggedInId.equals(user_id))
				canRate = false;
			else
				canRate = true;
			////////////////////////////////////////
			
			// Username                           
			User postUser = userManager.getUserById(user_id);
			String username = postUser.getUsername();
			
			////////////////////////////////////////
			// Does user have profile pic?        //
			String profileImagePath = "";
			
			if(postUser.isHasProfilePic())
				profileImagePath = "profile_images/" + user_id + ".png";
			////////////////////////////////////////

			
			posts.add(new Post(id, text, likes, dislikes, total, timestamp, timeString, dateString, user_id, canRate,
					username, profileImagePath));
		}
		
		return posts;
	}
}
