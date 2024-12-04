package ru.everlast1ngw1nter.NauJava.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @Column(columnDefinition = "TEXT")
    private String note;

    private Integer score;

    private LocalDate date;

    public Note() {

    }

    public Note(User user, Product product,
                String note, Integer score,
                LocalDate date) {
        this.user = user;
        this.product = product;
        this.note = note;
        this.score = score;
        this.date = date;
    }

    public Note(User user, Product product,
                String note, Integer score) {
        this.user = user;
        this.product = product;
        this.note = note;
        this.score = score;
        this.date = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
