package iPrody.repo;

import iPrody.model.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class BookRepository extends SimpleRepository<Book>{

    public List<Book> getAll(){
        try(Session session = this.sessionFactory.openSession()){
            return session.createQuery("from Book b left join fetch b.borrowedList order by b.id", Book.class).getResultList();
        }
    }

    public Optional<Book> getById(Integer id){
        try(Session session = this.sessionFactory.openSession()){
            Query<Book> query = session.createQuery("from Book b left join fetch b.borrowedList where b.id = :id", Book.class);
            query.setParameter("id", id);
            return Optional.ofNullable(query.getSingleResultOrNull());
        }
    }

}
