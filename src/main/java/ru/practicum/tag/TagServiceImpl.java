package ru.practicum.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagJpaRepository jpaRepository;

    @Override
    public Tag save(Tag tag) {
        return jpaRepository.save(tag);
    }
}
