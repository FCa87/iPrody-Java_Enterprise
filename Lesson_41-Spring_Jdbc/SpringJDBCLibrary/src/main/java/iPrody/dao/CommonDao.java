package iPrody.dao;

import java.util.List;

public interface CommonDao <T>{

    T findById(Integer id);
    List<T> findAll();
    void create(T t);
    void update(T t);
    void delete(Integer id);

    default Integer saveAll(List<T> t){
        System.out.println("Method \"saveAll\" hasn't been overrided yet");
        return null;
    }

};
