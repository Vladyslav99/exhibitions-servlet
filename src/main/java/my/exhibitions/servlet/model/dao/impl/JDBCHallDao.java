package my.exhibitions.servlet.model.dao.impl;

import my.exhibitions.servlet.model.dao.HallDao;
import my.exhibitions.servlet.model.dao.exception.DaoException;
import my.exhibitions.servlet.model.dao.mapper.HallMapper;
import my.exhibitions.servlet.model.entity.Hall;
import my.exhibitions.servlet.util.Pageable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCHallDao implements HallDao {

    private static final String FIND_ALL_HALLS = "select halls.id as hall_id, " +
            "halls.name_english as hall_name_english, " +
            "halls.name_ukrainian as hall_name_ukrainian, " +
            "halls.description_english as hall_description_english, " +
            "halls.description_ukrainian as hall_description_ukrainian, " +
            "halls.image_url as hall_image_url from halls";

    private Connection connection;

    public JDBCHallDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Hall entity) {

    }

    @Override
    public Optional<Hall> findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public List<Hall> findAll() throws DaoException {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_HALLS);
            List<Hall> halls = new ArrayList<>();
            HallMapper hallMapper = new HallMapper();
            while (resultSet.next()){
                halls.add(hallMapper.extractFromResultSet(resultSet));
            }
            return halls;
        } catch (SQLException exception) {
            exception.printStackTrace();//log this
            throw new DaoException(exception);
        }
    }

    @Override
    public Pageable<Hall> findAll(Integer pageId, Integer total) throws DaoException {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void update(Hall entity) throws DaoException {

    }

    @Override
    public void delete(Long id) throws DaoException {

    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }
}
