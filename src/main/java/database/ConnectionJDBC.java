package database;


import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionJDBC {


    static final String user = PersonalValues.USER.getValue();
    static final String password = PersonalValues.PASSWORD.getValue();
    static final String bd = PersonalValues.DBNAME.getValue();
    static final String ip = PersonalValues.IP.getValue();
    static final String puerto = PersonalValues.PORT.getValue();
    static final String url = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    //PoolConnection
    private static DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setInitialSize(5);
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public static void closeConecction(Connection connection) throws SQLException {
        connection.close();
    }
}
