package com.example.spotifyplaylistapp.model;

import com.example.spotifyplaylistapp.model.enums.StyleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private StyleEnum name;

    @Basic
    private String description;

    public Style() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StyleEnum getName() {
        return name;
    }

    public void setName(StyleEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
