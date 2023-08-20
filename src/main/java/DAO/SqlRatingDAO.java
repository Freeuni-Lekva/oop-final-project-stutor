package DAO;

import DAO.Interfaces.RatingDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlRatingDAO implements RatingDAO {
    private final static String DBNAME = "stutor_db";
    private final static String TABLENAME = "ratings";

    public SqlRatingDAO() throws ClassNotFoundException {

    }

    @Override
    public boolean addRating(int user_id, int rated_id, int rating) throws SQLException {

        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO ").append(TABLENAME);
        sb.append(" (user_id, rated_id, rating_value) VALUES (");
        sb.append(user_id).append(" ,");
        sb.append(rated_id).append(" ,");
        sb.append(rating).append(");");

        int check = statement.executeUpdate(sb.toString());
        statement.close();
        ConnectionPool.releaseConnection(connection);

        return check == 1;
    }

    @Override
    public double getRating(int user_id) throws SQLException {
        int count = 0;
        int sum = 0;

        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLENAME);
        sb.append(" WHERE rated_id = ").append(user_id).append(";");

        ResultSet rs = statement.executeQuery(sb.toString());

        while(rs.next()) {
            count++;
            sum = sum + rs.getInt("rating_value");
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);

        if (count == 0) {
            return 0;
        }
        return (double) sum / count;
    }
}
