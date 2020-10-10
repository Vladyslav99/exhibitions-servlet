package my.exhibitions.servlet.model.dao.impl;

import my.exhibitions.servlet.model.dao.DaoFactory;
import my.exhibitions.servlet.model.dao.ExhibitionEventDao;
import my.exhibitions.servlet.model.dao.OrderDao;
import my.exhibitions.servlet.model.dao.exception.DaoException;
import my.exhibitions.servlet.model.entity.ExhibitionEvent;
import my.exhibitions.servlet.model.entity.Order;
import my.exhibitions.servlet.util.Pageable;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCOrderDao implements OrderDao {

    private static final Logger log = Logger.getLogger(JDBCOrderDao.class);

    private static final String INSERT_ORDER = "insert into orders (exhibition_event_id, user_id) values (?, ?)";

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private final Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Order order) throws DaoException {
        try (ExhibitionEventDao exhibitionEventDao = daoFactory.createExhibitionEventDao();
             PreparedStatement statement = connection.prepareStatement(INSERT_ORDER)) {
            connection.setAutoCommit(false);

            ExhibitionEvent exhibitionEvent =
                    exhibitionEventDao.findById(order.getExhibitionEvent().getId())
                            .orElseThrow(() -> new DaoException("Event with " + order.getExhibitionEvent().getId() +
                                    " not found!"));

            if (!(exhibitionEvent.getMaxAvailablePlaces() > exhibitionEvent.getSoldPlaces())) {
                throw new DaoException("Not enough places");
            }

            statement.setLong(1, order.getExhibitionEvent().getId());
            statement.setLong(2, order.getUser().getId());
            statement.executeUpdate();

            exhibitionEvent.setSoldPlaces(exhibitionEvent.getSoldPlaces() + 1);
            exhibitionEventDao.update(exhibitionEvent);
            connection.setAutoCommit(true);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                log.error(throwables.getMessage(), throwables);
                throw new DaoException(throwables);
            }
            throw new DaoException(exception);
        }
    }

    @Override
    public Optional<Order> findById(Long id) throws DaoException {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public List<Order> findAll() throws DaoException {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public Pageable<Order> findAll(Integer pageId, Integer total) throws DaoException {
        throw new UnsupportedOperationException("Operation has not been implemented yet!");
    }

    @Override
    public void update(Order entity) throws DaoException {
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
        } catch (SQLException exception) {
            log.error(exception.getMessage(), exception);
            throw new DaoException(exception);
        }
    }
}
