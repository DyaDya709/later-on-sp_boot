package ru.practicum.userDto;

import ru.practicum.user.User;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class UserToDtoMapper implements Function<User, UserDto> {
    private static final String PATTERN_FORMAT = "dd.MM.yyyy, hh:mm:ss";

    @Override
    public UserDto apply(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .registrationDate(instantToStringFormatter(user.getRegistrationDate()))
                .state(user.getState())
                .build();
    }

    private String instantToStringFormatter(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
