package springapp.service;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface LikeDislikeRecordManagerInterface {

	public void createLikeRecord(String userId, String postId, boolean fromPostComments);
	public void createDislikeRecord(String userId, String postId, boolean fromPostComments);
	public void removeDislikeRecord(String userId, String postId, boolean fromPostComments);
	public void removeLikeRecord(String userId, String postId, boolean fromPostComments);
	public ResultSet getLikeRecord(String userId, String postId, boolean fromPostComments);
	public ResultSet getDislikeRecord(String userId, String postId, boolean fromPostComments);
	public int getTotalPostsLiked(String userId, boolean fromPostComments) throws SQLException;
	public int getTotalPostsDisliked(String userId, boolean fromPostComments) throws SQLException;
}
