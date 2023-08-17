package DAO;

import DAO.Interfaces.SubjectDAO;
import Model.Subject;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlSubjectDAO implements SubjectDAO {

    private final static String USERNAME = "root";
    private final static String PASSWORD = "apokalips";
    private final static String DBNAME = "stutor_db";
    private final static String SUBJECTS_TABLE = "subjects";
    private final static String LEARNING_TABLE = "learning_subjects";
    private final static String TEACHING_TABLE = "teaching_subjects";

    BasicDataSource dataSource;

    public SqlSubjectDAO() throws ClassNotFoundException {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + DBNAME);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    @Override
    public boolean addSubject(Subject subject) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO ").append(SUBJECTS_TABLE).append(" (subject_name) VALUES (");
        sb.append("'").append(subject.getName()).append("');");

        int check = statement.executeUpdate(sb.toString());

        statement.close();
        connection.close();

        return check == 1;
    }

    @Override
    public boolean removeSubject(String subjectName) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM ").append(SUBJECTS_TABLE).append(" WHERE ");
        sb.append("subject_name = ").append(subjectName).append(";");

        int check = statement.executeUpdate(sb.toString());

        statement.close();
        connection.close();

        return check == 1;
    }

    @Override
    public Subject getSubjectByName(String subjectName) throws SQLException {
        Subject res = null;

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("select * from ").append(SUBJECTS_TABLE);
        sb.append(" where subject_name = '").append(subjectName).append("';");

        ResultSet rs = statement.executeQuery(sb.toString());

        while(rs.next()) {
            res = new Subject(rs.getString("subject_name"));
            res.setId(rs.getInt("sub_id"));
        }

        statement.close();
        connection.close();

        return res;
    }

    @Override
    public Subject getSubjectById(int id) throws SQLException {
        Subject res = null;

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("select * from ").append(SUBJECTS_TABLE);
        sb.append(" where sub_id = '").append(id).append("';");

        ResultSet rs = statement.executeQuery(sb.toString());

        while(rs.next()) {
            res = new Subject(rs.getString("subject_name"));
            res.setId(rs.getInt("sub_id"));
        }

        statement.close();
        connection.close();

        return res;
    }

    @Override
    public List<Subject> getAllSubjects() throws SQLException {
        List<Subject> res = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("select * from ").append(SUBJECTS_TABLE).append(";");

        ResultSet rs = statement.executeQuery(sb.toString());

        while(rs.next()) {
            Subject tmp = new Subject(rs.getString("subject_name"));
            tmp.setId(rs.getInt("sub_id"));
            res.add(tmp);
        }

        statement.close();
        connection.close();

        return res;
    }

    @Override
    public void clearSubjects() throws SQLException {

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder sb = new StringBuilder();

        statement.execute("USE " + DBNAME + ";\n");

        sb.append("DELETE FROM ").append(SUBJECTS_TABLE).append(";");

        statement.executeUpdate(sb.toString());

        statement.close();
        connection.close();
    }

    @Override
    public boolean addLearningSubject(int user_id, int sub_id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("INSERT INTO " + LEARNING_TABLE + " (user_id, sub_id) VALUES (");
        code.append("'" + user_id+ "', ");
        code.append("'" + sub_id + "');");

        int check = statement.executeUpdate(code.toString());

        statement.close();
        connection.close();

        return check == 1;

    }

    @Override
    public boolean addTeachingSubject(int user_id, int sub_id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("INSERT INTO " + TEACHING_TABLE + " (user_id, sub_id) VALUES (");
        code.append("'" + user_id+ "', ");
        code.append("'" + sub_id + "');");

        int check = statement.executeUpdate(code.toString());

        statement.close();
        connection.close();

        return check == 1;
    }
}
