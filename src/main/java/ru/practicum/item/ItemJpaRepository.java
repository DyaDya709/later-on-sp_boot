package ru.practicum.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemJpaRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    Item findByResolvedUrl(String resolvedUrl);

    Item findByUserId(Long userId);
}
