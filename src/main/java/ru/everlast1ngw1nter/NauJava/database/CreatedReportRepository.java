package ru.everlast1ngw1nter.NauJava.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.everlast1ngw1nter.NauJava.models.CreatedReport;

@RepositoryRestResource(path = "reports")
public interface CreatedReportRepository extends CrudRepository<CreatedReport, Long> {
}
