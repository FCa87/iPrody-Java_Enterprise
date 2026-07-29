package iPrody.repo;

import iPrody.dto.ReservationsOfReaderDTO;
import iPrody.model.BorrowedBook;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class BorrowedBookRepository extends SimpleRepository<BorrowedBook>{

    public List<BorrowedBook> getAll(){
        try(Session session = this.sessionFactory.openSession()){
            return session.createQuery("from BorrowedBook bb order by bb.id", BorrowedBook.class).getResultList();
        }
    }

    public Optional<BorrowedBook> getById(Integer id){
        try(Session session = this.sessionFactory.openSession()){
            Query<BorrowedBook> query = session.createQuery("from BorrowedBook bb where bb.id = :id", BorrowedBook.class);
            query.setParameter("id", id);
            return Optional.ofNullable(query.getSingleResultOrNull());
        }
    }

    public List<ReservationsOfReaderDTO> getReservationsOfReader(Integer id){
        try(Session session = this.sessionFactory.openSession()){
            var query = session.createQuery("select bb.bookId, b.title, b.author, b.publishedYear, b.genre, bb.borrowDate " +
                    "from Book b join BorrowedBook bb on b.id = bb.bookId " +
                    "where bb.readerId = :id and bb.status = :status", ReservationsOfReaderDTO.class);
            query.setParameter("id", id);
            query.setParameter("status", "borrowed");
            return query.getResultList();
        }
    }

}
