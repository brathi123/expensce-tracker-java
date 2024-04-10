import java.util.*;

class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

class Group {
    private String groupName;
    private List<User> members;

    public Group(String groupName) {
        this.groupName = groupName;
        this.members = new ArrayList<>();
    }

    public void addMember(User user) {
        members.add(user);
    }

    // Method to retrieve an unmodifiable view of the members list
    public List<User> getMembers() {
        return Collections.unmodifiableList(members);
    }
}

public class ExpenseTracker {
    private List<User> users;
    private List<Group> groups;

    public ExpenseTracker() {
        users = new ArrayList<>();
        groups = new ArrayList<>();
    }

    public void registerUser(String name, String email, String password) {
        users.add(new User(name, email, password));
    }

    public User loginUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // User not found or incorrect password
    }

    public void createGroup(String groupName, User creator) {
        Group group = new Group(groupName);
        group.addMember(creator);
        groups.add(group);
    }

    public List<User> getGroupMembers(int groupIndex) {
        Group group = groups.get(groupIndex);
        return group.getMembers();
    }

    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();

        // Register users
        expenseTracker.registerUser("rathish", "bashettyrathish@gmail.com", "password1");
        expenseTracker.registerUser("rakesh", "bashettyrakesh@gmail.com", "password2");

        // Login
        User loggedInUser = expenseTracker.loginUser("bashettyrathish@gmail.com", "password1");
        if (loggedInUser != null) {
            System.out.println("Logged in as: " + loggedInUser.getName());
        }

        // Create a group
        if (loggedInUser != null) {
            expenseTracker.createGroup("Friends", loggedInUser);
            System.out.println("Group created successfully");

            // Print information about created group
            System.out.println("Group Members:");
            for (User member : expenseTracker.getGroupMembers(0)) {
                System.out.println("Name: " + member.getName() + ", Email: " + member.getEmail());
            }
        }
    }
}
