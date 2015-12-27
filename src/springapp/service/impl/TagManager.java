package springapp.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springapp.dbcon.DbCon;
import springapp.service.TagManagerInterface;

@Component
public class TagManager implements TagManagerInterface{
	
	@Autowired
	private DbCon dbCon;
	
	public void insertTag(String tag, String postId){
		
		String query = "insert into tags (tag, post_id) values ('" + tag + "', " + postId + ");";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public ArrayList<String> getPostIdsWithTag(String tag) throws SQLException{
		
		ArrayList<String> postIds = new ArrayList<String>();
		
		String query = "select * from tags where tag = '" + tag + "';";
		
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		while (rs.next()) {
			
			String postId = rs.getString("post_id");
			postIds.add(postId);
		}
		
		return postIds;
	}
}
