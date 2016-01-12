package springapp.domain;

import java.sql.Timestamp;

public class User{

	private String id;
	private String username;
	private String email;
	private String password;
	private String authority;
	private boolean enabled;
	private boolean hasProfilePic;
	private double latitude;
	private double longitude;
	private Timestamp registeredTimestamp;
	private String registeredTimeString;
	private String registeredDateString;
	private int profileViews;

	public User(String id, String username, String email, String password, String authority, boolean enabled,
				boolean hasProfilePic, double latitude, double longitude, Timestamp registeredTimestamp, String
						registeredTimeString,
				String registeredDateString, int profileViews){

		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authority = authority;
		this.enabled = enabled;
		this.hasProfilePic = hasProfilePic;
		this.latitude = latitude;
		this.longitude = longitude;
		this.registeredTimestamp = registeredTimestamp;
		this.registeredTimeString = registeredTimeString;
		this.registeredDateString = registeredDateString;
		this.profileViews = profileViews;
	}

	public User(String username, String email, String password){

		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getAuthority(){
		return authority;
	}

	public void setAuthority(String authority){
		this.authority = authority;
	}

	public boolean isEnabled(){
		return enabled;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	public boolean isHasProfilePic(){
		return hasProfilePic;
	}

	public void setHasProfilePic(boolean hasProfilePic){
		this.hasProfilePic = hasProfilePic;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLongitude(){
		return longitude;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	public Timestamp getRegisteredTimestamp(){
		return registeredTimestamp;
	}

	public void setRegisteredTimestamp(Timestamp registeredTimestamp){
		this.registeredTimestamp = registeredTimestamp;
	}

	public String getRegisteredTimeString(){
		return registeredTimeString;
	}

	public void setRegisteredTimeString(String registeredTimeString){
		this.registeredTimeString = registeredTimeString;
	}

	public String getRegisteredDateString(){
		return registeredDateString;
	}

	public void setRegisteredDateString(String registeredDateString){
		this.registeredDateString = registeredDateString;
	}

	public int getProfileViews(){
		return profileViews;
	}

	public void setProfileViews(int profileViews){
		this.profileViews = profileViews;
	}

	public String toString(){

		return "\nid: " + id + "\nusername: " + username + "\nemail: " + email + "\nauthority: " + authority
				+ "\nenabled: " + enabled + "\nhasProfilePic: " + hasProfilePic + "\nlatitude: " + latitude
				+ "\nlongitude: " + longitude + "\nregisteredTimestamp: " + registeredTimestamp + "\nprofileViews: " +
				profileViews;
	}
}
