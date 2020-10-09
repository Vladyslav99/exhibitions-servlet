package my.exhibitions.servlet.model.dao.impl;

import my.exhibitions.servlet.model.dao.ExhibitionDao;
import my.exhibitions.servlet.model.dao.exception.DaoException;
import my.exhibitions.servlet.model.dao.mapper.ExhibitionMapper;
import my.exhibitions.servlet.model.entity.Exhibition;
import my.exhibitions.servlet.util.Pageable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCExhibitionDao implements ExhibitionDao {

    private static final String FIND_ALL_EXHIBITIONS = "select exhibitions.id as exhibition_id, " +
            "exhibitions.theme_english as exhibition_theme_english, " +
            "exhibitions.theme_ukrainian as exhibition_theme_ukrainian, " +
            "exhibitions.description_english as exhibition_description_english, " +
            "exhibitions.description_ukrainian as exhibition_description_ukrainian, " +
            "exhibitions.image_url as exhibition_image_url " +
            "from exhibitions";

    private final Connection connection;

    public JDBCExhibitionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Exhibition entity) throws DaoException {

    }

    @Override
    public Optional<Exhibition> findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public List<Exhibition> findAll() throws DaoException {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_EXHIBITIONS);
            List<Exhibition> exhibitions = new ArrayList<>();
            ExhibitionMapper exhibitionMapper = new ExhibitionMapper();
            while (resultSet.next()){
                exhibitions.add(exhibitionMapper.extractFromResultSet(resultSet));
            }
            return exhibitions;
        } catch (SQLException exception) {
            exception.printStackTrace();//log this
            throw new DaoException(exception);
        }
    }

    @Override
    public Pageable<Exhibition> findAll(Integer pageId, Integer total) throws DaoException {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void update(Exhibition entity) throws DaoException {

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
