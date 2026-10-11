package org.example.mapper;

import org.example.field.UserField;
import org.example.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(UserField.ID.getAlias()));
        user.setName(rs.getString(UserField.NAME.getAlias()));
        user.setEmail(rs.getString(UserField.EMAIL.getAlias()));
        user.setAddresses(new ArrayList<>());

        return user;
    }



}
