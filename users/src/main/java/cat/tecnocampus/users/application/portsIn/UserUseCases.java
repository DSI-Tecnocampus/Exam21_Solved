package cat.tecnocampus.users.application.portsIn;

import cat.tecnocampus.users.domain.User;

import java.util.List;

public interface UserUseCases {
    User createUser(String username, String name, String secondName, String email);

    int registerUser(User user);

    User deleteUser(String username);

    List<User> getUsers();

    User getUser(String userName);

    boolean userExists(String username);
}
