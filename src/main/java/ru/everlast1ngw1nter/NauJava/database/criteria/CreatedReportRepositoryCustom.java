package ru.everlast1ngw1nter.NauJava.database.criteria;

import java.time.LocalDate;
import java.util.List;
import ru.everlast1ngw1nter.NauJava.models.CreatedReport;

public interface CreatedReportRepositoryCustom {

    List<CreatedReport> findByUserId(long userId);

    List<CreatedReport> findByCreationDate(LocalDate date);
}
