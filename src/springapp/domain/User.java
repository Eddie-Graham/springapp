package springapp.domain;

public class User {

	private String id;
	private String username;
	private String email;
	private String password;
	private String authority;
	private boolean enabled;
	private boolean hasProfilePic;
	private double latitude;
	private double longitude;
	
	public User(String id, String username, String email, String password, String authority, boolean enabled,
			boolean hasProfilePic, double latitude, double longitude) {
		
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authority = authority;
		this.enabled = enabled;
		this.hasProfilePic = hasProfilePic;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public User(String username, String email, String password){
		
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isHasProfilePic() {
		return hasProfilePic;
	}

	public void setHasProfilePic(boolean hasProfilePic) {
		this.hasProfilePic = hasProfilePic;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String toString(){

		return "\nid: " + id + "\nusername: " + username + "\nemail: " + email + "\nauthority: " + authority
				+ "\nenabled: " + enabled + "\nhasProfilePic: " + hasProfilePic + "\nlatitude: " + latitude
				+ "\nlongitude: " + longitude;
	}
}
