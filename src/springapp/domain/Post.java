package springapp.domain;

import java.sql.Timestamp;

public class Post {
	
	private String text;
	private int likes;
	private int dislikes;
	private Timestamp timestamp;
	private String username;
	
	public Post(String text, int likes, int dislikes, Timestamp timestamp, String username){
		this.text = text;
		this.likes = likes;
		this.dislikes = dislikes;
		this.timestamp = timestamp;
		this.username = username;
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String toString(){
		
		return text + " " + likes + " " + dislikes + " " + timestamp + " " + username;
	}
}
