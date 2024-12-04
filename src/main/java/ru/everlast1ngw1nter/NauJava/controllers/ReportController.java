package ru.everlast1ngw1nter.NauJava.controllers;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.everlast1ngw1nter.NauJava.domain.ReportService;
import ru.everlast1ngw1nter.NauJava.models.GenerationStatus;

@RestController
@RequestMapping("/data_report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/create")
    public String createReport() {
        var report = reportService.createReport();
        CompletableFuture.runAsync(() -> reportService.generateReport(report));
        return String.valueOf(report);
    }

    @GetMapping("{id}")
    public String getReportData(@PathVariable("id") long reportId) {
        var report = reportService.getReport(reportId);
        if (report == null) {
            return "Report with this id doesnt exist";
        }
        if (!report.getStatus().equals(GenerationStatus.COMPLETED)) {
            return "Report was not completed. Current status " + report.getStatus().toString();
        }
        return report.getReportData();
    }
}
