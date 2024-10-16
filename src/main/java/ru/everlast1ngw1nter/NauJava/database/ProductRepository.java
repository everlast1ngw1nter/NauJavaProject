package ru.everlast1ngw1nter.NauJava.database;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.everlast1ngw1nter.NauJava.models.Product;
import org.springframework.data.repository.CrudRepository;

@RepositoryRestResource(path = "products")
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product getById(Long id);

    Product getByName(String name);

    void deleteByName(String name);
}
