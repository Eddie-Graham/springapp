package springapp.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
