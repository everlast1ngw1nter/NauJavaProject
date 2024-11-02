package ru.everlast1ngw1nter.NauJava.domain;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.everlast1ngw1nter.NauJava.database.ProductRepository;
import ru.everlast1ngw1nter.NauJava.database.ReportRepository;
import ru.everlast1ngw1nter.NauJava.database.UserRepository;
import ru.everlast1ngw1nter.NauJava.dto.ProductsReportGenerationData;
import ru.everlast1ngw1nter.NauJava.dto.UserReportGenerationData;
import ru.everlast1ngw1nter.NauJava.models.GenerationStatus;
import ru.everlast1ngw1nter.NauJava.models.Product;
import ru.everlast1ngw1nter.NauJava.models.Report;
import ru.everlast1ngw1nter.NauJava.models.User;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository,
                         UserRepository userRepository,
                         ProductRepository productRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Report getReport(long reportId) {
        var report = reportRepository.findById(reportId);
        return report.orElse(null);
    }

    public String getReportData(long reportId) {
        var report = reportRepository.findById(reportId);
        return report
                .map(Report::getReportData)
                .orElse(null);
    }

    public long createReport() {
        var report = new Report(GenerationStatus.CREATED);
        reportRepository.save(report);
        return report.getId();
    }

    public void generateReport(long reportId) {
        var optReport = reportRepository.findById(reportId);
        if (optReport.isEmpty()) {
            return;
        }
        var report = optReport.get();
        long reportStartTime = System.currentTimeMillis();

        CompletableFuture<UserReportGenerationData> userReportFuture = CompletableFuture.supplyAsync(
                () -> {
                    long startTime = System.currentTimeMillis();
                    var users = (List<User>) userRepository.findAll();
                    long elapsed = (System.currentTimeMillis() - startTime);
                    return new UserReportGenerationData(users.size(), elapsed);
                }
        ).exceptionally((err) -> null);

        CompletableFuture<ProductsReportGenerationData> productsReportFuture = CompletableFuture.supplyAsync(
                () -> {
                    long startTime = System.currentTimeMillis();
                    var products = (List<Product>) productRepository.findAll();
                    long elapsed = (System.currentTimeMillis() - startTime);
                    return new ProductsReportGenerationData(products, elapsed);
                }
        ).exceptionally((err) -> null);

        var productsReport = productsReportFuture.join();
        var userReport = userReportFuture.join();
        long reportElapsed = (System.currentTimeMillis() - reportStartTime);

        if (productsReport == null || userReport == null) {
            report.setStatus(GenerationStatus.ERROR);
            reportRepository.save(report);
            return;
        }

        report.setReportData(generateHtml(userReport, productsReport, reportElapsed));
        report.setStatus(GenerationStatus.COMPLETED);
        reportRepository.save(report);
    }

    private String generateHtml(UserReportGenerationData userReport,
                                ProductsReportGenerationData productsReport,
                                long totalTimeMillis) {
        StringBuilder htmlReport = new StringBuilder();
        htmlReport.append("<html>")
                .append("<head><title>Отчет</title></head>")
                .append("<body>")
                .append("<h1>Отчет</h1>")
                .append("<table border='1'>")
                .append("<tr><th>Описание</th><th>Значение</th></tr>")
                .append("<tr><td>Количество зарегистрированных пользователей</td><td>")
                .append(userReport.usersCount())
                .append("</td></tr>")
                .append("<tr><td>Список всех продуктов</td><td>")
                .append(productsReport.products())
                .append("</td></tr>")
                .append("<tr><td>Время на вычисление количества пользователей (мс)</td><td>")
                .append(userReport.generationTimeMillis())
                .append("</td></tr>")
                .append("<tr><td>Время на вычисление списка продуктов (мс)</td><td>")
                .append(productsReport.generationTimeMillis())
                .append("</td></tr>")
                .append("<tr><td>Время на формирование отчета (мс)</td><td>")
                .append(totalTimeMillis)
                .append("</td></tr>")
                .append("</table>")
                .append("</body>")
                .append("</html>");
        return htmlReport.toString();
    }
}
