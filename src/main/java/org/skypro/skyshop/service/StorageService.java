package org.skypro.skyshop.service;

import jakarta.annotation.PostConstruct;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;


    public StorageService(List<Product> productsStorage, List<Article> articlesStorage) {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();

        for (Product product:productsStorage){
            productMap.put(UUID.randomUUID(), product);
        }

        for (Article article: articlesStorage){
            articleMap.put(UUID.randomUUID(), article);
        }
    }

    //Создание объектов Product и Article
    public void addProduct(Product product){
        productMap.put(UUID.randomUUID(), product);
    }

    public void addArticle(Article article){
        articleMap.put(UUID.randomUUID(), article);
    }

    public Collection<Searchable> getSearchables(){
        List<Searchable> searchableObjects = new ArrayList<>();
        searchableObjects.addAll(productMap.values());
        searchableObjects.addAll(articleMap.values());
        return searchableObjects;
    }

    @PostConstruct
    public void init(){
        SimpleProduct product1 = new SimpleProduct("Пиво темное", 300, UUID.randomUUID());
        FixPriceProduct product2 = new FixPriceProduct("Чай", UUID.randomUUID());
        DiscountedProduct product3 = new DiscountedProduct("Пиво светлое", 300, 20 ,UUID.randomUUID());
        addProduct(product1);
        addProduct(product2);
        addProduct(product3);

        Article article1 = new Article("Варка пива", "Текст", UUID.randomUUID());
        Article article2 = new Article("Название 1", "Текст 2", UUID.randomUUID());
        Article article3 = new Article("Название 2", "Текст 3", UUID.randomUUID());
        addArticle(article1);
        addArticle(article2);
        addArticle(article3);
    }

    //Методы вывода значений
    public Collection<Product> productCollection() {
        return productMap.values();
    }
    public Collection<Article> articleCollection() {
        return articleMap.values();
    }

}
