package iPrody.repo;

import iPrody.model.Book;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    List<T> getAll();
    Optional<T> getById(Integer id);
    T create(T object);
    void update(T object);
    void delete(T object);

}
