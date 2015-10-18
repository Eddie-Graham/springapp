package springapp.service;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springapp.dbcon.DbCon;

@Component
public class LikeDislikeRecordManager {

	@Autowired
	private DbCon dbCon;
	
	public void createLikeRecord(String userId, String postId){
		
		String query = "insert into like_Records (user_id, post_id) values ('" + userId + "', '" + postId + "');";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public void createDislikeRecord(String userId, String postId){
		
		String query = "insert into dislike_Records (user_id, post_id) values ('" + userId + "', '" + postId + "');";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public void removeDislikeRecord(String userId, String postId){
		
		String query = "delete from dislike_records where user_id = " + userId + " and post_id = " + postId + ";";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public void removeLikeRecord(String userId, String postId){
		
		String query = "delete from like_records where user_id = " + userId + " and post_id = " + postId + ";";
		
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
