package org.example.dao;

import java.util.List;

public interface Dao<T> {
    void create(T user);
    List<T> findAll();
    void update(T user);
    void delete(T user);
}
