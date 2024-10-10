package ru.everlast1ngw1nter.NauJava.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.everlast1ngw1nter.NauJava.database.ConsumedProductRepository;
import ru.everlast1ngw1nter.NauJava.database.NoteRepository;
import ru.everlast1ngw1nter.NauJava.database.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ConsumedProductRepository consumedProductRepository;
    private final ProductRepository productRepository;
    private final NoteRepository noteRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public ProductServiceImpl(ConsumedProductRepository consumedProductRepository,
                              ProductRepository productRepository,
                              NoteRepository noteRepository,
                              PlatformTransactionManager transactionManager) {
        this.consumedProductRepository = consumedProductRepository;
        this.productRepository = productRepository;
        this.noteRepository = noteRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteProductByName(String name) {
        var status = transactionManager.getTransaction(new
                DefaultTransactionDefinition());
        try {
            var product = productRepository.getByName(name);
            var productId = product.getId();
            productRepository.delete(product);
            consumedProductRepository.deleteAllByProductId(productId);
            noteRepository.deleteAllByProductId(productId);
        } catch (DataAccessException ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
