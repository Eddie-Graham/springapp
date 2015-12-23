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
	
	@Autowired
	private TagManager tagManager;
	
	public void incrementLikes(String postId, boolean fromPostComments){
		
		String query = "";
		String query2 = "";
		
		if(!fromPostComments){
			query = "update posts set likes = likes + 1 where Id = " + postId + ";";
			query2 = "update posts set total = total + 1 where Id = " + postId + ";";
		}
		else{
			query = "update post_comments set likes = likes + 1 where Id = " + postId + ";";
			query2 = "update post_comments set total = total + 1 where Id = " + postId + ";";
		}
		
		dbCon.makeConnectionAndExecuteQuery(query);
		dbCon.makeConnectionAndExecuteQuery(query2);
	}
	
	public void decrementLikes(String postId, boolean fromPostComments){
		
		String query = "";
		String query2 = "";
		
		if(!fromPostComments){
			query = "update posts set likes = likes - 1 where Id = " + postId + ";";
			query2 = "update posts set total = total - 1 where Id = " + postId + ";";
		}	
		else{
			query = "update post_comments set likes = likes - 1 where Id = " + postId + ";";
			query2 = "update post_comments set total = total - 1 where Id = " + postId + ";";
		}
		
		dbCon.makeConnectionAndExecuteQuery(query);
		dbCon.makeConnectionAndExecuteQuery(query2);
	}
	
	public void decrementDislikes(String postId, boolean fromPostComments){
		
		String query = "";
		String query2 = "";
		
		if(!fromPostComments){
			query = "update posts set dislikes = dislikes - 1 where Id = " + postId + ";";
			query2 = "update posts set total = total - 1 where Id = " + postId + ";";
		}
		else{
			query = "update post_comments set dislikes = dislikes - 1 where Id = " + postId + ";";
			query2 = "update post_comments set total = total - 1 where Id = " + postId + ";";
		}
		
		dbCon.makeConnectionAndExecuteQuery(query);
		dbCon.makeConnectionAndExecuteQuery(query2);
	}
	
	public void incrementDislikes(String postId, boolean fromPostComments){
		
		String query = "";
		String query2 = "";
		
		if(!fromPostComments){
			query = "update posts set dislikes = dislikes + 1 where Id = " + postId + ";";
			query2 = "update posts set total = total + 1 where Id = " + postId + ";";
		}	
		else{
			query = "update post_comments set dislikes = dislikes + 1 where Id = " + postId + ";";
			query2 = "update post_comments set total = total + 1 where Id = " + postId + ";";
		}
		
		dbCon.makeConnectionAndExecuteQuery(query);
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
	
	/**
	 * This method returns the auto generated id field of the inserted post
	 * @param postText
	 * @param user_id
	 * @return
	 */
	public String submitPost(String postText, String user_id){
		
		String query = "insert into posts (text, user_id) values ('" + postText + "', '" + user_id + "');";
		
		return dbCon.makeConnectionAndExecuteQueryGettingAutoId(query);
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
	
	public ArrayList<Post> getPostsByTag(String tag) throws SQLException, ParseException{
		
		ArrayList<Post> posts = new ArrayList<Post>();
		ArrayList<String> postIds = tagManager.getPostIdsWithTag(tag);
		
		for(String postId: postIds){
			
			ArrayList<Post> result = getPostById(postId);
			Post post = result.get(0);
			posts.add(post);
		}
		
		return posts;
	}
	
	public ArrayList<Post> getPostById(String id) throws SQLException, ParseException{
		
		String query = "select * from posts where id = " + id + ";";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return getPostsFromResultSet(rs);
	}
	
	@SuppressWarnings("null")
	public int getNoOfPostsByUser(String id, boolean fromPostComments) throws SQLException {
		
		String query = "";
		
		if(!fromPostComments)
			query = "select count(user_id) from posts where user_id = '" + id + "';";
		else
			query = "select count(user_id) from post_comments where user_id = '" + id + "';";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		while (rs.next())
			return Integer.parseInt(rs.getString("count"));
		
		return (Integer) null;
	}
	
	public int getTotalLikes(String id, boolean fromPostComments) throws SQLException, ParseException{
		
		int totalLikes = 0;
		String query = "";
		
		if(!fromPostComments)
			query = "select likes from posts where user_id = '" + id + "';";
		else
			query = "select likes from post_comments where user_id = '" + id + "';";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			int likes = Integer.parseInt(rs.getString("likes"));
			totalLikes += likes;
		}
		
		return totalLikes;
	}
	
	public int getTotalDislikes(String id, boolean fromPostComments) throws SQLException, ParseException{
		
		int totalDislikes = 0;
		String query = "";
		
		if(!fromPostComments)
			query = "select dislikes from posts where user_id = '" + id + "';";
		else
			query = "select dislikes from post_comments where user_id = '" + id + "';";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			int dislikes = Integer.parseInt(rs.getString("dislikes"));
			totalDislikes += dislikes;
		}
		
		return totalDislikes;
	}
	
	private ArrayList<Post> getPostsFromResultSet(ResultSet rs) throws NumberFormatException, SQLException, ParseException{
		
		ArrayList<Post> posts = new ArrayList<Post>();
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		User loggedInUser =  (User) attr.getRequest().getSession().getAttribute("user");
		String loggedInId = loggedInUser.getId();
		
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
			boolean canRate;
			
			if(loggedInId.equals(user_id))
				canRate = false;
			else
				canRate = true;
			////////////////////////////////////////
			
			// User object for post's user                           
			User postUser = userManager.getUserById(user_id);
			
			posts.add(new Post(id, text, likes, dislikes, total, timestamp, timeString, dateString, canRate, postUser));
		}
		
		return posts;
	}
}
