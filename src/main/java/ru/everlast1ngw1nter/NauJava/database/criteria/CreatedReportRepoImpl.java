package ru.everlast1ngw1nter.NauJava.database.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.JoinType;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.everlast1ngw1nter.NauJava.models.CreatedReport;

@Repository
public class CreatedReportRepoImpl implements CreatedReportRepositoryCustom{

    private final EntityManager entityManager;

    @Autowired
    public CreatedReportRepoImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<CreatedReport> findByUserId(long userId) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(CreatedReport.class);
        var reportRoot = criteriaQuery.from(CreatedReport.class);
        var user = reportRoot.join("user", JoinType.INNER);
        var userIdPredicate = criteriaBuilder.equal(user.get("id"), userId);
        criteriaQuery.select(reportRoot).where(userIdPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<CreatedReport> findByCreationDate(LocalDate date) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(CreatedReport.class);
        var reportRoot = criteriaQuery.from(CreatedReport.class);
        var creationDatePredicate = criteriaBuilder.equal(reportRoot.get("creationDate"), date);
        criteriaQuery.select(reportRoot).where(creationDatePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
