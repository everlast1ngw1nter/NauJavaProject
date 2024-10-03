package ru.everlast1ngw1nter.NauJava.database;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.everlast1ngw1nter.NauJava.models.Product;

@Component
public class ProductRepository implements CrudRepository<Product, Long> {

    private final Map<Long, Product> products;

    @Autowired
    public ProductRepository(Map<Long, Product> products) {
        this.products = products;
    }

    @Override
    public void add(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }

    @Override
    public Product getById(Long id) {
        return products.get(id);
    }

    @Override
    public void updateById(Long id, Product newProduct) {
        products.put(id, newProduct);
    }
}
