package cat.tecnocampus.notes.usersAdapter;

import cat.tecnocampus.notes.application.portsOut.CallUserExists;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserExistsAdapter implements CallUserExists {
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final String userServiceUrl;


    public UserExistsAdapter(RestTemplate restTemplate, CircuitBreakerFactory circuitBreakerFactory,
                             @Value("${app.users-service.host}") String usersServiceHost) {
        this.restTemplate = restTemplate;
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.userServiceUrl = "http://" + usersServiceHost + "/users/exists/";
    }

    @Override
    @Retry(name="user")
    public String userExists(String userName) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user");
        return circuitBreaker.run(
                () -> restTemplate.getForObject(userServiceUrl + userName, String.class),
                throwable -> {
                    System.out.println(throwable.getMessage());
                    return "open";});
    }
}
