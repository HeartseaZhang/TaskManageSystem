package app.heartsea.taskmanager.service;

import app.heartsea.taskmanager.domain.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByEmail(String email);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    boolean checkPassword(String rawPassword, String encodedPassword);
}
