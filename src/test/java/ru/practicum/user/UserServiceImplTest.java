package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.practicum.LaterApplication;

import java.util.List;

@SpringBootTest(
        classes = {LaterApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImplTest {
    private final UserService userService;

    @Test
    void getAllUsers() {
        List<User> users = userService.getAllUsers();
        User user = new User();
        user.setId(1L);
        MatcherAssert.assertThat(users.get(0), Matchers.equalTo(user));
    }

}
