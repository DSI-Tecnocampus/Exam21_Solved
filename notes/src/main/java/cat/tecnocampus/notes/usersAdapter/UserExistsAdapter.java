package cat.tecnocampus.notes.usersAdapter;

import cat.tecnocampus.notes.application.portsOut.CallUserExists;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserExistsAdapter implements CallUserExists {
    private final RestTemplate restTemplate;

    public UserExistsAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean userExists(String userName) {
        return restTemplate.getForObject("http://localhost:8080/users/exists/" + userName, boolean.class);
    }
}
