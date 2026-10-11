package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDao extends Dao<User>{
    User findById(Integer id);
    void create(List<User> users);
}
