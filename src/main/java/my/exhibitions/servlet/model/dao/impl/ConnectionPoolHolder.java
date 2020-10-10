package my.exhibitions.servlet.model.dao.impl;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static final Logger log = Logger.getLogger(ConnectionPoolHolder.class);

    private static DataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    try {
                        Context context = new InitialContext();
                        dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ConnectionPoolHolder");
                    } catch (NamingException exception) {
                        log.error(exception.getMessage(), exception);
                    }
                }
            }
        }
        return dataSource;
    }
}
