package cat.tecnocampus.users.persistenceAdapter;

import cat.tecnocampus.users.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAO implements cat.tecnocampus.users.application.portsOut.UserDAO {
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_USER = "insert into user_lab values(?, ?, ?, ?)";
    private final String FIND_ALL = "Select * from user_lab";
    private final String FIND_BY_USERNAME = "Select * from user_lab where username = ?";
    private final String DELETE = "delete from user_lab where username = ?";
    private final String USER_EXISTS = "select count(*) from user_lab where username = ?";

    private User userMapper(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setName(resultSet.getString("name"));
        user.setSecondName(resultSet.getString("second_name"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }

    private RowMapper<User> mapper = (resultSet, i) -> {
        return userMapper(resultSet);
    };

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(User userLab) {
        return jdbcTemplate.update(INSERT_USER, userLab.getUsername(), userLab.getName(), userLab.getSecondName(),
                userLab.getEmail());

    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public User findByUsername(String userName) {
        return jdbcTemplate.queryForObject(FIND_BY_USERNAME, mapper, userName);
    }

    @Override
    public int delete(String username) {
        return jdbcTemplate.update(DELETE, username);
    }

    @Override
    public boolean existsUser(String username) {
        Integer cnt = jdbcTemplate.queryForObject(USER_EXISTS, Integer.class, username);
        return cnt != null && cnt > 0;
    }
}
