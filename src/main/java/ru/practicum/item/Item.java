package ru.practicum.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items", schema = "public")
@Getter
@Setter
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "url")
    private String url;

    @ElementCollection(fetch = FetchType.LAZY) //по умолчанию и так lazy
    @CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "name")
    @ToString.Exclude
    private Set<String> tags = new HashSet<>();

    @Column(name = "resolved_url")
    private String resolvedUrl;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "title")
    private String title;

    @Column(name = "has_image")
    private Boolean hasImage;

    @Column(name = "has_video")
    private Boolean hasVideo;

    @Column(name = "date_resolved")
    private Instant dateResolved;

    @Column(name = "unread")
    private Boolean unread;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        return id != null && id.equals(((Item) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}