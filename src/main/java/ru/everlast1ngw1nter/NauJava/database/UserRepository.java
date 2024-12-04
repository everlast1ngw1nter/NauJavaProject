package ru.everlast1ngw1nter.NauJava.database;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.everlast1ngw1nter.NauJava.models.User;

@RepositoryRestResource(path = "users")
public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);

}
