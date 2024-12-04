package ru.everlast1ngw1nter.NauJava;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.everlast1ngw1nter.NauJava.database.*;
import ru.everlast1ngw1nter.NauJava.database.criteria.CreatedReportRepoImpl;
import ru.everlast1ngw1nter.NauJava.domain.ProductService;
import ru.everlast1ngw1nter.NauJava.models.*;

@SpringBootTest
class NauJavaApplicationTests {

    private final CreatedReportRepoImpl criteriaReportRepo;

    private final CreatedReportRepository createdReportRepository;

    private final ConsumedProductRepository consumedProductRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final NoteRepository noteRepository;

    private final ProductService productService;

    @Autowired
    public NauJavaApplicationTests(CreatedReportRepoImpl criteriaReportRepo,
                                   ConsumedProductRepository consumedProductRepository,
                                   ProductRepository productRepository,
                                   UserRepository userRepository,
                                   NoteRepository noteRepository,
                                   CreatedReportRepository createdReportRepository,
                                   ProductService productService) {
        this.criteriaReportRepo = criteriaReportRepo;
        this.consumedProductRepository = consumedProductRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.createdReportRepository = createdReportRepository;
        this.productService = productService;
    }

    @Test
    @Transactional
    @Rollback
    public void getAllByScoreTest() {
        var product = new Product("milk", 100);
        productRepository.save(product);
        var user = new User("Junko", 169, 45);
        userRepository.save(user);
        var note1 = new Note(user, product, "good", 5);
        var note2 = new Note(user, product, "good", 4);
        var note3 = new Note(user, product, "ok", 5);
        noteRepository.save(note1);
        noteRepository.save(note2);
        noteRepository.save(note3);

        var notes = noteRepository.getAllByScore(5);
        Assertions.assertEquals(notes.size(), 2);
    }

    @Test
    @Transactional
    @Rollback
    public void deleteAllByProductIdTest() {
        var product1 = new Product("milk", 100);
        productRepository.save(product1);
        var productId = product1.getId();
        var product2 = new Product("not milk", 777);
        productRepository.save(product2);
        var user = new User("Junko", 169, 45);
        userRepository.save(user);
        var note1 = new Note(user, product1, "good", 5);
        var note2 = new Note(user, product1, "good", 4);
        var note3 = new Note(user, product2, "ok", 5);
        noteRepository.save(note1);
        noteRepository.save(note2);
        noteRepository.save(note3);

        noteRepository.deleteAllByProductId(productId);
        var notesLeft = 0;
        for (Note note : noteRepository.findAll()) {
            notesLeft ++;
        }
        Assertions.assertEquals(1 , notesLeft);
    }

    @Test
    @Transactional
    @Rollback
    public void criteriaFindByUserIdTest() {
        var product1 = new Product("milk", 100);
        productRepository.save(product1);
        var product2 = new Product("not milk", 777);
        productRepository.save(product2);
        var user1 = new User("Junko", 169, 45);
        userRepository.save(user1);
        var userId = user1.getId();
        var user2 = new User("Stas", 179, 444);
        userRepository.save(user2);
        var createdReport1 = new CreatedReport(user1, "report",
                LocalDate.now(), LocalDate.now());
        var createdReport2 = new CreatedReport(user2, "report",
                LocalDate.now(), LocalDate.now());
        createdReportRepository.save(createdReport1);
        createdReportRepository.save(createdReport2);
        var reports = criteriaReportRepo.findByUserId(userId);
        Assertions.assertEquals(1 , reports.size());
    }

    @Test
    @Transactional
    @Rollback
    public void criteriaFindByCreationDateTest() {
        var user1 = new User("Junko", 169, 45);
        userRepository.save(user1);
        var user2 = new User("Stas", 179, 444);
        userRepository.save(user2);
        var createdReport1 = new CreatedReport(user1, "report",
                LocalDate.now(), LocalDate.now(), LocalDate.of(12, 12, 12));
        var createdReport2 = new CreatedReport(user2, "report",
                LocalDate.now(), LocalDate.now());
        createdReportRepository.save(createdReport1);
        createdReportRepository.save(createdReport2);
        var reports = criteriaReportRepo.findByCreationDate(LocalDate.now());
        Assertions.assertEquals(1 , reports.size());
    }

    @Test
    @Transactional
    @Rollback
    public void getConsumedProductByConsumedDateBetweenTest() {
        var product1 = new Product("milk", 100);
        productRepository.save(product1);
        var user1 = new User("Junko", 169, 45);
        userRepository.save(user1);
        var consumedProduct1 = new ConsumedProduct(product1, LocalDate.now(), user1);
        var consumedProduct2 = new ConsumedProduct(product1, LocalDate.of(12, 12, 12), user1);
        consumedProductRepository.save(consumedProduct1);
        consumedProductRepository.save(consumedProduct2);
        var consumedBetween = consumedProductRepository.getConsumedProductByConsumedDateBetween(LocalDate.now(), LocalDate.now());
        Assertions.assertEquals(1 , consumedBetween.size());
    }

    @Test
    @Transactional
    @Rollback
    void transDeleteProductByNameTest()
    {
        var product = new Product("milk", 100);
        productRepository.save(product);
        var user = new User("Junko", 169, 45);
        userRepository.save(user);
        var consumedProduct = new ConsumedProduct(product, LocalDate.now(), user);
        consumedProductRepository.save(consumedProduct);
        var note = new Note(user, product, "good", 5);
        noteRepository.save(note);
        productService.deleteProductByName(product.getName());

        Optional<Product> foundProduct = productRepository.findById(product.getId());
        Assertions.assertTrue(foundProduct.isEmpty());

        Optional<ConsumedProduct> foundConsumedProduct = consumedProductRepository.findById(consumedProduct.getId());
        Assertions.assertTrue(foundConsumedProduct.isEmpty());

        Optional<Note> foundNote = noteRepository.findById(note.getId());
        Assertions.assertTrue(foundNote.isEmpty());
    }
}
