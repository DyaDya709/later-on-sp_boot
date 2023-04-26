import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.practicum.LaterApplication;
import ru.practicum.user.User;
import ru.practicum.user.UserService;
import ru.practicum.user.UserServiceImpl;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(
        classes = {LaterApplication.class},
        properties = "db.name=test",
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImplTest {
    private final UserService userService;

    @Test
    void getAllUsers() {
        List<User> users = userService.getAllUsers();
        User user = new User();
        user.setId(1L);
        assertThat(users.get(0), equalTo(user));
    }

}
