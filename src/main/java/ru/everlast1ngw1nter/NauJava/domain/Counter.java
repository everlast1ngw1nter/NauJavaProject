package ru.everlast1ngw1nter.NauJava.domain;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.everlast1ngw1nter.NauJava.database.ConsumedProductRepository;
import ru.everlast1ngw1nter.NauJava.database.ProductRepository;
import ru.everlast1ngw1nter.NauJava.models.ConsumedProduct;
import ru.everlast1ngw1nter.NauJava.models.Product;

@Service
public class Counter implements CalorieCounter {

    private final ProductRepository productDb;

    private final ConsumedProductRepository consumedProductDb;

    @Autowired
    public Counter(ProductRepository productDb, ConsumedProductRepository consumedProductDb) {
        this.productDb = productDb;
        this.consumedProductDb = consumedProductDb;
    }

    @Override
    public void addConsumedProduct(String productName, LocalDate date) {
        var product = productDb.getByName(productName);
        consumedProductDb.save(new ConsumedProduct(product, date, null));
    }

    @Override
    public int getCaloriesByProduct(String productName) {
        var product = productDb.getByName(productName);
        return product.getCalories();
    }

    @Override
    public int getCaloriesByDate(LocalDate date) {
        return consumedProductDb.getConsumedProductByConsumedDateBetween(date, date)
                .stream()
                .mapToInt(x -> x.getProduct().getCalories())
                .sum();
    }

    @Override
    public int getCaloriesByInterval(LocalDate startDate, LocalDate endDate) {
        return consumedProductDb.getConsumedProductByConsumedDateBetween(startDate, endDate)
                .stream()
                .mapToInt(x -> x.getProduct().getCalories())
                .sum();
    }

    @Override
    public void addProduct(String newProductName, int calories) {
        productDb.save(new Product(newProductName, calories));
    }

    @Override
    public void deleteProductByName(String productName) {
        productDb.deleteByName(productName);
    }
}
