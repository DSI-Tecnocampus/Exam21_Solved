package cat.tecnocampus.users.application.service;

import cat.tecnocampus.users.application.portsOut.DeleteUserNotes;
import cat.tecnocampus.users.application.portsOut.UserDAO;
import cat.tecnocampus.users.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserUseCases implements cat.tecnocampus.users.application.portsIn.UserUseCases {

    private final UserDAO userDAO;
    private final DeleteUserNotes deleteUserNotes;

    public UserUseCases(UserDAO UserDAO, DeleteUserNotes deleteUserNotes) {
        this.userDAO = UserDAO;
        this.deleteUserNotes = deleteUserNotes;
    }

    @Override
    public User createUser(String username, String name, String secondName, String email) {
        User user = new User();
        user.setName(name);
        user.setSecondName(secondName);
        user.setUsername(username);
        user.setEmail(email);

        registerUser(user);
        return user;
    }

    @Override
    public int registerUser(User user) {
        return userDAO.insert(user);
    }

    @Override
    public User deleteUser(String username) {
        if (userExists(username)) {
            User user = new User();
            user = getUser(username);
            userDAO.delete(username);
            deleteUserNotes.deleteUserNotes(username);
            return user;
        }

        return null;
    }

    @Override
    public List<User> getUsers() {
        return userDAO.findAll();
    }

    @Override
    public User getUser(String userName) {
        return userDAO.findByUsername(userName);
    }

    @Override
    public boolean userExists(String username) {
        return userDAO.existsUser(username);
    }

}
