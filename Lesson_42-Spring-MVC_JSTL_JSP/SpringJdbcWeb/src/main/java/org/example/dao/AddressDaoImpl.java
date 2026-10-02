package org.example.dao;

import org.example.mapper.AddressRowMapper;
import org.example.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class AddressDaoImpl implements AddressDao {

    private final JdbcTemplate jdbcTemplate;
    private final AddressRowMapper addressRowMapper;

    @Autowired
    public AddressDaoImpl(DataSource dataSource, AddressRowMapper addressRowMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.addressRowMapper = addressRowMapper;
    }

    @Override
    public void create(Address address) {
        try {
            jdbcTemplate.update("insert into addresses(street,city,postal_code,user_id) values (?,?,?,?)",
                    address.getStreet(), address.getCity(), address.getPostalCode(), address.getUserId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Address address) {
        try {
            jdbcTemplate.update("update addresses set street =?,city=?,postal_code= ? where id = ?",
                    address.getStreet(), address.getCity(), address.getPostalCode(), address.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Address address) {
        try {
            jdbcTemplate.update("delete from addresses where id = ?", address.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> findByUserId(Integer userId) {
        return jdbcTemplate.query("select " +
                "id as a_id, street as a_street, city as a_city, user_id as a_user_id, postal_code as a_postal_code" +
                " from addresses where user_id=?", addressRowMapper, userId);
    }

    @Override
    public List<Address> findAll() {
        return jdbcTemplate.query("select " +
                "id as a_id, street as a_street, city as a_city, user_id as a_user_id, postal_code as a_postal_code" +
                " from addresses", addressRowMapper);
    }
}
