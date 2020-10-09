package my.exhibitions.servlet.model.dao;

import my.exhibitions.servlet.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public abstract ExhibitionDao createExhibitionDao();

    public abstract HallDao createHallDao();

    public abstract ExhibitionEventDao createExhibitionEventDao();

    public abstract OrderDao createOrderDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
