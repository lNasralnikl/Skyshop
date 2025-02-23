package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {

    private final double basePrice;
    private final int discountPercent;

    public DiscountedProduct(String name, double basePrice, int discountPercent, UUID id) {
        super(name, id);
        this.basePrice = basePrice;
        if (discountPercent < 0 || discountPercent > 100){
            throw new IllegalArgumentException("Ошибка: Скидка на товар должна быть в диапазоне от 0 до 100%");
        }
        this.discountPercent = discountPercent;
    }

    @Override
    public double getPrice() {
        return basePrice*(1- (double) discountPercent /100);
    }

    @Override
    public String toString() {
        return getName() + " со скидкой " + +discountPercent + "%: " + getPrice();
    }

    @Override
    public boolean isSpecial(){
        return true;
    }
}
