package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) для теста saveUser в UserControllerTest
    public User saveNewUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}