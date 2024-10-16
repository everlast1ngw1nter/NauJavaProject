package ru.everlast1ngw1nter.NauJava.database;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.everlast1ngw1nter.NauJava.models.Note;

@RepositoryRestResource(path = "notes")
public interface NoteRepository extends CrudRepository<Note, Long> {

    @Query(value = "SELECT note FROM Note note WHERE note.score = :score")
    List<Note> getAllByScore(@Param("score") Integer score);

    void deleteAllByProductId(long productId);
}
