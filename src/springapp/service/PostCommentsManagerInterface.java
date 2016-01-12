package springapp.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import springapp.domain.Post;

public interface PostCommentsManagerInterface{

	public ArrayList<Post> getPostComments(String masterPostId) throws NumberFormatException, SQLException,
			ParseException;

	public void submitPostComment(String text, String userId, String masterPostId, String postIndex) throws
			NumberFormatException, SQLException, ParseException;

	public String getIncrementedPostIndex(String masterPostId) throws NumberFormatException, SQLException,
			ParseException;

	public int getNoOfCommentsForMasterPost(String masterPostId) throws SQLException;

	public void deleteAllCommentsForMasterPost(String masterPostId);
}
