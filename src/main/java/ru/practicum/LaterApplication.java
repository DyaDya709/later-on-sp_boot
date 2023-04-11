package ru.practicum;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.practicum.itemNote.ItemNote;
import ru.practicum.itemNote.ItemNoteService;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class LaterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LaterApplication.class, args);
        ItemNoteService itemNoteService = applicationContext.getBean(ItemNoteService.class);
        List<ItemNote> notes = itemNoteService.findAllByItemTagsIs("ya",1L);
        List<ItemNote> notes1 = itemNoteService.findAllByNoteLike("some note1");
        Set<String> tags = notes1.get(0).getItem().getTags();
    }
}