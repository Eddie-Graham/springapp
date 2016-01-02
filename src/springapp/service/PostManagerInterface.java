package springapp.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import springapp.domain.Post;

public interface PostManagerInterface {

	public void incrementLikes(String postId, boolean fromPostComments);
	public void decrementLikes(String postId, boolean fromPostComments);
	public void decrementDislikes(String postId, boolean fromPostComments);
	public void incrementDislikes(String postId, boolean fromPostComments);
	public ArrayList<Post> getPostsByTimestamp(String userId) throws SQLException, ParseException;
	public ArrayList<Post> getPostsByNoOfReplies(String userId) throws SQLException, ParseException;
	public ArrayList<Post> getPostsByLikes(String userId) throws SQLException, ParseException;
	public String submitPost(String postText, String userId);
	public ArrayList<Post> getPostsByDislikes(String userId) throws SQLException, ParseException;
	public ArrayList<Post> getPostsByTotal(String userId) throws SQLException, ParseException;
	public ArrayList<Post> getPostsByTag(String tag, String userId) throws SQLException, ParseException;
	public ArrayList<Post> getPostById(String id) throws SQLException, ParseException;
	public int getNoOfPostsByUser(String userId, boolean fromPostComments) throws SQLException;
	public int getTotalLikes(String userId, boolean fromPostComments) throws SQLException, ParseException;
	public int getTotalDislikes(String userId, boolean fromPostComments) throws SQLException, ParseException;
	public void deletePost(String postId, boolean fromPostComments);
}
