package ru.practicum.item;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.exception.InsufficientPermissionException;
import ru.practicum.exception.NotFoundException;
import ru.practicum.item.request.GetItemRequest;
import ru.practicum.item.request.ModifyItemRequest;
import ru.practicum.itemDto.ItemDto;
import ru.practicum.itemDto.ItemMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;
    private final ItemJpaRepository jpaRepository;
    private final UrlMetadataRetriever urlMetadataRetriever;

    @Override
    public List<ItemDto> getItems(long userId, GetItemRequest req) {
        // Для поиска ссылок используем QueryDSL чтобы было удобно настраивать разные варианты фильтров
        QItem item = QItem.item;
        // Мы будем анализировать какие фильтры указал пользователь
        // И все нужные условия фильтрации будем собирать в список
        List<BooleanExpression> conditions = new ArrayList<>();
        // Условие, которое будет проверяться всегда - пользователь сделавший запрос
        // должен быть тем же пользователем, что сохранил ссылку
        conditions.add(item.userId.eq(userId));

        // Проверяем один из фильтров указанных в запросе - state
        GetItemRequest.State state = req.getState();
        // Если пользователь указал, что его интересуют все ссылки, вне зависимости
        // от состояния, тогда пропускаем этот фильтр. В обратном случае анализируем
        // указанное состояние и формируем подходящее условие для запроса
        if (!state.equals(GetItemRequest.State.ALL)) {
            conditions.add(makeStateCondition(state));
        }

        // Если пользователь указал, что его интересуют ссылки вне зависимости
        // от типа их содержимого, то пропускаем фильтра, иначе анализируем
        // указанный тип контента и формируем соответствующее условие
        GetItemRequest.ContentType contentType = req.getContentType();
        if (!contentType.equals(GetItemRequest.ContentType.ALL)) {
            conditions.add(makeContentTypeCondition(contentType));
        }

        // если пользователя интересуют ссылки с конкретными тэгами,
        // то добавляем это условие в запрос
        if (req.hasTags()) {
            conditions.add(item.tags.any().in(req.getTags()));
        }

        // из всех подготовленных условий, составляем единое условие
        BooleanExpression finalCondition = conditions.stream()
                .reduce(BooleanExpression::and)
                .get();

        // анализируем, какой вариант сортировки выбрал пользователь
        // и какое количество элементов он выбрал для отображения
        Sort sort = makeOrderByClause(req.getSort());
        PageRequest pageRequest = PageRequest.of(0, req.getLimit(), sort);

        // выполняем запрос к базе данных со всеми подготовленными настройками
        // конвертируем результат в DTO и возвращаем контроллеру
        Iterable<Item> items = jpaRepository.findAll(finalCondition, pageRequest);
        return ItemMapper.mapToItemDto(items);
    }

    @Override
    public ItemDto addNewItem(long userId, ItemDto itemDto) {
        UrlMetadataRetriever.UrlMetadata urlMetadata = urlMetadataRetriever.retrieve(itemDto.getNormalUrl());
        Item item = jpaRepository.findByResolvedUrl(urlMetadata.getNormalUrl());
        if (item == null) {
            item = ItemMapper.mapToItem(urlMetadata, userId, itemDto.getTags());
        } else {
            Long itemId = item.getId();
            item = ItemMapper.mapToItem(urlMetadata, userId, itemDto.getTags());
            item.setId(itemId);
        }
        return ItemMapper.mapToItemDto(jpaRepository.save(item));
    }

    @Override
    public void deleteItem(long userId, long itemId) {
        Item item = jpaRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Item not found"));
        if (!item.getUserId().equals(userId)) {
            throw new InsufficientPermissionException("user not owner item");
        }
        //item.getTags(); теги тоже удаляются
        jpaRepository.delete(item);
    }

    @Override
    public ItemDto findByUserId(Long userId) {
        return ItemMapper.mapToItemDto(jpaRepository.findByUserId(userId));
    }

    @Override
    public ItemDto changeItem(long userId, ModifyItemRequest request) {
        Item item = jpaRepository.findById(request.getItemId()).orElseThrow(() -> new NotFoundException("No such item"));
        if (item.getUserId() != userId) {
            throw new InsufficientPermissionException("Invalid item owner");
        }
        item.setUnread(!request.isRead());
        if (request.isReplaceTags()) {
            item.setTags(request.getTags());
        } else {
            item.getTags().addAll(request.getTags());
        }
        return ItemMapper.mapToItemDto(jpaRepository.save(item));
    }

    private BooleanExpression makeStateCondition(GetItemRequest.State state) {
        if (state.equals(GetItemRequest.State.READ)) {
            return QItem.item.unread.isFalse();
        } else {
            return QItem.item.unread.isTrue();
        }
    }

    private BooleanExpression makeContentTypeCondition(GetItemRequest.ContentType contentType) {
        if (contentType.equals(GetItemRequest.ContentType.ARTICLE)) {
            return QItem.item.mimeType.eq("text");
        } else if (contentType.equals(GetItemRequest.ContentType.IMAGE)) {
            return QItem.item.mimeType.eq("image");
        } else {
            return QItem.item.mimeType.eq("video");
        }
    }

    private Sort makeOrderByClause(GetItemRequest.Sort sort) {
        switch (sort) {
            case TITLE:
                return Sort.by("title").ascending();
            case SITE:
                return Sort.by("resolvedUrl").ascending();
            case OLDEST:
                return Sort.by("dateResolved").ascending();
            case NEWEST:
            default:
                return Sort.by("dateResolved").descending();
        }
    }
}
