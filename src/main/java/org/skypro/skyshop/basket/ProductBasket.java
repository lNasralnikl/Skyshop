package org.skypro.skyshop.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.*;
import java.util.function.Predicate;

public class ProductBasket {

    private final List<Product> deletedProducts = new ArrayList<>();
    private final Map<String, List<Product>> products = new HashMap<>();

    //Статические
    static boolean check = false;

    //Методы
    //Добавление продукта в корзину
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Продукт не может быть пустым");
        }
        if (!products.containsKey(product.getName())) {
            products.put(product.getName(), new ArrayList<>());
        }
        products.get(product.getName()).add(product);
    }

    //Удаление продукта по наименованию
    public List<Product> deleteProduct(String productName) {
        if (productName.isBlank() || productName.isEmpty()) {
            return deletedProducts;
        }

        List<String> toRemove = new ArrayList<>();
        for (String key : products.keySet()) {
            if (key.toLowerCase().contains(productName.toLowerCase())) {
                deletedProducts.addAll(products.get(key));
                toRemove.add(key);
            }
        }

        for (String key : toRemove) {
            products.remove(key);
        }

        return deletedProducts;

    }

    /*
    //Проверка продукта в корзине
    public void checkProduct(String name) {

        if (name.isBlank()) {
            throw new IllegalArgumentException("Наименование товара не может быть пустым");
        }
        products.forEach(n -> {
            int index = products.indexOf(n);
            if (n.getName().equalsIgnoreCase(name)) {
                System.out.println("Товар в корзине под номером " + (index + 1));
                check = true;
            }
        });
        if (!check) {
            System.out.println("Товара " + name + " нет в корзине\n");
        }
        System.out.println();
    }

     */

    //Очистка корзины
    public void busketClean() {
        System.out.println("Корзина очищена\n");
        products.clear();
    }

    //Вывод корзины
    public void printBusket() {
        System.out.println("Продуктовая корзина:");

        List<Product> busketPrint = products.values().stream()
                .flatMap(Collection::stream)
                .toList();

        //Расчет суммы товаров
        double sum = products.values().stream()
                .flatMap(Collection::stream)
                .mapToDouble(Product::getPrice)
                .sum();

        //Счетчик спец. продуктов
        long specials = busketPrint.stream()
                .filter(new CheckSpecials())
                .count();

        busketPrint.forEach(product ->
                System.out.println(product.toString()));
        System.out.print("Сумма товаров: " + sum);
        if (specials != 0) {
            System.out.print(" | Специальных товаров: " + specials);
        }

        System.out.println();

    }

    //Счетчик специальных продуктов
    static class CheckSpecials implements Predicate<Product> {
        @Override
        public boolean test(Product product) {
            return product.isSpecial();
        }
    }


}
