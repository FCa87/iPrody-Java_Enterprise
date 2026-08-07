package iPrody.dao;

import iPrody.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


@Repository
public class BookDaoImpl implements BookDao, RowMapper<Book> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Book findById(Integer id) {
        try{
            String sql = "SELECT * FROM books where id = ?";
            var result = jdbcTemplate.query(sql, this, id);
            if (result.isEmpty()){
                System.out.println("There is no book whit id=" + id);
                return null;
            }
            return result.getFirst();
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> findAll() {
        try{
            String sql = "SELECT * FROM books";
            return jdbcTemplate.query(sql, this);
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Book book) {
        try{
            String sql = "INSERT INTO books (title, published_year, author_id) VALUES  (? , ?, ?)";
            jdbcTemplate.update(sql, book.getTitle(), book.getPublishedYear(), book.getAuthorId());
        }catch(DataAccessException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        try{
            String sql = "UPDATE books SET title = ?, published_year = ?, author_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, book.getTitle(), book.getPublishedYear(), book.getAuthorId(), book.getId());
        }catch(DataAccessException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            String sql = "DELETE FROM books WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer saveAll(List<Book> books){
        String sql = "INSERT INTO books (title, published_year, author_id) VALUES  (? , ?, ?)";
        var result = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, books.get(i).getTitle());
                ps.setInt(2, books.get(i).getPublishedYear());
                ps.setInt(3, books.get(i).getAuthorId());
            }

            @Override
            public int getBatchSize() {
                return books.size();
            }
        });
        return Arrays.stream(result).sum();
    }

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setPublishedYear(rs.getInt("published_year"));
        book.setAuthorId(rs.getInt("author_id"));
        return book;
    }
}
