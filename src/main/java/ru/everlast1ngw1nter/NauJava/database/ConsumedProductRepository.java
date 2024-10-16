package ru.everlast1ngw1nter.NauJava.database;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.everlast1ngw1nter.NauJava.models.ConsumedProduct;

@RepositoryRestResource(path = "consumed")
public interface ConsumedProductRepository extends CrudRepository<ConsumedProduct, Long> {

    List<ConsumedProduct> getConsumedProductByConsumedDateBetween(LocalDate startDate,
                                                                  LocalDate endDate);

    void deleteAllByProductId(long productId);
}
