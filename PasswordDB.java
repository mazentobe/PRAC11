import java.util.*;
import javax.swing.JOptionPane;

public class PasswordDB {
	private List<User> users;

	public PasswordDB() {
		users = new ArrayList<User>();
	}

	public void addUser(User user) {
		users.add(user);
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void checkPassword(String password) throws DatabaseException {
		if (password.length() < 8) {
			throw new DatabaseException("Password is too short!");
		}
	}

	public void checkUsername(String username) throws DatabaseException {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				throw new DatabaseException("User with this username already exists!");
			}
		}
	}
	
	public String getUserInfoBy(String username) throws DatabaseException {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return "Name: " + user.getName() + "; Username: " + user.getUsername() + "; Email: " + user.getEmail();
			}
		}
		throw new DatabaseException("No user found with provided username!");
	}

	public static void main(String[] args) throws DatabaseException {
		PasswordDB database = new PasswordDB();
		Boolean shouldExecute = true;
		while (shouldExecute) {
			Object[] possibleValues = { "New User", "Search For User", "Exit"};
			Object command = JOptionPane.showInputDialog(null,
			"Please select command", "Practical 11",
			JOptionPane.INFORMATION_MESSAGE, null,
			possibleValues, possibleValues[0]);
			if (command.equals("New User")) {
				String name = JOptionPane.showInputDialog(null, "Input name");
				String username = "";
				Boolean shouldAskUsernameAgain = true;
				while (shouldAskUsernameAgain) {
					shouldAskUsernameAgain = false;
					username = JOptionPane.showInputDialog(null, "Input username");
					try {
						database.checkUsername(username);
					} catch(DatabaseException err) {
						err.toString();
						shouldAskUsernameAgain = true;
					}
				}
				String password = "";
				Boolean shouldAskAgain = true;
				while (shouldAskAgain) {
					shouldAskAgain = false;
					password = JOptionPane.showInputDialog(null, "Input password");
					try {
						database.checkPassword(password);
					} catch(DatabaseException err) {
						err.toString();
						shouldAskAgain = true;
					}
				}
				String email = JOptionPane.showInputDialog(null, "Input email");
				User user = new User(name, username, password, email);
				database.addUser(user);
				System.out.println(database.getUsers().size());
			} else if (command.equals("Exit")) {
				shouldExecute = false;
			} else if (command.equals("Search For User")) {
				try {
					String usernameToSearch = JOptionPane.showInputDialog(null, "Input username");
					String info = database.getUserInfoBy(usernameToSearch);
					JOptionPane.showMessageDialog(null, info,
							"User information", JOptionPane.INFORMATION_MESSAGE);
				} catch (DatabaseException err) {
					err.toString();
				}
			} else {
				System.out.println("no func");
			}
		}
	}
}