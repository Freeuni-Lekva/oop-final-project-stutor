package DAO;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
        private static List<Connection> connectionPool;
        private static int INITIAL_POOL_SIZE = 8;
        private final static String USERNAME = "root";
        private final static String PASSWORD = "Ikako2525";
        private final static String DBNAME = "stutor_db";

        public ConnectionPool() throws SQLException, ClassNotFoundException {
            connectionPool = new ArrayList<>();
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/" + DBNAME);
            dataSource.setUsername(USERNAME);
            dataSource.setPassword(PASSWORD);
            Class.forName("com.mysql.cj.jdbc.Driver");
            for(int i = 0; i < INITIAL_POOL_SIZE; i++){
                connectionPool.add(dataSource.getConnection());
            }
        }

        public static Connection getConnection() {
            Connection connection = connectionPool.get(connectionPool.size() - 1);
            connectionPool.remove(connectionPool.size() - 1);
            return connection;
        }

        public static void releaseConnection(Connection connection) {
            connectionPool.add(connection);
        }

}
