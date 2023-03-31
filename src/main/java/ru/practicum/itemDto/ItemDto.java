package ru.practicum.itemDto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ItemDto {

    private Long id;

    private Long userId;

    private String url;

    private Set<String> tags;
}
