package my.exhibitions.servlet.model.dao;

import my.exhibitions.servlet.model.dao.exception.DaoException;
import my.exhibitions.servlet.util.Pageable;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    void create(T entity) throws DaoException;

    Optional<T> findById(Long id) throws DaoException;

    List<T> findAll() throws DaoException;

    Pageable<T> findAll(Integer pageId, Integer total) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Long id) throws DaoException;
}
