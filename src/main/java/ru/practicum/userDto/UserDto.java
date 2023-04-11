package ru.practicum.userDto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.user.UserState;

@Data
@Builder
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String registrationDate;

    private UserState state;
}
