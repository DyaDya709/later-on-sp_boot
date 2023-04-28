package ru.practicum.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.practicum.userDto.UserDto;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class UserDtoJsonTest {
    @Autowired
    private JacksonTester<UserDto> json;

    @Test
    void testUserDto() throws Exception {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .email("john.doe@mail.com")
                .firstName("John")
                .lastName("Doe")
                .registrationDate("2022.07.03 19:55:00")
                .state(UserState.ACTIVE)
                .dateOfBirth(LocalDateTime.of(2000, 1, 1, 1, 1))
                .build();

        JsonContent<UserDto> result = json.write(userDto);

        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.firstName").isEqualTo("John");
        assertThat(result).extractingJsonPathStringValue("$.lastName").isEqualTo("Doe");
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo("john.doe@mail.com");
        assertThat(result).extractingJsonPathStringValue("$.dateOfBirth")
                .isEqualTo("01.01.2000 01:01:00");
    }
}
