import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlRatingsDAO {

    String username = "root";
    String password = "apokalips";
    String url = "jdbc:mysql://localhost:3306/stutor_db";
    private BasicDataSource ds = null;

    public SqlRatingsDAO() {
        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/stutor_db");
        ds.setUsername("root");
        ds.setPassword("apokalips");
    }


    public void addRating (int user_id, int rated_id, int rating) {
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("INSERT INTO ratings (user_id, rated_id, rating_value) VALUES (");
            sb.append(user_id).append(" ,");
            sb.append(rated_id).append(" ,");
            sb.append(rating).append(");");
            statement.executeUpdate(sb.toString());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error adding rating");;
        }
    }

    public double getRating(int user_id) {
        int count = 0;
        int sum = 0;
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("SELECT * FROM ratings ");
            sb.append(" WHERE rated_id = ").append(user_id).append(";");

            ResultSet rs = statement.executeQuery(sb.toString());

            while(rs.next()) {
                count++;
                sum = sum + rs.getInt("rating_value");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error calculating rating");
        }

        if (count == 0) {
            return 0;
        }
        return (double) sum / count;
    }
}
