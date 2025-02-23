package org.skypro.skyshop.service;


import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {


    private final StorageService storageService;

    public SearchService(StorageService storageService){
        this.storageService = storageService;
    }

    //Поиск по наименованию
    public List<SearchResult> search(String pattern){
        if (pattern.isBlank()){
            throw new IllegalArgumentException("Наименование пустое!");
        }
        System.out.println("Результаты поиска по наименованию " + pattern + ": ");

        Collection<Searchable> founded = storageService.getSearchables();

        if (founded.isEmpty()){
            System.out.println("Коллекция пустая!");
            return List.of();
        }

        return founded.stream()
                .filter(searchable -> searchable.searchTerm().toLowerCase().contains(pattern.toLowerCase()))
                .map(n -> new SearchResult(n.getId(), n.searchTerm(), n.content()))
                .collect(Collectors.toList());

    }

}
