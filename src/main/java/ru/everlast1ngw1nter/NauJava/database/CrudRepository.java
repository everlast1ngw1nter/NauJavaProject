package ru.everlast1ngw1nter.NauJava.database;

public interface CrudRepository<T, ID> {

    void add(T product);

    void deleteById(ID id);

    T getById(ID id);

    void updateById(ID id, T newProduct);
}
