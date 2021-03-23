package cat.tecnocampus.users.webAdapter;

import cat.tecnocampus.users.application.portsIn.UserUseCases;
import cat.tecnocampus.users.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRESTController {
    private UserUseCases userUseCases;

    public UserRESTController(UserUseCases userUseCases) {
        this.userUseCases = userUseCases;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> listUsers() {
        return userUseCases.getUsers();
    }

    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User showUser(@PathVariable String username) {
        return userUseCases.getUser(username);
    }

    @GetMapping(value = "/users/exists/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean existsUser(@PathVariable String username) {
        return userUseCases.userExists(username);
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user) {

        userUseCases.registerUser(user);

        return user;
    }

    @DeleteMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@PathVariable String username) {
        User user;

        user = userUseCases.deleteUser(username);
        return new ResponseEntity(user, user==null? HttpStatus.NOT_FOUND: HttpStatus.OK);
    }
}
