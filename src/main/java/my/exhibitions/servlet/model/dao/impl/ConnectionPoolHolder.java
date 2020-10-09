package my.exhibitions.servlet.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPoolHolder {

    private static DataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    try {
                        Context context = new InitialContext();
                        dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ConnectionPoolHolder");
                    } catch (NamingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dataSource;
    }
}
