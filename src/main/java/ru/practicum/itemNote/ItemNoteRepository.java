package ru.practicum.itemNote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemNoteRepository extends JpaRepository<ItemNote, Long> {
     List<ItemNote> findAllByItemUrlLike(String url);

     List<ItemNote> findAllByNoteLike(String note);

     @Query("select inote from ItemNote as inote " +
             "inner join inote.item as i " +
             "where :tag member of i.tags and i.userId = :userId")
     List<ItemNote> findAllByItemTagsIs(String tag, Long userId);

     @Override
     List<ItemNote> findAll();
}
