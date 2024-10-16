package ru.everlast1ngw1nter.NauJava.controllers;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.everlast1ngw1nter.NauJava.database.criteria.CreatedReportRepositoryCustom;
import ru.everlast1ngw1nter.NauJava.models.CreatedReport;

@RestController
@RequestMapping("/reports")
public class AppController {

    private final CreatedReportRepositoryCustom createdReportRepositoryCustom;

    @Autowired
    public AppController(CreatedReportRepositoryCustom createdReportRepositoryCustom) {
        this.createdReportRepositoryCustom = createdReportRepositoryCustom;
    }

    @GetMapping("/user/{userId}")
    public List<CreatedReport> getReportByUserId(@PathVariable("userId") long userId) {
        return createdReportRepositoryCustom.findByUserId(userId);
    }

    @GetMapping("/date/{date}")
    public List<CreatedReport> getReportByUserId(@PathVariable("date") LocalDate date) {
        return createdReportRepositoryCustom.findByCreationDate(date);
    }
}
