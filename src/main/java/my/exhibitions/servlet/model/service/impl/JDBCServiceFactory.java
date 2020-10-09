package my.exhibitions.servlet.model.service.impl;

import my.exhibitions.servlet.model.dao.ExhibitionEventDao;
import my.exhibitions.servlet.model.dao.impl.JDBCExhibitionDao;
import my.exhibitions.servlet.model.service.*;
import my.exhibitions.servlet.model.service.UserService;

public class JDBCServiceFactory extends ServiceFactory {

    @Override
    public UserService createUserService() {
        return new JDBCUserService();
    }

    @Override
    public ExhibitionService createExhibitionService() {
        return new JDBCExhibitionService();
    }

    @Override
    public HallService createHallService() {
        return new JDBCHallService();
    }

    @Override
    public ExhibitionEventService createExhibitionEventService() {
        return new JDBCExhibitionEventService();
    }

    @Override
    public OrderService createOrderService() {
        return new JDBCOrderService();
    }
}
