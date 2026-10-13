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
    private final AuthorityRowMapper authorityRowMapper;

    @Autowired
    public UserResultSetExtractor(UserRowMapper userRowMapper, AddressRowMapper addressRowMapper, AuthorityRowMapper authorityRowMapper) {
        this.userRowMapper = userRowMapper;
        this.addressRowMapper = addressRowMapper;
        this.authorityRowMapper = authorityRowMapper;

    }

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, User> userMap = new HashMap<>();
        while (rs.next()) {
            var userId = rs.getInt(UserField.ID.getAlias());
            var user = userMap.getOrDefault(userId, userRowMapper.mapRow(rs, rs.getRow()));

            if (!userMap.containsKey(userId))
                userMap.put(userId, user);

            if (rs.getObject("a_id", Integer.class) != null) {
                var address = addressRowMapper.mapRow(rs, rs.getRow());
                if (!addressRowMapper.containsAddress(user.getAddresses(), address)) {
                    user.getAddresses().add(address);
                }

            }

            if (rs.getObject("au_id", Integer.class) != null){
                var authority = authorityRowMapper.mapRow(rs, rs.getRow());
                if (!authorityRowMapper.containsAuthority(user.getAuthorities(), authority)) {
                    user.getAuthorities().add(authority);
                }
            }

        }

        return userMap.values().stream().toList();
    }
}
