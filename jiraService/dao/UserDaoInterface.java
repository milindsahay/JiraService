package test.dao;

import test.models.User;

public interface UserDaoInterface {
    public void addUser(User user);

    public User getUserById(String userId);

    User createUser(String name, String id, boolean isAdmin);

    public void deleteUser(String userId);
}
