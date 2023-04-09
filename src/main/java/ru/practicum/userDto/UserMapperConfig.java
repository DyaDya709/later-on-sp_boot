package ru.practicum.userDto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapperConfig {
    @Bean
    public UserToDtoMapper userMapper() {
        return new UserToDtoMapper();
    }
}
