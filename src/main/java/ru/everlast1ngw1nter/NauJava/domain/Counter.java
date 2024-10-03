package ru.everlast1ngw1nter.NauJava.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.everlast1ngw1nter.NauJava.database.CrudRepository;
import ru.everlast1ngw1nter.NauJava.models.ConsumedProduct;
import ru.everlast1ngw1nter.NauJava.models.Product;

@Service
public class Counter implements CalorieCounter {

    private final CrudRepository<Product, Long> productDb;

    // пока оставил так, чтобы больше соотвествовало тз задания (потом сделаю также как для Product)
    private final List<ConsumedProduct> consumedProducts;

    @Autowired
    public Counter(CrudRepository<Product, Long> productDb) {
        this.productDb = productDb;
        consumedProducts = new ArrayList<>();
    }

    @Override
    public void addConsumedProduct(String productName, LocalDate date) {
        var product = productDb.getById(getProductId(productName));
        consumedProducts.add(new ConsumedProduct(product, date));
    }

    @Override
    public int getCaloriesByProduct(String productName) {
        var product = productDb.getById(getProductId(productName));
        return product.getCalories();
    }

    @Override
    public int getCaloriesByDate(LocalDate date) {
        return consumedProducts
                .stream()
                .filter(pr -> pr.getConsumedDate().isEqual(date))
                .mapToInt(Product::getCalories)
                .sum();
    }

    @Override
    public int getCaloriesByInterval(LocalDate startDate, LocalDate endDate) {
        return consumedProducts
                .stream()
                .filter(pr -> pr.getConsumedDate().isAfter(startDate) && pr.getConsumedDate().isBefore(endDate))
                .mapToInt(Product::getCalories)
                .sum();
    }

    @Override
    public void addNewProduct(String newProductName, int calories) {
        productDb.add(new Product(getProductId(newProductName), newProductName, calories));
    }

    @Override
    public void deleteProduct(String newProductName) {
        productDb.deleteById(getProductId(newProductName));
    }

    private long getProductId(String productName) {
        return productName.hashCode();
    }
}
