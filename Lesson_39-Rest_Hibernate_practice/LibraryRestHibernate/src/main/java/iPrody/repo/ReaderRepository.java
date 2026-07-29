package iPrody.repo;

import iPrody.model.Reader;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class ReaderRepository extends SimpleRepository<Reader>{

    public List<Reader> getAll(){
        try(Session session = this.sessionFactory.openSession()){
            return session.createQuery("from Reader r left join fetch r.borrowedList order by r.id", Reader.class).getResultList();
        }
    }

    public Optional<Reader> getById(Integer id){
        try(Session session = this.sessionFactory.openSession()){
            Query<Reader> query = session.createQuery("from Reader r left join fetch r.borrowedList where r.id = :id", Reader.class);
            query.setParameter("id", id);
            return Optional.ofNullable(query.getSingleResultOrNull());
        }
    }

    public String checkForUnique(Reader reader){
        if (this.checkParamInDB("name", reader.getName())){
            return "Name is not unique";
        }
        if (this.checkParamInDB("email", reader.getEmail())){
            return "Email is not unique";
        }
        if (this.checkParamInDB("phone", reader.getPhone())){
            return "Phone is not unique";
        }
        return null;
    }

    private boolean checkParamInDB(String parameter, String value){
        try(Session session = this.sessionFactory.openSession()){
            Query<Reader> query = session.createQuery("from Reader where " + parameter + " = :value", Reader.class);
            query.setParameter("value", value);
            Reader result = query.getSingleResultOrNull();
            return result != null;
        }
    }

}
