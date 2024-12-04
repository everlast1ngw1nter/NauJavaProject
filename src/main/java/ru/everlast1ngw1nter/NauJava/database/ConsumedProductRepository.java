package ru.everlast1ngw1nter.NauJava.database;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ru.everlast1ngw1nter.NauJava.models.ConsumedProduct;

public interface ConsumedProductRepository extends CrudRepository<ConsumedProduct, Long> {

    List<ConsumedProduct> getConsumedProductByConsumedDateBetween(LocalDate startDate,
                                                                  LocalDate endDate);

    void deleteAllByProductId(long productId);
}
