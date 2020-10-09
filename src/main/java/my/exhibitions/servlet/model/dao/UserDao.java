package my.exhibitions.servlet.model.dao;

import my.exhibitions.servlet.model.dao.exception.DaoException;
import my.exhibitions.servlet.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User>{
    Optional<User> findUserByUsernameAndPassword(String username, String password) throws DaoException;

    Optional<User> findUserByUsername(String username) throws DaoException;
}
