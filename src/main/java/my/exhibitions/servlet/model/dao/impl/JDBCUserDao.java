package my.exhibitions.servlet.model.dao.impl;

import my.exhibitions.servlet.model.dao.UserDao;
import my.exhibitions.servlet.model.dao.exception.DaoException;
import my.exhibitions.servlet.model.dao.mapper.UserMapper;
import my.exhibitions.servlet.model.entity.User;
import my.exhibitions.servlet.util.Pageable;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {

    private static final String CREATE_USER =
            "INSERT INTO USERS (username, email, password, role_id) VALUES (?, ?, ?, ?)";

    private static final String FIND_USER_BY_USERNAME = "SELECT users.id, users.username, users.email, users.password, " +
            "roles.id AS role_id, roles.name AS role_name FROM users, roles WHERE users.role_id = roles.id AND " +
            "users.username =?";

    private static final String FIND_USER_BY_USERNAME_AND_PASSWORD =
            "SELECT users.id, users.username, users.email, users.password, roles.id AS role_id, roles.name AS " +
            "role_name FROM users, roles WHERE users.role_id = roles.id AND users.username=? AND " +
            "users.password=?";

    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getRole().getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();//log this
            throw new DaoException(exception);
        }
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        Optional<User> userOptional = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_USERNAME_AND_PASSWORD)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                UserMapper userMapper = new UserMapper();
                return Optional.of(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();//log this
            throw new DaoException(exception);
        }
        return userOptional;
    }

    @Override
    public Optional<User> findUserByUsername(String username) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                UserMapper userMapper = new UserMapper();
                return Optional.of(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();//log this
            throw new DaoException(exception);
        }
        return userOptional;
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public List<User> findAll() throws DaoException {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public Pageable<User> findAll(Integer pageId, Integer total) throws DaoException {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void update(User entity) throws DaoException {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void delete(Long id) throws DaoException {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (Exception exception) {
            throw new DaoException(exception);
        }
    }
}
