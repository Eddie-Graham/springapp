package springapp.service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TagManagerInterface {
	
	public void insertTag(String tag, String postId);
	public ArrayList<String> getPostIdsWithTag(String tag) throws SQLException;
}
