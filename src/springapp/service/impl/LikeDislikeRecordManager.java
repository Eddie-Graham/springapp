package springapp.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springapp.dbcon.DbConInterface;
import springapp.service.LikeDislikeRecordManagerInterface;

@Component
public class LikeDislikeRecordManager implements LikeDislikeRecordManagerInterface{

	@Autowired
	private DbConInterface dbCon;
	
	public void createLikeRecord(String userId, String postId, boolean fromPostComments){
		
		String query = "";
		
		if(!fromPostComments)
			query = "insert into like_records (user_id, post_id) values ('" + userId + "', '" + postId + "');";
		else
			query = "insert into like_records_comments (user_id, post_id) values ('" + userId + "', '" + postId + "');";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public void createDislikeRecord(String userId, String postId, boolean fromPostComments){
		
		String query = "";
		
		if(!fromPostComments)
			query = "insert into dislike_records (user_id, post_id) values ('" + userId + "', '" + postId + "');";
		else
			query = "insert into dislike_records_comments (user_id, post_id) values ('" + userId + "', '" + postId + "');";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public void removeDislikeRecord(String userId, String postId, boolean fromPostComments){
		
		String query = "";
		
		if(!fromPostComments)
			query = "delete from dislike_records where user_id = " + userId + " and post_id = " + postId + ";";
		else
			query = "delete from dislike_records_comments where user_id = " + userId + " and post_id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public void removeLikeRecord(String userId, String postId, boolean fromPostComments){
		
		String query = "";
		
		if(!fromPostComments)
			query = "delete from like_records where user_id = " + userId + " and post_id = " + postId + ";";
		else
			query = "delete from like_records_comments where user_id = " + userId + " and post_id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public ResultSet getLikeRecord(String userId, String postId, boolean fromPostComments){
		
		String query = "";
		
		if(!fromPostComments)
			query = "select * from like_records where user_id = " + userId + " and post_id = " + postId + ";";
		else
			query = "select * from like_records_comments where user_id = " + userId + " and post_id = " + postId + ";";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return rs;
	}

	public ResultSet getDislikeRecord(String userId, String postId, boolean fromPostComments) {
		
		String query = "";
		
		if(!fromPostComments)
			query = "select * from dislike_records where user_id = " + userId + " and post_id = " + postId + ";";
		else
			query = "select * from dislike_records_comments where user_id = " + userId + " and post_id = " + postId + ";";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		return rs;
	}
	
	public int getTotalPostsLiked(String userId, boolean fromPostComments) throws SQLException {
		
		String query = "";
		
		if(!fromPostComments)
			query = "select count(user_id) from like_records where user_id = " + userId + ";";
		else
			query = "select count(user_id) from like_records_comments where user_id = " + userId + ";";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		rs.next();
		
		return rs.getInt("count");
	}
	
	public int getTotalPostsDisliked(String userId, boolean fromPostComments) throws SQLException {
		
		String query = "";
		
		if(!fromPostComments)
			query = "select count(user_id) from dislike_records where user_id = " + userId + ";";
		else
			query = "select count(user_id) from dislike_records_comments where user_id = " + userId + ";";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		rs.next();
		
		return rs.getInt("count");
	}
	
	public void removeAllLikeRecordsForPostId(String postId, boolean fromPostComments){
		
		String query = "";
		
		if(!fromPostComments)
			query = "delete from like_records where post_id = " + postId + ";";
		else
			query = "delete from like_records_comments where post_id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public void removeAllDislikeRecordsForPostId(String postId, boolean fromPostComments){
		
		String query = "";
		
		if(!fromPostComments)
			query = "delete from dislike_records where post_id = " + postId + ";";
		else
			query = "delete from dislike_records_comments where post_id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
}
