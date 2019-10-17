package dao;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<User> users = new ArrayList<>();
    public UserDAO() {
        users.add(new User("1", "A", "Customer"));
        users.add(new User("2", "B", "Customer"));
        users.add(new User("3", "C", "Customer"));
        users.add(new User("4", "D", "Admin"));
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(String userId) {
        return users.stream().filter(user -> user.getId().equals(userId)).findFirst().get();
    }
}
