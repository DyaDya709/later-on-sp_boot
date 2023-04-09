package ru.practicum.tag;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tags",schema = "public")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "item_id")
    private Long itemId;
}
