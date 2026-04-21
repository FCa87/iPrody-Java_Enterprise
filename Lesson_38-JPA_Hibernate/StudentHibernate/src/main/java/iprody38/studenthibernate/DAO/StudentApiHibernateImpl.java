
package iprody38.studenthibernate.DAO;

import iprody38.studenthibernate.Student;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class StudentApiHibernateImpl implements StudentAPI{
    
    private final SessionFactory sessionFactory;
    
    public StudentApiHibernateImpl(){
        this.sessionFactory = HibernateSession.getSESSIONFACTORY();
    }

    @Override
    public Student findById(Long id) throws DaoException {
        Student result = null;
        try(Session session = sessionFactory.openSession()){
            var query = session.createQuery("from Student where id = :id", Student.class);
            query.setParameter("id", id);
            result = query.uniqueResult();
            if (result == null){
                System.out.println("There is no student with id = " + id + "!");
            }
        }catch(Exception ex){
            throw new DaoException("Error in method \"findById\" has occurred! Class \"StudentApiHibernateImpl\".", ex);
        }
        return result;
    }

    @Override
    public List<Student> findAll() throws DaoException {
        List<Student> result = new LinkedList();
        try(Session session = sessionFactory.openSession()){
            result = session.createQuery("from Student", Student.class).getResultList();
            if (result == null){
                System.out.println("There is no student in base!");
            }
        }catch(Exception ex){
            throw new DaoException("Error in method \"findById\" has occurred! Class \"StudentApiHibernateImpl\".", ex);
        }
        return result;
    }

    @Override
    public Student create(Student student) throws DaoException {
        Student result = null;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            student.setId(null);
            session.persist(student);
            transaction.commit();
            result = student;
        }catch(Exception ex){
            if (transaction != null) transaction.rollback();
            throw new DaoException("Error in method \"create\" has occurred! Class \"StudentApiHibernateImpl\".", ex);
        }
        return result;
    }

    @Override
    public void update(Student student) throws DaoException {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            var query = session.createQuery("from Student where id = :id", Student.class);
            query.setParameter("id", student.getId());
            if (query.getSingleResult() == null){
                System.out.println("There is no student with id = " + student.getId() + "!");
            } else{
               transaction = session.beginTransaction();
                session.merge(student);
                transaction.commit();
            }
        }catch(Exception ex){
            if (transaction != null) transaction.rollback();
            throw new DaoException("Error in method \"update\" has occurred! Class \"StudentApiHibernateImpl\".", ex);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {;
        Transaction transaction = null;    
        try (Session session = sessionFactory.openSession()) {
            var query = session.createQuery("from Student where id = :id", Student.class);
            query.setParameter("id", id);
            Student buf = query.getSingleResult();
            if (buf == null) {
                System.out.println("There is no student with id = " + id + "!");
            } else {
                transaction = session.beginTransaction();
                session.remove(buf);
                transaction.commit();
            }
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            throw new DaoException("Error in method \"delete\" has occurred! Class \"StudentApiHibernateImpl\".", ex);
        }
    }
    
}
