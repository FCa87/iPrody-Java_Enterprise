package org.example.mapper;

import org.example.model.Authority;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class AuthorityRowMapper implements RowMapper<Authority> {
    @Override
    public Authority mapRow(ResultSet rs, int rowNum) throws SQLException {
        Authority authority = new Authority();
        authority.setId(rs.getInt("au_id"));
        authority.setAuthority(rs.getString("au_authority"));
        return authority;
    }

    public boolean containsAuthority(List<Authority> authorities, Authority testAuthority){
        if (authorities.isEmpty()) return false;
        for (var authority : authorities){
            if (authority.equals(testAuthority)) return true;
        }
        return false;
    }
}
