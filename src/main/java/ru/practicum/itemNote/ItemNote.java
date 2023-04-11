package ru.practicum.itemNote;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.item.Item;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "item_notes", schema = "public")
@Setter
@Getter
@ToString
public class ItemNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    Instant instant;

    @Column(name = "note", length = 2000)
    private String note;

    @ManyToOne
    @JoinColumn(name = "id")
    private Item item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemNote)) return false;
        return id != null && id.equals(((ItemNote) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
