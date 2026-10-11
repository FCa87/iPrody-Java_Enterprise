package org.example.dao;

import org.example.field.AddressField;
import org.example.field.AuthorityField;
import org.example.field.UserField;
import org.example.mapper.UserResultSetExtractor;
import org.example.mapper.UserRowMapper;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;
    private final UserResultSetExtractor userResultSetExtractor;

    @Autowired
    public UserDaoImpl(DataSource dataSource, UserRowMapper userRowMapper, UserResultSetExtractor userResultSetExtractor) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userRowMapper = userRowMapper;
        this.userResultSetExtractor = userResultSetExtractor;
    }


    @Override
    public void create(User user) {
        try {
            jdbcTemplate.update("insert into users(name,email,password) values (?,?,?)", user.getName(), user.getEmail(), user.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select " + UserField.buildFieldsQuery() + "," +
                AddressField.buildFieldsQuery() + "," +
                AuthorityField.buildFieldsQuery() + " " +
                "from users u left join addresses a on u.id = a.user_id " +
                "left join authorities au on u.id = au.user_id", userResultSetExtractor);
    }

    @Override
    public void update(User user) {
        try {
            jdbcTemplate.update("update users set name = ?, email = ?, password = ? where id = ?", user.getName(), user.getEmail(), user.getPassword(), user.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try {
            jdbcTemplate.update("delete from users where id = ?", user.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(Integer id) {
        var users = jdbcTemplate.query("select " + UserField.buildFieldsQuery() + "," +
                AddressField.buildFieldsQuery() + "," +
                AuthorityField.buildFieldsQuery() + " " +
                "from users u left join addresses a on u.id = a.user_id " +
                "left join authorities au on u.id = au.user_id where u.id = ?", userResultSetExtractor, id);

        if (users == null || users.isEmpty())
            return null;

        if (users.size() > 1)
            throw new RuntimeException("For id " + id + " found more than 1 user");

        return users.getFirst();
    }

    @Override
    public void create(List<User> users) {
        jdbcTemplate.batchUpdate("insert into users(name,email,password) values (?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, users.get(i).getName());
                ps.setString(2, users.get(i).getEmail());
                ps.setString(3, users.get(i).getPassword());
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }
        });
    }

    public User getBasicUserById(Integer id){
        var users = jdbcTemplate.query("select " + UserField.buildFieldsQuery() +
                " from users u where u.id = ?", userRowMapper, id);

        if (users.isEmpty())
            return null;

        if (users.size() > 1)
            throw new RuntimeException("For id " + id + " found more than 1 user");

        return users.getFirst();
    }

    @Override
    public User findByUserMail(String email){
        String sql = "select " + UserField.buildFieldsQuery() + "," +
                     AddressField.buildFieldsQuery() + "," +
                     AuthorityField.buildFieldsQuery() + " " +
                     "from users u left join addresses a on u.id = a.user_id " +
                     "left join authorities au on u.id = au.user_id where u.email = ?";
        var users = jdbcTemplate.query(sql, userResultSetExtractor, email);

        if (users.isEmpty())
            return null;

        if (users.size() > 1)
            throw new RuntimeException("For email " + email + " found more than 1 user");

        return users.getFirst();
    }
}
