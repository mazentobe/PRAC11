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

	public static void main(String[] args) throws DatabaseException {
		PasswordDB database = new PasswordDB();
		Boolean shouldExecute = true;
		while (shouldExecute) {
			String command = JOptionPane.showInputDialog(null, "Input command (new, exit)");
			if (command.equals("new")) {
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
			} else if (command.equals("exit")) {
				shouldExecute = false;
			} else {
				System.out.println("no func");
			}
		}
	}
}