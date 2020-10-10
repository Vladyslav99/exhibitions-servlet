package my.exhibitions.servlet.model.dao.impl;

import my.exhibitions.servlet.model.dao.*;
import my.exhibitions.servlet.model.dao.exception.DaoException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private static final Logger log = Logger.getLogger(JDBCDaoFactory.class);

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public ExhibitionDao createExhibitionDao() {
        return new JDBCExhibitionDao(getConnection());
    }

    @Override
    public HallDao createHallDao() {
        return new JDBCHallDao(getConnection());
    }

    @Override
    public ExhibitionEventDao createExhibitionEventDao() {
        return new JDBCExhibitionEventDao(getConnection());
    }

    @Override
    public OrderDao createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException exception) {
            log.error(exception.getMessage(), exception);
            throw new DaoException(exception);
        }
    }
}
