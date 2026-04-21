
package iprody38.studenthibernate;

import iprody38.studenthibernate.DAO.DaoException;
import iprody38.studenthibernate.DAO.StudentAPI;
import iprody38.studenthibernate.DAO.StudentApiHibernateImpl;


public class StudentHibernate {

    public static void main(String[] args) throws DaoException {
        StudentAPI studentAPI = new StudentApiHibernateImpl();
        
        System.out.println(studentAPI.create(new Student("Mike", "qwerty@mail.ru")));
        System.out.println(studentAPI.create(new Student("Sergey", "asdfg@mail.ru")));
        System.out.println(studentAPI.create(new Student("Vasiliy", "123@yandex.ru")));
        System.out.println(studentAPI.findById(2L));
        System.out.println(studentAPI.findById(4L));
        studentAPI.update(new Student(3L,"Vasiliy", "zxcvb@mail.ru"));
        studentAPI.findAll().forEach(System.out::println);
        System.out.println(studentAPI.create(new Student("John", "sleows@yandex.ru")));
        studentAPI.delete(4L);
        studentAPI.findAll().forEach(System.out::println);
    }
}
