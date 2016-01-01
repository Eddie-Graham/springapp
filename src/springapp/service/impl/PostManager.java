package springapp.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import springapp.dbcon.DbConInterface;
import springapp.domain.Post;
import springapp.domain.User;
import springapp.service.PostCommentsManagerInterface;
import springapp.service.PostManagerInterface;
import springapp.service.TagManagerInterface;
import springapp.service.UserManagerInterface;
import springapp.service.UtilsInterface;

@Component
public class PostManager implements PostManagerInterface {

	@Autowired
	private DbConInterface dbCon;
	
	@Autowired
	private UserManagerInterface userManager;
	
	@Autowired
	private PostCommentsManagerInterface postCommentsManager;
	
	@Autowired
	private UtilsInterface utils;
	
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
	
	public ArrayList<Post> getPostsByTimestamp(String userId) throws SQLException, ParseException{
		
		String query = "";
		
		if(userId == null)
			query = "select * from posts order by timestamp desc;";
		else
			query = "select * from posts where user_id = " + userId + " order by timestamp desc;";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		return getPostsFromResultSet(rs);
	}
	
	public ArrayList<Post> getPostsByNoOfReplies(String userId) throws SQLException, ParseException{
		
		String query = "";
		
		if(userId == null)
			query = "select * "
				+ "from posts left join (select masterpost_id, count(*) from post_comments group by masterpost_id) as noOfComments "
				+ "on posts.id = noOfComments.masterpost_id "
				+ "order by count desc NULLS LAST;";
		
		else //TODO optimize query?? (only do count for comments against the user's posts)
			query = "select * "
				+ "from posts left join (select masterpost_id, count(*) from post_comments group by masterpost_id) as noOfComments "
				+ "on posts.id = noOfComments.masterpost_id "
				+ "where user_id = " + userId + " "
				+ "order by count desc NULLS LAST;";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);

		return getPostsFromResultSet(rs);
	}
	
	public ArrayList<Post> getPostsByLikes(String userId) throws SQLException, ParseException{
		
		String query = "";
		
		if(userId == null)
			query = "select * from posts order by likes desc;";
		else
			query = "select * from posts where user_id = " + userId + " order by likes desc;";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return getPostsFromResultSet(rs);
	}
	
	/**
	 * This method returns the auto generated id field of the inserted post
	 * @param postText
	 * @param userId
	 * @return
	 */
	public String submitPost(String postText, String userId){
		
		String query = "insert into posts (text, user_id) values ('" + postText + "', '" + userId + "');";
		
		return dbCon.makeConnectionAndExecuteQueryGettingAutoId(query);
	}
	
	public ArrayList<Post> getPostsByDislikes(String userId) throws SQLException, ParseException{
		
		String query = "";
		
		if(userId == null)
			query = "select * from posts order by dislikes;";
		else
			query = "select * from posts where user_id = " + userId + " order by dislikes;";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return getPostsFromResultSet(rs);
	}
	
	public ArrayList<Post> getPostsByTotal(String userId) throws SQLException, ParseException{
		
		String query = "";
		
		if(userId == null)
			query = "select * from posts order by total desc;";
		else
			query = "select * from posts where user_id = " + userId + " order by total desc;";
		
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return getPostsFromResultSet(rs);
	}
	
	public ArrayList<Post> getPostsByTag(String tag, String userId) throws SQLException, ParseException{
		
		String query = "";
		
		if(userId == null)
			query = "select * "
					+ "from posts join (select * from tags where tag = '" + tag + "') as tags "
					+ "on posts.id = tags.post_id;";
		else
			query = "select * "
					+ "from posts join (select * from tags where tag = '" + tag + "') as tags "
					+ "on posts.id = tags.post_id "
					+ "where user_id = " + userId + ";";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return getPostsFromResultSet(rs);
	}
	
	public ArrayList<Post> getPostById(String id) throws SQLException, ParseException{
		
		String query = "select * from posts where id = " + id + ";";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return getPostsFromResultSet(rs);
	}
	
	public int getNoOfPostsByUser(String userId, boolean fromPostComments) throws SQLException {
		
		String query = "";
		
		if(!fromPostComments)
			query = "select count(user_id) from posts where user_id = '" + userId + "';";
		else
			query = "select count(user_id) from post_comments where user_id = '" + userId + "';";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		rs.next();
		
		return Integer.parseInt(rs.getString("count"));
	}
	
	public int getTotalLikes(String userId, boolean fromPostComments) throws SQLException, ParseException{
		
		int totalLikes = 0;
		String query = "";
		
		if(!fromPostComments)
			query = "select likes from posts where user_id = '" + userId + "';";
		else
			query = "select likes from post_comments where user_id = '" + userId + "';";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			int likes = Integer.parseInt(rs.getString("likes"));
			totalLikes += likes;
		}
		
		return totalLikes;
	}
	
	public int getTotalDislikes(String userId, boolean fromPostComments) throws SQLException, ParseException{
		
		int totalDislikes = 0;
		String query = "";
		
		if(!fromPostComments)
			query = "select dislikes from posts where user_id = '" + userId + "';";
		else
			query = "select dislikes from post_comments where user_id = '" + userId + "';";
		
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
			
			Timestamp timestamp = utils.getTimestamp(rs.getString("timestamp"));
			String timeString = utils.getTimeString(timestamp);
			String dateString = utils.getDateString(timestamp);
			
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
			
			int noOfComments = postCommentsManager.getNoOfCommentsForMasterPost(id);
			
			posts.add(new Post(id, text, likes, dislikes, total, timestamp, timeString, dateString, canRate, postUser,
					noOfComments));
		}
		
		return posts;
	}
}
