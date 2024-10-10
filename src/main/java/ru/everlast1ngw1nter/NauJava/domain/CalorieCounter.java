package ru.everlast1ngw1nter.NauJava.domain;

import java.time.LocalDate;

public interface CalorieCounter {

    void addConsumedProduct(String productName, LocalDate date);

    int getCaloriesByProduct(String productName);

    int getCaloriesByDate(LocalDate date);

    int getCaloriesByInterval(LocalDate startDate, LocalDate endDate);

    void addProduct(String newProductName, int calories);

    void deleteProductByName(String productName);

    default void addTodayConsumedProduct(String productName) {
        addConsumedProduct(productName, LocalDate.now());
    }
}
