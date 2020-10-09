package my.exhibitions.servlet.model.dao.impl;

import my.exhibitions.servlet.model.dao.*;
import my.exhibitions.servlet.model.dao.exception.DaoException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

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
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
