package ru.everlast1ngw1nter.NauJava.database;

import org.springframework.data.repository.CrudRepository;
import ru.everlast1ngw1nter.NauJava.models.Report;

public interface ReportRepository extends CrudRepository<Report, Long> {
}
