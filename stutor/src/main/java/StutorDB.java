import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StutorDB {
    String username = "root";
    String password = "apokalips";
    String url = "jdbc:mysql://localhost:3306/stutor_db";
    private BasicDataSource ds = null;

    public StutorDB() {
        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/stutor_db");
        ds.setUsername("root");
        ds.setPassword("apokalips");


//        try {
//            Connection connection = basicDataSource.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from users;");
//            while(resultSet.next()){
//                System.out.println(resultSet.getString("username"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }



    public static void main(String[] args) {
        StutorDB stutorDB = new StutorDB();
    }
}
