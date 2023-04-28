package ru.practicum.itemNote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemNoteServiceImpl implements ItemNoteService {
    private final ItemNoteRepository itemNoteRepository;

    @Autowired
    public ItemNoteServiceImpl(ItemNoteRepository itemNoteRepository) {
        this.itemNoteRepository = itemNoteRepository;
    }

    @Override
    public List<ItemNote> findAllByItemUrlLike(String url) {
        return itemNoteRepository.findAllByItemUrlLike(url);
    }

    @Override
    public List<ItemNote> findAllByItemTagsIs(String tag, Long userId) {
        return itemNoteRepository.findAllByItemTagsIs(tag, userId);
    }

    @Override
    public List<ItemNote> findAll() {
        return itemNoteRepository.findAll();
    }

    @Override
    public List<ItemNote> findAllByNoteLike(String note) {
        return itemNoteRepository.findAllByNoteLike(note);
    }

    @Transactional
    @Override
    public ItemNoteDto addNewItemNote(long userId, ItemNoteDto itemNoteDto) {
        return null;
    }

    @Override
    public List<ItemNoteDto> searchNotesByUrl(String url, Long userId) {
        return null;
    }

    @Override
    public List<ItemNoteDto> searchNotesByTag(long userId, String tag) {
        return null;
    }

    @Override
    public List<ItemNoteDto> listAllItemsWithNotes(long userId, int from, int size) {
        return null;
    }

}
