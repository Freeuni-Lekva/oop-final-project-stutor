import jdk.dynalink.linker.LinkerServices;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
//            Connection connection = ds.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from users;");
//            while(resultSet.next()){
//                System.out.println(resultSet.getString("username"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void addAccount(Account account) {
        // TO DO
    }

    public void removeAccount(){
        // TO DO
    }

    // search username and return list of ids
    public ArrayList<Account> searchIdLike(String username){

        return null;
    }


    // For later
    public ArrayList<Account> search(int role, ArrayList<String> subject, int minRating, int maxRating, String username){

        return null;
    }




    public static void main(String[] args) {
        StutorDB stutorDB = new StutorDB();
    }
}
