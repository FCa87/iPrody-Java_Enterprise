package iPrody.repo;

import iPrody.config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.BiConsumer;

public abstract class SimpleRepository<T> implements Repository<T>{

    protected SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    @Override
    public T create(T object) {
        return performInTransaction(object, Session::persist);
    }

    @Override
    public void update(T object) {
        performInTransaction(object, Session::merge);
    }

    @Override
    public void delete(T object){
        performInTransaction(object, Session::remove);
    }

    protected T performInTransaction(T object, BiConsumer<Session, T> action){
        Transaction transaction = null;
        try(var session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            action.accept(session, object);
            transaction.commit();
            return object;
        } catch (Exception ex){
            if (transaction != null && transaction.getStatus().canRollback()){
                transaction.rollback();
            }
            throw ex;
        }
    }

}
