import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlSubjectsDAO {

    String username = "root";
    String password = "apokalips";
    String url = "jdbc:mysql://localhost:3306/stutor_db";
    private BasicDataSource ds = null;

    public SqlSubjectsDAO() {
        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/stutor_db");
        ds.setUsername("root");
        ds.setPassword("apokalips");
    }
    public void addSubject(int user_id, String sub_id) {
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("INSERT INTO tutor_subjects (user_id, sub_id) VALUES (");
            sb.append(user_id).append(" , '");
            sb.append(sub_id).append("');");
            statement.executeUpdate(sb.toString());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error adding subject");;
        }
    }


    public void removeSubject(int user_id, String sub_id) {
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("DELETE FROM tutor_subjects WHERE ");
            sb.append("user_id = ").append(user_id).append(" AND sub_id = '").append(sub_id).append("';");
            statement.executeUpdate(sb.toString());

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can't remove subject");
        }
    }


    public List<String> getSubjects(int user_id) {
        List<String> subjects = new ArrayList<>();
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("select s.subject_name from tutor_subjects as ts\n" +
                    "JOIN subjects as s ON ts.sub_id = s.sub_id");
            sb.append(" WHERE ts.user_id = ").append(user_id).append(";");

            ResultSet rs = statement.executeQuery(sb.toString());

            while(rs.next()) {
                String subject = rs.getString("subject_name");
                subjects.add(subject);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getting subjects");
        }
        return subjects;
    }

    public List<String> getAllSubjectIds() {
        List<String> subIds = new ArrayList<>();
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("select * from subjects;");
            ResultSet rs = statement.executeQuery(sb.toString());

            while(rs.next()) {
                String subId = rs.getString("sub_id");
                String subName = rs.getString("subject_name");
                subIds.add(subId + " - " + subName);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getting subject Ids");
        }
        return subIds;
    }
}
