package ru.everlast1ngw1nter.NauJava.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_consumed_products")
public class ConsumedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate consumedDate;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    public ConsumedProduct() {
    }

    public ConsumedProduct(Product product, LocalDate consumedDate, User user) {
        this.product = product;
        this.consumedDate = consumedDate;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getConsumedDate() {
        return consumedDate;
    }

    public void setConsumedDate(LocalDate consumedDate) {
        this.consumedDate = consumedDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
