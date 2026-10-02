package iPrody.dao;

import iPrody.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Author findById(Integer id) {
        try{
            String sql = "SELECT * FROM authors where id = ?";
            var result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Author.class), id);
            if (result.isEmpty()){
                System.out.println("There is no author whit id=" + id);
                return null;
            }
            return result.getFirst();
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Author> findAll() {
        try{
            String sql = "SELECT * FROM authors";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Author.class));
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Author author) {
        try{
            String sql = "INSERT INTO authors (name, country) VALUES  (? , ?)";
            jdbcTemplate.update(sql, author.getName(), author.getCountry());
        }catch(DataAccessException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(Author author) {
        try{
            String sql = "UPDATE authors SET name = ?, country = ? WHERE id = ?";
            jdbcTemplate.update(sql, author.getName(), author.getCountry(), author.getId());
        }catch(DataAccessException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            String sql = "DELETE FROM authors WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }
}
