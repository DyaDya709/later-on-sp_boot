package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final String requestHeaderUserId = "X-Later-User-Id";

    @GetMapping
    public List<Item> get(@RequestHeader(requestHeaderUserId) long userId) {
        return itemService.getItems(userId);
    }

    @PostMapping
    public Item add(@RequestHeader(requestHeaderUserId) Long userId,
                    @RequestBody Item item) {
        return itemService.addNewItem(userId, item);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@RequestHeader(requestHeaderUserId) long userId,
                           @PathVariable long itemId) {
        itemService.deleteItem(userId, itemId);
    }
}