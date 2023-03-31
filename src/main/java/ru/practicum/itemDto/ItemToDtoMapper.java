package ru.practicum.itemDto;

import ru.practicum.item.Item;

import java.util.function.Function;

public class ItemToDtoMapper implements Function<Item, ItemDto> {

    @Override
    public ItemDto apply(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .userId(item.getUserId())
                .url(item.getUrl())
                .tags(item.getTags())
                .build();
    }
}
