package test.dao;

import test.models.User;

import java.util.HashMap;

public class UserDao implements UserDaoInterface {
    private HashMap<String, User> userHashMap = new HashMap<>();

    @Override
    public void addUser(User user) {
        userHashMap.put(user.getId(), user);
    }

    @Override
    public User getUserById(String userId) {
        //validate user first, then return
        validateUser(userId);
        return userHashMap.get(userId);
    }
    @Override
    public User createUser(String name, String id, boolean isAdmin) {
        User user = new User(name, id, isAdmin);
        userHashMap.put(id, user);
        return user;
    }

    public void validateUser(String userId) {
        if (userHashMap.get(userId) == null) {
            //todo create customised exception
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void deleteUser(String userId) {
        validateUser(userId);
        userHashMap.remove(userId);
    }
}
