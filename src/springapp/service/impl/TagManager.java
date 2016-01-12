package springapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springapp.dbcon.DbConInterface;
import springapp.service.TagManagerInterface;

@Component
public class TagManager implements TagManagerInterface{

	@Autowired
	private DbConInterface dbCon;

	public void insertTag(String tag, String postId){

		String query = "insert into tags (tag, post_id) values ('" + tag + "', " + postId + ");";

		dbCon.makeConnectionAndExecuteQuery(query);
	}

	public void deleteAllTagsForPost(String postId){

		String query = "delete from tags where post_id = " + postId + ";";

		dbCon.makeConnectionAndExecuteQuery(query);
	}
}
