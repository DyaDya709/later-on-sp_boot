package ru.practicum.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoMapperConfig {
    @Bean
    public DtoToUserMapper dtoMapper() {
        return new DtoToUserMapper();
    }
}
