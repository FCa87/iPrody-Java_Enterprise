package org.example.dao;

import org.example.field.UserField;
import org.example.mapper.UserResultSetExtractor;
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
    private final UserResultSetExtractor userResultSetExtractor;

    @Autowired
    UserDaoImpl(DataSource dataSource, UserResultSetExtractor userResultSetExtractor) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userResultSetExtractor = userResultSetExtractor;
    }


    @Override
    public void create(User user) {
        try {
            jdbcTemplate.update("insert into users(name,email) values (?,?)", user.getName(), user.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select " + UserField.buildFieldsQuery() + ", " +
                "a.id as a_id, a.street as a_street, a.user_id as a_user_id, a.city as a_city, a.postal_code as a_postal_code" +
                " from users u left join addresses a on u.id = a.user_id ", userResultSetExtractor);
    }

    @Override
    public void update(User user) {
        try {
            jdbcTemplate.update("update users set name = ?, email = ? where id = ?", user.getName(), user.getEmail(), user.getId());
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
        var users = jdbcTemplate.query("select " + UserField.buildFieldsQuery() + ", " +
                "a.id as a_id, a.street as a_street, a.user_id as a_user_id, a.city as a_city, a.postal_code as a_postal_code" +
                " from users u left join addresses a on u.id = a.user_id where u.id = ?", userResultSetExtractor, id);

        if (users == null || users.isEmpty())
            return null;

        if (users.size() > 1)
            throw new RuntimeException("For id " + id + " found more than 1 user");

        return users.getFirst();
    }

    @Override
    public void create(List<User> users) {
        jdbcTemplate.batchUpdate("insert into users(name,email) values (?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, users.get(i).getName());
                ps.setString(2, users.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }
        });
    }
}
