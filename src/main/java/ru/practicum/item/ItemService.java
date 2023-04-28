package ru.practicum.item;

import ru.practicum.item.request.ModifyItemRequest;
import ru.practicum.itemDto.ItemDto;
import ru.practicum.item.request.GetItemRequest;

import java.util.List;

interface ItemService {
    ItemDto addNewItem(long userId, ItemDto itemDto);

    List<ItemDto> getItems(long userId, GetItemRequest request);

    void deleteItem(long userId, long itemId);

    ItemDto findByUserId(Long userId);

    ItemDto changeItem(long userId, ModifyItemRequest request);
}
