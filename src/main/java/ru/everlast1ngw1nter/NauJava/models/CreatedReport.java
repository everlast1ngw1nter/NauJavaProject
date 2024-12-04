package ru.everlast1ngw1nter.NauJava.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_reports")
public class CreatedReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    private String report;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate creationDate;

    public CreatedReport() {

    }

    public CreatedReport(User user, String report,
                         LocalDate startDate, LocalDate endDate,
                         LocalDate creationDate) {
        this.user = user;
        this.report = report;
        this.startDate = startDate;
        this.endDate = endDate;
        this.creationDate = creationDate;
    }

    public CreatedReport(User user, String report,
                         LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.report = report;
        this.startDate = startDate;
        this.endDate = endDate;
        this.creationDate = LocalDate.now();
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
