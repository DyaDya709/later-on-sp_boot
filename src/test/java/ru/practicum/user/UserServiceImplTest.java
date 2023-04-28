package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestPropertySource(locations = "classpath:application.properties")
public class UserServiceImplTest {
    @Autowired
    private final UserService userService;

    @Autowired
    private final UserRepository userRepository;

    @Test
    void getAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Alice");

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Bob");
        userRepository.save(user1);
        userRepository.save(user2);
        // Получаем всех пользователей через UserService
        List<User> allUsers = userService.getAllUsers();

        // Проверяем, что полученный список содержит всех созданных пользователей
        assertThat(allUsers, hasSize(2));
        assertThat(allUsers, containsInAnyOrder(user1, user2));
    }

    @Test
    void testSaveUser() {
        // Создаем тестового пользователя
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Alice");

        // Сохраняем пользователя через UserService
        User savedUser = userService.saveUser(user1);

        // Получаем пользователя из репозитория и проверяем, что он соответствует сохраненному пользователю
        User retrievedUser = userRepository.findById(savedUser.getId()).orElse(null);
        assertThat(retrievedUser, notNullValue());
        assertThat(retrievedUser, equalTo(savedUser));
    }
}
