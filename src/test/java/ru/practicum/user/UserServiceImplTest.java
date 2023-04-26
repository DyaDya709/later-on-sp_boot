package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.practicum.LaterApplication;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;


@SpringBootTest(
        classes = {LaterApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringJUnitConfig({DtoMapperConfig.class})
public class UserServiceImplTest {
    private final UserService userService;

    @Test
    void getAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Alice");

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Bob");

        userService.saveUser(user1);
        userService.saveUser(user2);

        List<User> users = userService.getAllUsers();
        assertThat(users, hasSize(2));
        assertThat(users, containsInAnyOrder(user1, user2));
    }

}
