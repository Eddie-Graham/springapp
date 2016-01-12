package springapp.service;

public interface TagManagerInterface{

	public void insertTag(String tag, String postId);

	public void deleteAllTagsForPost(String postId);
}
