public class User {
	private String name;
	private String username;
	private String password;
	private String email;

	public User(String name, String username, String password, String email) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
}