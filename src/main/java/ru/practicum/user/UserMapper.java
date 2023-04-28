package ru.practicum.user;

import ru.practicum.userDto.UserDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class UserMapper implements Function<UserDto, User> {
    private static final String PATTERN_FORMAT = "dd.MM.yyyy HH:mm:ss";

    @Override
    public User apply(UserDto userDto) {
        return null;
    }

    public Instant stringDateTimeToInstantFormatter(String stringDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = LocalDateTime.parse(stringDateTime, dateTimeFormatter);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return zonedDateTime.toInstant();
    }
}
