package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.item.request.GetItemRequest;
import ru.practicum.item.request.ModifyItemRequest;
import ru.practicum.itemDto.ItemDto;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final String requestHeaderUserId = "X-Later-User-Id";

    @GetMapping
    public List<ItemDto> get(@RequestHeader(requestHeaderUserId) long userId,
                          @RequestParam(defaultValue = "unread") String state,
                          @RequestParam(defaultValue = "all") String contentType,
                          @RequestParam(defaultValue = "newest") String sort,
                          @RequestParam(defaultValue = "10") int limit,
                          @RequestParam(required = false) List<String> tags) {
        GetItemRequest request = GetItemRequest.of(state, contentType, sort, limit, tags);
        return itemService.getItems(userId, request);
    }

    @PostMapping
    public ItemDto add(@RequestHeader(requestHeaderUserId) Long userId,
                       @RequestBody ItemDto itemDto) {
        return itemService.addNewItem(userId, itemDto);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@RequestHeader(requestHeaderUserId) long userId,
                           @PathVariable long itemId) {
        itemService.deleteItem(userId, itemId);
    }

    @PatchMapping
    public ItemDto modifyItem(@RequestHeader(requestHeaderUserId) long userId,
                              @RequestBody ModifyItemRequest request) {
        return itemService.changeItem(userId, request);
    }
}