package cat.tecnocampus.users.application.portsOut;

import cat.tecnocampus.users.domain.User;

import java.util.List;

public interface UserDAO {
    int insert(User userLab);

    List<User> findAll();

    User findByUsername(String userName);

    int delete(String username);

    boolean existsUser(String username);
}
