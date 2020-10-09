package my.exhibitions.servlet.model.dao.mapper;

import my.exhibitions.servlet.model.entity.Role;
import my.exhibitions.servlet.model.entity.RoleType;
import my.exhibitions.servlet.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .role(new Role.Builder()
                        .id(resultSet.getLong("role_id"))
                        .roleType(RoleType.valueOf(resultSet.getString("role_name")))
                        .build())
                .build();
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User object) {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }
}
