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

    private final static String DBNAME = "stutor_db";
    private final static String SUBJECTS_TABLE = "subjects";

    public SqlSubjectDAO() throws ClassNotFoundException {

    }

    @Override
    public boolean addSubject(Subject subject) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO ").append(SUBJECTS_TABLE).append(" (subject_name) VALUES (");
        sb.append("'").append(subject.getName()).append("');");

        int check = statement.executeUpdate(sb.toString());

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return check == 1;
    }

    @Override
    public boolean removeSubject(String subjectName) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM ").append(SUBJECTS_TABLE).append(" WHERE ");
        sb.append("subject_name = '").append(subjectName).append("';");

        int check = statement.executeUpdate(sb.toString());

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return check == 1;
    }

    @Override
    public Subject getSubjectByName(String subjectName) throws SQLException {
        Subject res = null;

        Connection connection = ConnectionPool.getConnection();
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
        ConnectionPool.releaseConnection(connection);

        return res;
    }

    @Override
    public Subject getSubjectById(int id) throws SQLException {
        Subject res = null;

        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("select * from ").append(SUBJECTS_TABLE);
        sb.append(" where sub_id = ").append(id).append(";");

        ResultSet rs = statement.executeQuery(sb.toString());

        while(rs.next()) {
            res = new Subject(rs.getString("subject_name"));
            res.setId(rs.getInt("sub_id"));
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return res;
    }

    @Override
    public List<Subject> getAllSubjects() throws SQLException {
        List<Subject> res = new ArrayList<>();

        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();

        sb.append("select * from ").append(SUBJECTS_TABLE).append(";");

        ResultSet rs = statement.executeQuery(sb.toString());

        while(rs.next()) {
            Subject tmp = new Subject(rs.getString("subject_name"));
            res.add(tmp);
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return res;
    }

    @Override
    public void clearSubjects() throws SQLException {

        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder sb = new StringBuilder();

        statement.execute("USE " + DBNAME + ";\n");

        sb.append("DELETE FROM ").append(SUBJECTS_TABLE).append(";");

        statement.executeUpdate(sb.toString());

        statement.close();
        ConnectionPool.releaseConnection(connection);
    }

}
