package ru.practicum.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public UserMapper dtoMapper() {
        return new UserMapper();
    }
}
