package ru.everlast1ngw1nter.NauJava.database;

import org.springframework.data.repository.CrudRepository;
import ru.everlast1ngw1nter.NauJava.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
