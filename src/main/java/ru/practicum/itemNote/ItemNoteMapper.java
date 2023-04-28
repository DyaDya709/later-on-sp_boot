package ru.practicum.itemNote;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemNoteMapper {
    private static final String PATTERN_FORMAT = "dd.MM.yyyy, hh:mm:ss";

    public ItemNote map(ItemNoteDto itemNoteDto) {
        ItemNote itemNote = new ItemNote();
        itemNote.setDateOfNote(stringDateTimeToInstantFormatter(itemNoteDto.getDateOfNote()));
        itemNote.setNote(itemNote.getNote());
        itemNote.setId(itemNoteDto.getId());
        return itemNote;
    }

    private Instant stringDateTimeToInstantFormatter(String stringDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = LocalDateTime.parse(stringDateTime, dateTimeFormatter);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return zonedDateTime.toInstant();
    }

    private String instantToStringFormatter(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
