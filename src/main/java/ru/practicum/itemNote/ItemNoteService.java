package ru.practicum.itemNote;

import java.util.List;

public interface ItemNoteService {
    List<ItemNote> findAllByItemUrlLike(String url);

    List<ItemNote> findAllByItemTagsIs(String tag, Long userId);

    List<ItemNote> findAll();

    List<ItemNote> findAllByNoteLike(String note);

    ItemNoteDto addNewItemNote(long userId, ItemNoteDto itemNoteDto);

    List<ItemNoteDto> searchNotesByUrl(String url, Long userId);

    List<ItemNoteDto> searchNotesByTag(long userId, String tag);

    List<ItemNoteDto> listAllItemsWithNotes(long userId, int from, int size);
}
