package org.example.mapper;

import org.example.field.UserField;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class UserResultSetExtractor implements ResultSetExtractor<List<User>> {

    private final UserRowMapper userRowMapper;
    private final AddressRowMapper addressRowMapper;

    @Autowired
    public UserResultSetExtractor(UserRowMapper userRowMapper, AddressRowMapper addressRowMapper) {
        this.userRowMapper = userRowMapper;
        this.addressRowMapper = addressRowMapper;
    }

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, User> userMap = new HashMap<>();
        while (rs.next()) {
            var userId = rs.getInt(UserField.ID.getAlias());
            var user = userMap.getOrDefault(userId, userRowMapper.mapRow(rs, rs.getRow()));

            if (!userMap.containsKey(userId))
                userMap.put(userId, user);

            if (rs.getObject("a_id", Integer.class) != null)
                user.getAddresses().add(addressRowMapper.mapRow(rs, rs.getRow()));
        }

        return userMap.values().stream().toList();
    }
}
