package ru.everlast1ngw1nter.NauJava.domain;

import java.time.LocalDate;

public interface CalorieCounter {

    void addConsumedProduct(String productName, LocalDate date);

    int getCaloriesByProduct(String productName);

    int getCaloriesByDate(LocalDate date);

    int getCaloriesByInterval(LocalDate startDate, LocalDate endDate);

    void addNewProduct(String newProductName, int calories);

    void deleteProduct(String newProductName);

    default void addTodayConsumedProduct(String productName) {
        addConsumedProduct(productName, LocalDate.now());
    }
}
