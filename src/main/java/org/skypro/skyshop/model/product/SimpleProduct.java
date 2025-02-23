package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {

    private final double price;

    public SimpleProduct(String name, double price, UUID id) {
        super(name, id);
        if (price <= 0) {
            throw new IllegalArgumentException("Ошибка: Цена товара не может быть меньше или равна 0");
        }
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }


    @Override
    public boolean isSpecial() {
        return false;
    }

}
