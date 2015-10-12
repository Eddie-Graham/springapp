package springapp.domain;

import java.sql.Timestamp;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import springapp.service.Utils;

public class Post {
	
	private int id;
	private String text;
	private int likes;
	private int dislikes;
	private int total;
	private Timestamp timestamp;
	private String timeString;
	private String dateString;
	private String username;
	private boolean canRate;
	
	public Post(int id, String text, int likes, int dislikes, int total, Timestamp timestamp, String username){
		this.id = id;
		this.text = text;
		this.likes = likes;
		this.dislikes = dislikes;
		this.total = total;
		this.timestamp = timestamp;
		this.timeString = Utils.getTimeString(timestamp);
		this.dateString = Utils.getDateString(timestamp);
		this.username = username;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUsername = auth.getName();
		
		if(loggedInUsername.equals(username))
			canRate = false;
		else
			canRate = true;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isCanRate() {
		return canRate;
	}

	public void setCanRate(boolean canRate) {
		this.canRate = canRate;
	}

	public String toString(){
		
		return "\nid: " + id + "\ntext: " + text + "\nlikes: " + likes + "\ndislikes: " + dislikes + "\ntotal: " + total
				+ "\ntimestamp: " + timestamp + "\nusername: " + username + "\ncanRate: " + canRate;
	}
}
