package springapp.domain;

import java.sql.Timestamp;

public class Post {
	
	private String id;
	private String text;
	private int likes;
	private int dislikes;
	private int total;
	private Timestamp timestamp;
	private String timeString;
	private String dateString;
	private String user_id;
	private boolean canRate;
	private String username;
	
	public Post(String id, String text, int likes, int dislikes, int total, Timestamp timestamp, String timeString,
			String dateString, String user_id, boolean canRate, String username){
		
		this.id = id;
		this.text = text;
		this.likes = likes;
		this.dislikes = dislikes;
		this.total = total;
		this.timestamp = timestamp;
		this.timeString = timeString;
		this.dateString = dateString;
		this.user_id = user_id;
		this.canRate = canRate;
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public boolean isCanRate() {
		return canRate;
	}

	public void setCanRate(boolean canRate) {
		this.canRate = canRate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String toString(){
		
		return "\nid: " + id + "\ntext: " + text + "\nlikes: " + likes + "\ndislikes: " + dislikes + "\ntotal: " + total
				+ "\ntimestamp: " + timestamp + "\nuser_id: " + user_id + "\ncanRate: " + canRate + "\nusername: " + username;
	}
}
