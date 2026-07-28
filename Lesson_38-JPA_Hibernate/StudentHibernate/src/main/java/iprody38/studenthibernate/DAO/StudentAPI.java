
package iprody38.studenthibernate.DAO;

import iprody38.studenthibernate.Student;
import java.util.List;


public interface StudentAPI {
    
    Student findById(Long id) throws DaoException;
    List<Student> findAll() throws DaoException;
    Student create(Student student) throws DaoException;
    void update(Student student) throws DaoException;
    void delete(Long id) throws DaoException;
    
}
