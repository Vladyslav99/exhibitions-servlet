package my.exhibitions.servlet.model.service;

import my.exhibitions.servlet.model.dao.DaoFactory;
import my.exhibitions.servlet.model.service.impl.JDBCServiceFactory;

public abstract class ServiceFactory {

    private static ServiceFactory serviceFactory;

    public abstract UserService createUserService();

    public abstract ExhibitionService createExhibitionService();

    public abstract HallService createHallService();

    public abstract ExhibitionEventService createExhibitionEventService();

    public abstract OrderService createOrderService();

    public static ServiceFactory getInstance(){
        if( serviceFactory == null ){
            synchronized (DaoFactory.class){
                if(serviceFactory==null){
                    serviceFactory = new JDBCServiceFactory();
                }
            }
        }
        return serviceFactory;
    }
}
