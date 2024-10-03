package ru.everlast1ngw1nter.NauJava.models;

import java.time.LocalDate;

// не окончательный класс, может измениться к след. заданию
public class ConsumedProduct extends Product {

    private LocalDate consumedDate;

    public ConsumedProduct(long id, String name, int calories, LocalDate consumedDate) {
        super(id, name, calories);
        this.consumedDate = consumedDate;
    }

    public ConsumedProduct(Product product, LocalDate consumedDate) {
        super(product.getId(), product.getName(), product.getCalories());
        this.consumedDate = consumedDate;
    }

    public LocalDate getConsumedDate() {
        return consumedDate;
    }

    public void setConsumedDate(LocalDate date) {
        consumedDate = date;
    }
}
