package ru.practicum.item;

import ru.practicum.itemDto.ItemDto;

import java.util.function.Function;

public class DtoToItemMapper implements Function<ItemDto, Item> {
    @Override
    public Item apply(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .userId(itemDto.getUserId())
                .url(itemDto.getUrl())
                .tags(itemDto.getTags())
                .build();
    }
}
