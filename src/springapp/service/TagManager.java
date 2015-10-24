package springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springapp.dbcon.DbCon;

@Component
public class TagManager {
	
	@Autowired
	private DbCon dbCon;
	
	public void insertTag(String tag, String postId){
		
		String query = "insert into tags (tag, post_id) values ('" + tag + "', " + postId + ");";
		
		dbCon.makeConnectionAndExecuteQuery(query);
	}
}
