package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public final class Article implements Searchable {

    private final UUID id;
    private final String title;
    private final String text;

    public Article(String title, String text, UUID id) {
        this.title = title;
        this.text = text;
        this.id = id;
    }

    @Override
    public UUID getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getText(){
        return text;
    }

    @Override
    public String toString(){
        return title + "\n" + text;
    }

    @JsonIgnore
    public String searchTerm() {
        return title + "\n" + text;
    }

    @JsonIgnore
    public String content() {
        return "Article";
    }
}
