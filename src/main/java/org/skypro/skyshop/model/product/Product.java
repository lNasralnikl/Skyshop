package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;
import java.util.UUID;

public abstract class Product implements Searchable {

    private final UUID id;
    private final String name;

    public Product(String name, UUID id) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ошибка: Наименование не может состоять из пробелов или иметь значение null");
        }
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice();
    public abstract boolean isSpecial();

    @Override
    public UUID getId(){
        return id;
    }

    //Добавление поиска
    @JsonIgnore
    public String searchTerm() {
        return name;
    }

    @JsonIgnore
    public String content() {
        return "PRODUCT";
    }

}
